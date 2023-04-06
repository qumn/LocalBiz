CREATE TABLE `lb_order_item`
(
    `oiid`             bigint   NULL COMMENT 'order item id',
    `oid`              bigint   NULL comment 'order id',
    `sid`              bigint   NULL comment 'specification id',
    `count`            int      NULL comment '商品数量',
    `appointment_time` datetime null comment '预约时间(physical类型商品为null)'
);