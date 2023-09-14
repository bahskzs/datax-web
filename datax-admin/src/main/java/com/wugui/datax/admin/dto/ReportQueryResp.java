package com.wugui.datax.admin.dto;

import com.wugui.datax.admin.entity.AreaList;
import lombok.Data;

import java.util.List;


@Data
public class ReportQueryResp {

    private Integer id;

    private String moduleId;

    private String moduleName;

    private String reportName;

    private String reportAddress;

    private List<AreaList> areaList;

    private int sort;

    private int status;


    @Override
    public String toString() {
        return "ReportCreateReq{" +
                "id=" + id +
                ", moduleId='" + moduleId + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", reportName='" + reportName + '\'' +
                ", reportAddress='" + reportAddress + '\'' +
                ", areaList=" + areaList +
                ", sortId='" + sort + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}