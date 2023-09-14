package com.wugui.datax.admin.service;

import com.wugui.datax.admin.dto.ReportCreateReq;
import com.wugui.datax.admin.dto.ReportQueryResp;
import io.swagger.models.auth.In;

import java.util.List;


public interface ReportService {

    List<ReportQueryResp> getAllReports();

    Integer insertSelective(ReportCreateReq record);

    Integer updateByPrimaryKey(ReportCreateReq report, Integer id);

    Integer deleteByPrimaryKey(Integer id);
}
