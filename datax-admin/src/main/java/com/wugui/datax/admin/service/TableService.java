package com.wugui.datax.admin.service;

import com.wugui.datax.admin.dto.DatasourceTableBO;
import com.wugui.datax.admin.dto.DatasourceTablesBO;
import com.wugui.datax.admin.dto.MultiTargetBO;

/**
 * @author Cat
 * @createTime 2023-01-29 15:32
 * @description table相关业务
 */
public interface TableService {

    boolean create(DatasourceTableBO tableBO);


    boolean createMulti(DatasourceTablesBO tableBO);

    boolean createMultiTargetTables(MultiTargetBO multiTargetBO);

    int createAllTables(String sourceId,String targetId);


}
