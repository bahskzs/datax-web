package com.wugui.datax.admin.service.impl;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.JobJdbcDatasourceSnapshot;
import com.wugui.datax.admin.mapper.JobDatasourceMapper;
import com.wugui.datax.admin.mapper.JobJdbcDatasourceSnapshotMapper;
import com.wugui.datax.admin.service.JobJdbcDatasourceSnapshotService;
import com.wugui.datax.admin.util.AESUtil;
import com.wugui.datax.admin.util.CopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author bahsk
 * @createTime 2021-12-01 16:14
 * @description
 */
@Service
@Slf4j
public class JobJdbcDatasourceSnapshotServiceImpl implements JobJdbcDatasourceSnapshotService {

    @Resource
    private JobJdbcDatasourceSnapshotMapper jobJdbcDatasourceSnapshotMapper;

    @Resource
    private JobDatasourceMapper jobDatasourceMapper;
    /**
     * @author: bahsk
     * @date: 2021-12-01 16:13
     * @description: 解密
     * @params:
     * @return:
     */
    @Override
    public ReturnT<String> genSnapshot() {

        //查询数据,解密,生成快照
        List<JobDatasource> jobDatasources = jobDatasourceMapper.selectList(null);
        List<JobJdbcDatasourceSnapshot> jobJdbcDatasourceSnapshots = CopyUtil.copyList(jobDatasources, JobJdbcDatasourceSnapshot.class);
        for(JobJdbcDatasourceSnapshot jobJdbcDatasourceSnapshot :  jobJdbcDatasourceSnapshots) {
            String userName = AESUtil.decrypt(jobJdbcDatasourceSnapshot.getJdbcUsername());
            jobJdbcDatasourceSnapshot.setJdbcUsername(userName);
            jobJdbcDatasourceSnapshot.setSnapshotTime(new Date());
            log.info("jobJdbcDatasourceSnapshot.status : {}",jobJdbcDatasourceSnapshot.getStatus());
            jobJdbcDatasourceSnapshotMapper.insert(jobJdbcDatasourceSnapshot);
        }

        return new ReturnT<>("快照成功");
    }
}
