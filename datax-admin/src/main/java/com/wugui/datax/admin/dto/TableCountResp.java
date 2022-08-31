package com.wugui.datax.admin.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hyh
 * @createTime 2022-08-19 16:20
 * @description 返回表名和数据记录数
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableCountResp {
    private String tableName;
    private  String tableCounts;
}
