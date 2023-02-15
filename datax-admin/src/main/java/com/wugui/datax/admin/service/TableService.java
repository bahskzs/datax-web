package com.wugui.datax.admin.service;

import com.wugui.datax.admin.dto.DatasourceTableBO;

/**
 * @author Cat
 * @createTime 2023-01-29 15:32
 * @description table相关业务
 */
public interface TableService {

    boolean create(DatasourceTableBO tableBO);
}
