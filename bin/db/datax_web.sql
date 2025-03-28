/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : datax_web

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 15/12/2019 22:27:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for job_group
-- ----------------------------
DROP TABLE IF EXISTS `job_group`;
CREATE TABLE `job_group`
(
    `id`           int(11)                                                       NOT NULL AUTO_INCREMENT,
    `app_name`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '执行器AppName',
    `title`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '执行器名称',
    `order`        int(11)                                                       NOT NULL DEFAULT 0 COMMENT '排序',
    `address_type` tinyint(4)                                                    NOT NULL DEFAULT 0 COMMENT '执行器地址类型：0=自动注册、1=手动录入',
    `address_list` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '执行器地址列表，多地址逗号分隔',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job_group
-- ----------------------------
INSERT INTO `job_group`
VALUES (1, 'datax-executor', 'datax执行器', 1, 0, NULL);

-- ----------------------------
-- Table structure for job_info
-- ----------------------------
CREATE TABLE `job_info` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
                            `job_cron` varchar(128) NOT NULL COMMENT '任务执行CRON',
                            `job_desc` varchar(255) NOT NULL,
                            `project_id` int(11) DEFAULT NULL COMMENT '所属项目id',
                            `add_time` datetime DEFAULT NULL,
                            `update_time` datetime DEFAULT NULL,
                            `user_id` int(11) NOT NULL COMMENT '修改用户',
                            `alarm_email` varchar(255) DEFAULT NULL COMMENT '报警邮件',
                            `executor_route_strategy` varchar(50) DEFAULT NULL COMMENT '执行器路由策略',
                            `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
                            `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
                            `executor_block_strategy` varchar(50) DEFAULT NULL COMMENT '阻塞处理策略',
                            `executor_timeout` int(11) NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位分钟',
                            `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
                            `glue_type` varchar(50) NOT NULL COMMENT 'GLUE类型',
                            `glue_source` mediumtext COMMENT 'GLUE源代码',
                            `glue_remark` varchar(128) DEFAULT NULL COMMENT 'GLUE备注',
                            `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
                            `child_jobid` varchar(255) DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
                            `trigger_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
                            `trigger_last_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '上次调度时间',
                            `trigger_next_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '下次调度时间',
                            `job_json` text CHARACTER SET utf8 COMMENT 'datax运行脚本',
                            `replace_param` varchar(100) DEFAULT NULL COMMENT '动态参数',
                            `jvm_param` varchar(200) DEFAULT NULL COMMENT 'jvm参数',
                            `inc_start_time` datetime DEFAULT NULL COMMENT '增量初始时间',
                            `partition_info` varchar(100) DEFAULT NULL COMMENT '分区信息',
                            `last_handle_code` int(11) DEFAULT '0' COMMENT '最近一次执行状态',
                            `replace_param_type` varchar(255) DEFAULT NULL COMMENT '增量时间格式',
                            `reader_table` varchar(255) DEFAULT NULL COMMENT 'reader表名称',
                            `primary_key` varchar(50) DEFAULT NULL COMMENT '增量表主键',
                            `inc_start_id` varchar(20) DEFAULT NULL COMMENT '增量初始id',
                            `increment_type` tinyint(4) DEFAULT '0' COMMENT '增量类型',
                            `datasource_id` bigint(11) DEFAULT NULL COMMENT '数据源id',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8211 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



-- ----------------------------
-- Table structure for job_jdbc_datasource
-- ----------------------------
DROP TABLE IF EXISTS `job_jdbc_datasource`;
CREATE TABLE `job_jdbc_datasource`
(
    `id`                bigint(20)                                                     NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `datasource_name`   varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '数据源名称',
    `datasource_group`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT 'Default' COMMENT '数据源分组',
    `jdbc_username`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户名',
    `jdbc_password`     varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '密码',
    `jdbc_url`          varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'jdbc url',
    `jdbc_driver_class` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT 'jdbc驱动类',
    `status`            tinyint(1)                                                     NOT NULL DEFAULT 1 COMMENT '状态：0删除 1启用 2禁用',
    `create_by`         varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL     DEFAULT NULL COMMENT '创建人',
    `create_date`       datetime(0)                                                    NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_by`         varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL     DEFAULT NULL COMMENT '更新人',
    `update_date`       datetime(0)                                                    NULL     DEFAULT NULL COMMENT '更新时间',
    `comments`          varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'jdbc数据源配置'
  ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for job_lock
-- ----------------------------
DROP TABLE IF EXISTS `job_lock`;
CREATE TABLE `job_lock`
(
    `lock_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '锁名称',
    PRIMARY KEY (`lock_name`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job_lock
-- ----------------------------
INSERT INTO `job_lock`
VALUES ('schedule_lock');

-- ----------------------------
-- Table structure for job_log
-- ----------------------------
DROP TABLE IF EXISTS `job_log`;
CREATE TABLE `job_log`
(
    `id`                        bigint(20)                                                    NOT NULL AUTO_INCREMENT,
    `job_group`                 int(11)                                                       NOT NULL COMMENT '执行器主键ID',
    `job_id`                    int(11)                                                       NOT NULL COMMENT '任务，主键ID',
    `job_desc`                  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `executor_address`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
    `executor_handler`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '执行器任务handler',
    `executor_param`            varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '执行器任务参数',
    `executor_sharding_param`   varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
    `executor_fail_retry_count` int(11)                                                       NULL     DEFAULT 0 COMMENT '失败重试次数',
    `trigger_time`              datetime(0)                                                   NULL     DEFAULT NULL COMMENT '调度-时间',
    `trigger_code`              int(11)                                                       NOT NULL COMMENT '调度-结果',
    `trigger_msg`               text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '调度-日志',
    `handle_time`               datetime(0)                                                   NULL     DEFAULT NULL COMMENT '执行-时间',
    `handle_code`               int(11)                                                       NOT NULL COMMENT '执行-状态',
    `handle_msg`                text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '执行-日志',
    `alarm_status`              tinyint(4)                                                    NOT NULL DEFAULT 0 COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
    `process_id`                varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT 'datax进程Id',
    `max_id`                    bigint(20)                                                    NULL     DEFAULT NULL COMMENT '增量表max id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `I_trigger_time` (`trigger_time`) USING BTREE,
    INDEX `I_handle_code` (`handle_code`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `job_log_report`;
CREATE TABLE `job_log_report`
(
    `id`            int(11)     NOT NULL AUTO_INCREMENT,
    `trigger_day`   datetime(0) NULL     DEFAULT NULL COMMENT '调度-时间',
    `running_count` int(11)     NOT NULL DEFAULT 0 COMMENT '运行中-日志数量',
    `suc_count`     int(11)     NOT NULL DEFAULT 0 COMMENT '执行成功-日志数量',
    `fail_count`    int(11)     NOT NULL DEFAULT 0 COMMENT '执行失败-日志数量',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 28
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job_log_report
-- ----------------------------
INSERT INTO `job_log_report`
VALUES (20, '2019-12-07 00:00:00', 0, 0, 0);
INSERT INTO `job_log_report`
VALUES (21, '2019-12-10 00:00:00', 77, 52, 23);
INSERT INTO `job_log_report`
VALUES (22, '2019-12-11 00:00:00', 9, 2, 11);
INSERT INTO `job_log_report`
VALUES (23, '2019-12-13 00:00:00', 9, 48, 74);
INSERT INTO `job_log_report`
VALUES (24, '2019-12-12 00:00:00', 10, 8, 30);
INSERT INTO `job_log_report`
VALUES (25, '2019-12-14 00:00:00', 78, 45, 66);
INSERT INTO `job_log_report`
VALUES (26, '2019-12-15 00:00:00', 24, 76, 9);
INSERT INTO `job_log_report`
VALUES (27, '2019-12-16 00:00:00', 23, 85, 10);

-- ----------------------------
-- Table structure for job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `job_logglue`;
CREATE TABLE `job_logglue`
(
    `id`          int(11)                                                       NOT NULL AUTO_INCREMENT,
    `job_id`      int(11)                                                       NOT NULL COMMENT '任务，主键ID',
    `glue_type`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT 'GLUE类型',
    `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL COMMENT 'GLUE源代码',
    `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'GLUE备注',
    `add_time`    datetime(0)                                                   NULL DEFAULT NULL,
    `update_time` datetime(0)                                                   NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job_registry
-- ----------------------------
DROP TABLE IF EXISTS `job_registry`;
CREATE TABLE `job_registry`
(
    `id`             int(11)                                                       NOT NULL AUTO_INCREMENT,
    `registry_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `registry_key`   varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `registry_value` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `update_time`    datetime(0)                                                   NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `i_g_k_v` (`registry_group`, `registry_key`, `registry_value`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 26
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for job_user
-- ----------------------------
DROP TABLE IF EXISTS `job_user`;
CREATE TABLE `job_user`
(
    `id`         int(11)                                                       NOT NULL AUTO_INCREMENT,
    `username`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '账号',
    `password`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
    `role`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '角色：0-普通用户、1-管理员',
    `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `i_username` (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job_user
-- ----------------------------
INSERT INTO `job_user`
VALUES (1, 'admin', '$2a$10$2KCqRbra0Yn2TwvkZxtfLuWuUP5KyCWsljO/ci5pLD27pqR3TV1vy', 'ROLE_ADMIN', NULL);


/**
v2.1.1脚本更新
*/
ALTER TABLE `job_info`
    ADD COLUMN `replace_param` VARCHAR(100) NULL DEFAULT NULL COMMENT '动态参数' AFTER `job_json`,
    ADD COLUMN `jvm_param`     VARCHAR(200) NULL DEFAULT NULL COMMENT 'jvm参数' AFTER `replace_param`,
    ADD COLUMN `time_offset`   INT(11)      NULL DEFAULT '0' COMMENT '时间偏移量' AFTER `jvm_param`;
/**
增量改版脚本更新
 */
ALTER TABLE `job_info`
    DROP COLUMN `time_offset`;
ALTER TABLE `job_info`
    ADD COLUMN `inc_start_time` DATETIME NULL DEFAULT NULL COMMENT '增量初始时间' AFTER `jvm_param`;

-- ----------------------------
-- Table structure for job_template
-- ----------------------------
DROP TABLE IF EXISTS `job_template`;
CREATE TABLE `job_template`
(
    `id`                        int(11)                                                       NOT NULL AUTO_INCREMENT,
    `job_group`                 int(11)                                                       NOT NULL COMMENT '执行器主键ID',
    `job_cron`                  varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务执行CRON',
    `job_desc`                  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `add_time`                  datetime(0)                                                   NULL     DEFAULT NULL,
    `update_time`               datetime(0)                                                   NULL     DEFAULT NULL,
    `user_id`                   int(11)                                                       NOT NULL COMMENT '修改用户',
    `alarm_email`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '报警邮件',
    `executor_route_strategy`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '执行器路由策略',
    `executor_handler`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '执行器任务handler',
    `executor_param`            varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '执行器参数',
    `executor_block_strategy`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '阻塞处理策略',
    `executor_timeout`          int(11)                                                       NOT NULL DEFAULT 0 COMMENT '任务执行超时时间，单位秒',
    `executor_fail_retry_count` int(11)                                                       NOT NULL DEFAULT 0 COMMENT '失败重试次数',
    `glue_type`                 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'GLUE类型',
    `glue_source`               mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL COMMENT 'GLUE源代码',
    `glue_remark`               varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT 'GLUE备注',
    `glue_updatetime`           datetime(0)                                                   NULL     DEFAULT NULL COMMENT 'GLUE更新时间',
    `child_jobid`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
    `trigger_last_time`         bigint(13)                                                    NOT NULL DEFAULT 0 COMMENT '上次调度时间',
    `trigger_next_time`         bigint(13)                                                    NOT NULL DEFAULT 0 COMMENT '下次调度时间',
    `job_json`                  text CHARACTER SET utf8 COLLATE utf8_general_ci               NULL COMMENT 'datax运行脚本',
    `jvm_param`                 varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT 'jvm参数',
    `project_id`                int(11)                                                       NULL     DEFAULT NULL COMMENT '所属项目Id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 22
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

/**
添加数据源字段
 */
ALTER TABLE `job_jdbc_datasource`
    ADD COLUMN `datasource` VARCHAR(45) NOT NULL COMMENT '数据源' AFTER `datasource_name`;

/**
添加分区字段
 */
ALTER TABLE `job_info`
    ADD COLUMN `partition_info` VARCHAR(100) NULL DEFAULT NULL COMMENT '分区信息' AFTER `inc_start_time`;

/**
2.1.1版本新增----------------------------------------------------------------------------------------------
 */

/**
最近一次执行状态
 */
ALTER TABLE `job_info`
    ADD COLUMN `last_handle_code` INT(11) NULL DEFAULT '0' COMMENT '最近一次执行状态' AFTER `partition_info`;

/**
zookeeper地址
 */
ALTER TABLE `job_jdbc_datasource`
    ADD COLUMN `zk_adress` VARCHAR(200) NULL DEFAULT NULL AFTER `jdbc_driver_class`;

ALTER TABLE `job_info`
    CHANGE COLUMN `executor_timeout` `executor_timeout` INT(11) NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位分钟';

/**
用户名密码改为非必填
 */
ALTER TABLE `job_jdbc_datasource`
    CHANGE COLUMN `jdbc_username` `jdbc_username` VARCHAR(100) CHARACTER SET 'utf8mb4' NULL DEFAULT NULL COMMENT '用户名',
    CHANGE COLUMN `jdbc_password` `jdbc_password` VARCHAR(200) CHARACTER SET 'utf8mb4' NULL DEFAULT NULL COMMENT '密码';
/**
添加mongodb数据库名字段
 */
ALTER TABLE `job_jdbc_datasource`
    ADD COLUMN `database_name` VARCHAR(45) NULL DEFAULT NULL COMMENT '数据库名' AFTER `datasource_group`;
/**
添加执行器资源字段
 */
ALTER TABLE `job_registry`
    ADD COLUMN `cpu_usage`    DOUBLE NULL AFTER `registry_value`,
    ADD COLUMN `memory_usage` DOUBLE NULL AFTER `cpu_usage`,
    ADD COLUMN `load_average` DOUBLE NULL AFTER `memory_usage`;

-- ----------------------------
-- Table structure for job_permission
-- ----------------------------
DROP TABLE IF EXISTS `job_permission`;
CREATE TABLE `job_permission`
(
    `id`          int(11)                                                       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '权限名',
    `description` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '权限描述',
    `url`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `pid`         int(11)                                                       NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;



ALTER TABLE `job_info`
    ADD COLUMN `replace_param_type` varchar(255) NULL COMMENT '增量时间格式' AFTER `last_handle_code`;


ALTER TABLE `job_info`
    ADD COLUMN `project_id` int(11) NULL COMMENT '所属项目id' AFTER `job_desc`;

ALTER TABLE `job_info`
    ADD COLUMN `reader_table`   VARCHAR(255) NULL COMMENT 'reader表名称' AFTER `replace_param_type`,
    ADD COLUMN `primary_key`    VARCHAR(50)  NULL COMMENT '增量表主键' AFTER `reader_table`,
    ADD COLUMN `inc_start_id`   VARCHAR(20)  NULL COMMENT '增量初始id' AFTER `primary_key`,
    ADD COLUMN `increment_type` TINYINT(4)   NULL COMMENT '增量类型' AFTER `inc_start_id`,
    ADD COLUMN `datasource_id`  BIGINT(11)   NULL COMMENT '数据源id' AFTER `increment_type`;

CREATE TABLE `job_project`
(
    `id`          int(11)                                                       NOT NULL AUTO_INCREMENT COMMENT 'key',
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'project name',
    `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `user_id`     int(11)                                                       NULL DEFAULT NULL COMMENT 'creator id',
    `flag`        tinyint(4)                                                    NULL DEFAULT 1 COMMENT '0 not available, 1 available',
    `create_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT 'create time',
    `update_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT 'update time',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;


ALTER TABLE `job_info`
    CHANGE COLUMN `author` `user_id` INT(11) NOT NULL COMMENT '修改用户';

ALTER TABLE `job_info`
    CHANGE COLUMN `increment_type` `increment_type` TINYINT(4) NULL DEFAULT 0 COMMENT '增量类型';


-- auto-generated definition
create table job_jdbc_datasource_snapshot
(
    id               bigint                       default 0         not null comment '自增主键',
    datasource_name  varchar(200) charset utf8mb4                   not null comment '数据源名称',
    datasource       varchar(45) charset utf8mb4                    not null comment '数据源',
    datasource_group varchar(200) charset utf8mb4 default 'Default' null comment '数据源分组',
    database_name    varchar(45) charset utf8mb4                    null comment '数据库名',
    jdbc_username    varchar(100) charset utf8mb4                   null comment '用户名',
    jdbc_url         varchar(500) charset utf8mb4                   not null comment 'jdbc url',
    status           tinyint(1)                   default 1         not null comment '状态：0删除 1启用 2禁用',
    snapshot_time    date                                           null
);


create table job_log_snapshot
(
    id                        bigint  default 0            not null,
    job_group                 int                          not null comment '执行器主键ID',
    job_id                    int                          not null comment '任务，主键ID',
    job_desc                  varchar(255) charset utf8mb4 null,
    executor_address          varchar(255) charset utf8mb4 null comment '执行器地址，本次执行的地址',
    executor_handler          varchar(255) charset utf8mb4 null comment '执行器任务handler',
    executor_param            varchar(512) charset utf8mb4 null comment '执行器任务参数',
    executor_sharding_param   varchar(20) charset utf8mb4  null comment '执行器任务分片参数，格式如 1/2',
    executor_fail_retry_count int     default 0            null comment '失败重试次数',
    trigger_time              datetime                     null comment '调度-时间',
    trigger_code              int                          not null comment '调度-结果',
    trigger_msg               text charset utf8mb4         null comment '调度-日志',
    handle_time               datetime                     null comment '执行-时间',
    handle_code               int                          not null comment '执行-状态',
    handle_msg                text charset utf8mb4         null comment '执行-日志',
    alarm_status              tinyint default 0            not null comment '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
    process_id                varchar(20) charset utf8mb4  null comment 'datax进程Id',
    max_id                    bigint                       null comment '增量表max id',
    snapshot_time             date                         null comment '快照时间'
);


-- 创建视图  v_job_datasource job与数据源的关联视图
-- CREATE ALGORITHM = UNDEFINED DEFINER =`root`@`%` SQL SECURITY DEFINER VIEW v_job_datasource AS
-- select `datax_web`.`job_info`.`id`                                                                  AS `id`,
--        `datax_web`.`job_info`.`job_desc`                                                            AS `job_desc`,
--        trim(substring_index(`datax_web`.`job_info`.`job_desc`, '~', 1))                             AS `sourceName`,
--        trim(replace(substring_index(`datax_web`.`job_info`.`job_desc`, '~', 1), '生产-', '贴源-'))      AS `targetName`,
--        replace(json_extract(`datax_web`.`job_info`.`job_json`, '$.job.content[0].reader.parameter.username'), '"',
--                '')                                                                                  AS `sourceUser`,
--        replace(json_extract(`datax_web`.`job_info`.`job_json`, '$.job.content[0].writer.parameter.username'), '"',
--                '')                                                                                  AS `targetUser`,
--        replace(json_extract(`datax_web`.`job_info`.`job_json`,
--                             '$.job.content[0].reader.parameter.connection[0].jdbcUrl[0]'), '"', '') AS `sourceUrl`,
--        replace(json_extract(`datax_web`.`job_info`.`job_json`,
--                             '$.job.content[0].writer.parameter.connection[0].jdbcUrl'), '"', '')    AS `targetUrl`
-- from job_info;


CREATE TABLE `job_log_snapshot` (
                                    `id` bigint(20) NOT NULL DEFAULT '0',
                                    `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
                                    `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
                                    `job_desc` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
                                    `executor_address` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
                                    `executor_handler` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '执行器任务handler',
                                    `executor_param` varchar(512) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '执行器任务参数',
                                    `executor_sharding_param` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
                                    `executor_fail_retry_count` int(11) DEFAULT '0' COMMENT '失败重试次数',
                                    `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
                                    `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
                                    `trigger_msg` text CHARACTER SET utf8mb4 COMMENT '调度-日志',
                                    `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
                                    `handle_code` int(11) NOT NULL COMMENT '执行-状态',
                                    `handle_msg` text CHARACTER SET utf8mb4 COMMENT '执行-日志',
                                    `alarm_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
                                    `process_id` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'datax进程Id',
                                    `max_id` bigint(20) DEFAULT NULL COMMENT '增量表max id',
                                    `snapshot_time` date DEFAULT NULL COMMENT '快照时间',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


-- auto-generated definition
create table t_composite_query
(
    FMODULE_ID   varchar(20)  null comment '模块id',
    FMODULE_NAME varchar(100) null comment '模块名称',
    FTABLE_NAME  varchar(100) null comment '报表名称',
    FROW         varchar(10)  null comment '所在行',
    FR_NAME      varchar(50)  null comment '报表地址',
    FMODULE_CODE varchar(50)  null comment '模块代码',
    SORT_ID      int          null comment '排序',
    RN           decimal(4)   null,
    AREA_LIST    text         null comment '区划信息',
    STATUS       varchar(20)  null comment '状态;1启用，0禁用',
    id           int auto_increment comment 'id'
        primary key
)
    comment '历史数据模块报表明细表';


-- auto-generated definition
create table t_composite_query_all
(
    FMODULE_ID   varchar(20) null comment '模块id',
    FMODULE_NAME varchar(20) null comment '模块名称',
    FMODULE_CODE varchar(20) null comment '模块代码',
    F_STATUS     varchar(20) null comment '状态',
    SORT_ID      varchar(20) null comment '排序'
)
    comment '历史数据模块表';



-- auto-generated definition
create table area_list
(
    area_code varchar(20) null comment '区划代码',
    area_name varchar(50) null comment '区划名称'
)
    comment '区划表';

create table t_pubagency
(
    ITEMID                        decimal(9)   null,
    ELEMENTCODE                   tinytext     null,
    STARTDATE                     timestamp    null,
    CODE                          tinytext     null,
    NAME                          tinytext     null,
    SHORTNAME                     tinytext     null,
    WHOLENAME                     tinytext     null,
    ISBNCODE                      tinytext     null,
    LEVELNO                       decimal(3)   null,
    ISLEAF                        decimal(2)   null,
    SUPERITEMID                   decimal(9)   null,
    ENDDATE                       datetime     null,
    TYPE                          decimal(9)   null,
    SYSTEMPRETAG                  decimal(2)   null,
    REMARK                        varchar(510) null,
    STATUS                        decimal(9)   null,
    FROMITEMID                    decimal(9)   null,
    TOITEMID                      decimal(9)   null,
    CODEALIAS                     tinytext     null,
    BILLPROMISE                   tinytext     null,
    GUID                          tinytext     null,
    EMWCODE                       decimal(9)   null,
    BGTLEVEL                      decimal(9)   null,
    BGTLEVELMARK                  decimal(9)   null,
    BGTCODE                       tinytext     null,
    MOFDEPMANAGER                 decimal(9)   null,
    ISCENTRALIZEDPAY              decimal(9)   null,
    ISBNCODEBAK                   tinytext     null,
    SECRETDEGREE                  decimal(9)   null,
    ISVIRTUAL                     decimal(9)   null,
    MOFDEPMANAGER1                decimal(9)   null,
    MOFDEPMANAGER2                decimal(9)   null,
    MOFDEPMANAGER3                decimal(9)   null,
    MOFDEPMANAGER4                decimal(9)   null,
    MOFDEPMANAGER5                decimal(9)   null,
    ISCAPITALOPERATE              decimal(9)   null,
    ORGCODE                       decimal(9)   null,
    IND                           decimal(9)   null,
    TEL                           tinytext     null,
    FAX                           tinytext     null,
    ADDRESS                       varchar(400) null,
    ZIP                           tinytext     null,
    PERKIND                       decimal(9)   null,
    MOFDEP                        decimal(9)   null,
    FUNDSUP                       decimal(9)   null,
    ADMINPLAN                     decimal(9)   null,
    ADMINFACT                     decimal(9)   null,
    ENTPRPLAN                     decimal(9)   null,
    ENTPRFACT                     decimal(9)   null,
    ELSEFACT                      decimal(9)   null,
    ISUNIFIED                     decimal(9)   null,
    ISLOCAL                       decimal(9)   null,
    AGENCYTYPE                    decimal(9)   null,
    ISHSAGENCY                    decimal(9)   null,
    ADMINCODE                     tinytext     null,
    GOVYEAR                       decimal(4)   null,
    GOVID                         decimal(9)   null,
    ISYSBZAGENCY                  decimal(9)   null,
    ISYSZXAGENCY                  decimal(9)   null,
    ISZJZHAGENCY                  decimal(9)   null,
    ISGDZCAGENCY                  decimal(9)   null,
    AGENCYNATURE                  decimal(9)   null,
    SRC_DATA_NAME                 tinytext     null,
    SRC_SYSTEM_ID                 tinytext     null,
    SRC_SYSTEM_NAME               varchar(256) null,
    DATA_PROVIDER                 varchar(256) null,
    DATA_SRC_ID                   tinytext     null,
    DATA_COLLECTION_DATETIME      timestamp    null,
    DATA_COLLECTION_DEVICE_NUMBER tinytext     null,
    DATA_COLLECTION_PERSON_NAME   tinytext     null,
    BELONG_YEAR                   int          null
);

create table t_pubbdgmanagedivision
(
    ITEMID                        decimal(9)   null,
    ELEMENTCODE                   tinytext     null,
    STARTDATE                     timestamp    null,
    CODE                          tinytext     null,
    NAME                          tinytext     null,
    SHORTNAME                     tinytext     null,
    WHOLENAME                     tinytext     null,
    ISBNCODE                      tinytext     null,
    ENDDATE                       timestamp    null,
    TYPE                          decimal(9)   null,
    SYSTEMPRETAG                  decimal(2)   null,
    REMARK                        varchar(510) null,
    STATUS                        decimal(9)   null,
    FROMITEMID                    decimal(9)   null,
    TOITEMID                      decimal(9)   null,
    CODEALIAS                     tinytext     null,
    BILLPROMISE                   tinytext     null,
    GUID                          tinytext     null,
    BGTLEVEL                      decimal(9)   null,
    BGTLEVELMARK                  decimal(9)   null,
    BGTCODE                       tinytext     null,
    SECRETDEGREE                  tinytext     null,
    SUPPLYTYPE                    decimal(9)   null,
    AGENCYKIND                    decimal(9)   null,
    AGENCYLEVEL                   decimal(9)   null,
    GOVID                         decimal(9)   null,
    GOVYEAR                       decimal(4)   null,
    SRC_DATA_NAME                 tinytext     null,
    SRC_SYSTEM_ID                 tinytext     null,
    SRC_SYSTEM_NAME               varchar(256) null,
    DATA_PROVIDER                 varchar(256) null,
    DATA_SRC_ID                   tinytext     null,
    DATA_COLLECTION_DATETIME      timestamp    null,
    DATA_COLLECTION_DEVICE_NUMBER tinytext     null,
    DATA_COLLECTION_PERSON_NAME   tinytext     null,
    BELONG_YEAR                   int          null
);

create table t_pubgov
(
    GOVID                         decimal(9)   null,
    CODE                          tinytext     null,
    NAME                          tinytext     null,
    SUPERID                       decimal(9)   null,
    STATUS                        decimal(2)   null,
    GUID                          tinytext     null,
    TYPE                          decimal(2)   null,
    LEVELNO                       decimal(2)   null,
    SHORTCODE                     tinytext     null,
    SRC_DATA_NAME                 tinytext     null,
    SRC_SYSTEM_ID                 tinytext     null,
    SRC_SYSTEM_NAME               varchar(256) null,
    DATA_PROVIDER                 varchar(256) null,
    DATA_SRC_ID                   tinytext     null,
    DATA_COLLECTION_DATETIME      timestamp    null,
    DATA_COLLECTION_DEVICE_NUMBER tinytext     null,
    DATA_COLLECTION_PERSON_NAME   tinytext     null,
    BELONG_YEAR                   int          null
);

create table field_mapping
(
    id                int auto_increment comment 'id'
        primary key,
    fields_type       varchar(30)   null comment '字段类型',
    datasource        varchar(30)   null comment '源类型',
    source_field_type varchar(30)   null comment '源字段类型',
    target_datasource varchar(30)   null comment '目标类型',
    target_field_type varchar(30)   null comment '目标字段类型',
    comments          varchar(1000) null comment '备注'
)
    comment '字段映射管理';

create table job_ds_environment
(
    id              bigint       null,
    datasource_id   varchar(255) null,
    datasource_name varchar(200) null,
    datasource_type varchar(45)  null,
    path            varchar(255) null,
    default_fs      varchar(255) null,
    file_type       varchar(255) null,
    field_delimiter varchar(255) null,
    name_nodes      varchar(255) null,
    name_services   varchar(255) null,
    address         varchar(255) null,
    proxy_provider  varchar(255) null,
    status          varchar(255) null,
    create_by       varchar(255) null,
    create_date     datetime     null,
    update_by       varchar(255) null,
    update_date     datetime     null,
    comments        varchar(255) null
);

create table job_log_snapshot
(
    id                        bigint  default 0            not null
        primary key,
    job_group                 int                          not null comment '执行器主键ID',
    job_id                    int                          not null comment '任务，主键ID',
    job_desc                  varchar(255) charset utf8mb4 null,
    executor_address          varchar(255) charset utf8mb4 null comment '执行器地址，本次执行的地址',
    executor_handler          varchar(255) charset utf8mb4 null comment '执行器任务handler',
    executor_param            varchar(512) charset utf8mb4 null comment '执行器任务参数',
    executor_sharding_param   varchar(20) charset utf8mb4  null comment '执行器任务分片参数，格式如 1/2',
    executor_fail_retry_count int     default 0            null comment '失败重试次数',
    trigger_time              datetime                     null comment '调度-时间',
    trigger_code              int                          not null comment '调度-结果',
    trigger_msg               text charset utf8mb4         null comment '调度-日志',
    handle_time               datetime                     null comment '执行-时间',
    handle_code               int                          not null comment '执行-状态',
    handle_msg                text charset utf8mb4         null comment '执行-日志',
    alarm_status              tinyint default 0            not null comment '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
    process_id                varchar(20) charset utf8mb4  null comment 'datax进程Id',
    max_id                    bigint                       null comment '增量表max id',
    snapshot_time             date                         null comment '快照时间'
)
    charset = utf8mb3
    row_format = DYNAMIC;

