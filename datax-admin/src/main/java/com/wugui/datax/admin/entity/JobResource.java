package com.wugui.datax.admin.entity;

import com.wugui.datax.admin.dto.PageResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * job_resource
 * @author
 */
@ApiModel(value="com.wugui.datax.admin.entity.JobResource资源配置")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResource extends PageResp implements Serializable {
    private Integer id;

    /**
     * 资源名称
     */
    @ApiModelProperty(value="资源名称")
    private String sourceName;

    /**
     * hadoop的defaultFS
     */
    @ApiModelProperty(value="hadoop的defaultFS")
    private String defaultFs;

    /**
     * 路径
     */
    @ApiModelProperty(value="路径")
    private String path;

    /**
     * 写入模式
     */
    @ApiModelProperty(value="写入模式")
    private String writeMode;

    /**
     * 分割
     */
    @ApiModelProperty(value="分割")
    private String fieldDelimiter;

    /**
     * 关联数据源
     */
    @ApiModelProperty(value="关联数据源")
    private String datasourceId;

    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createdTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value="更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
