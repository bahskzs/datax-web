package com.wugui.datax.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 *@author Cat
 *@createTime 2023-09-19 17:41
 *@description 
 */
@ApiModel(description="t_pubagency")
public class Agency implements Serializable {
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
    private Short levelno;

    @ApiModelProperty(value="")
    private Short isleaf;

    @ApiModelProperty(value="")
    private Integer superitemid;

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
    private Integer emwcode;

    @ApiModelProperty(value="")
    private Integer bgtlevel;

    @ApiModelProperty(value="")
    private Integer bgtlevelmark;

    @ApiModelProperty(value="")
    private String bgtcode;

    @ApiModelProperty(value="")
    private Integer mofdepmanager;

    @ApiModelProperty(value="")
    private Integer iscentralizedpay;

    @ApiModelProperty(value="")
    private String isbncodebak;

    @ApiModelProperty(value="")
    private Integer secretdegree;

    @ApiModelProperty(value="")
    private Integer isvirtual;

    @ApiModelProperty(value="")
    private Integer mofdepmanager1;

    @ApiModelProperty(value="")
    private Integer mofdepmanager2;

    @ApiModelProperty(value="")
    private Integer mofdepmanager3;

    @ApiModelProperty(value="")
    private Integer mofdepmanager4;

    @ApiModelProperty(value="")
    private Integer mofdepmanager5;

    @ApiModelProperty(value="")
    private Integer iscapitaloperate;

    @ApiModelProperty(value="")
    private Integer orgcode;

    @ApiModelProperty(value="")
    private Integer ind;

    @ApiModelProperty(value="")
    private String tel;

    @ApiModelProperty(value="")
    private String fax;

    @ApiModelProperty(value="")
    private String address;

    @ApiModelProperty(value="")
    private String zip;

    @ApiModelProperty(value="")
    private Integer perkind;

    @ApiModelProperty(value="")
    private Integer mofdep;

    @ApiModelProperty(value="")
    private Integer fundsup;

    @ApiModelProperty(value="")
    private Integer adminplan;

    @ApiModelProperty(value="")
    private Integer adminfact;

    @ApiModelProperty(value="")
    private Integer entprplan;

    @ApiModelProperty(value="")
    private Integer entprfact;

    @ApiModelProperty(value="")
    private Integer elsefact;

    @ApiModelProperty(value="")
    private Integer isunified;

    @ApiModelProperty(value="")
    private Integer islocal;

    @ApiModelProperty(value="")
    private Integer agencytype;

    @ApiModelProperty(value="")
    private Integer ishsagency;

    @ApiModelProperty(value="")
    private String admincode;

    @ApiModelProperty(value="")
    private Short govyear;

    @ApiModelProperty(value="")
    private Integer govid;

    @ApiModelProperty(value="")
    private Integer isysbzagency;

    @ApiModelProperty(value="")
    private Integer isyszxagency;

    @ApiModelProperty(value="")
    private Integer iszjzhagency;

    @ApiModelProperty(value="")
    private Integer isgdzcagency;

    @ApiModelProperty(value="")
    private Integer agencynature;

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

    public Short getLevelno() {
        return levelno;
    }

    public void setLevelno(Short levelno) {
        this.levelno = levelno;
    }

