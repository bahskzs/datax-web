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
@ApiModel(description="t_pubbdgmanagedivision")
public class Office implements Serializable {
    @ApiModelProperty(value="")
    private Integer itemid;

    @ApiModelProperty(value="")
    private String elementcode;

    @ApiModelProperty(value="")
    private Date startdate;

    @ApiModelProperty(value="")
    private String code;

    @ApiModelProperty(value="")
    private String name;

    @ApiModelProperty(value="")
    private String shortname;

    @ApiModelProperty(value="")
    private String wholename;

    @ApiModelProperty(value="")
    private String isbncode;

    @ApiModelProperty(value="")
    private Date enddate;

    @ApiModelProperty(value="")
    private Integer type;

    @ApiModelProperty(value="")
    private Short systempretag;

    @ApiModelProperty(value="")
    private String remark;

    @ApiModelProperty(value="")
    private Integer status;

    @ApiModelProperty(value="")
    private Integer fromitemid;

    @ApiModelProperty(value="")
    private Integer toitemid;

    @ApiModelProperty(value="")
    private String codealias;

    @ApiModelProperty(value="")
    private String billpromise;

    @ApiModelProperty(value="")
    private String guid;

    @ApiModelProperty(value="")
    private Integer bgtlevel;

    @ApiModelProperty(value="")
    private Integer bgtlevelmark;

    @ApiModelProperty(value="")
    private String bgtcode;

    @ApiModelProperty(value="")
    private String secretdegree;

    @ApiModelProperty(value="")
    private Integer supplytype;

    @ApiModelProperty(value="")
    private Integer agencykind;

    @ApiModelProperty(value="")
    private Integer agencylevel;

    @ApiModelProperty(value="")
    private Integer govid;

    @ApiModelProperty(value="")
    private Short govyear;

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

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public String getElementcode() {
        return elementcode;
    }

    public void setElementcode(String elementcode) {
        this.elementcode = elementcode;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
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

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getWholename() {
        return wholename;
    }

    public void setWholename(String wholename) {
        this.wholename = wholename;
    }

    public String getIsbncode() {
        return isbncode;
    }

    public void setIsbncode(String isbncode) {
        this.isbncode = isbncode;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Short getSystempretag() {
        return systempretag;
    }

    public void setSystempretag(Short systempretag) {
        this.systempretag = systempretag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFromitemid() {
        return fromitemid;
    }

    public void setFromitemid(Integer fromitemid) {
        this.fromitemid = fromitemid;
    }

    public Integer getToitemid() {
        return toitemid;
    }

    public void setToitemid(Integer toitemid) {
        this.toitemid = toitemid;
    }

    public String getCodealias() {
        return codealias;
    }

    public void setCodealias(String codealias) {
        this.codealias = codealias;
    }

    public String getBillpromise() {
        return billpromise;
    }

    public void setBillpromise(String billpromise) {
        this.billpromise = billpromise;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer getBgtlevel() {
        return bgtlevel;
    }

    public void setBgtlevel(Integer bgtlevel) {
        this.bgtlevel = bgtlevel;
    }

    public Integer getBgtlevelmark() {
        return bgtlevelmark;
    }

    public void setBgtlevelmark(Integer bgtlevelmark) {
        this.bgtlevelmark = bgtlevelmark;
    }

    public String getBgtcode() {
        return bgtcode;
    }

    public void setBgtcode(String bgtcode) {
        this.bgtcode = bgtcode;
    }

    public String getSecretdegree() {
        return secretdegree;
    }

    public void setSecretdegree(String secretdegree) {
        this.secretdegree = secretdegree;
    }

    public Integer getSupplytype() {
        return supplytype;
    }

    public void setSupplytype(Integer supplytype) {
        this.supplytype = supplytype;
    }

    public Integer getAgencykind() {
        return agencykind;
    }

    public void setAgencykind(Integer agencykind) {
        this.agencykind = agencykind;
    }

    public Integer getAgencylevel() {
        return agencylevel;
    }

    public void setAgencylevel(Integer agencylevel) {
        this.agencylevel = agencylevel;
    }

    public Integer getGovid() {
        return govid;
    }

    public void setGovid(Integer govid) {
        this.govid = govid;
    }

    public Short getGovyear() {
        return govyear;
    }

    public void setGovyear(Short govyear) {
        this.govyear = govyear;
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
        sb.append(", itemid=").append(itemid);
        sb.append(", elementcode=").append(elementcode);
        sb.append(", startdate=").append(startdate);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", shortname=").append(shortname);
        sb.append(", wholename=").append(wholename);
        sb.append(", isbncode=").append(isbncode);
        sb.append(", enddate=").append(enddate);
        sb.append(", type=").append(type);
        sb.append(", systempretag=").append(systempretag);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", fromitemid=").append(fromitemid);
        sb.append(", toitemid=").append(toitemid);
        sb.append(", codealias=").append(codealias);
        sb.append(", billpromise=").append(billpromise);
        sb.append(", guid=").append(guid);
        sb.append(", bgtlevel=").append(bgtlevel);
        sb.append(", bgtlevelmark=").append(bgtlevelmark);
        sb.append(", bgtcode=").append(bgtcode);
        sb.append(", secretdegree=").append(secretdegree);
        sb.append(", supplytype=").append(supplytype);
        sb.append(", agencykind=").append(agencykind);
        sb.append(", agencylevel=").append(agencylevel);
        sb.append(", govid=").append(govid);
        sb.append(", govyear=").append(govyear);
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