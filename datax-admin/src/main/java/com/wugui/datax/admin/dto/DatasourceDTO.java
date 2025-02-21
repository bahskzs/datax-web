package com.wugui.datax.admin.dto;

import com.wugui.datax.admin.entity.JobDatasource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * @author: bahsk
  * @date: 2021-10-14 17:49
  * @description:
  * @params:
  * @return:
  */
 @Data
 @NoArgsConstructor
 @AllArgsConstructor
 @Builder
public class DatasourceDTO {

     /* 来源库id */
     private String source;

    /* 目标库id */
     private String targetSource;

}
