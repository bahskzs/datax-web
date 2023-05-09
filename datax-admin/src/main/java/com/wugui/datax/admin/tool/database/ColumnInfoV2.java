package com.wugui.datax.admin.tool.database;

import lombok.Data;

/**
 * @author Cat
 * @createTime 2023-05-09 11:32
 * @description ColumnInfoV2 升级版
 */

@Data
public class ColumnInfoV2 {

    // 字段名称
    private String columnName;

    // 字段类型
    private ColumnType columnType;

    // 字段注释
    private String columnComment;

    // 是否为空
    private int isNull;

    // 是否是主键
    private boolean isPrimaryKey;
}
