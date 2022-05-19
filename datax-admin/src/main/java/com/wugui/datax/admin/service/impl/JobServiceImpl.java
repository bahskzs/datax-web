package com.wugui.datax.admin.service.impl;


import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datatx.core.enums.ExecutorBlockStrategyEnum;
import com.wugui.datatx.core.glue.GlueTypeEnum;
import com.wugui.datatx.core.log.JobLogger;
import com.wugui.datatx.core.util.DateUtil;
import com.wugui.datax.admin.config.HadoopConfig;
import com.wugui.datax.admin.core.cron.CronExpression;
import com.wugui.datax.admin.core.route.ExecutorRouteStrategyEnum;
import com.wugui.datax.admin.core.thread.JobScheduleHelper;
import com.wugui.datax.admin.core.util.I18nUtil;
import com.wugui.datax.admin.dto.*;
import com.wugui.datax.admin.entity.*;
import com.wugui.datax.admin.mapper.*;
import com.wugui.datax.admin.service.*;
import com.wugui.datax.admin.util.CopyUtil;
import com.wugui.datax.admin.util.DateFormatUtils;
import com.wugui.datax.admin.util.JSONUtils;
import com.wugui.datax.admin.util.SystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.*;

/**
 * core job action for xxl-job
 *
 * @author xuxueli 2016-5-28 15:30:33
 */
@Service
@Slf4j
public class JobServiceImpl implements JobService {
//    private static Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    @Value("${datax.executor.jsonpath}")
    private String jsonPath;

    @Resource
    private JobGroupMapper jobGroupMapper;
    @Resource
    private JobInfoMapper jobInfoMapper;
    @Resource
    private JobLogMapper jobLogMapper;
    @Resource
    private JobLogGlueMapper jobLogGlueMapper;
    @Resource
    private JobLogReportMapper jobLogReportMapper;
    @Resource
    private DatasourceQueryService datasourceQueryService;
    @Resource
    private JobTemplateMapper jobTemplateMapper;
    @Resource
    private DataxJsonService dataxJsonService;
    @Resource
    private JobDatasourceService jobDatasourceService;
    @Resource
    private JobDatasourceMapper jobDatasourceMapper;
    @Resource
    private VJobDatasourceService vJobDatasourceService;

    @Resource
    private HadoopConfig hadoopConfig;


    @Override
    public Map<String, Object> pageList(int start, int length, int jobGroup, int triggerStatus, String jobDesc, String glueType, int userId, Integer[] projectIds) {

        // page list
        List<JobInfo> list = jobInfoMapper.pageList(start, length, jobGroup, triggerStatus, jobDesc, glueType, userId, projectIds);
        int list_count = jobInfoMapper.pageListCount(start, length, jobGroup, triggerStatus, jobDesc, glueType, userId, projectIds);

        // package result
        Map<String, Object> maps = new HashMap<>();
        // 总记录数
        maps.put("recordsTotal", list_count);
        // 过滤后的总记录数
        maps.put("recordsFiltered", list_count);
        // 分页列表
        maps.put("data", list);
        return maps;
    }

    @Override
    public List<JobInfo> list() {
        return jobInfoMapper.findAll();
    }

