package com.wugui.datax.admin.job;

import com.wugui.datax.admin.service.JobJdbcDatasourceSnapshotService;
import com.wugui.datax.admin.service.JobLogSnapshotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author bahsk
 * @createTime 2021-12-01 16:17
 * @description
 */

@Component
@Slf4j
public class SnapshotJob {

    @Resource
    private JobLogSnapshotService jobLogSnapshotService;

    @Resource
    private JobJdbcDatasourceSnapshotService jobJdbcDatasourceSnapshotService;

    @Scheduled(cron = "01 01 01 * * *")
    public void cron() {
        log.info("快照日志表");
        long start = System.currentTimeMillis();
        jobLogSnapshotService.genLogsSnapshot();
        log.info("快照日志表数据结束,耗时: {}毫秒", System.currentTimeMillis()
                - start);
    }

    /**
     * TODO 临时方法
     */
    @Scheduled(cron = "20 34 10 * * *")
    public void cronDS() {
        log.info("快照数据源表");
        long start = System.currentTimeMillis();
        jobJdbcDatasourceSnapshotService.genSnapshot();
        log.info("快照数据源表数据结束,耗时: {}毫秒", System.currentTimeMillis()
                - start);
    }
}
