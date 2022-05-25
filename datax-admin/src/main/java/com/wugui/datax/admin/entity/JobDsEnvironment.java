package com.wugui.datax.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * ;
 * @author : http://www.chiner.pro
 * @date : 2022-5-25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "",description = "")
public class JobDsEnvironment implements Serializable,Cloneable{
    /** 自增主键 */
    @TableId
    @ApiModelProperty(value = "自增主键")
    private Long id ;
    /** 数据源id */
    private Integer datasourceId ;
    /** 数据源名称 */
    private String datasourceName ;
    /** 数据源类型 */
    private Integer datasourceType ;
    /** HDFS路径 */
    private String path ;
    /** HDFS地址 */
    private String defaultFs ;
    /** 文件类型 */
    private String fileType ;
    /** 分隔符 */
    private String fieldDelimiter ;
    /** HA节点 */
    private String nameNodes ;
    /** 名称 */
    private String nameServices ;
    /** HA配置地址 */
    private String address ;
    /**  */
    private String proxyProvider ;
    /** 状态：0删除;1启用 2禁用 */
    private String status ;
    /** 创建人 */
    private String createBy ;
    /** 创建时间 */
    private Date createDate ;
    /** 更新人 */
    private String updateBy ;
    /** 更新时间 */
    private Date updateDate ;
    /** 备注 */
    private String comments ;

}
