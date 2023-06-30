create table if not exists district
(
    id          bigint      not null
        primary key,
    name        varchar(31) null comment '区域名称',
    is_deleted  tinyint     null comment '逻辑删除',
    update_time datetime    null comment '更新时间',
    create_time datetime    null comment '添加时间',
    version     int         null comment '乐观锁'
);

create table if not exists t_air_exception
(
    id          int          not null
        primary key,
    user        bigint       null comment '上报用户id/关联用户表',
    handlers    bigint       null comment '处理人id/关联用户表',
    district    bigint       null comment '区域id/关联区域表',
    address     varchar(255) null comment '详细地址',
    name        varchar(31)  null comment '姓名',
    picture     varchar(255) null comment '图片',
    description varchar(255) null comment '描述',
    status      tinyint      null comment '处理状态',
    info_id     bigint       null comment '检测结果id/关联info表',
    is_deleted  tinyint      null comment '逻辑删除',
    update_time datetime     null comment '更新时间',
    create_time datetime     null comment '添加时间',
    version     int          null comment '乐观锁'
);

create table if not exists t_aqi_info
(
    id          bigint   not null
        primary key,
    district    bigint   null comment '区域id/关联区域表',
    pm25        decimal(6, 2) null comment 'pm2.5',
    so2         decimal(6,2)      null comment 'so2',
    co          decimal(6,2)      null comment 'co',
    aqi         decimal(6,2)      null comment 'aqi',
    level       tinyint  null comment '空气质量等级',
    is_deleted  tinyint  null comment '逻辑删除',
    update_time datetime null comment '更新时间',
    create_time datetime null comment '添加时间',
    version     int      null comment '乐观锁'
);

create table if not exists t_user
(
    id          bigint      not null
        primary key,
    account     varchar(31) null comment '账号',
    password    varchar(63) null comment '密码',
    name        varchar(31) null comment '姓名',
    phone       varchar(31) null comment '手机号',
    type        tinyint     null comment '用户类型',
    area        bigint      null comment '地区id/关联区域表',
    is_deleted  tinyint     null comment '逻辑删除',
    update_time datetime    null comment '更新时间',
    create_time datetime    null comment '添加时间',
    version     int         null comment '乐观锁'
);