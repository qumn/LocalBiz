CREATE TABLE `lb_order`
(
    `oid`          bigint                                                                              NOT NULL COMMENT '订单id',
    `uid`          bigint                                                                              NULL COMMENT '用户id',
    `total_amount` integer                                                                             NULL COMMENT '订单总金额',
    `status`       enum ('NONPAYMENT','PENDING','CANCELLED','REFUSED','SHIPPED','WAITING','DELIVERED') NULL DEFAULT 'NONPAYMENT' COMMENT '订单状态',
    `create_time`  datetime                                                                            NULL COMMENT '创建时间',
    `update_time`  datetime                                                                            NULL COMMENT '更新时间',
    PRIMARY KEY (`oid`)
);