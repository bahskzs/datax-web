package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.dto.ElementDTO;
import com.wugui.datax.admin.entity.Agency;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 *@author Cat
 *@createTime 2023-09-19 17:41
 *@description
 */
@Mapper
public interface AgencyMapper {
    int insert(Agency record);

    int insertSelective(Agency record);

    List<ElementDTO> findByYear(@Param("areaCodes") List<String> areaCodes);
}
