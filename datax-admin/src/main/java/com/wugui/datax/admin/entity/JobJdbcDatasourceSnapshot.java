package com.wugui.datax.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * job_jdbc_datasource_snapshot
 * @author
 */
@ApiModel(value="com.wugui.datax.admin.entity.JobJdbcDatasourceSnapshot")
@Data
public class JobJdbcDatasourceSnapshot implements Serializable {
    /**
     * 自增主键
     */
    @ApiModelProperty(value="自增主键")
    private Long id;

    /**
     * 数据源名称
     */
    @ApiModelProperty(value="数据源名称")
    private String datasourceName;

    /**
     * 数据源
     */
    @ApiModelProperty(value="数据源")
    private String datasource;

    /**
     * 数据源分组
     */
    @ApiModelProperty(value="数据源分组")
    private String datasourceGroup;

    /**
     * 数据库名
     */
    @ApiModelProperty(value="数据库名")
    private String databaseName;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String jdbcUsername;

    /**
     * jdbc url
     */
    @ApiModelProperty(value="jdbc url")
    private String jdbcUrl;

    /**
     * 状态：0删除 1启用 2禁用
     */
    @ApiModelProperty(value="状态：0删除 1启用 2禁用")
    private Integer status;

    private Date snapshotTime;

    private static final long serialVersionUID = 1L;
}
