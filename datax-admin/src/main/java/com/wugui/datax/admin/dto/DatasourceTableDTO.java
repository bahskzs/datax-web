package com.wugui.datax.admin.dto;

import lombok.Data;

import java.util.List;

@Data
public
class DatasourceTableDTO {
    List<String> tableList;
    List<Integer> datasourceList;
}
