package com.wugui.datax.admin.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.wugui.datax.admin.core.handler.AESEncryptHandler;
import com.wugui.datax.admin.entity.JobDatasource;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@author bahsk
 *@createTime 2021-10-20
 *@description  JobDatasourceDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDatasourceDTO extends Model<JobDatasource> {

    /**
     * 数据源
     */
    @ApiModelProperty(value = "数据源")
    private String datasource;

    /**
     * 用户名
     * AESEncryptHandler 加密类
     * MyBatis Plus 3.0.7.1之前版本没有typeHandler属性，需要升级到最低3.1.2
     */
    @ApiModelProperty(value = "用户名")
    private String jdbcUsername;

    /**
     * 密码
     */
    @TableField(typeHandler = AESEncryptHandler.class)
    private String jdbcPassword;

    /**
     * jdbc url
     */
    @ApiModelProperty(value = "jdbc url")
    private String jdbcUrl;

}
