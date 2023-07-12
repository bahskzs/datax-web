package com.wugui.datax.admin.tool.meta;

import com.wugui.datax.admin.tool.database.ColumnInfoV2;
import com.wugui.datax.admin.tool.database.ColumnType;
import com.wugui.datax.admin.tool.database.TableInfoV2;

import java.util.Arrays;
import java.util.List;

/**
 * SqlServer数据库 meta信息查询
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName SqlServerDatabaseMeta
 * @Version 1.0
 * @since 2019/8/2 15:45
 */
public class SqlServerDatabaseMeta extends BaseDatabaseMeta implements DatabaseInterface {
    private volatile static SqlServerDatabaseMeta single;

    public static SqlServerDatabaseMeta getInstance() {
        if (single == null) {
            synchronized (SqlServerDatabaseMeta.class) {
                if (single == null) {
                    single = new SqlServerDatabaseMeta();
                }
            }
        }
        return single;
    }

    @Override
    public String getSQLQueryTables() {

        //return "select name from sysobjects where xtype='U' ORDER BY name";
        return "select name from sysobjects where xtype in ('V','U')";
    }

    @Override
    public String getSQLQueryTables(String... tableSchema) {
        return "select schema_name(schema_id)+'.'+object_name(object_id) from sys.objects \n" +
                "where type ='U' \n" +
                "and schema_name(schema_id) ='" + tableSchema[0] + "'";

    }

    @Override
    public String getSQLQueryTableSchema(String... args) {
        //return "select distinct schema_name(schema_id) from sys.objects where type ='U';";
        return "SELECT DISTINCT [schema_name] = user_name(uid) \n" +
                "FROM sysobjects \n" +
                "WHERE xtype IN ('U', 'V', 'P', 'FN', 'IF', 'TF')";
    }


    /**
     * 获取表定义
     * @param tableInfoV2 表信息
     * @return 表定义
     */
    @Override
    public List<String> createTable(TableInfoV2 tableInfoV2) {
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
            if (i < columns.size() - 1) {
                builder.append(",");
            }
        }

        // 追加表注释
        builder.append(")");

        // TODO sql server 暂不支持注解
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
