package com.wugui.datax.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用于启动任务接收的实体
 *
 * @author jingwk
 * @ClassName TriggerJobDto
 * @Version 1.0
 * @since 2019/12/01 16:12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TriggerJobRespDTO implements Serializable {

    private String executorParam;

    private int jobId;

    private String jobDesc;
}
