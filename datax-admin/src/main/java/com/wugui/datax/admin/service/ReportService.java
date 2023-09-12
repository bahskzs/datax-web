package com.wugui.datax.admin.service;

import com.wugui.datax.admin.dto.ReportCreateReq;
import com.wugui.datax.admin.dto.ReportQueryResp;

import java.util.List;


public interface ReportService {

    List<ReportQueryResp> getAllReports();

    void insertSelective(ReportCreateReq record);

    void updateByPrimaryKey(ReportCreateReq report, Integer id);

    void deleteByPrimaryKey(Integer id);
}
