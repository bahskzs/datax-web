package com.wugui.datax.admin.service;

import com.wugui.datax.admin.dto.ColumnDetailsDiffRespDTO;
import com.wugui.datax.admin.dto.ColumnDetailsRespDTO;
import com.wugui.datax.admin.dto.TableCountResp;
import com.wugui.datax.admin.dto.TableDetailsResp;
import com.wugui.datax.admin.tool.database.DasColumn;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库查询服务
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName JdbcDatasourceQueryService
 * @Version 1.0
 * @since 2019/7/31 20:50
 */
public interface DatasourceQueryService {

    /**
     * 获取db列表
     * @param id
     * @return
     */
    List<String> getDBs(Long id) throws IOException;

    /**
     * 根据数据源表id查询出可用的表
     *
     * @param id
     * @return
     */
    List<String> getTables(Long id,String tableSchema) throws IOException;

    /**
     * 获取CollectionNames
     * @param dbName
     * @return
     */
    List<String> getCollectionNames(long id,String dbName) throws IOException;

    /**
     * 根据数据源id，表名查询出该表所有字段
     *
     * @param id
     * @return
     */
    List<String> getColumns(Long id, String tableName) throws IOException;

    /**
     * 根据 sql 语句获取字段
     *
     * @param datasourceId
     * @param querySql
     * @return
     */
    List<String> getColumnsByQuerySql(Long datasourceId, String querySql) throws SQLException;

    /**
     * 获取PG table schema
     * @param id
     * @return
     */
    List<String> getTableSchema(Long id);

     /**
      * @author: bahsk
      * @date: 2021-10-27 10:58
      * @description: [项目定制]根据数据源id和表名获取所有字段明细
      * @params:
      * @return:
      */
     List<ColumnDetailsRespDTO> getColumnsDetails(Long datasourceId, String tableName) throws IOException;

      /**
       * @author: bahsk
       * @date: 2021-12-08 16:49
       * @description:
       * @params:
       * @return:
       */
      List<TableDetailsResp> getDdlSQL(String tableName, Long datasourceId);


    /**
     * @date:2022-08-16 15:28
     * @description: [项目定制]根据数据源id和表名获取数据量
     * @params:
     * @return:
     */
    TableCountResp getTableCount(String tableName, Long datasourceId);


    /**
     * @date:2022-08-16 15:28
     * @description: [项目定制]根据数据源id和表名获取数据量
     * @params:
     * @return:
     */
    List<TableCountResp> getTableCounts(List<String> tableList, List<Integer> datasourceList);



    /**
     * @date:2022-08-22
     * @description: [项目定制]根据不同数据源id与表名  对比数据类型与长度
     * @param sourceDatasourceId
     * @param targetDatasourceId
     * @param
     * @return  List<ColumnDetailsDiffRespDTO>
     * @throws IOException
     */
    List<ColumnDetailsDiffRespDTO> getColumnsDiffDetails(Long sourceDatasourceId,Long targetDatasourceId,List<String> tableNameList) throws IOException;

}
