package com.wugui.datax.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author bahsk
 * @createTime 2021-06-28 22:30
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResp<T> {
    private long total;

    private List<T> list;

}
