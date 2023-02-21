package com.wugui.datax.admin.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datatx.core.util.DateUtil;
import com.wugui.datax.admin.core.cron.CronExpression;
import com.wugui.datax.admin.core.thread.JobTriggerPoolHelper;
import com.wugui.datax.admin.core.trigger.TriggerTypeEnum;
import com.wugui.datax.admin.core.util.I18nUtil;
import com.wugui.datax.admin.dto.*;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.service.JobDatasourceService;
import com.wugui.datax.admin.service.JobService;
import com.wugui.datax.admin.util.CopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.var;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;

/**
 * index controller
 *
 * @author xuxueli 2015-12-19 16:13:16
 */
@Api(tags = "任务配置接口")
@RestController
@RequestMapping("/api/job")
public class JobInfoController extends BaseController{

    @Resource
    private JobService jobService;

    @Resource
    private JobDatasourceService jobDatasourceService;


    @GetMapping("/pageList")
    @ApiOperation("任务列表")
    public ReturnT<Map<String, Object>> pageList(@RequestParam(required = false, defaultValue = "0") int current,
                                        @RequestParam(required = false, defaultValue = "10") int size,
                                        int jobGroup, int triggerStatus, String jobDesc, String glueType, Integer[] projectIds) {

        return new ReturnT<>(jobService.pageList((current-1)*size, size, jobGroup, triggerStatus, jobDesc, glueType, 0, projectIds));
    }

    @GetMapping("/list")
    @ApiOperation("全部任务列表")
    public ReturnT<List<JobInfo>> list(){
        return new ReturnT<>(jobService.list());
    }

    @GetMapping("/job")
    @ApiOperation("获取指定job的json")
    public ReturnT<JobInfo> selectOne(@RequestParam long id) {
        JobInfo jobInfo = jobService.selectOne(id);
        return new ReturnT<>(jobInfo);
    }

     /**
      * @author: bahsk
      * @date: 2021-10-27 9:22
      * @description:
      * @params:
      * @return:
      */
    @GetMapping("/search")
    @ApiOperation("[项目定制]查询指定任务列表")
    public ReturnT<TriggerJobGroupDTO> searchList(@RequestParam(required = true) String words, @RequestParam(required = false,defaultValue = "") String year){
        return new ReturnT<>(jobService.searchList(words,year));
    }


    @PostMapping("/add")
    @ApiOperation("添加任务")
    public ReturnT<String> add(HttpServletRequest request, @RequestBody JobInfo jobInfo) {
        jobInfo.setUserId(getCurrentUserId(request));
        return jobService.add(jobInfo);
    }

     /**
      * @author: bahsk
      * @date: 2021-10-14 9:54
      * @description: 复制任务
      * @params:
      * @return:
      */
    @PostMapping("/copy/{id}")
    @ApiOperation("[项目定制]复制任务(不替换数据源)")
    public ReturnT<String> copy(@PathVariable(value = "id") Integer jobId,@RequestBody JobDatasource datasource) {
        return jobService.copy(jobId,datasource.getId());
    }
     /**
      * @author: bahsk
      * @date: 2021-10-14 10:58
      * @description: 传入数据源列表，批量复制任务
      * @params:
      * @return:
      */
    @PostMapping("/copy/batch/{id}")
    @ApiOperation("[项目定制]批量复制任务(不替换数据源)")
    public ReturnT<String> copyBatch(@PathVariable(value = "id") Integer jobId,@RequestBody List<JobDatasource> dsList) {
        return jobService.batchCopy(jobId,dsList);
    }

