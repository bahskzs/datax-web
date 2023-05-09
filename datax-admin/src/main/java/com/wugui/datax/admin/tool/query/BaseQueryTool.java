package com.wugui.datax.admin.tool.query;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wugui.datatx.core.util.Constants;
import com.wugui.datax.admin.core.util.LocalCacheUtil;
import com.wugui.datax.admin.dto.ColumnDetailsDiffRespDTO;
import com.wugui.datax.admin.dto.ColumnDetailsRespDTO;
import com.wugui.datax.admin.dto.TableCountResp;
import com.wugui.datax.admin.dto.TableDetailsResp;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.tool.database.*;
import com.wugui.datax.admin.tool.meta.DatabaseInterface;
import com.wugui.datax.admin.tool.meta.DatabaseMetaFactory;
import com.wugui.datax.admin.util.AESUtil;
import com.wugui.datax.admin.util.JdbcConstants;
import com.wugui.datax.admin.util.JdbcUtils;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * 抽象查询工具
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName BaseQueryTool
 * @Version 1.0
 * @since 2019/7/18 9:22
 */
public abstract class BaseQueryTool implements QueryToolInterface {

    protected static final Logger logger = LoggerFactory.getLogger(BaseQueryTool.class);
    /**
     * 用于获取查询语句
     */
    private DatabaseInterface sqlBuilder;

    private DataSource datasource;

    private Connection connection;
    /**
     * 当前数据库名
     */
    private String currentSchema;
    private String currentDatabase;

    /**
     * 构造方法
     *
     * @param jobDatasource
     */
    BaseQueryTool(JobDatasource jobDatasource) throws SQLException {
        if (LocalCacheUtil.get(jobDatasource.getDatasourceName()) == null) {
            getDataSource(jobDatasource);
        } else {
            this.connection = (Connection) LocalCacheUtil.get(jobDatasource.getDatasourceName());
            if (!this.connection.isValid(3000)) {
                LocalCacheUtil.remove(jobDatasource.getDatasourceName());
                getDataSource(jobDatasource);
            }
        }
        sqlBuilder = DatabaseMetaFactory.getByDbType(jobDatasource.getDatasource());
        currentSchema = getSchema(jobDatasource.getJdbcUsername());
        currentDatabase = jobDatasource.getDatasource();
        LocalCacheUtil.set(jobDatasource.getDatasourceName(), this.connection, 4 * 60 * 60 * 1000);
    }

    private void getDataSource(JobDatasource jobDatasource) throws SQLException {
        String userName = AESUtil.decrypt(jobDatasource.getJdbcUsername());

        //这里默认使用 hikari 数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(userName);
        dataSource.setPassword(AESUtil.decrypt(jobDatasource.getJdbcPassword()));
        dataSource.setJdbcUrl(jobDatasource.getJdbcUrl());
        dataSource.setDriverClassName(jobDatasource.getJdbcDriverClass());
        dataSource.setMaximumPoolSize(1);
        dataSource.setMinimumIdle(0);
        dataSource.setConnectionTimeout(3000);
        this.datasource = dataSource;
        this.connection = this.datasource.getConnection();
    }

