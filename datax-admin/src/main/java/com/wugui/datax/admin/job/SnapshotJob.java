package com.wugui.datax.admin.job;

import com.wugui.datax.admin.service.JobJdbcDatasourceSnapshotService;
import com.wugui.datax.admin.service.JobLogSnapshotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

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

    @Scheduled(cron = " 00 00 01 * * ?")
    public void cron() {
        log.info("log snapshot begin:");
        long start = System.currentTimeMillis();
        jobLogSnapshotService.genLogsSnapshot();
        log.info("log snapshot end,spend: {} ms", System.currentTimeMillis()
                - start);
    }

    /**
     * 快照数据源
     */
    @Scheduled(cron = "00 50 01 * * ?")
    public void cronDS() {
        log.info("datasource snapshot begin:");
        long start = System.currentTimeMillis();
        jobJdbcDatasourceSnapshotService.genSnapshot();
        log.info("datasource snapshot end,spend: {} ms", System.currentTimeMillis()
                - start);
    }
}
