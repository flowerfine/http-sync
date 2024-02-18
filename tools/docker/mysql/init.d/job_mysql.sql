create database if not exists data_service default character set utf8mb4 collate utf8mb4_unicode_ci;
use data_service;

CREATE TABLE `job_group`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `group`        varchar(64) NOT NULL COMMENT '分组',
    `creator`      varchar(32) NOT NULL DEFAULT 'system' COMMENT '创建人',
    `modifier`     varchar(32) NOT NULL DEFAULT 'system' COMMENT '修改人',
    `is_deleted`   tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识。0: 未删除, 1: 已删除',
    `gmt_create`   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_group` (`group`)
) ENGINE = InnoDB COMMENT ='任务分组';

CREATE TABLE `job_info`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `group_id`     bigint(20) NOT NULL COMMENT '分组id',
    `job`          varchar(255) NOT NULL COMMENT '任务',
    `sub_job`      varchar(255) NOT NULL COMMENT '子任务',
    `creator`      varchar(32)  NOT NULL DEFAULT 'system' COMMENT '创建人',
    `modifier`     varchar(32)  NOT NULL DEFAULT 'system' COMMENT '修改人',
    `is_deleted`   tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识。0: 未删除, 1: 已删除',
    `gmt_create`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_job` (`group_id`, `job`, `sub_job`)
) ENGINE = InnoDB COMMENT ='任务信息';

CREATE TABLE `job_authorization`
(
    `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `account`       varchar(255) NOT NULL COMMENT '主账号',
    `sub_account`   varchar(255) NOT NULL COMMENT '子账号',
    `authorization` varchar(255) COMMENT '授权信息',
    `creator`       varchar(32)  NOT NULL DEFAULT 'system' COMMENT '创建人',
    `modifier`      varchar(32)  NOT NULL DEFAULT 'system' COMMENT '修改人',
    `is_deleted`    tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识。0: 未删除, 1: 已删除',
    `gmt_create`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_account` (`account`, `sub_account`)
) ENGINE = InnoDB COMMENT ='任务授权';

CREATE TABLE `job_sync_offset`
(
    `id`               bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `last_sync_offset` varchar(255) COMMENT '上次进度位点',
    `sync_offset`      varchar(255) COMMENT '进度位点',
    `creator`          varchar(32) NOT NULL DEFAULT 'system' COMMENT '创建人',
    `modifier`         varchar(32) NOT NULL DEFAULT 'system' COMMENT '修改人',
    `is_deleted`       tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识。0: 未删除, 1: 已删除',
    `gmt_create`       datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`     datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
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
    `is_deleted`       tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识。0: 未删除, 1: 已删除',
    `gmt_create`       datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`     datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_key` (`job_id`, `authorization_id`, `sync_offset_id`)
) ENGINE = InnoDB COMMENT ='任务实例';
