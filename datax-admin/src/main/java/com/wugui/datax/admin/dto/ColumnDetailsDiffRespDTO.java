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

    private String column;
    private String columnType;
    private String targetColumnType;
    private String columnLength;
    private String targetColumnLength;
    private String alterType;
    private String alterString;


}
