package com.wugui.datax.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author bahsk
 * @createTime 2021-12-07 14:48
 * @description
 */
@Data
@Builder
@ApiModel(value="com.wugui.datax.admin.dto.JobDatasourceSnapshotDTO")
public class JobDatasourceSnapshotDTO {
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
     * 状态：0删除 1启用 2禁用
     */
    @ApiModelProperty(value="状态：0删除 1启用 2禁用")
    private Integer status;

}
