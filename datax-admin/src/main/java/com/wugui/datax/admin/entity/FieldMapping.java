package com.wugui.datax.admin.entity;

/**
 * @author Cat
 * @createTime 2023-01-16 14:55
 * @description 字段映射实体类
 */
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 字段映射管理;
 * @author : http://www.chiner.pro
 * @date : 2023-1-16
 */
@ApiModel(value = "字段映射管理",description = "")
@TableName("field_mapping")
public class FieldMapping implements Serializable,Cloneable{
    /** id */
    @TableId
    @ApiModelProperty(name = "id",notes = "")
    private Integer id ;
    /** 字段类型 */
    @ApiModelProperty(name = "字段类型",notes = "")
    private String fieldsType ;
    /** 源类型 */
    @ApiModelProperty(name = "源类型",notes = "")
    private String datasource ;
    /** 源字段类型 */
    @ApiModelProperty(name = "源字段类型",notes = "")
    private String sourceFieldType ;
    /** 目标类型 */
    @ApiModelProperty(name = "目标类型",notes = "")
    private String targetDatasource ;
    /** 目标字段类型 */
    @ApiModelProperty(name = "目标字段类型",notes = "")
    private String targetFieldType ;
    /** 备注 */
    @ApiModelProperty(name = "备注",notes = "")
    private String comments ;

    /** id */
    public Integer getId(){
        return this.id;
    }
    /** id */
    public void setId(Integer id){
        this.id=id;
    }
    /** 字段类型 */
    public String getFieldsType(){
        return this.fieldsType;
    }
    /** 字段类型 */
    public void setFieldsType(String fieldsType){
        this.fieldsType=fieldsType;
    }
    /** 源类型 */
    public String getDatasource(){
        return this.datasource;
    }
    /** 源类型 */
    public void setDatasource(String datasource){
        this.datasource=datasource;
    }
    /** 源字段类型 */
    public String getSourceFieldType(){
        return this.sourceFieldType;
    }
    /** 源字段类型 */
    public void setSourceFieldType(String sourceFieldType){
        this.sourceFieldType=sourceFieldType;
    }
    /** 目标类型 */
    public String getTargetDatasource(){
        return this.targetDatasource;
    }
    /** 目标类型 */
    public void setTargetDatasource(String targetDatasource){
        this.targetDatasource=targetDatasource;
    }
    /** 目标字段类型 */
    public String getTargetFieldType(){
        return this.targetFieldType;
    }
    /** 目标字段类型 */
    public void setTargetFieldType(String targetFieldType){
        this.targetFieldType=targetFieldType;
    }
    /** 备注 */
    public String getComments(){
        return this.comments;
    }
    /** 备注 */
    public void setComments(String comments){
        this.comments=comments;
    }
}
