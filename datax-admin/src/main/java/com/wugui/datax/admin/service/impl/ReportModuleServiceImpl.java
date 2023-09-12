package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.entity.Report;
import com.wugui.datax.admin.entity.ReportModule;
import com.wugui.datax.admin.mapper.ReportMapper;
import com.wugui.datax.admin.mapper.ReportModuleMapper;
import com.wugui.datax.admin.service.ReportModuleService;
import com.wugui.datax.admin.service.ReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReportModuleServiceImpl implements ReportModuleService {

    @Resource
    private ReportModuleMapper reportModuleMapper;


    @Override
    public List<ReportModule> getAllModules() {

        return reportModuleMapper.getAllModules();
    }
}
