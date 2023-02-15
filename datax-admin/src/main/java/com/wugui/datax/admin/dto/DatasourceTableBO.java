package com.wugui.datax.admin.dto;

import lombok.Data;

import java.util.List;

@Data
public
class DatasourceTableBO {
    int datasourceId;
    String tableName;
    int sourceId;
    String readerTableName;
    List<String> columnsList;
}
