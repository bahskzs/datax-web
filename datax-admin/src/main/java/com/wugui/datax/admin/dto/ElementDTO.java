package com.wugui.datax.admin.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Cat
 * @createTime 2023-09-17 22:23
 * @description 历史数据要素DTO
 */

@Data
public class ElementDTO {

    private String id;
    private String pid;
    private String code;
    private String name;
    private String year;
    private String areaCode;
    private List<ElementDTO> children;
}
