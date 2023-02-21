package com.wugui.datax.admin.service;


import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.dto.*;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.JobInfo;
import io.swagger.models.auth.In;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * core job action for datax-web
 *
 * @author xuxueli 2016-5-28 15:30:33
 */
public interface JobService {

    /**
     * page list
     *
     * @param start
     * @param length
     * @param jobGroup
     * @param jobDesc
     * @param glueType
     * @param userId
     * @return
     */
    Map<String, Object> pageList(int start, int length, int jobGroup, int triggerStatus, String jobDesc, String glueType, int userId, Integer[] projectIds);

    List<JobInfo> list();

    /**
     * add job
     *
     * @param jobInfo
     * @return
     */
    ReturnT<String> add(JobInfo jobInfo);

    /**
     * copy job
     *
     * @param jobId,datasourceId
     * @return
     */
    ReturnT<String> copy(Integer jobId, Long datasourceId);

    ReturnT<String> buildBatchJob(String sourceId, String targetId, String templateId) throws IOException;

    /**
     * update job
     *
     * @param jobInfo
     * @return
     */
    ReturnT<String> update(JobInfo jobInfo);

    /**
     * remove job
     * *
     *
     * @param id
     * @return
     */
    ReturnT<String> remove(int id);

    /**
     * start job
     *
     * @param id
     * @return
     */
    ReturnT<String> start(int id);

    /**
     * @author: bahsk
     * @date: 2021-10-15 16:21
     * @description: 批量执行任务
     * @params:
     * @return:
     */
    ReturnT<String> start(List<Integer> ids);


    /**
     * stop job
     *
     * @param id
     * @return
     */
    ReturnT<String> stop(int id);

    /**
     * dashboard info
     *
     * @return
     */
    Map<String, Object> dashboardInfo();

    /**
     * chart info
     *
     * @return
     */
    ReturnT<Map<String, Object>> chartInfo();

    /**
     * batch add
     *
     * @param dto
     * @return
     */
    ReturnT<String> batchAdd(DataXBatchJsonBuildDto dto) throws IOException;

    /**
     * @author: bahsk
     * @date: 2021-10-14 10:39
     * @description: batch copy
     * @params: jobId, dsList
     * @return:
     */
    ReturnT<String> batchCopy(Integer jobId, List<JobDatasource> dsList);

    /**
     * @author: bahsk
     * @date: 2021-10-14 17:18
     * @description: 项目定制接口 批量复制并替换数据源
     * @params:
     * @return:
     */
    ReturnT<String> batchDSCopy(Integer jobId, List<DatasourceDTO> dsList) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    /**
     * @author: bahsk
     * @date: 2021-10-20 8:39
     * @description: 项目定制接口 批量复制任务组
     * @params:
     * @return:
     */
    ReturnT<String> batchJobCopy(MultiJobsDTO jobs) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    /**
     * @author: bahsk
     * @date: 2021/10/24 11:50
     * @description: 项目定制接口 根据查询参数返回指定任务组
     * @params:
     * @return:
     */
    TriggerJobGroupDTO searchList(String words, String year);

    /**
     * @author: bahsk
     * @date: 2021-11-01 16:55
     * @description: 项目定制接口, 批量修改任务json串中数据源
     * @params:
     * @return:
     */
    ReturnT<String> batchUpdate(List<JobDatasourceRespDTO> jobDatasourceRespDTOList);

     /**
      * @author: bahsk
      * @date: 2021-12-31 16:00
      * @description: 导出全部json串
      * @params:
      * @return:
      */
    ReturnT<String> downloadJson();

     /**
      * @author: bahsk
      * @date: 2022-01-11 11:26
      * @description: 导出指定json串
      * @params:
      * @return:
      */
    ReturnT<String> downloadJson(Long id);

     /**
      * @author: bahsk
      * @date: 2022-01-20 15:34
      * @description: 查询任务json(解密的)
      * @params:
      * @return:
      */
    JobInfo selectOne(Long id);


}
