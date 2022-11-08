package com.wugui.datax.admin.tool.meta;

/**
 * MySQL数据库 meta信息查询
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName MySQLDatabaseMeta
 * @Version 1.0
 * @since 2019/7/17 15:48
 */
public class MySQLDatabaseMeta extends BaseDatabaseMeta implements DatabaseInterface {

    private volatile static MySQLDatabaseMeta single;

    public static MySQLDatabaseMeta getInstance() {
        if (single == null) {
            synchronized (MySQLDatabaseMeta.class) {
                if (single == null) {
                    single = new MySQLDatabaseMeta();
                }
            }
        }
        return single;
    }

    @Override
    public String getSQLQueryComment(String schemaName, String tableName, String columnName) {
        return String.format("SELECT COLUMN_COMMENT FROM information_schema.COLUMNS where TABLE_SCHEMA = '%s' and TABLE_NAME = '%s' and COLUMN_NAME = '%s'", schemaName, tableName, columnName);
    }

    /**
     * 获取当前表建表sql
     *
     * @param
     * @param
     * @return
     */
    @Override
    public String getDdlSQL(String ...args) {
        return "SHOW CREATE TABLE " + args[0] + "." + args[1];
    }


    @Override
    public String getSQLQueryPrimaryKey() {
        return "select column_name from information_schema.columns where table_schema=? and table_name=? and column_key = 'PRI'";
    }

    @Override
    public String getSQLQueryTables() {
        return "show tables";
    }

    @Override
    public String getSQLQueryColumns(String... args) {
        return "select column_name from information_schema.columns where table_schema=? and table_name=?";
    }

    /**
     *
     * @param args 用户名，需要查找数据记录数的表名
     *
     * @return 数据表名，表的记录数
     */
    @Override
    public String getTableCount(String... args){
        return "select '" + args[1] + "' as table_name,count(1) table_counts from " + args[0] + "." + args[1];
    }


    /**
     * 获取mysql  alter Modify语句
     * @param args
     * @return
     */
    @Override
    public String getAlterModify(String... args){
        // 获取mysql Modify语句
        return "alter table " + args[0] + " modify " + args[1]+ " " + args[2] + "(" + args[3] + ");";
    }

    /**
     * 获取mysql  alter add语句
     * @param args
     * @return
     */
    @Override
    public String getAlterAdd(String... args){
        // 获取mysql Add语句
        return "alter table " + args[0] + " add " + args[1]+ " " + args[2] + "(" + args[3] + ");";
    }
}
