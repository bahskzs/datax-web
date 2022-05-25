package com.wugui.datax.admin.service;


import com.wugui.datax.admin.dto.CommonResp;
import com.wugui.datax.admin.dto.JobResourceDTO;
import com.wugui.datax.admin.dto.PageResp;
import com.wugui.datax.admin.entity.JobResource;

import java.util.List;

/**
 * @author bahsk
 * @createTime 2022-01-25 15:03
 * @description 任务资源
 */
public interface JobResourceService {


     PageResp<JobResource> list(JobResourceDTO jobResourceDTO);

     CommonResp save(JobResource jobResource);

}
