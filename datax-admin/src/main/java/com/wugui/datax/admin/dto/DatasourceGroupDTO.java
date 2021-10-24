package com.wugui.datax.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author bahsk
 * @createTime 2021-10-24 10:56
 * @description 项目定制
 * @program: datax-web
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatasourceGroupDTO {

    private List<DatasourceDTO> datasourceDTOList;

    private List<DatasourceRespDTO> datasourceRespDTOList;

}
