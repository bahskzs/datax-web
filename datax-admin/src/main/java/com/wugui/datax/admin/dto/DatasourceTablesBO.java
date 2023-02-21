package com.wugui.datax.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public
class DatasourceTablesBO {
    @NotNull(message = "源数据源不允许为空! ")
    private int datasourceId;
    @NotNull(message = "建表列表不能为空! ")
    private List<String> tableList;
    @NotNull(message = "目标数据源不允许为空! ")
    private int sourceId;

    private String sourceSchema;
    private String targetSchema;
}
