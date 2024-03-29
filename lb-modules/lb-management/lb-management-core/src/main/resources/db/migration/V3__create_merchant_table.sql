create table if not exists lb_merchant
(
    mid         bigint       not null auto_increment
        primary key,
    `desc`      varchar(255) null comment '商铺简介',
    geom        geometry     null comment '经纬度',
    geom_desc   varchar(255) null comment '地址描述',
    intro_img   varchar(255) null comment '封面图片',
    `name`      varchar(255) null comment '商店名称',
    `owner`     bigint       null comment '店主的id',
    phone       varchar(255) null comment '手机号',
    score       float        null comment '评分',
    create_time datetime(6)  null comment '创建时间',
    update_time datetime(6)  null comment '修改时间'
);
