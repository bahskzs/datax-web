package com.wugui.datax.admin.tool.strategy;

import com.wugui.datax.admin.tool.database.ColumnInfoV2;

/**
 * @author Cat
 * @createTime 2023-05-09 16:07
 * @description
 */
public class OracleColumnConverter implements ColumnConverter{
    /**
     * 转换
     *
     * @param columnInfoV2
     * @return
     */
    @Override
    public ColumnInfoV2 convert(ColumnInfoV2 columnInfoV2) {
        ColumnInfoV2 newColumnInfoV2 = new ColumnInfoV2();
        newColumnInfoV2.setColumnName(columnInfoV2.getColumnName());
        newColumnInfoV2.setColumnType(columnInfoV2.getColumnType());
        //newColumnInfoV2.setIsPrimaryKey(columnInfoV2.isPrimaryKey());
        newColumnInfoV2.setIsNull(columnInfoV2.getIsNull());
        newColumnInfoV2.setColumnComment(columnInfoV2.getColumnComment());



        return newColumnInfoV2;
    }
}
