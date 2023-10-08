package com.wugui.datax.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 *@author Cat
 *@createTime 2023-09-19 17:42
 *@description 
 */
@ApiModel(description="t_pubgov")
public class Region implements Serializable {
    @ApiModelProperty(value="")
    private Integer govid;

    @ApiModelProperty(value="")
    private String code;

    @ApiModelProperty(value="")
    private String name;

    @ApiModelProperty(value="")
    private Integer superid;

    @ApiModelProperty(value="")
    private Short status;

    @ApiModelProperty(value="")
    private String guid;

    @ApiModelProperty(value="")
    private Short type;

    @ApiModelProperty(value="")
    private Short levelno;

    @ApiModelProperty(value="")
    private String shortcode;

    @ApiModelProperty(value="")
    private String srcDataName;

    @ApiModelProperty(value="")
    private String srcSystemId;

    @ApiModelProperty(value="")
    private String srcSystemName;

    @ApiModelProperty(value="")
    private String dataProvider;

    @ApiModelProperty(value="")
    private String dataSrcId;

    @ApiModelProperty(value="")
    private Date dataCollectionDatetime;

    @ApiModelProperty(value="")
    private String dataCollectionDeviceNumber;

    @ApiModelProperty(value="")
    private String dataCollectionPersonName;

    @ApiModelProperty(value="")
    private Integer belongYear;

    private static final long serialVersionUID = 1L;

    public Integer getGovid() {
        return govid;
    }

    public void setGovid(Integer govid) {
        this.govid = govid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSuperid() {
        return superid;
    }

    public void setSuperid(Integer superid) {
        this.superid = superid;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getLevelno() {
        return levelno;
    }

    public void setLevelno(Short levelno) {
        this.levelno = levelno;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getSrcDataName() {
        return srcDataName;
    }

    public void setSrcDataName(String srcDataName) {
        this.srcDataName = srcDataName;
    }

    public String getSrcSystemId() {
        return srcSystemId;
    }

    public void setSrcSystemId(String srcSystemId) {
        this.srcSystemId = srcSystemId;
    }

    public String getSrcSystemName() {
        return srcSystemName;
    }

    public void setSrcSystemName(String srcSystemName) {
        this.srcSystemName = srcSystemName;
    }

    public String getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(String dataProvider) {
        this.dataProvider = dataProvider;
    }

    public String getDataSrcId() {
        return dataSrcId;
    }

    public void setDataSrcId(String dataSrcId) {
        this.dataSrcId = dataSrcId;
    }

    public Date getDataCollectionDatetime() {
        return dataCollectionDatetime;
    }

    public void setDataCollectionDatetime(Date dataCollectionDatetime) {
        this.dataCollectionDatetime = dataCollectionDatetime;
    }

    public String getDataCollectionDeviceNumber() {
        return dataCollectionDeviceNumber;
    }

    public void setDataCollectionDeviceNumber(String dataCollectionDeviceNumber) {
        this.dataCollectionDeviceNumber = dataCollectionDeviceNumber;
    }

    public String getDataCollectionPersonName() {
        return dataCollectionPersonName;
    }

    public void setDataCollectionPersonName(String dataCollectionPersonName) {
        this.dataCollectionPersonName = dataCollectionPersonName;
    }

    public Integer getBelongYear() {
        return belongYear;
    }

    public void setBelongYear(Integer belongYear) {
        this.belongYear = belongYear;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", govid=").append(govid);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", superid=").append(superid);
        sb.append(", status=").append(status);
        sb.append(", guid=").append(guid);
        sb.append(", type=").append(type);
        sb.append(", levelno=").append(levelno);
        sb.append(", shortcode=").append(shortcode);
        sb.append(", srcDataName=").append(srcDataName);
        sb.append(", srcSystemId=").append(srcSystemId);
        sb.append(", srcSystemName=").append(srcSystemName);
        sb.append(", dataProvider=").append(dataProvider);
        sb.append(", dataSrcId=").append(dataSrcId);
        sb.append(", dataCollectionDatetime=").append(dataCollectionDatetime);
        sb.append(", dataCollectionDeviceNumber=").append(dataCollectionDeviceNumber);
        sb.append(", dataCollectionPersonName=").append(dataCollectionPersonName);
        sb.append(", belongYear=").append(belongYear);
        sb.append("]");
        return sb.toString();
    }
}