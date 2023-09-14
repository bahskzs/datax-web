package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.Report;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {

    void deleteByPrimaryKey(Integer id);

    int insert(Report record);

    void insertSelective(Report record);

    Report selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKey(Report report);

    List<Report> getAllReports();

}