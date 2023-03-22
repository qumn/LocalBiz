INSERT INTO `ruoyi-cloud`.`lb_category` (`cat_id`, `mid`, `name`, `priority`) VALUES (1, 5, '素', 1);
INSERT INTO `ruoyi-cloud`.`lb_category` (`cat_id`, `mid`, `name`, `priority`) VALUES (2, 5, '荤', 2);

INSERT INTO `ruoyi-cloud`.`lb_commodity` (`cid`, `create_time`, `desc`, `img`, `mid`, `name`, `update_time`, `cat_id`) VALUES (1, '2023-03-21 16:36:16.000000', 'commodity1', NULL, NULL, 'cat1', NULL, 1);
INSERT INTO `ruoyi-cloud`.`lb_commodity` (`cid`, `create_time`, `desc`, `img`, `mid`, `name`, `update_time`, `cat_id`) VALUES (2, '2023-03-21 16:48:46.000000', 'commodity2', NULL, NULL, 'cat2', NULL, 1);

INSERT INTO `ruoyi-cloud`.`lb_commodity_spc` (`sid`, `price`, `cid`) VALUES (1, 23, 1);
INSERT INTO `ruoyi-cloud`.`lb_commodity_spc` (`sid`, `price`, `cid`) VALUES (2, 44, 1);

INSERT INTO `ruoyi-cloud`.`lb_commodity_spc_atb` (`aid`, `key`, `value`, `sid`) VALUES (1, 'size', 'small', 1);
INSERT INTO `ruoyi-cloud`.`lb_commodity_spc_atb` (`aid`, `key`, `value`, `sid`) VALUES (2, 'color', 'red', 1);
INSERT INTO `ruoyi-cloud`.`lb_commodity_spc_atb` (`aid`, `key`, `value`, `sid`) VALUES (3, 'size', 'small', 2);
INSERT INTO `ruoyi-cloud`.`lb_commodity_spc_atb` (`aid`, `key`, `value`, `sid`) VALUES (4, 'color', 'red', 2);