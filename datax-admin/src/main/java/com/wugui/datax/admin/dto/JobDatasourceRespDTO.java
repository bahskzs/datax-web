package com.wugui.datax.admin.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 *@createTime 2021-11-01
 *@description  JobDatasourceRespDTO 用于批量修改数据源的DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDatasourceRespDTO extends Model<JobDatasource> {

    /**
     * 自增主键
     */
    @TableId
    @ApiModelProperty(value = "自增主键")
    private Long id;

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
