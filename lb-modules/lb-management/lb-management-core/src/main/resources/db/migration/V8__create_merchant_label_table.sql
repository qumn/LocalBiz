create table if not exists `lb_label`
(
    `lid`   bigint       not null auto_increment comment 'label id',
    `label` varchar(255) not null comment 'label',
    primary key (`lid`)
);

CREATE TABLE if not exists `lb_merchant_label`
(
    `lid`   bigint       NOT NULL AUTO_INCREMENT COMMENT 'label id',
    `mid`   bigint       NOT NULL COMMENT 'merchant id',
    PRIMARY KEY (`lid`, `mid`)
);