    //根据connection获取schema
    private String getSchema(String jdbcUsername) {
        String res = null;
        try {
            res = connection.getCatalog();
        } catch (SQLException e) {
            try {
                res = connection.getSchema();
            } catch (SQLException e1) {
                logger.error("[SQLException getSchema Exception] --> "
                        + "the exception message is:" + e1.getMessage());
            }
            logger.error("[getSchema Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        // 如果res是null，则将用户名当作 schema
        if (StrUtil.isBlank(res) && StringUtils.isNotBlank(jdbcUsername)) {
            res = jdbcUsername.toUpperCase();
        }
        return res;
    }

    @Override
    public TableInfo buildTableInfo(String tableName) {
        //获取表信息
        List<Map<String, Object>> tableInfos = this.getTableInfo(tableName);
        if (tableInfos.isEmpty()) {
            throw new NullPointerException("查询出错! 问题表: " + tableName);
        }

        TableInfo tableInfo = new TableInfo();
        //表名，注释
        List tValues = new ArrayList(tableInfos.get(0).values());

        tableInfo.setName(StrUtil.toString(tValues.get(0)));
        tableInfo.setComment(StrUtil.toString(tValues.get(1)));


        //获取所有字段
        List<ColumnInfo> fullColumn = getColumns(tableName);
        tableInfo.setColumns(fullColumn);

        //获取主键列
        List<String> primaryKeys = getPrimaryKeys(tableName);
        logger.info("主键列为：{}", primaryKeys);

        //设置ifPrimaryKey标志
        fullColumn.forEach(e -> {
            if (primaryKeys.contains(e.getName())) {
                e.setIfPrimaryKey(true);
            } else {
                e.setIfPrimaryKey(false);
            }
        });
        return tableInfo;
    }


    /**
     * 构建表信息
     *
     * @param tableName
     * @param userName
     * @return
     */
    public TableInfo buildTableInfo(String tableName, String userName, Boolean isSamedCharaSet) {
        //获取表信息
        List<Map<String, Object>> tableInfos = this.getTableInfo(tableName);
        if (tableInfos.isEmpty()) {
            throw new NullPointerException("查询出错! 问题表: " + tableName);
        }

        TableInfo tableInfo = new TableInfo();
        //表名，注释
        List tValues = new ArrayList(tableInfos.get(0).values());

        tableInfo.setName(StrUtil.toString(tValues.get(0)));
        tableInfo.setComment(StrUtil.toString(tValues.get(1)));


        //TODO 获取所有字段 目前还有问题
        List<ColumnInfo> fullColumn = getColumns(tableName, userName, isSamedCharaSet);
        tableInfo.setColumns(fullColumn);

        //获取主键列
        List<String> primaryKeys = getPrimaryKeys(tableName);
        logger.info("主键列为：{}", primaryKeys);

        //设置ifPrimaryKey标志
        fullColumn.forEach(e -> {
            if (primaryKeys.contains(e.getName())) {
                e.setIfPrimaryKey(true);
            } else {
                e.setIfPrimaryKey(false);
            }
        });
        return tableInfo;
    }





    //无论怎么查，返回结果都应该只有表名和表注释，遍历map拿value值即可
    @Override
    public List<Map<String, Object>> getTableInfo(String tableName) {
        String sqlQueryTableNameComment = sqlBuilder.getSQLQueryTableNameComment();
        //logger.info(sqlQueryTableNameComment);
        List<Map<String, Object>> res = null;
        try {
            res = JdbcUtils.executeQuery(connection, sqlQueryTableNameComment, ImmutableList.of(tableName));
        } catch (SQLException e) {
            logger.error("[getTableInfo Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    @Override
    public List<Map<String, Object>> getTables() {
        String sqlQueryTables = sqlBuilder.getSQLQueryTables();
        //logger.info(sqlQueryTables);
        List<Map<String, Object>> res = null;
        try {
            res = JdbcUtils.executeQuery(connection, sqlQueryTables, ImmutableList.of(currentSchema));
        } catch (SQLException e) {
            logger.error("[getTables Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    @Override
    public List<ColumnInfo> getColumns(String tableName) {

        List<ColumnInfo> fullColumn = Lists.newArrayList();
        //获取指定表的所有字段
        try {

            //获取查询指定表所有字段的sql语句
            String querySql = sqlBuilder.getSQLQueryFields(tableName);
            //logger.info("querySql: {}", querySql);

            //获取所有字段
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querySql);
            ResultSetMetaData metaData = resultSet.getMetaData();

            List<DasColumn> dasColumns = buildDasColumn(tableName, metaData);
            statement.close();

            //构建 fullColumn
            fullColumn = buildFullColumn(dasColumns);

        } catch (SQLException e) {
            logger.error("[getColumns Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return fullColumn;
    }


    /**
     * 根据用户名以及表名获取对应字段信息
     *
     * @param tableName
     * @param userName
     * @return
     */
    public List<ColumnInfo> getColumns(String tableName, String userName, Boolean isSameCharaSet) {

        List<ColumnInfo> fullColumn = Lists.newArrayList();
        //获取指定表的所有字段
        try {

            //获取查询指定表所有字段的sql语句
            String querySql = sqlBuilder.getSQLQueryFields(tableName, userName);
            logger.info("querySql: {}", querySql);

            //获取所有字段
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querySql);
            ResultSetMetaData metaData = resultSet.getMetaData();

            List<DasColumn> dasColumns = buildCusColumn(tableName, metaData, isSameCharaSet);
            statement.close();

            //构建 fullColumn
            fullColumn = buildFullColumn(dasColumns);

        } catch (SQLException e) {
            logger.error("[getColumns Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return fullColumn;
    }


    /**
     * 返回字段名称,字段信息用于字段比较
     *
     * @param tableName
     * @return
     */
    public Map<String, ColumnInfo> getColumnMap(String tableName, String userName, Boolean isSamedCharaSet) {

        Map<String, ColumnInfo> res = Maps.newHashMap();
        //获取指定表的所有字段
        try {
            //获取查询指定表所有字段的sql语句
            String querySql = sqlBuilder.getSQLQueryFields(tableName, userName);
            logger.info("querySql: {}", querySql);

            //获取所有字段
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querySql);
            ResultSetMetaData metaData = resultSet.getMetaData();

            List<DasColumn> dasColumns = buildCusColumn(tableName, metaData, isSamedCharaSet);
            statement.close();

            //构建 fullColumn
            res = buildColumnMap(dasColumns);

        } catch (SQLException e) {
            logger.error("[getColumns Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    private List<ColumnInfo> buildFullColumn(List<DasColumn> dasColumns) {
        List<ColumnInfo> res = Lists.newArrayList();
        dasColumns.forEach(e -> {
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setName(e.getColumnName());
            columnInfo.setComment(e.getColumnComment());
            columnInfo.setType(e.getColumnTypeName());
            columnInfo.setIfPrimaryKey(e.isIsprimaryKey());
            columnInfo.setIsnull(e.getIsNull());
            res.add(columnInfo);
        });
        return res;
    }

    private Map<String, ColumnInfo> buildColumnMap(List<DasColumn> dasColumns) {
        Map<String, ColumnInfo> res = Maps.newHashMap();
        dasColumns.forEach(e -> {
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setName(e.getColumnName());
            columnInfo.setComment(e.getColumnComment());
            columnInfo.setType(e.getColumnTypeName());
            columnInfo.setIfPrimaryKey(e.isIsprimaryKey());
            columnInfo.setIsnull(e.getIsNull());
            res.put(e.getColumnName(), columnInfo);
        });
        return res;
    }

    //构建DasColumn对象
    private List<DasColumn> buildDasColumn(String tableName, ResultSetMetaData metaData) {
        List<DasColumn> res = Lists.newArrayList();
        try {
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                DasColumn dasColumn = new DasColumn();
                dasColumn.setColumnClassName(metaData.getColumnClassName(i));
                dasColumn.setColumnTypeName(metaData.getColumnTypeName(i));
                dasColumn.setColumnName(metaData.getColumnName(i));
                dasColumn.setIsNull(metaData.isNullable(i));

                res.add(dasColumn);
            }

            Statement statement = connection.createStatement();

            if (currentDatabase.equals(JdbcConstants.MYSQL) || currentDatabase.equals(JdbcConstants.ORACLE)) {
                DatabaseMetaData databaseMetaData = connection.getMetaData();

                ResultSet resultSet = databaseMetaData.getPrimaryKeys(null, null, tableName);

                while (resultSet.next()) {
                    String name = resultSet.getString("COLUMN_NAME");
                    res.forEach(e -> {
                        if (e.getColumnName().equals(name)) {
                            e.setIsprimaryKey(true);

                        } else {
                            e.setIsprimaryKey(false);
                        }
                    });
                }

                res.forEach(e -> {
                    String sqlQueryComment = sqlBuilder.getSQLQueryComment(currentSchema, tableName, e.getColumnName());
                    //查询字段注释
                    try {
                        ResultSet resultSetComment = statement.executeQuery(sqlQueryComment);
                        while (resultSetComment.next()) {
                            e.setColumnComment(resultSetComment.getString(1));
                        }
                        JdbcUtils.close(resultSetComment);
                    } catch (SQLException e1) {
                        logger.error("[buildDasColumn executeQuery Exception] --> "
                                + "the exception message is:" + e1.getMessage());
                    }
                });
            }

            JdbcUtils.close(statement);
        } catch (SQLException e) {
            logger.error("[buildDasColumn Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }


    /**
     * 构建定制字段类型,含字段长度,精度等等
     *
     * @param tableName
     * @param metaData
     * @param isSamedCharaSet 是否相同字符集
     * @return
     */
    private List<DasColumn> buildCusColumn(String tableName, ResultSetMetaData metaData, Boolean isSamedCharaSet) {
        List<DasColumn> res = Lists.newArrayList();
        try {
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                DasColumn dasColumn = new DasColumn();
                dasColumn.setColumnClassName(metaData.getColumnClassName(i));
                dasColumn.setColumnTypeName(metaData.getColumnTypeName(i));
                dasColumn.setColumnName(metaData.getColumnName(i));
                dasColumn.setIsNull(metaData.isNullable(i));
                int scale = metaData.getScale(i);
                int dataLength = metaData.getColumnDisplaySize(i);
                String colType = String.valueOf(dataLength);

                // 如果精度为-127,则不需要拼接

                // scale = 0 字符串,日期等  > 0 数字 -127 数字且没有精度
                if (scale > 0) {
                    // 数字
                    colType = colType + "," + scale;
                    dasColumn.setColumnTypeName(metaData.getColumnTypeName(i) + "(" + colType + ")");
                } else if (scale == 0) {
                    //字符串
                    if (metaData.getColumnTypeName(i).contains("CHAR")) {
                        if (!isSamedCharaSet) {
                            // 字符集不一致状态下,防止字符串超长转CLOB问题
                            if (dataLength * 2 <= 3000) {
                                dataLength = dataLength * 2;
                            }
                        }
                        colType = String.valueOf(dataLength);
                        dasColumn.setColumnTypeName(metaData.getColumnTypeName(i) + "(" + colType + ")");
                    }
                    if (metaData.getColumnTypeName(i) == "CLOB"
                            || metaData.getColumnTypeName(i) == "BLOB"
                            || metaData.getColumnTypeName(i) == "DATE"
                            || metaData.getColumnTypeName(i) == "FLOAT"
                            || metaData.getColumnTypeName(i) == "DOUBLE"
                            || metaData.getColumnTypeName(i) == "INT"
                    ) {
                        dasColumn.setColumnTypeName(metaData.getColumnTypeName(i));
                    }
                    if (metaData.getColumnTypeName(i) == "NUMBER") {
                        dataLength = dataLength - 1;
                        colType = String.valueOf(dataLength);
                        dasColumn.setColumnTypeName(metaData.getColumnTypeName(i) + "(" + colType + ")");
                    }
                }
                // 39 : NUMBER
                if (scale == -127 && dataLength == 39) {
                    dasColumn.setColumnTypeName(metaData.getColumnTypeName(i));
                }
                res.add(dasColumn);
            }

            Statement statement = connection.createStatement();

            if (currentDatabase.equals(JdbcConstants.MYSQL) || currentDatabase.equals(JdbcConstants.ORACLE)) {
                DatabaseMetaData databaseMetaData = connection.getMetaData();

                ResultSet resultSet = databaseMetaData.getPrimaryKeys(null, null, tableName);

                while (resultSet.next()) {
                    String name = resultSet.getString("COLUMN_NAME");

                    res.forEach(e -> {

                        if (e.getColumnName().equals(name)) {
                            e.setIsprimaryKey(true);

                        } else {
                            e.setIsprimaryKey(false);
                        }
                    });
                }

                res.forEach(e -> {
                    String sqlQueryComment = sqlBuilder.getSQLQueryComment(currentSchema, tableName, e.getColumnName());
                    //查询字段注释
                    try {
                        ResultSet resultSetComment = statement.executeQuery(sqlQueryComment);
                        while (resultSetComment.next()) {
                            e.setColumnComment(resultSetComment.getString(1));
                        }
                        JdbcUtils.close(resultSetComment);
                    } catch (SQLException e1) {
                        logger.error("[buildDasColumn executeQuery Exception] --> "
                                + "the exception message is:" + e1.getMessage());
                    }
                });
            }

            JdbcUtils.close(statement);
        } catch (SQLException e) {
            logger.error("[buildDasColumn Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    //获取指定表的主键，可能是多个，所以用list
    private List<String> getPrimaryKeys(String tableName) {
        List<String> res = Lists.newArrayList();
        String sqlQueryPrimaryKey = sqlBuilder.getSQLQueryPrimaryKey();
        try {
            List<Map<String, Object>> pkColumns = JdbcUtils.executeQuery(connection, sqlQueryPrimaryKey, ImmutableList.of(currentSchema, tableName));
            //返回主键名称即可
            pkColumns.forEach(e -> res.add((String) new ArrayList<>(e.values()).get(0)));
        } catch (SQLException e) {
            logger.error("[getPrimaryKeys Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    @Override
    public List<String> getColumnNames(String tableName, String datasource) {

        List<String> res = Lists.newArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //获取查询指定表所有字段的sql语句
            String querySql = sqlBuilder.getSQLQueryFields(tableName);
            logger.info("querySql: {}", querySql);

            //获取所有字段
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);


            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {


                String columnName = metaData.getColumnName(i);
                //logger.info("getColumnNames : {}",columnName);
                if (JdbcConstants.HIVE.equals(datasource)) {
                    if (columnName.contains(Constants.SPLIT_POINT)) {
                        res.add(i - 1 + Constants.SPLIT_SCOLON + columnName.substring(columnName.indexOf(Constants.SPLIT_POINT) + 1) + Constants.SPLIT_SCOLON + metaData.getColumnTypeName(i));
                    } else {
                        res.add(i - 1 + Constants.SPLIT_SCOLON + columnName + Constants.SPLIT_SCOLON + metaData.getColumnTypeName(i));
                    }
                } else {
                    res.add(columnName);
                }

            }
            //}


        } catch (SQLException e) {
            logger.error("[getColumnNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return res;
    }

    /**
     * @param datasource
     * @param tableName
     * @author: bahsk
     * @date: 2021-10-27 10:58
     * @description: [项目定制]根据数据源id和表名获取所有字段明细
     * @params:
     * @return:
     */
    public List<ColumnDetailsRespDTO> getColumnsDetails(String tableName, String datasource) {

        List<ColumnDetailsRespDTO> res = Lists.newArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //获取查询指定表所有字段的sql语句
            String querySql = sqlBuilder.getSQLQueryFields(tableName);
            logger.info("querySql: {}", querySql);

            //获取所有字段
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {

                String columnName = metaData.getColumnName(i);
                ColumnDetailsRespDTO build = ColumnDetailsRespDTO
                        .builder()
                        .column(columnName)
                        .columnType(metaData.getColumnTypeName(i))
                        .columnLength(metaData.getPrecision(i))
                        .build();
                if (JdbcConstants.HIVE.equals(datasource)) {
                    if (columnName.contains(Constants.SPLIT_POINT)) {
                        build.setColumn(i - 1 + Constants.SPLIT_SCOLON + columnName.substring(columnName.indexOf(Constants.SPLIT_POINT) + 1) + Constants.SPLIT_SCOLON + metaData.getColumnTypeName(i));

                    } else {
                        build.setColumn(i - 1 + Constants.SPLIT_SCOLON + columnName + Constants.SPLIT_SCOLON + metaData.getColumnTypeName(i));
                    }
                }
                res.add(build);

            }
        } catch (SQLException e) {
            logger.error("[getColumnNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return res;
    }

    /**
     * @author: bahsk
     * @date: 2021-12-08 16:12
     * @description: [项目定制] 查询建表sql
     * @params:
     * @return:
     */
    public List<TableDetailsResp> getDdlSQL(String tableName, String user) {
        List<TableDetailsResp> tableDetailsResps = new ArrayList<>();

        String[] tables = tableName.split(",");
        if (tables.length > 1) {
            return this.getMultiDdlSQL(tableName, user);
        } else {
            // 此处if后续可以考虑替换成策略模式
            String querySql = sqlBuilder.getDdlSQL(this.currentSchema, tableName.toUpperCase());
            TableDetailsResp tableDetailsResp = null;

            //oracle数据源比较特殊，是用用户而不是模式
            if (currentDatabase.equals(JdbcConstants.ORACLE)) {
                querySql = sqlBuilder.getDdlSQL(user.toUpperCase(), tableName.toUpperCase());
            }

            if (StringUtils.isBlank(querySql)) {
                tableDetailsResp = TableDetailsResp.builder()
                        .tableName(null)
                        .ddlSQL("获取ddl失败,暂不支持此数据源" + this.currentDatabase + "类型")
                        .build();
                tableDetailsResps.add(tableDetailsResp);
                return tableDetailsResps;
            }

            Statement stmt = null;
            ResultSet resultSet = null;
            try {

                stmt = this.connection.createStatement();
                resultSet = stmt.executeQuery(querySql);
                if (resultSet.next()) {
                    String ddl = resultSet.getString(2).replaceAll("\\n\\t|\\n|\\t|\\\"", " ");
                    TableDetailsResp build = TableDetailsResp.builder()
                            .tableName(tableName)
                            .ddlSQL(ddl)
                            .build();
                    tableDetailsResps.add(build);
                    return tableDetailsResps;
                } else {
                    TableDetailsResp build = TableDetailsResp.builder()
                            .tableName(tableName)
                            .ddlSQL("获取ddl失败无记录")
                            .build();
                    tableDetailsResps.add(build);
                    return tableDetailsResps;
                }

            } catch (SQLException e) {
                logger.error("获取ddl失败" + e.getMessage());
                e.printStackTrace();
            } finally {
                JdbcUtils.close(resultSet);
                JdbcUtils.close(stmt);
            }

            TableDetailsResp build = TableDetailsResp.builder()
                    .tableName(tableName)
                    .ddlSQL("获取ddl失败,无法创建链接")
                    .build();
            tableDetailsResps.add(build);
            return tableDetailsResps;
        }
    }


    /**
     * @author: bahsk
     * @date: 2021-12-23 9:46
     * @description: [项目定制] 查询多表建表sql,限oracle
     * @params:
     * @return:
     */
    public List<TableDetailsResp> getMultiDdlSQL(String tableName, String user) {
        List<TableDetailsResp> tableDetailsResps = new ArrayList<>();
//        String[] tables = (String[]) tableList.toArray();
//        String tableName = tables.length == 1 ? tables[0] : "'" + StringUtils.join(tables, "','") + "'";
        // 此处if后续可以考虑替换成策略模式
        String querySql = sqlBuilder.getMultiDdlSQL(this.currentSchema, tableName.toUpperCase());
        TableDetailsResp tableDetailsResp = null;

        //oracle数据源比较特殊，是用用户而不是模式
        if (currentDatabase.equals(JdbcConstants.ORACLE)) {
            querySql = sqlBuilder.getMultiDdlSQL(user.toUpperCase(), "'" + tableName.toUpperCase().replaceAll(",", "','") + "'");
        }

        if (StringUtils.isBlank(querySql)) {
            tableDetailsResp = TableDetailsResp.builder()
                    .tableName(null)
                    .ddlSQL("获取ddl失败,暂不支持" + this.currentDatabase + "的多表获取,请转为单表查询")
                    .build();
            tableDetailsResps.add(tableDetailsResp);
            return tableDetailsResps;
        }

        Statement stmt = null;
        ResultSet resultSet = null;
        try {

            stmt = this.connection.createStatement();
            resultSet = stmt.executeQuery(querySql);
            while (resultSet.next()) {
                String ddl = resultSet.getString(2).replaceAll("\\n\\t|\\n|\\t|\\\"", " ");
                TableDetailsResp build = TableDetailsResp.builder()
                        .tableName(tableName)
                        .ddlSQL(ddl)
                        .build();
                tableDetailsResps.add(build);
            }
        } catch (SQLException e) {
            logger.error("获取ddl失败" + e.getMessage());
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet);
            JdbcUtils.close(stmt);
        }

        return tableDetailsResps;
    }


    /**
     * @author: bahsk
     * @date: 2021-10-27 11:50
     * @description:
     * @params:
     * @return:
     */
    public List<DasColumn> getDasColumn(String tableName, String datasource) {

        List<ColumnDetailsRespDTO> res = Lists.newArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //获取查询指定表所有字段的sql语句
            String querySql = sqlBuilder.getSQLQueryFields(tableName);
            logger.info("querySql: {}", querySql);

            //获取所有字段
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);
            ResultSetMetaData metaData = rs.getMetaData();

            return buildDasColumn(tableName, metaData);

        } catch (SQLException e) {
            logger.error("[getColumnNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return null;
    }

    @Override
    public List<String> getTableNames(String tableSchema) {
        List<String> tables = new ArrayList<String>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = getSQLQueryTables(tableSchema);
            logger.info("# getTableNames - sql : {}", sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tableName = rs.getString(1);
                tables.add(tableName);
            }
            logger.info("# getTableNames - table size : {}", tables.size());
            tables.sort(Comparator.naturalOrder());
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return tables;
    }

    @Override
    public List<String> getTableNames() {
        List<String> tables = new ArrayList<String>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = getSQLQueryTables();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tableName = rs.getString(1);
                if(StringUtils.equals(currentDatabase,"hive")) {
                    String hiveTbl = rs.getString("tablename");
                    if (hiveTbl != null) {
                        tableName = hiveTbl;
                    }
                }
//                logger.info("col1 :{},col2: {},col3: {}",
//                        rs.getString("tablename"),
//                        rs.getString("namespace"),
//                        rs.getString("istemporaary"));
                tables.add(tableName);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return tables;
    }

    public Boolean dataSourceTest() {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            if (metaData.getDatabaseProductName().length() > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("[dataSourceTest Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return false;
    }


    protected String getSQLQueryTables(String tableSchema) {
        return sqlBuilder.getSQLQueryTables(tableSchema);
    }

    /**
     * 不需要其他参数的可不重写
     *
     * @return
     */
    protected String getSQLQueryTables() {
        return sqlBuilder.getSQLQueryTables();
    }

    @Override
    public List<String> getColumnsByQuerySql(String querySql) throws SQLException {


        List<String> res = Lists.newArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            querySql = querySql.replace(";", "");
            //拼装sql语句，在后面加上 where 1=0 即可
            String sql = querySql.concat(" where 1=0");
            //判断是否已有where，如果是，则加 and 1=0
            //从最后一个 ) 开始找 where，或者整个语句找
            if (querySql.contains(")")) {
                if (querySql.substring(querySql.indexOf(")")).contains("where")) {
                    sql = querySql.concat(" and 1=0");
                }
            } else {
                if (querySql.contains("where")) {
                    sql = querySql.concat(" and 1=0");
                }
            }
            //获取所有字段
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                res.add(metaData.getColumnName(i));
            }
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return res;
    }

    @Override
    public long getMaxIdVal(String tableName, String primaryKey) {
        Statement stmt = null;
        ResultSet rs = null;
        long maxVal = 0;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = getSQLMaxID(tableName, primaryKey);
            rs = stmt.executeQuery(sql);
            rs.next();
            maxVal = rs.getLong(1);
        } catch (SQLException e) {
            logger.error("[getMaxIdVal Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }


        return maxVal;
    }

    private String getSQLMaxID(String tableName, String primaryKey) {
        return sqlBuilder.getMaxId(tableName, primaryKey);
    }

    public void executeSql(String querySql) {
        if (StringUtils.isBlank(querySql)) {
            return;
        }
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(querySql);
        } catch (SQLException e) {
            logger.error("[executeCreateTableSql Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(stmt);
        }
    }

    /**
     * 执行建表语句(含注释)
     *
     * @param sqlList
     */
    public boolean executeCreateTableSqls(List<String> sqlList) {
        if (sqlList == null) {
            return false;
        }
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            for (String sql : sqlList) {
                stmt.executeUpdate(sql);
            }

        } catch (SQLException e) {
            logger.error("[executeCreateTableSql Exception] --> "
                    + "the exception message is:" + e.getMessage());
            return false;
        } finally {
            JdbcUtils.close(stmt);
        }
        return true;
    }

    public List<String> getTableSchema() {
        List<String> schemas = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = getSQLQueryTableSchema();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tableName = rs.getString(1);
                schemas.add(tableName);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return schemas;
    }

    protected String getSQLQueryTableSchema() {
        return sqlBuilder.getSQLQueryTableSchema();
    }


    /**
     * @param tableName
     * @description 根据表名返回记录数
     */
    public TableCountResp getTableCount(String tableName) {
        TableCountResp tableCountResps = new TableCountResp();

        String querySql = sqlBuilder.getTableCount(tableName);

        Statement stmt = null;
        ResultSet resultSet = null;
        try {

            stmt = this.connection.createStatement();
            resultSet = stmt.executeQuery(querySql);
            if (resultSet.next()) {
                String tableCount = resultSet.getString(1);
                tableCountResps = TableCountResp.builder()
                        .tableName(tableName)
                        .tableCounts(tableCount)
                        .build();

            } else {
                tableCountResps = TableCountResp.builder()
                        .tableName(tableName)
                        .tableCounts("获取失败，无记录")
                        .build();

            }

        } catch (SQLException e) {
            logger.error("获取记录数失败" + e.getMessage());
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet);
            JdbcUtils.close(stmt);
        }


        return tableCountResps;
    }

    /**
     * 获取更新语句
     *
     * @param diffDTO
     * @param tableName
     * @return
     */
    public String getColumnsAlter(ColumnDetailsDiffRespDTO diffDTO, String tableName) {
        // 拼接alter语句
        String alterType = diffDTO.getAlterType();
        String alterString;
        if (alterType == "a") {
            alterString = sqlBuilder.getAlterAdd(tableName, diffDTO.getColumn(), diffDTO.getColumnType(), diffDTO.getColumnLength());
        } else {
            alterString = sqlBuilder.getAlterModify(tableName, diffDTO.getColumn(), diffDTO.getColumnType(), diffDTO.getColumnLength());
        }
        return alterString;
    }


    @Override
    public List<String> buildCreateTableSql(TableInfo tableInfo) {
        return null;
    }


    /**
     * 获取数据库字符集类型
     *
     * @return
     */
    public String getCharacterSet() {
        String charaSet = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = getSQLQueryTableSchema();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                charaSet = rs.getString(1);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return charaSet;
    }

    public List<CusColumn> getDbColums() {
        return null;
    }

    /**
     * 构建table info
     * @param tableName 表名
     * @param userName 用户名
     * @return
     */
    @Override
    public TableInfoV2 buildTableInfoV2(String tableName,String userName) {
        //获取表信息
        List<Map<String, Object>> tableInfos = this.getTableInfo(tableName);
        if (tableInfos.isEmpty()) {
            throw new NullPointerException("查询出错! 问题表: " + tableName);
        }

        TableInfoV2 tableInfo = new TableInfoV2();
        //表名，注释
        List tValues = new ArrayList(tableInfos.get(0).values());

        tableInfo.setTableName(StrUtil.toString(tValues.get(0)));
        tableInfo.setComment(StrUtil.toString(tValues.get(1)));

        //字段信息
        List<ColumnInfoV2> columnInfos = new ArrayList<>();

        columnInfos = getColumnList(tableName,userName);

        tableInfo.setColumns(columnInfos);

        return tableInfo;
    }

    /**
     * 获取指定表的列信息
     * @param tableName
     * @param userName
     * @return
     */
    public List<ColumnInfoV2> getColumnList(String tableName, String userName) {

        List<ColumnInfoV2> fullColumn = Lists.newArrayList();
        //获取指定表的所有字段
        try {

            //获取查询指定表所有字段的sql语句
            String querySql = sqlBuilder.getSQLQueryFields(tableName, userName);
            logger.info("querySql: {}", querySql);

            //获取所有字段
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querySql);
            ResultSetMetaData metaData = resultSet.getMetaData();

            // 遍历metaData 获取字段信息存入fullColumn 中
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i < columnCount; i++) {
                // 读取metaData中的字段信息
                ColumnInfoV2 columnInfoV2 = new ColumnInfoV2();

                // 列名称
                columnInfoV2.setColumnName(metaData.getColumnName(i));

                ColumnType columnType = new ColumnType();
                // 列类型
                columnType.setType(metaData.getColumnTypeName(i));
                columnType.setPrecision(metaData.getPrecision(i));
                columnType.setLength(metaData.getColumnDisplaySize(i));
                columnType.setScale(metaData.getScale(i));

                columnInfoV2.setIsNull(metaData.isNullable(i));
                columnInfoV2.setColumnType(columnType);

                fullColumn.add(columnInfoV2);

                // 暂时不考虑主键以及索引等问题
            }
            fullColumn.forEach(e -> {
                String sqlQueryComment = sqlBuilder.getSQLQueryComment(currentSchema, tableName, e.getColumnName());
                //查询字段注释
                try (ResultSet resultSetComment = statement.executeQuery(sqlQueryComment)) {
                    while (resultSetComment.next()) {
                        e.setColumnComment(resultSetComment.getString(1));
                    }
                    JdbcUtils.close(resultSetComment);
                } catch (SQLException e1) {
                    logger.error("[buildDasColumn executeQuery Exception] --> "
                            + "the exception message is:" + e1.getMessage());
                }
            });
        } catch (SQLException e) {
            logger.error("[getColumns Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return fullColumn;
    }
}


