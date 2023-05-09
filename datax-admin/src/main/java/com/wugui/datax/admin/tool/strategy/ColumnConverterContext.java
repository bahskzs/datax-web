package com.wugui.datax.admin.tool.strategy;

import com.wugui.datax.admin.tool.database.ColumnInfoV2;

import java.util.Map;

/**
 * @author Cat
 * @createTime 2023-05-09 16:08
 * @description
 */
public class ColumnConverterContext {

    private ColumnConverter converter;
    private Map<String, String> typeMappings;

    public ColumnConverter getConverter() {
        return converter;
    }

    public void setConverter(ColumnConverter converter) {
        this.converter = converter;
    }

    public Map<String, String> getTypeMappings() {
        return typeMappings;
    }

    public void setTypeMappings(Map<String, String> typeMappings) {
        this.typeMappings = typeMappings;
    }
}
