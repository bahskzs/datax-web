package com.wugui.datax.admin.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author TT
 * @createTime 2022-06-01 17:45
 * @description
 */
@Data
@Builder
public class JobDsEnvironmentDTO extends PageReq{
    /**
     * 资源名称
     */
    @ApiModelProperty(value="数据源名称")
    private String datasourceName;

    @ApiModelProperty(value="数据源类型")
    private String dataSourceType;
}
