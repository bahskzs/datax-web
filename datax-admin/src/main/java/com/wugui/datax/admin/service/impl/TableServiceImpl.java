package com.wugui.datax.admin.service.impl;

import com.google.common.collect.Lists;
import com.wugui.datax.admin.dto.DatasourceTableBO;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.service.JobDatasourceService;
import com.wugui.datax.admin.service.TableService;
import com.wugui.datax.admin.tool.database.ColumnInfo;
import com.wugui.datax.admin.tool.database.TableInfo;
import com.wugui.datax.admin.tool.query.BaseQueryTool;
import com.wugui.datax.admin.tool.query.MongoDBQueryTool;
import com.wugui.datax.admin.tool.query.QueryToolFactory;
import com.wugui.datax.admin.util.AESUtil;
import com.wugui.datax.admin.util.JdbcConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Cat
 * @createTime 2023-01-29 15:33
 * @description
 */
@Service
public class TableServiceImpl implements TableService {

    @Resource
    private JobDatasourceService jobDatasourceService;

    @Override
    public boolean create(DatasourceTableBO tableBO) {
        // 使用基础工具类中的元数据相关拼凑建表语句

        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(tableBO.getSourceId());

        JobDatasource targetDatasource = jobDatasourceService.getById(tableBO.getDatasourceId());

        //TODO 同源类型直接转换,异源类型需要获取类型映射管理数据获得对应的字段类型转换
        if(datasource.getDatasource().equals(targetDatasource.getDatasource())) {
            BaseQueryTool qTool = QueryToolFactory.getByDbType(datasource);
            //获取源tableInfo信息
            TableInfo tableInfo = qTool.buildTableInfo(tableBO.getReaderTableName(), AESUtil.decrypt(datasource.getJdbcUsername()));

            Map<String, ColumnInfo> columnMap = qTool.getColumnMap(tableBO.getReaderTableName(),AESUtil.decrypt(datasource.getJdbcUsername()));
            List<String> columns = tableBO.getColumnsList();
            // 获取目标表对应的字段及类型
            List<ColumnInfo> targetColumns = Lists.newArrayList();
            for (String column : columns) {
                if(columnMap.containsKey(column)) {
                    targetColumns.add(columnMap.get(column));
                }
            }
            // 构造完整table信息
            tableInfo.setColumns(targetColumns);
            tableInfo.setName(tableBO.getTableName());
            List<String> createSqlList = qTool.buildCreateTableSql(tableInfo);

            QueryToolFactory.getByDbType(targetDatasource).executeCreateTableSqls(createSqlList);
        }


        return true;
    }
}