    @Override
    public ReturnT<String> add(JobInfo jobInfo) {
        // valid
        JobGroup group = jobGroupMapper.load(jobInfo.getJobGroup());
        if (group == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_choose") + I18nUtil.getString("jobinfo_field_jobgroup")));
        }
        if (!CronExpression.isValidExpression(jobInfo.getJobCron())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_invalid"));
        }
        if (jobInfo.getGlueType().equals(GlueTypeEnum.BEAN.getDesc()) && jobInfo.getJobJson().trim().length() <= 2) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_jobjson")));
        }
        if (jobInfo.getJobDesc() == null || jobInfo.getJobDesc().trim().length() == 0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_jobdesc")));
        }
        if (jobInfo.getUserId() == 0 ) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_author")));
        }
        if (ExecutorRouteStrategyEnum.match(jobInfo.getExecutorRouteStrategy(), null) == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_executorRouteStrategy") + I18nUtil.getString("system_invalid")));
        }
        if (ExecutorBlockStrategyEnum.match(jobInfo.getExecutorBlockStrategy(), null) == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_executorBlockStrategy") + I18nUtil.getString("system_invalid")));
        }
        if (GlueTypeEnum.match(jobInfo.getGlueType()) == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_gluetype") + I18nUtil.getString("system_invalid")));
        }
        if (GlueTypeEnum.BEAN == GlueTypeEnum.match(jobInfo.getGlueType()) && (jobInfo.getExecutorHandler() == null || jobInfo.getExecutorHandler().trim().length() == 0)) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + "JobHandler"));
        }


        if (StringUtils.isBlank(jobInfo.getReplaceParamType()) || !DateFormatUtils.formatList().contains(jobInfo.getReplaceParamType())) {
            jobInfo.setReplaceParamType(DateFormatUtils.TIMESTAMP);
        }

        // fix "\r" in shell
        if (GlueTypeEnum.GLUE_SHELL == GlueTypeEnum.match(jobInfo.getGlueType()) && jobInfo.getGlueSource() != null) {
            jobInfo.setGlueSource(jobInfo.getGlueSource().replaceAll("\r", ""));
        }

        // ChildJobId valid
        if (jobInfo.getChildJobId() != null && jobInfo.getChildJobId().trim().length() > 0) {
            String[] childJobIds = jobInfo.getChildJobId().split(",");
            for (String childJobIdItem : childJobIds) {
                if (StringUtils.isNotBlank(childJobIdItem) && isNumeric(childJobIdItem) && Integer.parseInt(childJobIdItem) > 0) {
                    JobInfo childJobInfo = jobInfoMapper.loadById(Integer.parseInt(childJobIdItem));
                    if (childJobInfo == null) {
                        return new ReturnT<String>(ReturnT.FAIL_CODE,
                                MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})" + I18nUtil.getString("system_not_found")), childJobIdItem));
                    }
                } else {
                    return new ReturnT<String>(ReturnT.FAIL_CODE,
                            MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})" + I18nUtil.getString("system_invalid")), childJobIdItem));
                }
            }

            // join , avoid "xxx,,"
            String temp = "";
            for (String item : childJobIds) {
                temp += item + ",";
            }
            temp = temp.substring(0, temp.length() - 1);

            jobInfo.setChildJobId(temp);
        }

        // add in db
        jobInfo.setAddTime(new Date());
        jobInfo.setJobJson(jobInfo.getJobJson());
        jobInfo.setUpdateTime(new Date());
        jobInfo.setGlueUpdatetime(new Date());
        jobInfoMapper.save(jobInfo);
        if (jobInfo.getId() < 1) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_add") + I18nUtil.getString("system_fail")));
        }

        return new ReturnT<>(String.valueOf(jobInfo.getId()));
    }

     /**
      * @author: bahskzs
      * @date: 2021-10-14 9:36
      * @description: 简单拷贝任务
      * @params: jobId,datasourceId
      * @return:
      */
    @Override
    public ReturnT<String> copy(Integer jobId, Long datasourceId) {
        JobInfo jobInfo = jobInfoMapper.loadById(jobId);
        JobDatasource datasource = jobDatasourceService.getById(datasourceId);
        jobInfo.setJobDesc(datasource.getDatasourceName() +"~"+ jobInfo.getJobDesc());
        jobInfo.setId(0);
        return add(jobInfo);
    }


     /**
      * @author: bahsk
      * @date: 2021-10-14 10:44
      * @description: 批量复制任务
      * @params:  dsList
      * @return:
      */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnT<String> batchCopy(Integer jobId, List<JobDatasource> dsList) {
        JobInfo jobInfo = jobInfoMapper.loadById(jobId);
        String jobDesc = jobInfo.getJobDesc();
        String jobJson = jobInfo.getJobJson();
        String json = JSONUtils.changeJson(jobJson, 0);

        for(JobDatasource ds : dsList) {
            JobDatasource datasource = jobDatasourceMapper.selectById(ds.getId());
            jobInfo.setJobDesc(datasource.getDatasourceName() +"~"+ jobDesc);
            jobInfo.setId(0);
            add(jobInfo);
        }
        return new ReturnT<>(String.valueOf(jobId + "复制成功"));
    }

    //
     /**
      * @author: bahsk
      * @date: 2021-10-14 17:19
      * @description:  项目定制批量拷贝,替换数据源版本
      * @params:
      * @return:
      */
    @Override
    public ReturnT<String> batchDSCopy(Integer jobId, List<DatasourceDTO> dsList) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //1.查询被复制的任务的信息
        JobInfo jobInfo = jobInfoMapper.loadById(jobId);
        //拿到原始流程的名称
        String sourceJobDesc = jobInfo.getJobDesc();
        //拿到原始的jobJosn
        String jobJson = jobInfo.getJobJson();

        //2.读取出 dsList(每一个map都是需要替换的reader和writer对应的数据源),并查询出数据源对应的账号,密码,url,构造成
        //List<Map<String,String>> dsList size =2 0是reader的hashmap  1是writer的hashmap
        for(DatasourceDTO datasourceDTO : dsList) {
            //1.需要改造jobinfo的名字

            //2.构造新的job_json

            JobDatasource source = jobDatasourceMapper.selectById(Integer.valueOf(datasourceDTO.getSource()));
            JobDatasourceDTO sourceDS = CopyUtil.copy(source,JobDatasourceDTO.class);
            String[] sourceJobDescArray = sourceJobDesc.split("~");

            if(sourceJobDescArray.length > 1) {
                sourceJobDesc = sourceJobDescArray[1];
            }

            String jobDesc = source.getDatasourceName() + "~" + sourceJobDesc;

            JobDatasource targetSource = jobDatasourceMapper.selectById(Integer.valueOf(datasourceDTO.getTargetSource()));
            JobDatasourceDTO targetDS = CopyUtil.copy(targetSource,JobDatasourceDTO.class);

            List<Map<String,String>> dbParamsList = new ArrayList<Map<String,String>>();
            dbParamsList.add(org.apache.commons.beanutils.BeanUtils.describe(sourceDS));
            dbParamsList.add(org.apache.commons.beanutils.BeanUtils.describe(targetDS));

            //改造数据源--修改的是jobJson
            String json = JSONUtils.changeJsonDSs(jobJson, dbParamsList);

            log.info("json",json);
            jobInfo.setJobJson(json);
            jobInfo.setJobDesc(jobDesc);
            jobInfo.setLastHandleCode(0);
            //3.调用新增方法
            add(jobInfo);

        }
        return new ReturnT<>("success");
    }

     /**
      * @author: bahsk
      * @date: 2021-10-20 10:25
      * @description: 批量执行任务组替换
      * @params:
      * @return:
      */
    @Override
    public ReturnT<String> batchJobCopy(MultiJobsDTO jobs) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        List<Integer> jobList = jobs.getJobs();
        List<DatasourceDTO> dsList = jobs.getDsList();

        for(Integer jobId : jobList) {
            batchDSCopy(jobId, dsList);
        }
        return new ReturnT<>("success");
    }

    /**
     * @author: bahsk
     * @date: 2021/10/24 11:50
     * @description: 项目定制接口 根据查询参数返回指定任务组
     * @params:
     * @return:
     */
    @Override
    public TriggerJobGroupDTO searchList(String words, String year) {

        QueryWrapper<JobInfo> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("id","job_desc")
                .like("job_desc", words)
                .like("job_desc", year)
                .orderByAsc("job_desc");

        List<JobInfo> jobInfoList = this.jobInfoMapper.selectList(queryWrapper);

        List<TriggerJobRespDTO> jobRespDTOList = new ArrayList<>();

        for(JobInfo jobInfo : jobInfoList) {
            TriggerJobRespDTO triggerJobRespDTO = TriggerJobRespDTO.builder()
                    .jobId(jobInfo.getId())
                    .jobDesc(jobInfo.getJobDesc())
                    .executorParam("")
                    .build();
            jobRespDTOList.add(triggerJobRespDTO);
        }

        TriggerJobGroupDTO jobGroupDTOs = TriggerJobGroupDTO.builder()
                .triggerJobDto(CopyUtil.copyList(jobRespDTOList,TriggerJobDto.class))
                .triggerJobRespDTO(jobRespDTOList)
                .build();

        return jobGroupDTOs;
    }

    /**
     * @param jobDatasourceRespDTOList
     * @author: bahsk
     * @date: 2021-11-01 16:55
     * @description: TODO 项目定制接口, 批量修改任务json串中数据源 目前写入params的工具类还是有点问题
     * @params:
     * @return:
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnT<String> batchUpdate(List<JobDatasourceRespDTO> jobDatasourceRespDTOList) {
        // 1. 查询传入list对应的JobDatasources (含名称,用户名,密码,url)
//        List<JobDatasource> jobDatasources = new ArrayList<>();
//        for(JobDatasourceRespDTO jobDatasourceRespDTO : jobDatasourceRespDTOList) {
//            jobDatasources.add(jobDatasourceService.getById(jobDatasourceRespDTO.getId()));
//        }
//
//        //数据源,数据源的DTO(用户,名称,url)
//        Map<Long,JobDatasourceRespDTO> datasourceRespDTOMap = new HashMap<>();

        // 2. 根据数据源的名称匹配相应的jobInfo并构造相应的数据
        for(JobDatasourceRespDTO jobDatasource : jobDatasourceRespDTOList) {

            //拿到要替换账号密码的任务列表
            List<JobInfo> source = vJobDatasourceService.findByParams(jobDatasource.getJdbcUsername(), jobDatasource.getJdbcUrl(), "source");
            List<JobInfo> target = vJobDatasourceService.findByParams(jobDatasource.getJdbcUsername(), jobDatasource.getJdbcUrl(), "target");

            String pwd = jobDatasource.getJdbcPassword();
            //TODO JobInfo 必须是过滤过是source 还是target的列表
            // 4.比对jobInfo的username ,jdbcUrl 和JobDatasource中的是否一致? 是--> 替换密码
            for (JobInfo jobInfo : source) {
                //比较数据源是和Reader中的一致还是writer中的一致
                String json =  JSONUtils.changeJsonPwd(jobInfo.getJobJson(),"source",pwd);
                //获取任务信息，替换密码
                JobInfo job = jobInfoMapper.loadById(jobInfo.getId());
                job.setJobJson(json);
                jobInfoMapper.update(job);
            }

            for (JobInfo jobInfo : target) {
                //比较数据源是和Reader中的一致还是writer中的一致
                String json =  JSONUtils.changeJsonPwd(jobInfo.getJobJson(),"target",pwd);
                //获取任务信息，替换密码
                JobInfo job = jobInfoMapper.loadById(jobInfo.getId());
                job.setJobJson(json);
                jobInfoMapper.update(job);
            }
        }


        return new ReturnT<>("success");
    }

    /**
     * @author: bahsk
     * @date: 2021-12-31 16:00
     * @description: 导出json串
     * @params:
     * @return:
     */
    @Override
    public ReturnT<String> downloadJson() {

        List<JobInfo> all = jobInfoMapper.findAll();

        for(JobInfo jobInfo : all) {
            String jobJson = jobInfo.getJobJson();
            String tmpFilePath;
            String dataXHomePath = SystemUtils.getDataXHomePath();
            if (!FileUtil.exist(jsonPath)) {
                FileUtil.mkdir(jsonPath);
            }
            tmpFilePath = jsonPath + "jobTmp-" + jobInfo.getId() + ".conf";
            // 根据json写入到临时本地文件
            try (PrintWriter writer = new PrintWriter(tmpFilePath, "UTF-8")) {
                writer.println(jobJson);
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                JobLogger.log("JSON 临时文件写入异常：" + e.getMessage());
            }
        }
        return new ReturnT<>("success");

    }

    /**
     * @param id
     * @author: bahsk
     * @date: 2022-01-11 11:26
     * @description: 导出指定json串
     * @params:
     * @return:
     */
    @Override
    public ReturnT<String> downloadJson(Long id) {

        JobInfo jobInfo = this.jobInfoMapper.loadById(Math.toIntExact(id));
        String json = JSONUtils.changeJobEnv(jobInfo.getJobJson(),hadoopConfig.getParameters());
        String jobJson = JSONUtils.changeJson(json, JSONUtils.decrypt);
        String tmpFilePath;
        String dataXHomePath = SystemUtils.getDataXHomePath();
        if (!FileUtil.exist(jsonPath)) {
            FileUtil.mkdir(jsonPath);
        }
        tmpFilePath = jsonPath + "jobTmp-" + jobInfo.getId() + ".conf";
        // 根据json写入到临时本地文件
        try (PrintWriter writer = new PrintWriter(tmpFilePath, "UTF-8")) {
            writer.println(jobJson);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            JobLogger.log("JSON 临时文件写入异常：" + e.getMessage());
        }
        return new ReturnT<>("success");
    }

    /**
     * @param id
     * @author: bahsk
     * @date: 2022-01-20 15:34
     * @description: 查询任务json(解密的)
     * @params:
     * @return:
     */
    @Override
    public JobInfo selectOne(Long id) {
        JobInfo jobInfo = this.jobInfoMapper.loadById(Math.toIntExact(id));
        String json = JSONUtils.changeJobEnv(jobInfo.getJobJson(),hadoopConfig.getParameters());
        String jobJson = JSONUtils.changeJson(json, JSONUtils.decrypt);
        jobInfo.setJobJson(jobJson.replaceAll("\\\"","\""));
        return jobInfo;
    }


    private boolean isNumeric(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public ReturnT<String> update(JobInfo jobInfo) {

        // valid
        if (!CronExpression.isValidExpression(jobInfo.getJobCron())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_invalid"));
        }
        if (jobInfo.getGlueType().equals(GlueTypeEnum.BEAN.getDesc()) && jobInfo.getJobJson().trim().length() <= 2) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_jobjson")));
        }
        if (jobInfo.getJobDesc() == null || jobInfo.getJobDesc().trim().length() == 0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_jobdesc")));
        }

        if (jobInfo.getProjectId() == 0) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_jobproject")));
        }
        if (jobInfo.getUserId() == 0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_author")));
        }
        if (ExecutorRouteStrategyEnum.match(jobInfo.getExecutorRouteStrategy(), null) == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_executorRouteStrategy") + I18nUtil.getString("system_invalid")));
        }
        if (ExecutorBlockStrategyEnum.match(jobInfo.getExecutorBlockStrategy(), null) == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_executorBlockStrategy") + I18nUtil.getString("system_invalid")));
        }

        // ChildJobId valid
        if (jobInfo.getChildJobId() != null && jobInfo.getChildJobId().trim().length() > 0) {
            String[] childJobIds = jobInfo.getChildJobId().split(",");
            for (String childJobIdItem : childJobIds) {
                if (childJobIdItem != null && childJobIdItem.trim().length() > 0 && isNumeric(childJobIdItem)) {
                    JobInfo childJobInfo = jobInfoMapper.loadById(Integer.parseInt(childJobIdItem));
                    if (childJobInfo == null) {
                        return new ReturnT<String>(ReturnT.FAIL_CODE,
                                MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})" + I18nUtil.getString("system_not_found")), childJobIdItem));
                    }
                } else {
                    return new ReturnT<String>(ReturnT.FAIL_CODE,
                            MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})" + I18nUtil.getString("system_invalid")), childJobIdItem));
                }
            }

            // join , avoid "xxx,,"
            String temp = "";
            for (String item : childJobIds) {
                temp += item + ",";
            }
            temp = temp.substring(0, temp.length() - 1);

            jobInfo.setChildJobId(temp);
        }

        // group valid
        JobGroup jobGroup = jobGroupMapper.load(jobInfo.getJobGroup());
        if (jobGroup == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_jobgroup") + I18nUtil.getString("system_invalid")));
        }

        // stage job info
        JobInfo exists_jobInfo = jobInfoMapper.loadById(jobInfo.getId());
        if (exists_jobInfo == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_id") + I18nUtil.getString("system_not_found")));
        }

        // next trigger time (5s后生效，避开预读周期)
        long nextTriggerTime = exists_jobInfo.getTriggerNextTime();
        if (exists_jobInfo.getTriggerStatus() == 1 && !jobInfo.getJobCron().equals(exists_jobInfo.getJobCron())) {
            try {
                Date nextValidTime = new CronExpression(jobInfo.getJobCron()).getNextValidTimeAfter(new Date(System.currentTimeMillis() + JobScheduleHelper.PRE_READ_MS));
                if (nextValidTime == null) {
                    return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_never_fire"));
                }
                nextTriggerTime = nextValidTime.getTime();
            } catch (ParseException e) {
                log.error(e.getMessage(), e);
                return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_invalid") + " | " + e.getMessage());
            }
        }

        BeanUtils.copyProperties(jobInfo, exists_jobInfo);
        if (StringUtils.isBlank(jobInfo.getReplaceParamType())) {
            jobInfo.setReplaceParamType(DateFormatUtils.TIMESTAMP);
        }
        exists_jobInfo.setTriggerNextTime(nextTriggerTime);
        exists_jobInfo.setUpdateTime(new Date());

        if (GlueTypeEnum.BEAN.getDesc().equals(jobInfo.getGlueType())) {
            exists_jobInfo.setJobJson(jobInfo.getJobJson());
            exists_jobInfo.setGlueSource(null);
        } else {
            exists_jobInfo.setGlueSource(jobInfo.getGlueSource());
            exists_jobInfo.setJobJson(null);
        }
        exists_jobInfo.setGlueUpdatetime(new Date());
        jobInfoMapper.update(exists_jobInfo);


        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<String> remove(int id) {
        JobInfo xxlJobInfo = jobInfoMapper.loadById(id);
        if (xxlJobInfo == null) {
            return ReturnT.SUCCESS;
        }

        jobInfoMapper.delete(id);
        jobLogMapper.delete(id);
        jobLogGlueMapper.deleteByJobId(id);
        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<String> start(int id) {
        JobInfo xxlJobInfo = jobInfoMapper.loadById(id);

        // next trigger time (5s后生效，避开预读周期)
        long nextTriggerTime = 0;
        try {
            Date nextValidTime = new CronExpression(xxlJobInfo.getJobCron()).getNextValidTimeAfter(new Date(System.currentTimeMillis() + JobScheduleHelper.PRE_READ_MS));
            if (nextValidTime == null) {
                return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_never_fire"));
            }
            nextTriggerTime = nextValidTime.getTime();
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_invalid") + " | " + e.getMessage());
        }

        xxlJobInfo.setTriggerStatus(1);
        xxlJobInfo.setTriggerLastTime(0);
        xxlJobInfo.setTriggerNextTime(nextTriggerTime);

        xxlJobInfo.setUpdateTime(new Date());
        jobInfoMapper.update(xxlJobInfo);
        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<String> start(List<Integer> ids) {

        if(ids.size() > 5) {
            throw new IllegalArgumentException("参数不合法");
        }else {
            for(Integer id : ids) {
                start(id);
            }
            return ReturnT.SUCCESS;
        }


    }


    @Override
    public ReturnT<String> stop(int id) {
        JobInfo jobInfo = jobInfoMapper.loadById(id);

        jobInfo.setTriggerStatus(0);
        jobInfo.setTriggerLastTime(0);
        jobInfo.setTriggerNextTime(0);

        jobInfo.setUpdateTime(new Date());
        jobInfoMapper.update(jobInfo);
        return ReturnT.SUCCESS;
    }

    @Override
    public Map<String, Object> dashboardInfo() {

        int jobInfoCount = jobInfoMapper.findAllCount();
        int jobLogCount = 0;
        int jobLogSuccessCount = 0;
        JobLogReport jobLogReport = jobLogReportMapper.queryLogReportTotal();
        if (jobLogReport != null) {
            jobLogCount = jobLogReport.getRunningCount() + jobLogReport.getSucCount() + jobLogReport.getFailCount();
            jobLogSuccessCount = jobLogReport.getSucCount();
        }

        // executor count
        Set<String> executorAddressSet = new HashSet<>();
        List<JobGroup> groupList = jobGroupMapper.findAll();

        if (groupList != null && !groupList.isEmpty()) {
            for (JobGroup group : groupList) {
                if (group.getRegistryList() != null && !group.getRegistryList().isEmpty()) {
                    executorAddressSet.addAll(group.getRegistryList());
                }
            }
        }

        int executorCount = executorAddressSet.size();

        Map<String, Object> dashboardMap = new HashMap<>();
        dashboardMap.put("jobInfoCount", jobInfoCount);
        dashboardMap.put("jobLogCount", jobLogCount);
        dashboardMap.put("jobLogSuccessCount", jobLogSuccessCount);
        dashboardMap.put("executorCount", executorCount);
        return dashboardMap;
    }

    @Override
    public ReturnT<Map<String, Object>> chartInfo() {
        // process
        List<String> triggerDayList = new ArrayList<String>();
        List<Integer> triggerDayCountRunningList = new ArrayList<Integer>();
        List<Integer> triggerDayCountSucList = new ArrayList<Integer>();
        List<Integer> triggerDayCountFailList = new ArrayList<Integer>();
        int triggerCountRunningTotal = 0;
        int triggerCountSucTotal = 0;
        int triggerCountFailTotal = 0;

        List<JobLogReport> logReportList = jobLogReportMapper.queryLogReport(DateUtil.addDays(new Date(), -7), new Date());

        if (logReportList != null && logReportList.size() > 0) {
            for (JobLogReport item : logReportList) {
                String day = DateUtil.formatDate(item.getTriggerDay());
                int triggerDayCountRunning = item.getRunningCount();
                int triggerDayCountSuc = item.getSucCount();
                int triggerDayCountFail = item.getFailCount();

                triggerDayList.add(day);
                triggerDayCountRunningList.add(triggerDayCountRunning);
                triggerDayCountSucList.add(triggerDayCountSuc);
                triggerDayCountFailList.add(triggerDayCountFail);

                triggerCountRunningTotal += triggerDayCountRunning;
                triggerCountSucTotal += triggerDayCountSuc;
                triggerCountFailTotal += triggerDayCountFail;
            }
        } else {
            for (int i = -6; i <= 0; i++) {
                triggerDayList.add(DateUtil.formatDate(DateUtil.addDays(new Date(), i)));
                triggerDayCountRunningList.add(0);
                triggerDayCountSucList.add(0);
                triggerDayCountFailList.add(0);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("triggerDayList", triggerDayList);
        result.put("triggerDayCountRunningList", triggerDayCountRunningList);
        result.put("triggerDayCountSucList", triggerDayCountSucList);
        result.put("triggerDayCountFailList", triggerDayCountFailList);

        result.put("triggerCountRunningTotal", triggerCountRunningTotal);
        result.put("triggerCountSucTotal", triggerCountSucTotal);
        result.put("triggerCountFailTotal", triggerCountFailTotal);

        return new ReturnT<>(result);
    }


    @Override
    public ReturnT<String> batchAdd(DataXBatchJsonBuildDto dto) throws IOException {

        String key = "system_please_choose";
        List<String> rdTables = dto.getReaderTables();
        List<String> wrTables = dto.getWriterTables();
        if (dto.getReaderDatasourceId() == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, I18nUtil.getString(key) + I18nUtil.getString("jobinfo_field_readerDataSource"));
        }
        if (dto.getWriterDatasourceId() == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, I18nUtil.getString(key) + I18nUtil.getString("jobinfo_field_writerDataSource"));
        }
        if (rdTables.size() != wrTables.size()) {
            return new ReturnT<>(ReturnT.FAIL_CODE, I18nUtil.getString("json_build_inconsistent_number_r_w_tables"));
        }

        DataXJsonBuildDto jsonBuild = new DataXJsonBuildDto();

        List<String> rColumns;
        List<String> wColumns;
        for (int i = 0; i < rdTables.size(); i++) {
            rColumns = datasourceQueryService.getColumns(dto.getReaderDatasourceId(), rdTables.get(i));
            wColumns = datasourceQueryService.getColumns(dto.getWriterDatasourceId(), wrTables.get(i));

            jsonBuild.setReaderDatasourceId(dto.getReaderDatasourceId());
            jsonBuild.setWriterDatasourceId(dto.getWriterDatasourceId());

            jsonBuild.setReaderColumns(rColumns);
            jsonBuild.setWriterColumns(wColumns);

            jsonBuild.setRdbmsReader(dto.getRdbmsReader());
            jsonBuild.setRdbmsWriter(dto.getRdbmsWriter());

            List<String> rdTable = new ArrayList<>();
            rdTable.add(rdTables.get(i));
            jsonBuild.setReaderTables(rdTable);

            List<String> wdTable = new ArrayList<>();
            wdTable.add(wrTables.get(i));
            jsonBuild.setWriterTables(wdTable);

            String json = dataxJsonService.buildJobJson(jsonBuild);

            JobTemplate jobTemplate = jobTemplateMapper.loadById(dto.getTemplateId());
            JobInfo jobInfo = new JobInfo();
            BeanUtils.copyProperties(jobTemplate, jobInfo);
            jobInfo.setJobJson(json);

            //批量构建的任务带入数据源
            JobDatasource datasource = jobDatasourceService.getById(dto.getReaderDatasourceId());
            String jobDesc = rdTables.get(i);
            jobInfo.setJobDesc(datasource.getDatasourceName() + "~" + jobDesc);
            jobInfo.setAddTime(new Date());
            jobInfo.setUpdateTime(new Date());
            jobInfo.setGlueUpdatetime(new Date());
            jobInfoMapper.save(jobInfo);
        }
        return ReturnT.SUCCESS;
    }


}
