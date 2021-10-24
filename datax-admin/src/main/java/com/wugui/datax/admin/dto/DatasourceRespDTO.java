package com.wugui.datax.admin.dto;

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
public class DatasourceRespDTO {

     private String source;

     private String sourceName;

     private String targetSource;

     private String targetSourceName;
}
