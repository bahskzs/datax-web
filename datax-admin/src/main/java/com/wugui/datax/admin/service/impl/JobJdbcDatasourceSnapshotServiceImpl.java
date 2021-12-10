package com.wugui.datax.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.dto.JobDatasourceSnapshotDTO;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.JobJdbcDatasourceSnapshot;
import com.wugui.datax.admin.mapper.JobDatasourceMapper;
import com.wugui.datax.admin.mapper.JobJdbcDatasourceSnapshotMapper;
import com.wugui.datax.admin.service.JobJdbcDatasourceSnapshotService;
import com.wugui.datax.admin.util.AESUtil;
import com.wugui.datax.admin.util.CopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
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
    @Transactional
    public ReturnT<String> genSnapshot() {

        //生成快照
        jobJdbcDatasourceSnapshotMapper.genSnapshot();

        //查询出状态是-1的记录并解密, -1代表用户未解密的数据
        QueryWrapper<JobJdbcDatasourceSnapshot> sourceQueryWrapper = new QueryWrapper<>();
        sourceQueryWrapper.eq("status",-1);
        List<JobJdbcDatasourceSnapshot> jobJdbcDatasourceSnapshots = jobJdbcDatasourceSnapshotMapper.selectList(sourceQueryWrapper);

        // 遍历并解密
        for(JobJdbcDatasourceSnapshot jobJdbcDatasourceSnapshot :  jobJdbcDatasourceSnapshots) {
            String userName = AESUtil.decrypt(jobJdbcDatasourceSnapshot.getJdbcUsername());
            jobJdbcDatasourceSnapshot.setJdbcUsername(userName);
            //log.info("jobJdbcDatasourceSnapshot.status : {}",jobJdbcDatasourceSnapshot.getStatus());
            jobJdbcDatasourceSnapshot.setStatus(1);
            jobJdbcDatasourceSnapshotMapper.updateById(jobJdbcDatasourceSnapshot);
        }
        return new ReturnT<>("快照成功");
    }
}
