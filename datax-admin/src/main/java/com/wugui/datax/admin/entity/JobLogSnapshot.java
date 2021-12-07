package com.wugui.datax.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * job_log_snapshot
 * @author 
 */
@ApiModel(value="com.wugui.datax.admin.entity.JobLogSnapshot")
@Data
public class JobLogSnapshot implements Serializable {
    private Long id;

    /**
     * 执行器主键ID
     */
    @ApiModelProperty(value="执行器主键ID")
    private Integer jobGroup;

    /**
     * 任务，主键ID
     */
    @ApiModelProperty(value="任务，主键ID")
    private Integer jobId;

    private String jobDesc;

    /**
     * 执行器地址，本次执行的地址
     */
    @ApiModelProperty(value="执行器地址，本次执行的地址")
    private String executorAddress;

    /**
     * 执行器任务handler
     */
    @ApiModelProperty(value="执行器任务handler")
    private String executorHandler;

    /**
     * 执行器任务参数
     */
    @ApiModelProperty(value="执行器任务参数")
    private String executorParam;

    /**
     * 执行器任务分片参数，格式如 1/2
     */
    @ApiModelProperty(value="执行器任务分片参数，格式如 1/2")
    private String executorShardingParam;

    /**
     * 失败重试次数
     */
    @ApiModelProperty(value="失败重试次数")
    private Integer executorFailRetryCount;

    /**
     * 调度-时间
     */
    @ApiModelProperty(value="调度-时间")
    private Date triggerTime;

    /**
     * 调度-结果
     */
    @ApiModelProperty(value="调度-结果")
    private Integer triggerCode;

    /**
     * 调度-日志
     */
    @ApiModelProperty(value="调度-日志")
    private String triggerMsg;

    /**
     * 执行-时间
     */
    @ApiModelProperty(value="执行-时间")
    private Date handleTime;

    /**
     * 执行-状态
     */
    @ApiModelProperty(value="执行-状态")
    private Integer handleCode;

    /**
     * 执行-日志
     */
    @ApiModelProperty(value="执行-日志")
    private String handleMsg;

    /**
     * 告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败
     */
    @ApiModelProperty(value="告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败")
    private Byte alarmStatus;

    /**
     * datax进程Id
     */
    @ApiModelProperty(value="datax进程Id")
    private String processId;

    /**
     * 增量表max id
     */
    @ApiModelProperty(value="增量表max id")
    private Long maxId;

    /**
     * 快照时间
     */
    @ApiModelProperty(value="快照时间")
    private Date snapshotTime;

    private static final long serialVersionUID = 1L;
}