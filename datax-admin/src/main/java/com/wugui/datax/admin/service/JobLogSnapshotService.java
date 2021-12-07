package com.wugui.datax.admin.service;

import com.wugui.datatx.core.biz.model.ReturnT;

/**
 * @author bahsk
 * @createTime 2021-12-01 16:12
 * @description
 */
public interface JobLogSnapshotService {
     /**
      * @author: bahsk
      * @date: 2021-12-01 16:13
      * @description: create job log snapshot
      * @params:
      * @return:
      */
    ReturnT<String> genLogsSnapshot();
}
