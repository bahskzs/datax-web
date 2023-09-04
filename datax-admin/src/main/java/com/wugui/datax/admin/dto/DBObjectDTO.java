package com.wugui.datax.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class DBObjectDTO {

    // 源,目标列表
    List<DatasourceDTO> dsList;

    // 数据库对象类型
    String databaseObjectType;


    // 数据库对象名
    String databaseObjectName;

    // 用户
    String schema;


}
