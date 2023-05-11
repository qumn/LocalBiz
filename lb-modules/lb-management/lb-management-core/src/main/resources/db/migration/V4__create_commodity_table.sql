create table if not exists lb_commodity
(
    cid         bigint                      not null primary key auto_increment,
    `name`      varchar(255)                null comment '商品名称',
    cat_id      bigint                      null comment '分类id',
    img         varchar(255)                null comment '商品图片',
    `desc`      varchar(255)                null comment '商品简介',
    mid         bigint                      null comment '商店id',
    `type`      enum ('PHYSICAL','SERVICE') NULL COMMENT '商品类型',
    create_time datetime(6)                 null comment '创建时间',
    update_time datetime(6)                 null comment '修改时间'
);

create table if not exists lb_commodity_spc
(
    sid   bigint not null auto_increment
        primary key,
    cid   bigint null comment '商品id',
    price int    null comment '价格'
);

create table if not exists lb_commodity_spc_atb
(
    `aid`   bigint       not null auto_increment primary key,
    `key`   varchar(255) null comment '属性名称',
    `value` varchar(255) null comment '属性值',
    sid     bigint       null
);
