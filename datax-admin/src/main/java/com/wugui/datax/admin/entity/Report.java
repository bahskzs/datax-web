package com.wugui.datax.admin.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private Integer id;

    private String moduleId;

    private String moduleName;

    private String reportName;

    private String reportAddress;

    private String areaList;

    private String sort;

    private String status;

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", moduleId='" + moduleId + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", reportName='" + reportName + '\'' +
                ", reportAddress='" + reportAddress + '\'' +
                ", areaList='" + areaList + '\'' +
                ", sortId='" + sort + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}