package com.wugui.datax.admin.dto;

import lombok.Data;

/**
 * @author Cat
 * @createTime 2023-09-20 11:34
 * @description
 */
@Data
public class YTHUserVO {

    /**
     * 用户账号
     */
    private String code;

    /**
     * 用户ID
     */
    private String guid;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户所属区划
     */
    private String province;

    /**
     * 用户手机号码
     */
    private String phonenumber;

}
