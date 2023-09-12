package com.wugui.datax.admin.dto;

import com.wugui.datax.admin.entity.AreaList;
import lombok.Data;

import java.util.List;


@Data
public class ReportCreateReq {

    private String moduleId;

    private String reportName;

    private String reportAddress;

    private List<AreaList> areaList;

    private int sort;

    private int status;


}