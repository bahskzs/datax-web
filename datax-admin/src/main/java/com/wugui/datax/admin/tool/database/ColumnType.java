package com.wugui.datax.admin.tool.database;

import lombok.Data;

/**
 * @author Cat
 * @createTime 2023-05-09 11:16
 * @description
 */
@Data
public class ColumnType {
    private String type;
    private int length;
    private int precision;
    private int scale;
}
