package com.wugui.datax.admin.tool.database;

import lombok.Data;

import java.util.List;

/**
 * 表信息
 *
 * @author zhouhongfa@gz-yibo.com
 * @version 1.0
 * @since 2019/7/30
 */
@Data
public class TableInfoV2 {
    /**
     * 表名
     */
    private String tableName;

    /**
     * 注释
     */
    private String comment;
    /**
     * 所有列
     */
    private List<ColumnInfoV2> columns;

}
