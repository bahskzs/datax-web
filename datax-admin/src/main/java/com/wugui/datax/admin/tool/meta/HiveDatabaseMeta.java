package com.wugui.datax.admin.tool.meta;

/**
 * hive元数据信息
 *
 * @author jingwk
 * @ClassName HiveDatabaseMeta
 * @Version 2.0
 * @since 2020/01/05 15:45
 */
public class HiveDatabaseMeta extends BaseDatabaseMeta implements DatabaseInterface {
    private volatile static HiveDatabaseMeta single;

    public static HiveDatabaseMeta getInstance() {
        if (single == null) {
            synchronized (HiveDatabaseMeta.class) {
                if (single == null) {
                    single = new HiveDatabaseMeta();
                }
            }
        }
        return single;
    }

    @Override
    public String getSQLQueryTables() {
        return "show tables";
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


//    @Override
//    public String getSQLQueryFields(String tableName) {
//        // return "show columns from " + tableName ;
//    }
}
