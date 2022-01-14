package com.wugui.datax.admin.mapper;


import com.wugui.datax.admin.entity.VJobDatasource;
import com.wugui.datax.admin.entity.VJobDatasourceExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VJobDatasourceMapper {

    long countByExample(VJobDatasourceExample example);
    List<VJobDatasource> selectByExample(VJobDatasourceExample example);

}