    public Short getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Short isleaf) {
        this.isleaf = isleaf;
    }

    public Integer getSuperitemid() {
        return superitemid;
    }

    public void setSuperitemid(Integer superitemid) {
        this.superitemid = superitemid;
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

    public Integer getEmwcode() {
        return emwcode;
    }

    public void setEmwcode(Integer emwcode) {
        this.emwcode = emwcode;
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

    public Integer getMofdepmanager() {
        return mofdepmanager;
    }

    public void setMofdepmanager(Integer mofdepmanager) {
        this.mofdepmanager = mofdepmanager;
    }

    public Integer getIscentralizedpay() {
        return iscentralizedpay;
    }

    public void setIscentralizedpay(Integer iscentralizedpay) {
        this.iscentralizedpay = iscentralizedpay;
    }

    public String getIsbncodebak() {
        return isbncodebak;
    }

    public void setIsbncodebak(String isbncodebak) {
        this.isbncodebak = isbncodebak;
    }

    public Integer getSecretdegree() {
        return secretdegree;
    }

    public void setSecretdegree(Integer secretdegree) {
        this.secretdegree = secretdegree;
    }

    public Integer getIsvirtual() {
        return isvirtual;
    }

    public void setIsvirtual(Integer isvirtual) {
        this.isvirtual = isvirtual;
    }

    public Integer getMofdepmanager1() {
        return mofdepmanager1;
    }

    public void setMofdepmanager1(Integer mofdepmanager1) {
        this.mofdepmanager1 = mofdepmanager1;
    }

    public Integer getMofdepmanager2() {
        return mofdepmanager2;
    }

    public void setMofdepmanager2(Integer mofdepmanager2) {
        this.mofdepmanager2 = mofdepmanager2;
    }

    public Integer getMofdepmanager3() {
        return mofdepmanager3;
    }

    public void setMofdepmanager3(Integer mofdepmanager3) {
        this.mofdepmanager3 = mofdepmanager3;
    }

    public Integer getMofdepmanager4() {
        return mofdepmanager4;
    }

    public void setMofdepmanager4(Integer mofdepmanager4) {
        this.mofdepmanager4 = mofdepmanager4;
    }

    public Integer getMofdepmanager5() {
        return mofdepmanager5;
    }

    public void setMofdepmanager5(Integer mofdepmanager5) {
        this.mofdepmanager5 = mofdepmanager5;
    }

    public Integer getIscapitaloperate() {
        return iscapitaloperate;
    }

    public void setIscapitaloperate(Integer iscapitaloperate) {
        this.iscapitaloperate = iscapitaloperate;
    }

    public Integer getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(Integer orgcode) {
        this.orgcode = orgcode;
    }

    public Integer getInd() {
        return ind;
    }

    public void setInd(Integer ind) {
        this.ind = ind;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Integer getPerkind() {
        return perkind;
    }

    public void setPerkind(Integer perkind) {
        this.perkind = perkind;
    }

    public Integer getMofdep() {
        return mofdep;
    }

    public void setMofdep(Integer mofdep) {
        this.mofdep = mofdep;
    }

    public Integer getFundsup() {
        return fundsup;
    }

    public void setFundsup(Integer fundsup) {
        this.fundsup = fundsup;
    }

    public Integer getAdminplan() {
        return adminplan;
    }

    public void setAdminplan(Integer adminplan) {
        this.adminplan = adminplan;
    }

    public Integer getAdminfact() {
        return adminfact;
    }

    public void setAdminfact(Integer adminfact) {
        this.adminfact = adminfact;
    }

    public Integer getEntprplan() {
        return entprplan;
    }

    public void setEntprplan(Integer entprplan) {
        this.entprplan = entprplan;
    }

    public Integer getEntprfact() {
        return entprfact;
    }

    public void setEntprfact(Integer entprfact) {
        this.entprfact = entprfact;
    }

    public Integer getElsefact() {
        return elsefact;
    }

    public void setElsefact(Integer elsefact) {
        this.elsefact = elsefact;
    }

    public Integer getIsunified() {
        return isunified;
    }

    public void setIsunified(Integer isunified) {
        this.isunified = isunified;
    }

    public Integer getIslocal() {
        return islocal;
    }

    public void setIslocal(Integer islocal) {
        this.islocal = islocal;
    }

    public Integer getAgencytype() {
        return agencytype;
    }

    public void setAgencytype(Integer agencytype) {
        this.agencytype = agencytype;
    }

    public Integer getIshsagency() {
        return ishsagency;
    }

    public void setIshsagency(Integer ishsagency) {
        this.ishsagency = ishsagency;
    }

    public String getAdmincode() {
        return admincode;
    }

    public void setAdmincode(String admincode) {
        this.admincode = admincode;
    }

    public Short getGovyear() {
        return govyear;
    }

    public void setGovyear(Short govyear) {
        this.govyear = govyear;
    }

    public Integer getGovid() {
        return govid;
    }

    public void setGovid(Integer govid) {
        this.govid = govid;
    }

    public Integer getIsysbzagency() {
        return isysbzagency;
    }

    public void setIsysbzagency(Integer isysbzagency) {
        this.isysbzagency = isysbzagency;
    }

    public Integer getIsyszxagency() {
        return isyszxagency;
    }

    public void setIsyszxagency(Integer isyszxagency) {
        this.isyszxagency = isyszxagency;
    }

    public Integer getIszjzhagency() {
        return iszjzhagency;
    }

    public void setIszjzhagency(Integer iszjzhagency) {
        this.iszjzhagency = iszjzhagency;
    }

    public Integer getIsgdzcagency() {
        return isgdzcagency;
    }

    public void setIsgdzcagency(Integer isgdzcagency) {
        this.isgdzcagency = isgdzcagency;
    }

    public Integer getAgencynature() {
        return agencynature;
    }

    public void setAgencynature(Integer agencynature) {
        this.agencynature = agencynature;
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
        sb.append(", levelno=").append(levelno);
        sb.append(", isleaf=").append(isleaf);
        sb.append(", superitemid=").append(superitemid);
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
        sb.append(", emwcode=").append(emwcode);
        sb.append(", bgtlevel=").append(bgtlevel);
        sb.append(", bgtlevelmark=").append(bgtlevelmark);
        sb.append(", bgtcode=").append(bgtcode);
        sb.append(", mofdepmanager=").append(mofdepmanager);
        sb.append(", iscentralizedpay=").append(iscentralizedpay);
        sb.append(", isbncodebak=").append(isbncodebak);
        sb.append(", secretdegree=").append(secretdegree);
        sb.append(", isvirtual=").append(isvirtual);
        sb.append(", mofdepmanager1=").append(mofdepmanager1);
        sb.append(", mofdepmanager2=").append(mofdepmanager2);
        sb.append(", mofdepmanager3=").append(mofdepmanager3);
        sb.append(", mofdepmanager4=").append(mofdepmanager4);
        sb.append(", mofdepmanager5=").append(mofdepmanager5);
        sb.append(", iscapitaloperate=").append(iscapitaloperate);
        sb.append(", orgcode=").append(orgcode);
        sb.append(", ind=").append(ind);
        sb.append(", tel=").append(tel);
        sb.append(", fax=").append(fax);
        sb.append(", address=").append(address);
        sb.append(", zip=").append(zip);
        sb.append(", perkind=").append(perkind);
        sb.append(", mofdep=").append(mofdep);
        sb.append(", fundsup=").append(fundsup);
        sb.append(", adminplan=").append(adminplan);
        sb.append(", adminfact=").append(adminfact);
        sb.append(", entprplan=").append(entprplan);
        sb.append(", entprfact=").append(entprfact);
        sb.append(", elsefact=").append(elsefact);
        sb.append(", isunified=").append(isunified);
        sb.append(", islocal=").append(islocal);
        sb.append(", agencytype=").append(agencytype);
        sb.append(", ishsagency=").append(ishsagency);
        sb.append(", admincode=").append(admincode);
        sb.append(", govyear=").append(govyear);
        sb.append(", govid=").append(govid);
        sb.append(", isysbzagency=").append(isysbzagency);
        sb.append(", isyszxagency=").append(isyszxagency);
        sb.append(", iszjzhagency=").append(iszjzhagency);
        sb.append(", isgdzcagency=").append(isgdzcagency);
        sb.append(", agencynature=").append(agencynature);
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