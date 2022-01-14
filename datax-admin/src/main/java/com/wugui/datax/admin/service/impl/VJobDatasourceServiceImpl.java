package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.entity.VJobDatasource;
import com.wugui.datax.admin.entity.VJobDatasourceExample;
import com.wugui.datax.admin.mapper.VJobDatasourceMapper;
import com.wugui.datax.admin.service.VJobDatasourceService;
import com.wugui.datax.admin.util.CopyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bahsk
 * @createTime 2022-01-12 16:41
 * @description
 */
@Service
public class VJobDatasourceServiceImpl implements VJobDatasourceService {

    @Resource
    private VJobDatasourceMapper vJobDatasourceMapper;
    /**
     * @param sourceUser
     * @param sourceUrl
     * @author: bahsk
     * @date: 2022-01-12 16:39
     * @description: 查询指定的job list
     * @params: sourceUser, sourceUrl
     * @return:
     */
    @Override
    public List<JobInfo> findByParams(String sourceUser, String sourceUrl, String type) {

        //查询匹配job列表
        VJobDatasourceExample vJobDatasourceExample = new VJobDatasourceExample();

        if(StringUtils.equals("source",type)) {
            vJobDatasourceExample.createCriteria().andSourceuserEqualTo(sourceUser).andSourceurlEqualTo(sourceUrl);
        } else {
            vJobDatasourceExample.createCriteria().andTargetuserEqualTo(sourceUser).andTargeturlEqualTo(sourceUrl);
        }

        List<VJobDatasource> vJobDatasources = vJobDatasourceMapper.selectByExample(vJobDatasourceExample);
        List<JobInfo> jobInfoList = new ArrayList<>();

        vJobDatasources.forEach((vJobDatasource)->{
            JobInfo jobInfo = new JobInfo();
            jobInfo.setId(vJobDatasource.getId());
            jobInfo.setJobDesc(vJobDatasource.getJobDesc());
            jobInfo.setJobJson(vJobDatasource.getJobJson());
            jobInfoList.add(jobInfo);
        });

        return jobInfoList;
    }
}
