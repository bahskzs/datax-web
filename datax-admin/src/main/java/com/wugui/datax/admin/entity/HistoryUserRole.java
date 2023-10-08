package com.wugui.datax.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *@author Cat
 *@createTime 2023-09-17 22:19
 *@description
 */
/**
    * 历史数据用户权限配置
    */
@ApiModel(description="历史数据用户权限配置")
@Data
public class HistoryUserRole implements Serializable {
    /**
    * id
    */
    @ApiModelProperty(value="id")
    private Integer id;

    /**
    * 用户id
    */
    @ApiModelProperty(value="用户id")
    private String userid;

    /**
    * 用户信息:用户名,用户账号,用户类型,用户机构
    */
    @ApiModelProperty(value="用户信息:用户名,用户账号,用户类型,用户机构")
    private String userInfo;

    /**
    * 模块id集合
    */
    @ApiModelProperty(value="模块id集合")
    private String modules;

    /**
    * 区划信息
    */
    @ApiModelProperty(value="区划信息")
    private String regions;

    /**
    * 单位
    */
    @ApiModelProperty(value="单位")
    private String agencies;

    /**
    * 处室
    */
    @ApiModelProperty(value="处室")
    private String offices;

    /**
    * 状态;1启用，0禁用
    */
    @ApiModelProperty(value="状态;1启用，0禁用")
    private String status;

    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", userInfo=").append(userInfo);
        sb.append(", modules=").append(modules);
        sb.append(", regions=").append(regions);
        sb.append(", agencies=").append(agencies);
        sb.append(", offices=").append(offices);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}