    @PostMapping("/copy/ds/{id}")
    @ApiOperation("[项目定制]批量复制任务")
    public ReturnT<String> batchDSCopy(@PathVariable(value = "id") Integer jobId,@RequestBody List<DatasourceDTO> dsList) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        return jobService.batchDSCopy(jobId,dsList);
    }

     /**
      * @author: bahsk
      * @date: 2021-10-19 17:22
      * @description:  项目定制 批量复制任务组
      * @params:
      * @return:
      */
    @PostMapping("/copy/jobs")
    @ApiOperation("[项目定制]批量复制任务组")
    public ReturnT<String> batchJobCopy(@RequestBody MultiJobsDTO jobs) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        return jobService.batchJobCopy(jobs);
    }


    @PostMapping("/update")
    @ApiOperation("更新任务")
    public ReturnT<String> update(HttpServletRequest request,@RequestBody JobInfo jobInfo) {
        jobInfo.setUserId(getCurrentUserId(request));
        return jobService.update(jobInfo);
    }

    @PostMapping(value = "/remove/{id}")
    @ApiOperation("移除任务")
    public ReturnT<String> remove(@PathVariable(value = "id") int id) {
        return jobService.remove(id);
    }

    @RequestMapping(value = "/stop",method = RequestMethod.POST)
    @ApiOperation("停止任务")
    public ReturnT<String> pause(int id) {
        return jobService.stop(id);
    }

    @RequestMapping(value = "/start",method = RequestMethod.POST)
    @ApiOperation("开启任务")
    public ReturnT<String> start(int id) {
        return jobService.start(id);
    }

     /**
      * @author: bahsk
      * @date: 2021-10-20 11:10
      * @description: 项目定制批量执行任务
      * @params:
      * @return:
      */
    @PostMapping("/trigger/batch")
    @ApiOperation("[项目定制]批量执行任务")
    public ReturnT<String> startBatch(@RequestBody List<TriggerJobDto> triggerJobDtos){
        String executorParam = "";
        for (TriggerJobDto dto : triggerJobDtos) {
            JobTriggerPoolHelper.trigger(dto.getJobId(), TriggerTypeEnum.MANUAL, -1, null, executorParam);
        }
        return ReturnT.SUCCESS;
    }

    @PostMapping(value = "/trigger")
    @ApiOperation("触发任务")
    public ReturnT<String> triggerJob(@RequestBody TriggerJobDto dto) {
        // force cover job param
        String executorParam=dto.getExecutorParam();
        if (executorParam == null) {
            executorParam = "";
        }
        JobTriggerPoolHelper.trigger(dto.getJobId(), TriggerTypeEnum.MANUAL, -1, null, executorParam);
        return ReturnT.SUCCESS;
    }

    @GetMapping("/nextTriggerTime")
    @ApiOperation("获取近5次触发时间")
    public ReturnT<List<String>> nextTriggerTime(String cron) {
        List<String> result = new ArrayList<>();
        try {
            CronExpression cronExpression = new CronExpression(cron);
            Date lastTime = new Date();
            for (int i = 0; i < 5; i++) {
                lastTime = cronExpression.getNextValidTimeAfter(lastTime);
                if (lastTime != null) {
                    result.add(DateUtil.formatDateTime(lastTime));
                } else {
                    break;
                }
            }
        } catch (ParseException e) {
            return new ReturnT<>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_invalid"));
        }
        return new ReturnT<>(result);
    }

    @PostMapping("/batchAdd")
    @ApiOperation("批量创建任务")
    public ReturnT<String> batchAdd(@RequestBody DataXBatchJsonBuildDto dto) throws IOException {
        if (dto.getTemplateId() ==0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_choose") + I18nUtil.getString("jobinfo_field_temp")));
        }
        return jobService.batchAdd(dto);
    }

    @GetMapping("/bulidBatchAdd")
    @ApiOperation("[项目定制]构建批量创建任务")
    public ReturnT<CusDataXBatchJsonBuildDTO> bulidBatchAdd(@RequestParam(required = true) String words,
                                                         @RequestParam(required = true) Integer year,
                                                         @RequestParam(required = true) String tableName
                                                            ) {
        DatasourceGroupRespDTO datasourceGroupRespDTO = this.jobDatasourceService.selectDatasourceByWords(words, year);
        List<DatasourceDTO> datasourceDTOList = datasourceGroupRespDTO.getDatasourceDTOList();
        DatasourceDTO datasourceDTO = datasourceDTOList.get(0);

        String[] tables = tableName.split(",");
        CusDataXBatchJsonBuildDTO build = CusDataXBatchJsonBuildDTO.builder()
                .readerDatasourceId(Long.valueOf(datasourceDTO.getSource()))
                .requireDSName(true)
                .writerDatasourceId(Long.valueOf(datasourceDTO.getTargetSource()))
                .readerTables(Arrays.asList(tables))
                .writerTables(Arrays.asList(tables))
                .rdbmsReader(new RdbmsReaderDto())
                .rdbmsWriter(new RdbmsWriterDto("truncate table "+tableName,null))
                .build();

        return new ReturnT<>(build);
    }

    @GetMapping("/autoBulid")
    @ApiOperation("[项目定制]自动构建创建任务")
    public ReturnT<CusDataXBatchJsonBuildDTO> bulidBatchAdd(@RequestParam(required = true) String source,
                                                            @RequestParam(required = true) String target,
                                                            @RequestParam(required = true) String tableName,
                                                            @RequestParam(required = true) String templateId,
                                                            @RequestParam(required = true) String targetTableName) throws IOException {

        //TODO table不允许多余1个
        String[] tables = tableName.split(",");
        String[] targetTables = targetTableName.split(",");
        CusDataXBatchJsonBuildDTO build = CusDataXBatchJsonBuildDTO.builder()
                .readerDatasourceId(Long.valueOf(source))
                .requireDSName(true)
                .writerDatasourceId(Long.valueOf(target))
                .readerTables(Arrays.asList(tables))
                .writerTables(Arrays.asList(targetTables))
                .rdbmsReader(new RdbmsReaderDto())
                .rdbmsWriter(new RdbmsWriterDto("truncate table "+targetTableName,null))
                .templateId(Integer.valueOf(templateId))
                .build();
        DataXBatchJsonBuildDto dto = CopyUtil.copy(build, DataXBatchJsonBuildDto.class);
        if (dto.getTemplateId() ==0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_choose") + I18nUtil.getString("jobinfo_field_temp")));
        }
        jobService.batchAdd(dto);
        return new ReturnT<>();
    }


    @GetMapping("/autoBulidJob")
    @ApiOperation("[项目定制]自动构建全库迁移任务")
    public ReturnT<String> bulidBatchAdd(@RequestParam(required = true) String source,
                                                            @RequestParam(required = true) String target,
                                                            @RequestParam(required = true) String templateId) throws IOException {
        return jobService.buildBatchJob(source,target,templateId);

    }
     /**
      * @author: bahsk
      * @date: 2021-11-01 15:24
      * @description: TODO [项目定制]批量替换json串数据连接 出现了一点意外 需要排查一下接口失效问题
      * @params:
      * @return:
      */
     @PutMapping("/batchUpdate/datasource")
     @ApiOperation("[项目定制]批量替换数据源")
     public ReturnT<String> batchUpdate(@RequestBody List<JobDatasourceRespDTO> jobDatasourceRespDTOList) {
         return  jobService.batchUpdate(jobDatasourceRespDTOList);
     }


     @PostMapping("/download/json")
     @ApiOperation("[项目定制]导出json串")
     public ReturnT<String> downloadJson() {
         return  jobService.downloadJson();
     }


    @PostMapping("/download/json/{id}")
    @ApiOperation("[项目定制]导出指定任务的json串")
    public ReturnT<String> downloadJson(@PathVariable(value = "id") Long id) {
        return  jobService.downloadJson(id);
    }

}
