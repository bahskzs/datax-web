package com.wugui.datax.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bahsk
 * @createTime 2021-10-27 11:00
 * @description
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColumnDetailsRespDTO {

    private String column;
    private String columnType;
    private Integer columnLength;

}
