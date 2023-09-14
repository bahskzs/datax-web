package com.wugui.datax.admin.entity;

import lombok.Data;

@Data
public class ReportModule {
    private String moduleId;

    private String moduleName;



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", moduleId=").append(moduleId);
        sb.append(", moduleName=").append(moduleName);
        sb.append("]");
        return sb.toString();
    }
}