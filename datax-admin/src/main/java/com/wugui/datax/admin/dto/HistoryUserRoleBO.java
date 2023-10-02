package com.wugui.datax.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Cat
 * @createTime 2023-09-17 22:21
 * @description
 */
@Data
public class HistoryUserRoleBO {
    /**
     * 用户id
     */
//    @ApiModelProperty(value="用户id")
//    private String userid;

    /**
     * 用户信息:用户名,用户账号,用户类型,用户机构
     */
    @ApiModelProperty(value="用户名/用户账号")
    private String userInfo;

    /**
     * 模块id集合
     */
    @ApiModelProperty(value="模块id集合")
    private List<String> modules;

    /**
     * 区划信息
     */
    @ApiModelProperty(value="区划信息")
    private List<String> regions;

    /**
     * 单位
     */
    @ApiModelProperty(value="单位")
    private List<String> agencies;

    /**
     * 处室
     */
    @ApiModelProperty(value="处室")
    private List<String> offices;

    /**
     * 状态;1启用，0禁用
     */
    @ApiModelProperty(value="状态;1启用，0禁用")
    private String status;
}
