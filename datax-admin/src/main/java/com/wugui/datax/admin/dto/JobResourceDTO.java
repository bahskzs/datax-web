package com.wugui.datax.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author bahsk
 * @createTime 2022-01-25 15:45
 * @description
 */
@Data
@Builder
public class JobResourceDTO extends PageReq{

    /**
     * 资源名称
     */
    @ApiModelProperty(value="资源名称")
    private String sourceName;
}
