package com.wugui.datax.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Cat
 * @createTime 2023-09-17 22:21
 * @description
 */
@Data
public class HistoryUserRoleDTO {
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
}
