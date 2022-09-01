package com.wugui.datax.admin.tool.meta;

public interface DatabaseInterface {

    /**
     * Returns the minimal SQL to launch in order to determine the layout of the resultset for a given com.com.wugui.datax.admin.tool.database table
     *
     * @param tableName The name of the table to determine the layout for
     * @return The SQL to launch.
     */
    String getSQLQueryFields(String tableName);

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

}
