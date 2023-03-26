INSERT INTO `ruoyi-cloud`.`lb_merchant` (`mid`, `create_time`, `desc`, `geom`, `geom_desc`, `intro_img`, `name`, `owner`, `phone`, `update_time`) VALUES (1, '2023-03-26 19:55:30.730000', '一个简短的介绍', ST_GeomFromText('POINT(112.041578 31.999343)'), '湖北省襄阳市襄城区隆中街道湖北文理学院-逸夫图书馆', 'http://127.0.0.1:9300/statics/2023/03/13/Main_body_R_20230313200037A002.png', '张三的店铺', 100, '18086617441', '2023-03-26 19:55:30.730000');
INSERT INTO `ruoyi-cloud`.`lb_category` (`cat_id`, `mid`, `name`, `priority`) VALUES (1, 1, '素', 1);
INSERT INTO `ruoyi-cloud`.`lb_category` (`cat_id`, `mid`, `name`, `priority`) VALUES (2, 1, '荤', 2);

INSERT INTO `ruoyi-cloud`.`lb_commodity` (`cid`, `create_time`, `desc`, `img`, `mid`, `name`, `update_time`, `cat_id`) VALUES (1, '2023-03-21 16:36:16.000000', 'commodity1', NULL, 1, 'cat1', NULL, 1);
INSERT INTO `ruoyi-cloud`.`lb_commodity` (`cid`, `create_time`, `desc`, `img`, `mid`, `name`, `update_time`, `cat_id`) VALUES (2, '2023-03-21 16:48:46.000000', 'commodity2', NULL, 1, 'cat2', NULL, 1);

INSERT INTO `ruoyi-cloud`.`lb_commodity_spc` (`sid`, `price`, `cid`) VALUES (1, 23, 1);
INSERT INTO `ruoyi-cloud`.`lb_commodity_spc` (`sid`, `price`, `cid`) VALUES (2, 44, 1);

INSERT INTO `ruoyi-cloud`.`lb_commodity_spc_atb` (`aid`, `key`, `value`, `sid`) VALUES (1, 'size', 'small', 1);
INSERT INTO `ruoyi-cloud`.`lb_commodity_spc_atb` (`aid`, `key`, `value`, `sid`) VALUES (2, 'color', 'red', 1);
INSERT INTO `ruoyi-cloud`.`lb_commodity_spc_atb` (`aid`, `key`, `value`, `sid`) VALUES (3, 'size', 'small', 2);
INSERT INTO `ruoyi-cloud`.`lb_commodity_spc_atb` (`aid`, `key`, `value`, `sid`) VALUES (4, 'color', 'red', 2);