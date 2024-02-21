create database if not exists data_service default character set utf8mb4 collate utf8mb4_unicode_ci;
use data_service;

CREATE TABLE `job_group`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `group`        varchar(64) NOT NULL COMMENT '分组',
    `creator`      varchar(32) NOT NULL DEFAULT 'system' COMMENT '创建人',
    `modifier`     varchar(32) NOT NULL DEFAULT 'system' COMMENT '修改人',
    `deleter`      varchar(32) COMMENT '删除人',
    `gmt_create`   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `gmt_delete`   datetime COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_group` (`group`, `gmt_delete`)
) ENGINE = InnoDB COMMENT ='任务分组';

INSERT INTO `job_group` (`id`, `group`, `creator`, `modifier`)
VALUES (1, 'jst-old', 'system', 'system');
INSERT INTO `job_group` (`id`, `group`, `creator`, `modifier`)
VALUES (2, 'kwai-merchant', 'system', 'system');
INSERT INTO `job_group` (`id`, `group`, `creator`, `modifier`)
VALUES (3, 'kwai-locallife', 'system', 'system');
INSERT INTO `job_group` (`id`, `group`, `creator`, `modifier`)
VALUES (4, 'jd-union', 'system', 'system');

CREATE TABLE `job_info`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `group_id`     bigint(20) NOT NULL COMMENT '分组id',
    `job`          varchar(255) NOT NULL COMMENT '任务',
    `sub_job`      varchar(255) NOT NULL COMMENT '子任务',
    `creator`      varchar(32)  NOT NULL DEFAULT 'system' COMMENT '创建人',
    `modifier`     varchar(32)  NOT NULL DEFAULT 'system' COMMENT '修改人',
    `deleter`      varchar(32) COMMENT '删除人',
    `gmt_create`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `gmt_delete`   datetime COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_job` (`group_id`, `job`, `sub_job`, `gmt_delete`)
) ENGINE = InnoDB COMMENT ='任务信息';

INSERT INTO `job_info` (`id`, `group_id`, `job`, `sub_job`, `creator`, `modifier`)
VALUES (1, 1, 'orders.single.query', 'normal', 'system', 'system');
INSERT INTO `job_info` (`id`, `group_id`, `job`, `sub_job`, `creator`, `modifier`)
VALUES (2, 1, 'orders.single.query', 'backtrack', 'system', 'system');
INSERT INTO `job_info` (`id`, `group_id`, `job`, `sub_job`, `creator`, `modifier`)
VALUES (3, 1, 'orders.single.query', 'ts', 'system', 'system');

CREATE TABLE `job_authorization`
(
    `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `account`       varchar(255) NOT NULL COMMENT '主账号',
    `sub_account`   varchar(255) COMMENT '子账号',
    `authorization` varchar(255) COMMENT '授权信息',
    `creator`       varchar(32)  NOT NULL DEFAULT 'system' COMMENT '创建人',
    `modifier`      varchar(32)  NOT NULL DEFAULT 'system' COMMENT '修改人',
    `deleter`       varchar(32) COMMENT '删除人',
    `gmt_create`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `gmt_delete`    datetime COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_account` (`account`, `sub_account`, `gmt_delete`)
) ENGINE = InnoDB COMMENT ='任务授权';

INSERT INTO `job_authorization` (`id`, `account`, `sub_account`, `authorization`, `creator`, `modifier`)
VALUES (1, '聚水潭-测试账号', NULL, '{"partnerId":"id","token":"token","partnerKey":"key"}', 'system', 'system');

CREATE TABLE `job_sync_offset`
(
    `id`               bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `last_sync_offset` varchar(255) COMMENT '上次进度位点',
    `sync_offset`      varchar(255) COMMENT '进度位点',
    `creator`          varchar(32) NOT NULL DEFAULT 'system' COMMENT '创建人',
    `modifier`         varchar(32) NOT NULL DEFAULT 'system' COMMENT '修改人',
    `deleter`          varchar(32) COMMENT '删除人',
    `gmt_create`       datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`     datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `gmt_delete`       datetime COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='任务进度位点';

CREATE TABLE `job_instance`
(
    `id`               bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `job_id`           bigint(20) NOT NULL COMMENT '任务id',
    `authorization_id` bigint(20) NOT NULL COMMENT '授权id',
    `sync_offset_id`   bigint(20) COMMENT '进度位点id',
    `creator`          varchar(32) NOT NULL DEFAULT 'system' COMMENT '创建人',
    `modifier`         varchar(32) NOT NULL DEFAULT 'system' COMMENT '修改人',
    `deleter`          varchar(32) COMMENT '删除人',
    `gmt_create`       datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`     datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `gmt_delete`       datetime COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_key` (`job_id`, `authorization_id`, `sync_offset_id`, `gmt_delete`)
) ENGINE = InnoDB COMMENT ='任务实例';

INSERT INTO `job_instance` (`id`, `job_id`, `authorization_id`, `sync_offset_id`, `creator`, `modifier`)
VALUES (1, 1, 1, NULL, 'system', 'system');