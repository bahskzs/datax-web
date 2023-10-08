package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.dto.ElementDTO;
import com.wugui.datax.admin.entity.Office;
import com.wugui.datax.admin.entity.Region;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 *@author Cat
 *@createTime 2023-09-19 17:42
 *@description
 */
@Mapper
public interface OfficeMapper {
    int insert(Office record);

    int insertSelective(Office record);


    List<ElementDTO> findByYear(@Param("areaCode") String areaCode, @Param("year") String year);

    List<ElementDTO> findByArea(@Param("areaCodes") List<String> areaCodes);
}
