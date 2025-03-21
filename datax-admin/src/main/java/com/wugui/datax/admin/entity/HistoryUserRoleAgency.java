package com.wugui.datax.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Cat
 * @createTime 2023-10-09 9:54
 * @description
 */
@ApiModel(description = "t_history_user_role_agency")
@Data
public class HistoryUserRoleAgency implements Serializable {
    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "")
    private String userGuid;

    @ApiModelProperty(value = "")
    private Integer itemid;

    @ApiModelProperty(value = "")
    private String code;

    @ApiModelProperty(value = "")
    private String name;

    @ApiModelProperty(value = "")
    private Integer govid;

    @ApiModelProperty(value = "")
    private Integer belongYear;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
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

    public Integer getGovid() {
        return govid;
    }

    public void setGovid(Integer govid) {
        this.govid = govid;
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
        sb.append(", id=").append(id);
        sb.append(", userGuid=").append(userGuid);
        sb.append(", itemid=").append(itemid);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", govid=").append(govid);
        sb.append(", belongYear=").append(belongYear);
        sb.append("]");
        return sb.toString();
    }


    public HistoryUserRoleAgency(String userGuid,String belongYear, String code, String name,String govid) {
        this.userGuid = userGuid;
        this.belongYear = Integer.valueOf(belongYear);
        this.code = code;
        this.name = name;
        this.govid = Integer.valueOf(govid);
    }
}
