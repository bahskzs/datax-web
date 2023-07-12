package com.wugui.datax.admin.tool.meta;

import com.wugui.datax.admin.tool.database.ColumnInfo;
import com.wugui.datax.admin.tool.database.ColumnInfoV2;
import com.wugui.datax.admin.tool.database.TableInfo;
import com.wugui.datax.admin.tool.database.TableInfoV2;

import java.util.List;

public interface DatabaseInterface {

    /**
     * Returns the minimal SQL to launch in order to determine the layout of the resultset for a given com.com.wugui.datax.admin.tool.database table
     *
     * @param tableName The name of the table to determine the layout for
     * @return The SQL to launch.
     */
    String getSQLQueryFields(String tableName);

    String getSQLQueryFields(String tableName, String userName);

    /**
     * 获取主键字段
     *
     * @return
     */
    String getSQLQueryPrimaryKey();

    String getSQLQueryTableNameComment();

    String getSQLQueryTablesNameComments();

    /**
     * 获取所有表名的sql
     *
     * @return
     */
    String getSQLQueryTables(String... tableSchema);

    /**
     * 获取所有表名的sql
     *
     * @return
     */
    String getSQLQueryTables();

    /**
     * 获取 Table schema
     *
     * @return
     */
    String getSQLQueryTableSchema(String... args);
    /**
     * 获取所有的字段的sql
     *
     * @return
     */
    String getSQLQueryColumns(String... args);

    /**
     * 获取表和字段注释的sql语句
     *
     * @return The SQL to launch.
     */
    String getSQLQueryComment(String schemaName, String tableName, String columnName);


    /**
     * 获取当前表maxId
     * @param tableName
     * @param primaryKey
     * @return
     */
    String getMaxId(String tableName,String primaryKey);

    /**
     * 获取当前表建表sql
     * @param args
     *
     * @return
     */
    String getDdlSQL(String... args);

    /**
     * 获取当前表集合的建表sql
     * @param args
     *
     * @return
     */
    String getMultiDdlSQL(String... args);

    /**
     * 获取当前数据库表的记录数
     * @param args 用户名，需要查找数据记录数的表名
     *
     * @returns 查询指定数据库表的记录数
     */
    String getTableCount(String... args);


    /**
     * 获取修改字段Modify
     * @param args
     * @return
     */
    String getAlterModify(String... args);

    /**
     * 获取添加字段add语句
     * @param args
     * @return
     */
    String getAlterAdd(String... args);


    /**
     *  根据表信息构建建表语句
     * @param tableName
     * @param tableInfo
     * @return 是否完成建表
     */
    boolean createTable(String tableName, TableInfo tableInfo);


    /**
     *
     * @return 获取数据库字符集
     */
    String getCharacterSet();

    //根据tableName,tableComment建表
    String createTable(String tableName,List<ColumnInfo> columnInfoList,String comment);

    // 根据TableInfoV2构建建表语句
    List<String> createTable(TableInfoV2 tableInfoV2);

    String getColumnDefinition(ColumnInfoV2 columnInfo);


    // 根据ColumnInfoV2添加字段
    String addColumn(String tableName, ColumnInfoV2 columnInfo);
}
