package com.wugui.datax.admin.dto;

import com.wugui.datax.admin.entity.JobDatasource;
import lombok.Builder;
import lombok.Data;

/**
  * @author: bahsk
  * @date: 2021-10-14 17:49
  * @description:
  * @params:
  * @return:
  */
 @Data
public class DatasourceDTO {

     private String source;

     private String targetSource;
}
