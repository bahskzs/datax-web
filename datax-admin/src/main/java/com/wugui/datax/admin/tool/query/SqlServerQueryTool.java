package com.wugui.datax.admin.tool.query;

import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.tool.database.TableInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * sql server
 *
 * @author zhouhongfa@gz-yibo.com
 * @version 1.0
 * @since 2019/8/2
 */
public class SqlServerQueryTool extends BaseQueryTool implements QueryToolInterface {
    public SqlServerQueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }


}
