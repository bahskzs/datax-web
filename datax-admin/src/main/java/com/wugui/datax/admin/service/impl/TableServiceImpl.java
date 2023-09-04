package com.wugui.datax.admin.service.impl;

import com.google.common.collect.Lists;
import com.wugui.datax.admin.dto.DatasourceTableBO;
import com.wugui.datax.admin.dto.DatasourceTablesBO;
import com.wugui.datax.admin.dto.MultiTargetBO;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.service.JobDatasourceService;
import com.wugui.datax.admin.service.TableService;
import com.wugui.datax.admin.tool.database.ColumnInfo;
import com.wugui.datax.admin.tool.database.ColumnInfoV2;
import com.wugui.datax.admin.tool.database.TableInfo;
import com.wugui.datax.admin.tool.database.TableInfoV2;
import com.wugui.datax.admin.tool.query.BaseQueryTool;
import com.wugui.datax.admin.tool.query.MongoDBQueryTool;
import com.wugui.datax.admin.tool.query.QueryToolFactory;
import com.wugui.datax.admin.util.AESUtil;
import com.wugui.datax.admin.util.CopyUtil;
import com.wugui.datax.admin.util.JdbcConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Cat
 * @createTime 2023-01-29 15:33
 * @description
 */
@Service
@Slf4j
public class TableServiceImpl implements TableService {

    @Resource
    private JobDatasourceService jobDatasourceService;

    @Override
    public boolean create(DatasourceTableBO tableBO) {
        // 使用基础工具类中的元数据相关拼凑建表语句

        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(tableBO.getSourceId());

        JobDatasource targetDatasource = jobDatasourceService.getById(tableBO.getDatasourceId());


        // 获取源表对应的BaseQueryTool
        BaseQueryTool qTool = QueryToolFactory.getByDbType(datasource);

        // 获取目标表对应的BaseQueryTool
        BaseQueryTool targetQTool = QueryToolFactory.getByDbType(targetDatasource);

        // 获取源tableInfo信息
        TableInfoV2 tableInfoV2 = qTool.buildTableInfoV2(tableBO.getReaderTableName(), AESUtil.decrypt(datasource.getJdbcUsername()));

        // 根据tableBO传入的字段在tableInfo中遍历得到需要建表的字段
        List<ColumnInfoV2> columnList = Lists.newArrayList();
        for (String column : tableBO.getColumnsList()) {
            for (ColumnInfoV2 columnInfo : tableInfoV2.getColumns()) {
                if (columnInfo.getColumnName().equals(column)) {
                    columnList.add(columnInfo);
                }
            }
        }

        // 根据获取到的columns对目标源的类型进行转换
        List<ColumnInfoV2> targetColumnList = Lists.newArrayList();





        // 构造目标表的完整table信息
        tableInfoV2.setColumns(targetColumnList);



        //TODO 同源类型直接转换,异源类型需要获取类型映射管理数据获得对应的字段类型转换
        if(datasource.getDatasource().equals(targetDatasource.getDatasource())) {


            //获取源tableInfo信息
            TableInfo tableInfo = qTool.buildTableInfo(tableBO.getReaderTableName(), AESUtil.decrypt(datasource.getJdbcUsername()),qTool.getCharacterSet().equals(targetQTool.getCharacterSet()));

            Map<String, ColumnInfo> columnMap = qTool.getColumnMap(tableBO.getReaderTableName(),AESUtil.decrypt(datasource.getJdbcUsername()),qTool.getCharacterSet().equals(targetQTool.getCharacterSet()));
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
            targetQTool.executeCreateTableSqls(createSqlList);
        }


        return true;
    }

    @Override
    public boolean createMulti(DatasourceTablesBO tableBO) {

        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(tableBO.getSourceId());

        JobDatasource targetDatasource = jobDatasourceService.getById(tableBO.getDatasourceId());



        // 获取table列表
        List<String> tableList = tableBO.getTableList();
        boolean flag = false;
        // 遍历列表构建表
        if(datasource.getDatasource().equals(targetDatasource.getDatasource())) {
            BaseQueryTool qTool = QueryToolFactory.getByDbType(datasource);
            BaseQueryTool targetQTool = QueryToolFactory.getByDbType(targetDatasource);
            List<String> targetTables = targetQTool.getTableNames(tableBO.getTargetSchema());
            //获取源tableInfo信息
            for (String tableName : tableList) {
                //TODO 此处应传模式名不应该传用户名
                TableInfo tableInfo = qTool.buildTableInfo(tableName, tableBO.getSourceSchema(),qTool.getCharacterSet().equals(targetQTool.getCharacterSet()));


                //TODO 判断表是否存在,若存在,则当前表追加尾部

                boolean isExist =  targetTables.contains(tableName);
                if(isExist) {
                    tableInfo.setName(tableName + "_V1");
                    flag = true;
                }
                tableInfo.setName(tableBO.getTargetSchema() + "." + tableInfo.getName());
                List<String> createSqlList = qTool.buildCreateTableSql(tableInfo);

                log.info("create: {}", createSqlList.get(0));

                flag = QueryToolFactory.getByDbType(targetDatasource).executeCreateTableSqls(createSqlList);

            }
            if(!flag){
                return flag;
            }
        }
        return flag;
    }

    @Override
    public boolean createMultiTargetTables(MultiTargetBO multiTargetBO) {

        ExecutorService executor = Executors.newFixedThreadPool(10); // 创建一个线程池
        // 批量调用 createMulti 方法
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (String targetSchema : multiTargetBO.getTargetSchema()) {

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                DatasourceTablesBO tableBO = CopyUtil.copy(multiTargetBO, DatasourceTablesBO.class);
                tableBO.setTargetSchema(targetSchema);
                createMulti(tableBO);

            }, executor);
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executor.shutdown(); // 关闭线程池

        return true;
    }


    /**
     *  创建链接数据源的所有表数量
     * @param sourceId
     * @param targetId
     * @return 建表的数量
     */
    @Override
    public int createAllTables(String sourceId, String targetId) {

        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(sourceId);
        JobDatasource targetDatasource = jobDatasourceService.getById(targetId);

        // 同源才做处理
        int tableCounts = 0;
        if (datasource.getDatasource().equals(targetDatasource.getDatasource())) {

            BaseQueryTool qTool = QueryToolFactory.getByDbType(datasource);
            BaseQueryTool targetQTool = QueryToolFactory.getByDbType(targetDatasource);

            // 获取要初始化的全部表
            List<String> sourceTableList = qTool.getTableNames();


            for (String tableName : sourceTableList) {
                TableInfo tableInfo = qTool.buildTableInfo(tableName, AESUtil.decrypt(datasource.getJdbcUsername()), qTool.getCharacterSet().equals(targetQTool.getCharacterSet()));
                List<String> createSqlList = qTool.buildCreateTableSql(tableInfo);
                boolean flag = QueryToolFactory.getByDbType(targetDatasource).executeCreateTableSqls(createSqlList);
                if (flag) {
                    tableCounts = tableCounts + 1;

                }
            }

        }

        return tableCounts;
    }


}
