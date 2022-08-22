package com.wugui.datax.admin.dto;


import lombok.Builder;
import lombok.Data;

/**
 * @author Hyh
 * @createTime 2022-08-19 16:20
 * @description 返回表名和数据记录数
 */
@Builder
@Data
public class TableCountResp {
    private String tableName;
    private  String tableCounts;
}
