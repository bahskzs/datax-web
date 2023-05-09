package com.wugui.datax.admin.tool.strategy;

import com.wugui.datax.admin.tool.database.ColumnInfoV2;

/**
 * @author Cat
 * @createTime 2023-05-09 16:01
 * @description 列转行策略
 */
public interface ColumnConverter {

        /**
        * 转换
        *
        * @param columnInfoV2
        * @return
        */
        ColumnInfoV2 convert(ColumnInfoV2 columnInfoV2);
}
