package com.wugui.datax.admin.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wugui.datax.admin.dto.CommonResp;
import com.wugui.datax.admin.dto.JobResourceDTO;
import com.wugui.datax.admin.dto.PageResp;
import com.wugui.datax.admin.entity.JobResource;
import com.wugui.datax.admin.entity.JobResourceExample;
import com.wugui.datax.admin.mapper.JobResourceMapper;

import com.wugui.datax.admin.service.JobResourceService;


import com.wugui.datax.admin.util.CopyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * jobResourceServiceImpl
 * @author jingwk
 * @since 2019-05-30
 * @version v2.1.2
 */
@Service("jobResourceService")
public class JobResourceServiceImpl  implements JobResourceService {

    @Resource
    private JobResourceMapper jobResourceMapper;


    /**
     *
     * @param jobResourceDTO
     * @return
     */
    @Override
    public PageResp<JobResource> list(JobResourceDTO jobResourceDTO) {
        JobResourceExample jobResourceExample = new JobResourceExample();
        JobResourceExample.Criteria criteria = jobResourceExample.createCriteria();
        List<JobResource> jobResourceList = new ArrayList<>();

        if (StringUtils.isBlank(jobResourceDTO.getSourceName())) {
            criteria.andSourceNameLike("%"+ jobResourceDTO.getSourceName() + "%");
            PageHelper.startPage(jobResourceDTO.getPage(), jobResourceDTO.getSize());
            jobResourceList = jobResourceMapper.selectByExample(jobResourceExample);


        } else {
            PageHelper.startPage(jobResourceDTO.getPage(), jobResourceDTO.getSize());
            jobResourceList = jobResourceMapper.selectByExample(null);
        }

        PageResp<JobResource> jobResourcePageResp = new PageResp<>();
        PageInfo<JobResource> jobResourcePageInfo = new PageInfo<>((List)jobResourceList);

        jobResourcePageResp.setList(jobResourceList);
        jobResourcePageResp.setTotal(jobResourcePageInfo.getTotal());

        return jobResourcePageResp;
    }

    @Override
    public CommonResp save(JobResource jobResource) {
        jobResourceMapper.insert(jobResource);
        CommonResp commonResp = new CommonResp();
        commonResp.setSuccess(true);
        return commonResp;
    }
}
