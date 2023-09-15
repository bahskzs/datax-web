package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.dto.ReportCreateReq;
import com.wugui.datax.admin.dto.ReportEditResp;
import com.wugui.datax.admin.dto.ReportQueryReq;
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
    public List<ReportQueryResp> list(ReportQueryReq req) {

        List<Report> list;
        List<ReportQueryResp> respList = new ArrayList<>();

        // 不为空 模糊查询对应模块报表
        if (!ObjectUtils.isEmpty(req.getModuleName())) {
            list = reportMapper.getReportByName("%" + req.getModuleName() + "%");

        } else {
            list = reportMapper.getAllReports();
        }
        for (Report report : list) {
            ReportQueryResp resp = CopyUtil.copy(report, ReportQueryResp.class);
            resp.setAreaList(JSONUtils.toList(report.getAreaList(), AreaList.class));
            respList.add(resp);
        }
        return respList;
    }


    @Override
    public List<ReportEditResp> getReportById(Integer id) {
        List<Report> list = reportMapper.getReportById(id);
        List<ReportEditResp> respList = new ArrayList<>();
        for (Report report : list) {
            ReportEditResp resp = CopyUtil.copy(report, ReportEditResp.class);
            resp.setAreaList(JSONUtils.toList(report.getAreaList(), AreaList.class));
//            resp.setModuleList(JSONUtils.toList(report.getModuleName(), ReportModule.class));
            respList.add(resp);
        }
        return respList;
    }


    @Override
    public Integer insertSelective(ReportCreateReq report) {

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
          int flag=   reportMapper.insert(rep);
          return  flag;
    }

    @Override
    public Integer updateByPrimaryKey(ReportCreateReq report, Integer id) {
        Report rept = reportMapper.selectByPrimaryKey(id);
        if(rept==null){
            throw new IllegalArgumentException();
        }else{
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
            rep.setId(id);
            int flag = reportMapper.updateByPrimaryKey(rep);
            return flag;
        }
    }


    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        int flag = reportMapper.deleteByPrimaryKey(id);
        return flag;
    }
}