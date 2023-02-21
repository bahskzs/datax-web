package com.wugui.datax.admin.tool.meta;

import com.wugui.datax.admin.tool.database.TableInfo;

/**
 * meta信息interface
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName BaseDatabaseMeta
 * @Version 1.0
 * @since 2019/7/17 15:45
 */
public abstract class BaseDatabaseMeta implements DatabaseInterface {

    @Override
    public String getSQLQueryFields(String tableName) {
        return "SELECT * FROM " + tableName + " where 1=0";
    }

    public String getSQLQueryFields(String tableName,String userName) {
        return "SELECT * FROM " + userName + "." + tableName + " where 1=0";
    }

    @Override
    public String getSQLQueryTablesNameComments() {
        return "select table_name,table_comment from information_schema.tables where table_schema=?";
    }

    @Override
    public String getSQLQueryTableNameComment() {
        return "select table_name,table_comment from information_schema.tables where table_schema=? and table_name = ?";
    }

    @Override
    public String getSQLQueryPrimaryKey() {
        return null;
    }

    @Override
    public String getSQLQueryComment(String schemaName, String tableName, String columnName) {
        return null;
    }

    @Override
    public String getSQLQueryColumns(String... args) {
        return null;
    }

    @Override
    public String getDdlSQL(String... args) {
        return null ;
    }

    @Override
    public String getMaxId(String tableName, String primaryKey) {
        return String.format("select max(%s) from %s",primaryKey,tableName);
    }

    @Override
    public String getSQLQueryTableSchema(String... args) {
        return null;
    }

    @Override
    public String getSQLQueryTables() {
        return null;
    }

    @Override
    public String getSQLQueryTables(String... tableSchema) {
        return null;
    }

    /**
     * 获取当前表建表sql
     *
     * @param user
     * @param tableName
     * @return
     */
    public String getDdlSQL(String user, String tableName) {
        return null;
    }

    /**
     *
     * @param args
     *
     * @return
     */
    @Override
    public String getMultiDdlSQL(String... args) {
        return null;
    }

    /**
     *
     * @param args 用户名，数据库表名
     *
     * @return 查询表的记录数
     */
    @Override
    public String getTableCount(String... args){
        return String.format("select count(1) nums from %s",args[0]);
    }

    /**
     * 获取alter语句
     * @param args
     * @return
     */
    @Override
    public String getAlterModify(String... args){
        return null;
    }

    /**
     * 获取alter语句
     * @param args
     * @return
     */
    @Override
    public String getAlterAdd(String... args){
        return null;
    }

    @Override
    public boolean createTable(String tableName, TableInfo tableInfo) {
        return false;
    }


    @Override
    public String getCharacterSet() {
        return null;
    }
}
