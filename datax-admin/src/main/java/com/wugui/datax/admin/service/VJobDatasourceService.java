package com.wugui.datax.admin.service;

import com.wugui.datax.admin.entity.JobInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bahsk
 * @createTime 2022-01-12 16:39
 * @description 视图对应的Service
 */

public interface VJobDatasourceService {

     /**
      * @author: bahsk
      * @date: 2022-01-12 16:39
      * @description: 查询指定的job list
      * @params:  sourceUser,sourceUrl,type == source,target
      * @return:
      */
     public List<JobInfo> findByParams(String sourceUser, String sourceUrl, String type);
}
