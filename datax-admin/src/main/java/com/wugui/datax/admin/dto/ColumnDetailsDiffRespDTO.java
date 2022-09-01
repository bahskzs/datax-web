package com.wugui.datax.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColumnDetailsDiffRespDTO {

    private String columnName;
    private String sourceColumnType;
    private String targetColumnType;
    private Integer sourceColumnLength;
    private Integer targetColumnLength;
    private Boolean isSame;
    private String updateSql;


}
