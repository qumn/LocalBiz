# add a column type to the commodity,
ALTER TABLE `lb_commodity`
    ADD COLUMN `type` enum ('PHYSICAL','SERVICE') NULL COMMENT '商品类型' AFTER `update_time`;