create table if not exists lb_category
(
    cat_id   bigint       not null primary key auto_increment,
    mid      bigint       null comment '商店id',
    `name`   varchar(255) null comment '分类名称',
    priority int          not null comment '排序优先级'
);
