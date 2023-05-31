CREATE TABLE if not exists `lb_car`
(
    `car_id`      bigint                         NOT NULL AUTO_INCREMENT COMMENT 'car id',
    `uid`     bigint                         NULL COMMENT '用户id',
    `mid`         bigint                         NULL COMMENT '商家id, 冗余字段方便查询',
    `cid`         bigint                         NULL COMMENT '商品id',
    `sid`         bigint                         NULL COMMENT '商品规格id',
    `count`       int                            NULL COMMENT '数量',
    `status`      enum ('SELECTED','UNSELECTED') NULL COMMENT '状态, 是否被选中',
    `create_time` datetime                       NULL COMMENT '创建时间',
    `update_time` datetime                       NULL COMMENT '修改时间',
    PRIMARY KEY (`car_id`)
);