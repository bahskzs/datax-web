package com.wugui.datax.admin.tool.meta;

import com.wugui.datax.admin.tool.database.ColumnInfoV2;
import com.wugui.datax.admin.tool.database.ColumnType;
import com.wugui.datax.admin.tool.database.TableInfoV2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * 获取表定义
     * @param tableInfoV2 表信息
     * @return 表定义
     */
    @Override
    public List<String> createTable(TableInfoV2 tableInfoV2) {


        // 拼凑建表语句
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ");
        builder.append(tableInfoV2.getTableName());
        builder.append(" (");

        // 拼凑字段
        List<ColumnInfoV2> columns = tableInfoV2.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            ColumnInfoV2 columnInfo = columns.get(i);
            String columnDefinition = getColumnDefinition(columnInfo);
            builder.append(columnDefinition);
            // 追加注释
            builder.append(" COMMENT '");
            builder.append(columnInfo.getColumnComment());
            builder.append("'");
            if (i < columns.size() - 1) {
                builder.append(",");
            }
        }

        // 追加表注释
        builder.append(")");
        builder.append(" COMMENT = '");
        builder.append(tableInfoV2.getComment());
        builder.append("'");

        return Arrays.asList(builder.toString());
    }

    /**
     * 获取字段定义
     * @param columnInfo 字段信息
     * @return 字段定义
     */
    @Override
    public  String getColumnDefinition(ColumnInfoV2 columnInfo) {
        String columnName = columnInfo.getColumnName();
        ColumnType columnType = columnInfo.getColumnType();
        String columnTypeName = columnType.getType().toUpperCase();
        int length = columnType.getLength();
        int precision = columnType.getPrecision();
        int scale = columnType.getScale();

        String columnDefinition = columnName + " ";

        switch (columnTypeName) {
            case "INT":
                columnDefinition += "INT";
                break;
            case "BIGINT":
                columnDefinition += "BIGINT";
                break;
            case "FLOAT":
                columnDefinition += "FLOAT";
                break;
            case "DOUBLE":
                columnDefinition += "DOUBLE";
                break;
            case "DECIMAL":
                if (precision > 0 && scale > 0) {
                    columnDefinition += "DECIMAL(" + precision + "," + scale + ")";
                } else if (precision > 0) {
                    columnDefinition += "DECIMAL(" + precision + ")";
                } else {
                    columnDefinition += "DECIMAL";
                }
                break;
            case "VARCHAR":
                columnDefinition += "VARCHAR(" + length + ")";
                break;
            case "TEXT":
                columnDefinition += "TEXT";
                break;
            case "DATE":
                columnDefinition += "DATE";
                break;
            case "DATETIME":
                columnDefinition += "DATETIME";
                break;
            case "TIMESTAMP":
                columnDefinition += "TIMESTAMP";
                break;
            default:
                // 如果是未知类型则直接返回列名
                columnDefinition += columnName;
                break;
        }

        return columnDefinition;
    }
}
