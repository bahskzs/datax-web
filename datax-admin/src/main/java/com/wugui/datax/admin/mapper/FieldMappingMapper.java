package com.wugui.datax.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wugui.datax.admin.entity.FieldMapping;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author Cat
 * @createTime 2023-01-16 14:57
 * @description
 */
@Mapper
public interface FieldMappingMapper extends BaseMapper<FieldMapping> {
    int update(FieldMapping fieldMapping);
}
