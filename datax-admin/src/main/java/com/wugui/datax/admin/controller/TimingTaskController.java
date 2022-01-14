package com.wugui.datax.admin.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.service.JobJdbcDatasourceSnapshotService;
import com.wugui.datax.admin.service.JobLogSnapshotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author bahsk
 * @createTime 2021-12-07 10:59
 * @description  用于测试调用定时任务对应的方法
 */
@RestController
@RequestMapping("api/timetask")
@Api(tags = "[项目定制]定时任务测试执行")
public class TimingTaskController {

    @Resource
    private JobLogSnapshotService jobLogSnapshotService;

    @Resource
    private JobJdbcDatasourceSnapshotService jobJdbcDatasourceSnapshotService;

    @PostMapping("/genLogSnapshot")
    @ApiOperation("日志快照")
    public ReturnT<String> genLogSnapshot() {
        this.jobLogSnapshotService.genLogsSnapshot();
        return new ReturnT<>("success");
    }

    @PostMapping("/genDSSnapshot")
    @ApiOperation("数据源快照并解密username")
    public ReturnT<String> genDSSnapshot() {
        this.jobJdbcDatasourceSnapshotService.genSnapshot();
        return new ReturnT<>("success");
    }
}
