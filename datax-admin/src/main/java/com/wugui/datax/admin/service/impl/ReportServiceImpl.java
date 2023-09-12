package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.dto.ReportCreateReq;
import com.wugui.datax.admin.dto.ReportQueryResp;
import com.wugui.datax.admin.entity.AreaList;
import com.wugui.datax.admin.entity.Report;
import com.wugui.datax.admin.entity.ReportModule;
import com.wugui.datax.admin.mapper.ReportMapper;
import com.wugui.datax.admin.mapper.ReportModuleMapper;
import com.wugui.datax.admin.service.ReportService;
import com.wugui.datax.admin.util.CopyUtil;
import com.wugui.datax.admin.util.JSONUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private ReportModuleMapper reportModuleMapper;

    @Override
    public List<ReportQueryResp> getAllReports() {
        List<Report> list = reportMapper.getAllReports();
        List<ReportQueryResp> respList = new ArrayList<>();
        for (Report report : list) {
            ReportQueryResp resp = CopyUtil.copy(report, ReportQueryResp.class);
            resp.setAreaList(JSONUtils.toList(report.getAreaList(), AreaList.class));
            respList.add(resp);
        }
        return respList;
    }

    @Override
    public void insertSelective(ReportCreateReq report) {

        Report rep = CopyUtil.copy(report, Report.class);

        // rep 其他字段填充, moduleName,areaList
        rep.setAreaList(JSONUtils.toJson(report.getAreaList()));
        List<ReportModule> allModules = reportModuleMapper.getAllModules();

        // 比对模块表的id和新增的记录模块id 匹配就添加对应模块名称
        allModules.stream().filter(
            reportModule -> reportModule.getModuleId().equals(rep.getModuleId())
        ).forEach(
                reportModule -> {
                    rep.setModuleName(reportModule.getModuleName());
                }
        );
            reportMapper.insertSelective(rep);
    }

    @Override
    public void updateByPrimaryKey(ReportCreateReq report, Integer id) {
        Report rept = reportMapper.selectByPrimaryKey(id);
        if(rept==null){
            throw new IllegalArgumentException();
        }else{
            Report rep = CopyUtil.copy(rept, Report.class);

            // rep 其他字段填充, moduleName,areaList
            rep.setAreaList(JSONUtils.toJson(report.getAreaList()));
            List<ReportModule> allModules = reportModuleMapper.getAllModules();

            // 比对模块表的id和新增的记录模块id 匹配就添加对应模块名称
            allModules.stream().filter(
                    reportModule -> reportModule.getModuleId().equals(rep.getModuleId())
            ).forEach(
                    reportModule -> {
                        rep.setModuleName(reportModule.getModuleName());
                    }
            );
            rep.setId(id);
            reportMapper.updateByPrimaryKey(rep);
        }
    }


    @Override
    public void deleteByPrimaryKey(Integer id) {
        reportMapper.deleteByPrimaryKey(id);
    }
}