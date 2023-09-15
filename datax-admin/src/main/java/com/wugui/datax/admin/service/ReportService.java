package com.wugui.datax.admin.service;

import com.wugui.datax.admin.dto.ReportCreateReq;
import com.wugui.datax.admin.dto.ReportEditResp;
import com.wugui.datax.admin.dto.ReportQueryReq;
import com.wugui.datax.admin.dto.ReportQueryResp;
import com.wugui.datax.admin.entity.Report;
import io.swagger.models.auth.In;

import java.util.List;


public interface ReportService {


    List<ReportQueryResp> list(ReportQueryReq req);

    List<ReportEditResp> getReportById(Integer id);


    Integer insertSelective(ReportCreateReq record);

    Integer updateByPrimaryKey(ReportCreateReq report, Integer id);

    Integer deleteByPrimaryKey(Integer id);
}
