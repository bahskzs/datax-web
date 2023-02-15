package com.wugui.datax.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wugui.datax.admin.entity.FieldMapping;

import com.wugui.datax.admin.mapper.FieldMappingMapper;

import com.wugui.datax.admin.service.FieldMappingService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;


/**
 * Created by cat on 2020/01/30
 */
@Service
//@Transactional(readOnly = true)
public class FieldMappingServiceImpl extends ServiceImpl<FieldMappingMapper, FieldMapping> implements FieldMappingService {

    @Resource
    private FieldMappingMapper fieldMappingMapper;


    /**
     * 更新数据源信息
     *
     * @param fieldMapping@return
     */
    @Override
    public int update(FieldMapping fieldMapping) {
        return fieldMappingMapper.update(fieldMapping);
    }

    /**
     * 获取所有字段映射
     *
     * @return FieldMapping
     */
    public List<FieldMapping> selectAllFieldMapping() {
        return fieldMappingMapper.selectList(null);
    }
}
