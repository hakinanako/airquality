CREATE TABLE `t_air_exception` (
                                   `id` bigint(20) NOT NULL,
                                   `user` bigint(20) DEFAULT NULL COMMENT '上报用户id/关联用户表',
                                   `handlers` bigint(20) DEFAULT NULL COMMENT '处理人id/关联用户表',
                                   `district` bigint(20) DEFAULT NULL COMMENT '区域id/关联区域表',
                                   `address` varchar(255) CHARACTER SET utf8 NOT NULL,
                                   `name` varchar(31) CHARACTER SET utf8 NOT NULL,
                                   `picture` varchar(255) DEFAULT NULL COMMENT '图片',
                                   `level` tinyint(4) DEFAULT NULL COMMENT '预估等级',
                                   `description` varchar(255) CHARACTER SET utf8 NOT NULL,
                                   `status` tinyint(4) DEFAULT NULL COMMENT '处理状态',
                                   `info_id` bigint(20) DEFAULT NULL COMMENT '检测结果id/关联info表',
                                   `is_deleted` tinyint(4) DEFAULT NULL COMMENT '逻辑删除',
                                   `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                   `create_time` datetime DEFAULT NULL COMMENT '添加时间',
                                   `version` int(11) DEFAULT NULL COMMENT '乐观锁',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_aqi_info` (
                              `id` bigint(20) NOT NULL,
                              `district` bigint(20) DEFAULT NULL COMMENT '区域id/关联区域表',
                              `pm25` decimal(6,2) DEFAULT NULL COMMENT 'pm2.5',
                              `so2` decimal(6,2) DEFAULT NULL COMMENT 'so2',
                              `co` decimal(6,2) DEFAULT NULL COMMENT 'co',
                              `aqi` decimal(6,2) DEFAULT NULL COMMENT 'aqi',
                              `level` tinyint(4) DEFAULT NULL COMMENT '空气质量等级',
                              `is_deleted` tinyint(4) DEFAULT NULL COMMENT '逻辑删除',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              `create_time` datetime DEFAULT NULL COMMENT '添加时间',
                              `version` int(11) DEFAULT NULL COMMENT '乐观锁',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_district` (
                              `id` bigint(20) NOT NULL,
                              `name` varchar(31) NOT NULL,
                              'pid' bigint(20) NOT NULL ,
                              `is_deleted` tinyint(4) DEFAULT NULL COMMENT '逻辑删除',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              `create_time` datetime DEFAULT NULL COMMENT '添加时间',
                              `version` int(11) DEFAULT NULL COMMENT '乐观锁',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_user` (
                          `id` bigint(20) NOT NULL,
                          `account` varchar(31) CHARACTER SET utf8 NOT NULL,
                          `password` varchar(63) CHARACTER SET utf8 NOT NULL,
                          `name` varchar(31) CHARACTER SET utf8 NOT NULL,
                          `phone` varchar(31) CHARACTER SET utf8 NOT NULL,
                          `type` tinyint(4) DEFAULT NULL COMMENT '用户类型',
                          `area` bigint(20) DEFAULT NULL COMMENT '地区id/关联区域表',
                          `is_deleted` tinyint(4) DEFAULT NULL COMMENT '逻辑删除',
                          `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                          `create_time` datetime DEFAULT NULL COMMENT '添加时间',
                          `version` int(11) DEFAULT NULL COMMENT '乐观锁',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

