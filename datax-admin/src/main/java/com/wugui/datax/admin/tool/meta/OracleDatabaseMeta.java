package com.wugui.datax.admin.tool.meta;
/**
 * Oracle数据库 meta信息查询
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName MySQLDatabaseMeta
 * @Version 1.0
 * @since 2019/7/17 15:48
 */
public class OracleDatabaseMeta extends BaseDatabaseMeta implements DatabaseInterface {

    private volatile static OracleDatabaseMeta single;

    public static OracleDatabaseMeta getInstance() {
        if (single == null) {
            synchronized (OracleDatabaseMeta.class) {
                if (single == null) {
                    single = new OracleDatabaseMeta();
                }
            }
        }
        return single;
    }


    @Override
    public String getSQLQueryComment(String schemaName, String tableName, String columnName) {
        return String.format("select B.comments \n" +
                "  from user_tab_columns A, user_col_comments B\n" +
                " where a.COLUMN_NAME = b.column_name\n" +
                "   and A.Table_Name = B.Table_Name\n" +
                "   and A.Table_Name = upper('%s')\n" +
                "   AND A.column_name  = '%s'", tableName, columnName);
    }



    @Override
    public String getSQLQueryPrimaryKey() {
        return "select cu.column_name from user_cons_columns cu, user_constraints au where cu.constraint_name = au.constraint_name and au.owner = ? and au.constraint_type = 'P' and au.table_name = ?";
    }

    @Override
    public String getSQLQueryTablesNameComments() {
        return "select table_name,comments from user_tab_comments";
    }

    @Override
    public String getSQLQueryTableNameComment() {
        return "select a. table_name, b.comments from all_tables a left join all_tab_comments b " +
                "on a.TABLE_NAME = b.TABLE_NAME where a.table_name = ? ";
    }

    @Override
    public String getSQLQueryTables(String... tableSchema) {
        //return "select OBJECT_NAME as table_name from all_objects where owner='" + tableSchema[0] + "' and OBJECT_TYPE in ('TABLE','VIEW')";
        return "select OBJECT_NAME as table_name from all_objects where owner='" + tableSchema[0] + "' and OBJECT_TYPE in ('TABLE')";
    }

    @Override
    public String getSQLQueryTableSchema(String... args) {
        return "select username from sys.dba_users";
    }


    @Override
    public String getSQLQueryTables() {
        return "select table_name from user_tables ";
    }

    @Override
    public String getSQLQueryColumns(String... args) {
        return "select table_name,comments from user_tab_comments where table_name = ?";
    }

     /**
      * @author: bahsk
      * @date: 2021-12-08 11:12
      * @description: 获取建表语句
      * @params:
      * @return:
      */
     @Override
     public String getDdlSQL(String... args) {
         return "SELECT u.object_name,DBMS_METADATA.GET_DDL(U.OBJECT_TYPE, u.object_name,u.OWNER) FROM All_OBJECTS u where owner='" + args[0] + "' and u.object_name ='" + args[1] + "'";
     }


     @Override
     public String getMultiDdlSQL(String... args) {
         return "SELECT u.object_name,DBMS_METADATA.GET_DDL(U.OBJECT_TYPE, u.object_name,u.OWNER) FROM All_OBJECTS u where owner='" + args[0] + "' and u.object_name in (" + args[1] + ")";
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
     * 获取oracle  alter Modify语句
     * @param args
     * @return
     */
    @Override
    public String getAlterModify(String... args){
        //TODO 获取oracle Modify语句
        return "alter table " + args[0] + " modify " + args[1]+ " " + args[2] + "(" + args[3] + ");";
    }
    /**
     * 获取oracle  alter add语句
     * @param args
     * @return
     */
    @Override
    public String getAlterAdd(String... args){
        //TODO 获取oracle add语句
        return "alter table " + args[0] + " add " + args[1]+ " " + args[2] + "(" + args[3] + ");";
    }


    @Override
    public String getCharacterSet() {
        return "select userenv('language') from dual";
    }
}
