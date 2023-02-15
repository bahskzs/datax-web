package com.wugui.datax.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wugui.datax.admin.dto.DatasourceGroupRespDTO;
import com.wugui.datax.admin.dto.JobDatasourceRespDTO;
import com.wugui.datax.admin.entity.FieldMapping;
import com.wugui.datax.admin.entity.JobDatasource;

import java.io.IOException;
import java.util.List;

/**
 * jdbc数据源配置表服务接口
 *
 * @author jingwk
 * @version v2.0
 * @since 2020-01-10
 */
public interface FieldMappingService extends IService<FieldMapping> {


    /**
     *更新数据源信息
     * @param datasource
     * @return
     */
    int update(FieldMapping fieldMapping);

    /**
     * 获取所有字段映射
     * @return
     */
    List<FieldMapping> selectAllFieldMapping();

}
