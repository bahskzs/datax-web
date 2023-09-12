package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.entity.AreaList;
import com.wugui.datax.admin.entity.Report;
import com.wugui.datax.admin.mapper.AreaListMapper;
import com.wugui.datax.admin.mapper.ReportMapper;
import com.wugui.datax.admin.service.AreaListService;
import com.wugui.datax.admin.service.ReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AreaListServiceImpl implements AreaListService {

    @Resource
    private AreaListMapper areaListMapper;

    @Override
    public List<AreaList> getArea() {

        return areaListMapper.getArea();
    }

}
