CREATE TABLE if not exists `lb_order`
(
    `oid`          bigint                                                                              NOT NULL auto_increment COMMENT '订单id',
    `uid`          bigint                                                                              NULL COMMENT '用户id',
    `mid`          bigint                                                                              null comment '商家id',
    `total_amount` integer                                                                             NULL COMMENT '订单总金额',
    `status`       enum ('NONPAYMENT','PENDING','CANCELLED','REFUSED','SHIPPING','WAITING','DELIVERED') NULL DEFAULT 'NONPAYMENT' COMMENT '订单状态',
    `create_time`  datetime                                                                            NULL COMMENT '创建时间',
    `update_time`  datetime                                                                            NULL COMMENT '更新时间',
    PRIMARY KEY (`oid`)
        comment '订单表, 每一个订单都属于一个商家管理'
);