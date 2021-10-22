package com.wugui.datax.admin.dto;

import lombok.Data;

import java.util.List;

 /**
  * @author: bahsk
  * @date: 2021-10-20 10:59
  * @description:  MultiJobsDTO
  * @params:
  * @return:
  */
@Data
public class MultiJobsDTO {

    // 任务组
    private List<Integer> jobs;

    // 数据源组
    private List<DatasourceDTO> dsList;
}
