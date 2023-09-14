package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.AreaList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AreaListMapper {

    List<AreaList> getArea();
}