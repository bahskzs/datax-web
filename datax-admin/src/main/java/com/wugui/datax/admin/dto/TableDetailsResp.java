package com.wugui.datax.admin.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author bahsk
 * @createTime 2021-12-20 10:23
 * @description
 */
@Builder
@Data
public class TableDetailsResp {
    private String tableName;
    private String ddlSQL;
}
