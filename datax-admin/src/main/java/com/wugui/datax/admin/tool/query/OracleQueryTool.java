package com.wugui.datax.admin.tool.query;

import com.google.common.collect.Lists;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.tool.database.ColumnInfo;
import com.wugui.datax.admin.tool.database.TableInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Oracle数据库使用的查询工具
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName MySQLQueryTool
 * @Version 1.0
 * @since 2019/7/18 9:31
 */
public class OracleQueryTool extends BaseQueryTool implements QueryToolInterface {

    public OracleQueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }

    /**
     * 构建建表语句
     *
     * @param tableInfo table 注释及字段信息
     * @return
     */
    @Override
    public List<String> buildCreateTableSql(TableInfo tableInfo) {

        ArrayList<String> sqlList = Lists.newArrayList();

        String name = tableInfo.getName();
        String comment = tableInfo.getComment();
        List<ColumnInfo> columns = tableInfo.getColumns();


        StringBuffer createTable = new StringBuffer();
        StringBuffer tableComment = new StringBuffer();

        if(name.contains(".")) {
            createTable.append("create table ")

                    .append(name)
                    .append(" ( \n");

        } else {
            createTable.append("create table ")
                    .append("\"")
                    .append(name)
                    .append("\" ( \n");
        }
        // 列
        ArrayList<String> columnCommentList = Lists.newArrayList();
        for (int i = 0; i < columns.size(); i++) {
            ColumnInfo column = columns.get(i);
            //建表语句
            createTable.append("\"")
                    .append(column.getName())
                    .append("\" ")
                    .append(column.getType());
            if (i < columns.size() - 1) {
                createTable.append(",");
            }

            // 字段注释
            StringBuffer columnComment = new StringBuffer();
            if (column.getComment() != null) {
                columnComment.append("comment on column  ")
                        .append(name)
                        .append(".")
                        .append("\"").append(column.getName()).append("\" ")
                        .append(" is ").append("'").append(column.getComment()).append("'");
                columnCommentList.add(columnComment.toString());
            }


        }
        createTable.append(")");





        //组装待执行的sql
        sqlList.add(createTable.toString());
        if (comment != null || comment != "null") {
            // 表注释部分
            tableComment.append("comment on table ")
                    .append(name)
                    .append(" is ").append("'").append(comment).append("'");
            sqlList.add(tableComment.toString());
        }

        sqlList.addAll(columnCommentList);
        return sqlList;
    }

    //TODO 自动构建注释

}
