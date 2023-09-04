package com.wugui.datax.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.dto.*;
import com.wugui.datax.admin.service.DatasourceQueryService;
import com.wugui.datax.admin.service.TableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询数据库表名，字段的控制器
 *
 * @author jingwk
 * @ClassName MetadataController
 * @Version 2.1.2
 * @since 2020/05/31 20:48
 */
@RestController
@RequestMapping("api/metadata")
@Api(tags = "jdbc数据库查询控制器")
public class MetadataController extends BaseController {

    @Autowired
    private DatasourceQueryService datasourceQueryService;

    @Resource
    private TableService tableService;

    /**
     * 根据数据源id获取mongo库名
     *
     * @param datasourceId
     * @return
     */
    @GetMapping("/getDBs")
    @ApiOperation("根据数据源id获取mongo库名")
    public R<List<String>> getDBs(Long datasourceId) throws IOException {
        return success(datasourceQueryService.getDBs(datasourceId));
    }


    /**
     * 根据数据源id,dbname获取CollectionNames
     *
     * @param datasourceId
     * @return
     */
    @GetMapping("/collectionNames")
    @ApiOperation("根据数据源id,dbname获取CollectionNames")
    public R<List<String>> getCollectionNames(Long datasourceId,String dbName) throws IOException {
        return success(datasourceQueryService.getCollectionNames(datasourceId,dbName));
    }

    /**
     * 获取PG table schema
     *
     * @param datasourceId
     * @return
     */
    @GetMapping("/getDBSchema")
    @ApiOperation("根据数据源id获取 db schema")
    public R<List<String>> getTableSchema(Long datasourceId) {
        return success(datasourceQueryService.getTableSchema(datasourceId));
    }

    /**
     * 根据数据源id获取可用表名
     *
     * @param datasourceId
     * @return
     */
    @GetMapping("/getTables")
    @ApiOperation("根据数据源id获取可用表名")
    public R<List<String>> getTableNames(Long datasourceId,String tableSchema) throws IOException {
        return success(datasourceQueryService.getTables(datasourceId,tableSchema));

    }

    /**
     * 根据数据源id和表名获取所有字段
     *
     * @param datasourceId 数据源id
     * @param tableName    表名
     * @return
     */
    @GetMapping("/getColumns")
    @ApiOperation("根据数据源id和表名获取所有字段")
    public R<List<String>> getColumns(Long datasourceId, String tableName) throws IOException {
        return success(datasourceQueryService.getColumns(datasourceId, tableName));
    }

     /**
      * @author: bahsk
      * @date: 2021-10-27 10:55
      * @description: 项目定制 获取数据源对应的表的所有字段以及字段类型长度等
      * @params:
      * @return:
      */
     @GetMapping("/getColumnsDetails")
     @ApiOperation("[项目定制]根据数据源id和表名获取所有字段明细")
     public R<List<ColumnDetailsRespDTO>> getColumnsDetails(Long datasourceId, String tableName) throws IOException {
         return success(datasourceQueryService.getColumnsDetails(datasourceId, tableName));
     }

     @GetMapping("/getDdlSQL")
     @ApiOperation("[项目定制]获取建表sql")
     public R<List<TableDetailsResp>> getDdlSQL(Long datasourceId, String tableName) {
        return success(datasourceQueryService.getDdlSQL(tableName,datasourceId));
     }



    /**
     * 根据数据源id和sql语句获取所有字段
     *
     * @param datasourceId 数据源id
     * @param querySql     表名
     * @return
     */
    @GetMapping("/getColumnsByQuerySql")
    @ApiOperation("根据数据源id和sql语句获取所有字段")
    public R<List<String>> getColumnsByQuerySql(Long datasourceId, String querySql) throws SQLException {
        return success(datasourceQueryService.getColumnsByQuerySql(datasourceId, querySql));
    }


    /**
     * 根据数据源id和表名获取数据量
     */
    @PostMapping ("/getTableCount")
    @ApiOperation("[项目定制]根据数据源id和表名获取数据量")
    public R<List<TableCountResp>> getTableCount(@RequestBody DatasourceTableDTO dto){
        return success(datasourceQueryService.getTableCounts(dto.getTableList(), dto.getDatasourceList()));
    }


    /**
     * 根据不同数据源id和表名获取字段区别
     * @param sourceDatasourceId 来源数据id
     * @param targetDatasourceId 目标数据id
     * @param
     * @return
     * @throws IOException
     */
    @GetMapping("/getTableDiff")
    @ApiOperation("[项目定制]根据不同数据源id和表名获取字段区别")
    public R<List<ColumnDetailsDiffRespDTO>> getTableDiff(@RequestParam(value = "tableNameList",required=false) List<String> tableNameList, Long sourceDatasourceId, Long targetDatasourceId) throws IOException {
        return success(datasourceQueryService.getColumnsDiffDetails(sourceDatasourceId, targetDatasourceId,tableNameList));
    }


//}


    @PostMapping("/createTable")
    @ApiOperation("[项目定制]单表创建")
    public R<Boolean> createtTable(@RequestBody DatasourceTableBO datasourceTableBO) throws IOException {
        //  return success(datasourceQueryService.getColumnsDiffDetails(sourceDatasourceId, targetDatasourceId,tableNameList));
        return success(tableService.create(datasourceTableBO));

    }


    //TODO 批量自动建表
    @PostMapping("/createTables")
    @ApiOperation("[项目定制]指定表创建")
    public R<Boolean> createtTables(@RequestBody DatasourceTablesBO tablesBO) throws IOException {
        //  return success(datasourceQueryService.getColumnsDiffDetails(sourceDatasourceId, targetDatasourceId,tableNameList));
            return success(tableService.createMulti(tablesBO));
    }


    @PostMapping("/createTables/multiTarget")
    @ApiOperation("[项目定制]指定表创建")
    public R<Boolean> createtTables(@RequestBody MultiTargetBO tablesBO) throws IOException {
        //  return success(datasourceQueryService.getColumnsDiffDetails(sourceDatasourceId, targetDatasourceId,tableNameList));
        return success(tableService.createMultiTargetTables(tablesBO));
    }

    @GetMapping("/createAllTables")
    @ApiOperation("[项目定制]全库建表")
    public R<Integer> createtAllTables(@RequestParam(value = "sourceId") String sourceId,@RequestParam(value = "targetId") String targetId) throws IOException {
        return R.ok(tableService.createAllTables(sourceId, targetId));
    }


    //TODO autoCreateTable 获取columns接口
    // 1.reader 数据源,表, writer数据源, 需要构建的字段列表, 是否自动构建
    @GetMapping("/getCustomColumns")
    @ApiOperation("[项目定制]自动构建表对应获取columns接口")
    public R<List<String>> getCustomColumns(Long datasourceId, String tableName,List<String> columns) throws IOException {
       //TODO 如果是自动构建则查询reader对应的字段/指定字段列表,如果非自动构建则
        return success(datasourceQueryService.getColumns(datasourceId, tableName));
    }



    @PostMapping("/syncDBObjects")
    @ApiOperation("[项目定制]同步数据库对象(函数,存储等)")
    public R<Boolean> syncDBObjects(@RequestBody DBObjectDTO dto)  {
        return success(datasourceQueryService.syncDBObjects(dto));
    }



}
