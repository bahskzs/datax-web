package com.wugui.datax.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * v_job_datasource
 * @author 
 */
@ApiModel(value="com.wugui.datax.admin.entity.VJobDatasource")
@Data
public class VJobDatasource implements Serializable {
    private Integer id;

    private String jobDesc;

    private String sourcename;

    private String targetname;

    private String sourceuser;

    private String targetuser;

    private String sourceurl;

    private String targeturl;

    /**
     * datax运行脚本
     */
    @ApiModelProperty(value="datax运行脚本")
    private String jobJson;

    private static final long serialVersionUID = 1L;
}