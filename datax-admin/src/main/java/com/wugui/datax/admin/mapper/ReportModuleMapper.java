package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.ReportModule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportModuleMapper {

    List<ReportModule> getAllModules();


}