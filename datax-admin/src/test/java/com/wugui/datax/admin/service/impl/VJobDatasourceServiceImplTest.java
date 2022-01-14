package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.entity.VJobDatasource;
import com.wugui.datax.admin.mapper.VJobDatasourceMapper;
import com.wugui.datax.admin.service.VJobDatasourceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;


/**
 * @author bahsk
 * @createTime 2022-01-13 11:21
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VJobDatasourceServiceImplTest {

    @Resource
    private VJobDatasourceService vJobDatasourceService;

    @Resource
    private VJobDatasourceMapper vJobDatasourceMapper;


    @Test
    public void findByParams() {
        List<JobInfo> target = this.vJobDatasourceService.findByParams("QV2kK7Dm8oDGpRucAPyeUA==", "jdbc:oracle:thin:@//192.168.100.49:1521/ytdb5", "target");
        Assert.assertEquals(1,target.size());
    }

    @Test
    public void findList() {
        List<VJobDatasource> vJobDatasources = vJobDatasourceMapper.selectByExample(null);
        Assert.assertTrue(vJobDatasources.size()>0);
        System.out.println(vJobDatasources.size());
    }
}
