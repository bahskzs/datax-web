package com.wugui.admin.dao;

import com.wugui.datax.admin.DataXAdminApplication;
import com.wugui.datax.admin.entity.JobDsEnvironment;
import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.mapper.JobDsEnvironmentMapper;
import com.wugui.datax.admin.service.JobDsEnvironmentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataXAdminApplication.class)
public class JobDsEnvironmentTest {

    @Resource
    JobDsEnvironmentMapper jobDsEnvironmentMapper;
    @Resource
    JobDsEnvironmentService JobDsEnvironmentService;
    @Test
    public void test(){
        JobDsEnvironment jobDsEnvironment=new JobDsEnvironment();


        jobDsEnvironment.setId(2L);
        jobDsEnvironment.setDatasourceId(725);
        int countNum = JobDsEnvironmentService.selectCountById(jobDsEnvironment);
        System.out.println("+++++++++++++++++++++++++++++++++++++"+countNum);
    }
}
