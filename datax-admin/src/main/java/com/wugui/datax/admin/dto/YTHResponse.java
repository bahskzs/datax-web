package com.wugui.datax.admin.dto;

import lombok.Data;
import org.apache.catalina.User;

import java.util.List;

/**
 * @author Cat
 * @createTime 2023-09-20 11:35
 * @description
 */
@Data
public class YTHResponse {
    private List<YTHUserDTO> data;
    private String result;
    private String rscode;
}
