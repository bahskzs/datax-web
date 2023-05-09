package com.wugui.datax.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wugui.datax.admin.dto.*;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.service.DatasourceQueryService;
import com.wugui.datax.admin.service.JobDatasourceService;
//import com.wugui.datax.admin.tool.database.DasColumn;
import com.wugui.datax.admin.tool.query.*;
import com.wugui.datax.admin.util.AESUtil;
import com.wugui.datax.admin.util.JdbcConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;

/**
 * datasource query
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName JdbcDatasourceQueryServiceImpl
 * @Version 1.0
 * @since 2019/7/31 20:51
 */
@Service
@Slf4j
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
            if (StringUtils.isBlank(tableSchema)) {
                List<String> tableNameList = qTool.getTableNames();
                log.info(" tableSchema {} , getTables.size {}", tableSchema, tableNameList.size());
                tableNameList.forEach(table -> log.info("table:{}", table));
                return tableNameList;
            } else {
                List<String> tableNameList = qTool.getTableNames(tableSchema);
                log.info(" tableSchema {} , getTables.size {}", tableSchema, tableNameList.size());
                tableNameList.forEach(table -> log.info("table:{}", table));
                return tableNameList;
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
    public List<ColumnDetailsRespDTO> getColumnsDetails(Long datasourceId, String tableName) {
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
        for (Integer datasource : datasourceList) {
            for (String table : tableList) {
                tableCountResp.add(getTableCount(table, Long.valueOf(datasource)));
            }
        }
        return tableCountResp;
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


    //TODO 实现获取同类型不同数据源之间相同表的表结构对比

    /**
     * 实现获取同类型不同数据源之间相同表的表结构对比
     *
     * @param sourceDatasourceId
     * @param targetDatasourceId
     * @param tableNameList
     * @return List<ColumnDetailsDiffRespDTO>
     * @throws IOException
     */
    @Override
    public List<ColumnDetailsDiffRespDTO> getColumnsDiffDetails(Long sourceDatasourceId, Long targetDatasourceId, List<String> tableNameList) throws IOException {
        //  1. 获取源，目标连接
        //获取来源数据源对象
        JobDatasource sourceDatasource = jobDatasourceService.getById(sourceDatasourceId);
        //获取目标数据源对象
        JobDatasource targetDatasource = jobDatasourceService.getById(targetDatasourceId);

        List<ColumnDetailsDiffRespDTO> result = new ArrayList<>();
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);

        // 2.1. 同源处理
        boolean flag = compareDatasource(sourceDatasource, targetDatasource);
        if (flag) {
            //仅支持oracle,mysql同源数据对比
            //3.比较  -- 开线程池处理多表字段比对
            for (String tableName : tableNameList) {
                // 线程池
                Callable<List<ColumnDetailsDiffRespDTO>> callable = () -> {
                    result.addAll(getDiffList(sourceDatasource, targetDatasource, tableName));
                    return result;
                };
                Future<List<ColumnDetailsDiffRespDTO>> future = newFixedThreadPool.submit(callable);
                try {
                    future.get();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            //2.2 非同源不处理
            throw new RuntimeException("不支持非同源处理!");
        }
        newFixedThreadPool.shutdown();
        //4.构造返回DTO
        return result;

    }

    private Boolean compareDatasource(JobDatasource sourceDatasource, JobDatasource targetDatasource) {

        if (sourceDatasource.getDatasource().equals(targetDatasource.getDatasource())) {
            return true;
        }
        return false;

    }


    /**
     * 对比取出不一样的字段信息
     *
     * @param sourceDatasource
     * @param targetDatasource
     * @return List<ColumnDetailsDiffRespDTO>
     */
    private List<ColumnDetailsDiffRespDTO> getDiffList(JobDatasource sourceDatasource, JobDatasource targetDatasource, String tableName) {
        // 2. 根据源获取对应元数据信息
        BaseQueryTool sourceQueryTool = QueryToolFactory.getByDbType(sourceDatasource);
        BaseQueryTool targetQueryTool = QueryToolFactory.getByDbType(targetDatasource);

        List<ColumnDetailsRespDTO> sourceList = sourceQueryTool.getColumnsDetails(tableName, sourceDatasource.getDatasource());
        List<ColumnDetailsRespDTO> targetList = targetQueryTool.getColumnsDetails(tableName, targetDatasource.getDatasource());
        //3.对比两个表的结构，取差
        HashSet<ColumnDetailsRespDTO> sourceSet = new HashSet<>(sourceList);
        HashSet<ColumnDetailsRespDTO> targetSet = new HashSet<>(targetList);
        Set<ColumnDetailsRespDTO> difference = Sets.difference(sourceSet, targetSet);
        //把difference,targetSet转为map
        HashMap<String, ArrayList<String>> diffMap = setToHashMap(difference);
        HashMap<String, ArrayList<String>> targetMap = setToHashMap(targetSet);
        //根据key，合并value ArrayList的值
        HashMap<String, ArrayList<String>> resultMap = mergeValueByKey(diffMap, targetMap);
        //将map结果转换为list
        List<ColumnDetailsDiffRespDTO> resultList;
        resultList = mapToList(resultMap);

        //更新对象中的alter语句
        resultList = updateAlterString(resultList, sourceDatasource, tableName);

        return resultList;
    }

    /**
     * 将set转为hashmap
     *
     * @param set
     * @return
     */
    private HashMap<String, ArrayList<String>> setToHashMap(Set<ColumnDetailsRespDTO> set) {
        HashMap<String, ArrayList<String>> resultMap = new HashMap<>();
        for (ColumnDetailsRespDTO diffDTO : set) {
            ArrayList<String> resultList = new ArrayList<>();
            resultList.add(diffDTO.getColumnType());
            resultList.add(diffDTO.getColumnLength().toString());
            resultMap.put(diffDTO.getColumn(), resultList);
        }
        return resultMap;
    }

    /**
     * 根据diffmap中的key去找targetmap中相同key的value  并合并到value的list中
     *
     * @param sourceMap
     * @param targetMap
     * @return HashMap<String, ArrayList < String>>
     */
    private HashMap<String, ArrayList<String>> mergeValueByKey(HashMap<String, ArrayList<String>> sourceMap, HashMap<String, ArrayList<String>> targetMap) {
        //根据diffmap中的key去找targetmap中相同key的value  并合并
        for (String key : sourceMap.keySet()) {
            ArrayList<String> targetValueList = targetMap.get(key);
            //找不到对应值 targetlength和targettype都是空，类型为a m:modify  a:add
            if (targetValueList == null || targetValueList.size() == 0) {
                ArrayList<String> difflist = sourceMap.get(key);
                difflist.add("");
                difflist.add("");
                //m:modify  a:add
                difflist.add("a");
                sourceMap.put(key, difflist);
            } else {
                //找到对应值 追加写入
                ArrayList<String> difflist = sourceMap.get(key);
                difflist.add(targetValueList.get(0));
                difflist.add(targetValueList.get(1));
                //m:modify  a:add
                difflist.add("m");
                sourceMap.put(key, difflist);
            }
        }
        return sourceMap;

    }

    /**
     * @param resultMap
     * @return
     */
    private List<ColumnDetailsDiffRespDTO> mapToList(HashMap<String, ArrayList<String>> resultMap) {
        //结果对象
        List<ColumnDetailsDiffRespDTO> resultList = new ArrayList<>();
        //把diffmap的数据填入resultList
        for (String key : resultMap.keySet()) {
            ColumnDetailsDiffRespDTO columnDetailsDiffRespDTO = new ColumnDetailsDiffRespDTO();
            columnDetailsDiffRespDTO.setColumn(key);
            ArrayList<String> difflist = resultMap.get(key);
            columnDetailsDiffRespDTO.setColumn(key);
            columnDetailsDiffRespDTO.setColumnType(difflist.get(0));
            columnDetailsDiffRespDTO.setColumnLength(difflist.get(1));
            columnDetailsDiffRespDTO.setTargetColumnType(difflist.get(2));
            columnDetailsDiffRespDTO.setTargetColumnLength(difflist.get(3));
            columnDetailsDiffRespDTO.setAlterType(difflist.get(4));
            resultList.add(columnDetailsDiffRespDTO);
        }
        return resultList;
    }


    /**
     * @param resultList
     * @param sourceDatasource
     * @param tableName
     * @return List<ColumnDetailsDiffRespDTO>
     */
    private List<ColumnDetailsDiffRespDTO> updateAlterString(List<ColumnDetailsDiffRespDTO> resultList, JobDatasource sourceDatasource, String tableName) {
        for (int i = 0; i < resultList.size(); i++) {
            ColumnDetailsDiffRespDTO columnDetailsDiffRespDTO = resultList.get(i);
            BaseQueryTool queryTool = QueryToolFactory.getByDbType(sourceDatasource);
            String alterString = queryTool.getColumnsAlter(columnDetailsDiffRespDTO, tableName);
            resultList.get(i).setAlterString(alterString);
        }

        return resultList;

    }


}
