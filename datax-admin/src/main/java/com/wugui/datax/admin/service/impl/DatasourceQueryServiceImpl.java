package com.wugui.datax.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import com.wugui.datax.admin.dto.*;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.service.DatasourceQueryService;
import com.wugui.datax.admin.service.JobDatasourceService;
//import com.wugui.datax.admin.tool.database.DasColumn;
import com.wugui.datax.admin.tool.query.*;
import com.wugui.datax.admin.util.AESUtil;
import com.wugui.datax.admin.util.JdbcConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * datasource query
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName JdbcDatasourceQueryServiceImpl
 * @Version 1.0
 * @since 2019/7/31 20:51
 */
@Service
public class DatasourceQueryServiceImpl implements DatasourceQueryService {

    private final JobDatasourceService jobDatasourceService;

    public DatasourceQueryServiceImpl(JobDatasourceService jobDatasourceService) {
        this.jobDatasourceService = jobDatasourceService;
    }

    @Override
    public List<String> getDBs(Long id) throws IOException {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(id);
        return new MongoDBQueryTool(datasource).getDBNames();
    }


    @Override
    public List<String> getTables(Long id, String tableSchema) throws IOException {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(id);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
//        if (JdbcConstants.HBASE.equals(datasource.getDatasource())) {
//            return new HBaseQueryTool(datasource).getTableNames();
//        } else

            if (JdbcConstants.MONGODB.equals(datasource.getDatasource())) {
            return new MongoDBQueryTool(datasource).getCollectionNames(datasource.getDatabaseName());
        } else {
            BaseQueryTool qTool = QueryToolFactory.getByDbType(datasource);
            if(StringUtils.isBlank(tableSchema)){
                return qTool.getTableNames();
            }else{
                return qTool.getTableNames(tableSchema);
            }
        }
    }

    @Override
    public List<String> getTableSchema(Long id) {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(id);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
        BaseQueryTool qTool = QueryToolFactory.getByDbType(datasource);
        return qTool.getTableSchema();
    }

    /**
     * @param datasourceId
     * @param tableName
     * @author: bahsk
     * @date: 2021-10-27 10:58
     * @description: [项目定制]根据数据源id和表名获取所有字段明细/仅限
     */
    @Override
    public List<ColumnDetailsRespDTO> getColumnsDetails(Long datasourceId, String tableName)  {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(datasourceId);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
        if (JdbcConstants.ORACLE.equals(datasource.getDatasource())) {
            BaseQueryTool queryTool = QueryToolFactory.getByDbType(datasource);
            return queryTool.getColumnsDetails(tableName, datasource.getDatasource());
        } else {
            return null;
        }
    }

    /**
     * @param tableName
     * @param datasourceId
     * @author: bahsk
     * @date: 2021-12-08 16:49
     * @description: 获取DDL
     */
    @Override
    public List<TableDetailsResp> getDdlSQL(String tableName, Long datasourceId) {
        JobDatasource datasource = jobDatasourceService.getById(datasourceId);
        BaseQueryTool queryTool = QueryToolFactory.getByDbType(datasource);
        return queryTool.getDdlSQL(tableName, AESUtil.decrypt(datasource.getJdbcUsername()));
    }

    //TODO  获取单张表的数据量
    public TableCountResp getTableCount(String tableName, Long datasourceId) {
        //根据id获取数据源 mysql oracle hive
        JobDatasource datasource = jobDatasourceService.getById(datasourceId);
        BaseQueryTool queryTool = QueryToolFactory.getByDbType(datasource);
        TableCountResp result = queryTool.getTableCount(tableName);
        return result;
    }


    public List<TableCountResp> getTableCounts(List<String> tableList, List<Integer> datasourceList) {
        List<TableCountResp> tableCountResp = new ArrayList<>();

        //数据源id A 进入循环 count表数据 ； B进入循环 count表数据
        for (Integer datasource : datasourceList){
            for (String table : tableList) {
                tableCountResp.add(getTableCount(table, Long.valueOf(datasource)));
            }
        }
        return  tableCountResp;
    }



    @Override
    public List<String> getCollectionNames(long id, String dbName) throws IOException {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(id);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
        return new MongoDBQueryTool(datasource).getCollectionNames(dbName);
    }


    @Override
    public List<String> getColumns(Long id, String tableName) throws IOException {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(id);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
//        if (JdbcConstants.HBASE.equals(datasource.getDatasource())) {
//            return new HBaseQueryTool(datasource).getColumns(tableName);
//        } else

            if (JdbcConstants.MONGODB.equals(datasource.getDatasource())) {
            return new MongoDBQueryTool(datasource).getColumns(tableName);
        } else {
            BaseQueryTool queryTool = QueryToolFactory.getByDbType(datasource);
            return queryTool.getColumnNames(tableName, datasource.getDatasource());
        }
    }

    @Override
    public List<String> getColumnsByQuerySql(Long datasourceId, String querySql) throws SQLException {
        //获取数据源对象
        JobDatasource jdbcDatasource = jobDatasourceService.getById(datasourceId);
        //queryTool组装
        if (ObjectUtil.isNull(jdbcDatasource)) {
            return Lists.newArrayList();
        }
        BaseQueryTool queryTool = QueryToolFactory.getByDbType(jdbcDatasource);
        return queryTool.getColumnsByQuerySql(querySql);
    }



    @Override
    public List<ColumnDetailsDiffRespDTO> getColumnsDiffDetails(Long sourceDatasourceId, Long targetDatasourceId, List<String> tableNameList) throws IOException {
        //获取来源数据源对象
        JobDatasource sourceDatasource = jobDatasourceService.getById(sourceDatasourceId);
        //获取目标数据源对象
        JobDatasource targetDatasource = jobDatasourceService.getById(targetDatasourceId);
        //queryTool组装
        if (ObjectUtil.isNull(sourceDatasource) || ObjectUtil.isNull(targetDatasource)) {
            return Lists.newArrayList();
        }
        BaseQueryTool queryTool = QueryToolFactory.getByDbType(sourceDatasource);

        List<ColumnDetailsDiffRespDTO> list = null;
        for (int i = 0; i < tableNameList.size(); i++) {
            List<ColumnDetailsDiffRespDTO> listAll;
            String tableName = tableNameList.get(i);
            List<ColumnDetailsRespDTO> sourceList = queryTool.getColumnsDetails(tableName, sourceDatasource.getDatasource());
            List<ColumnDetailsRespDTO> targetList = queryTool.getColumnsDetails(tableName, sourceDatasource.getDatasource());
            list = queryTool.getColumnsDetailsDiff(sourceList, targetList, tableName);

            System.out.println(tableName);

        }


        return list;

    }



}
