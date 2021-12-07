package com.wugui.datax.admin.service.impl;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.mapper.JobLogSnapshotMapper;
import com.wugui.datax.admin.service.JobLogSnapshotService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author bahsk
 * @createTime 2021-12-01 16:14
 * @description
 */
@Service
public class JobLogSnapshotServiceImpl implements JobLogSnapshotService {

    @Resource
    private JobLogSnapshotMapper jobLogSnapshotMapper;
    /**
     * @author: bahsk
     * @date: 2021-12-01 16:13
     * @description: create job log snapshot
     * @params:
     * @return:
     */
    @Override
    public ReturnT<String> genLogsSnapshot() {
        jobLogSnapshotMapper.genLogsSnapshot();
        return new ReturnT<>("快照成功");
    }
}
