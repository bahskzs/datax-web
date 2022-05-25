package com.wugui.datax.admin.controller;

import com.wugui.datax.admin.dto.CommonResp;
import com.wugui.datax.admin.dto.JobDatasourceDTO;
import com.wugui.datax.admin.dto.JobResourceDTO;
import com.wugui.datax.admin.dto.PageResp;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.JobResource;
import com.wugui.datax.admin.service.JobResourceService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author bahsk
 * @createTime 2022-01-25 17:04
 * @description 资源配置接口
 */
@RestController
@RequestMapping("/api/jobResource")
@Api(tags = "资源配置接口")
public class JobResourceController {

    @Resource
    private JobResourceService jobResourceService;

    @GetMapping("/list")
    public CommonResp list(JobResourceDTO jobResourceDTO) {
        CommonResp<PageResp<JobResource>> listCommonResp = new CommonResp<>();
        PageResp<JobResource> list = jobResourceService.list(jobResourceDTO);
        listCommonResp.setContent(list);
        return listCommonResp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody JobResource jobResource) {
        CommonResp commonResp = jobResourceService.save(jobResource);
        return  commonResp;
    }
}
