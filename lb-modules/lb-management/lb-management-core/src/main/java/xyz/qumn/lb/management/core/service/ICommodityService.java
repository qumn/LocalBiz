package xyz.qumn.lb.management.core.service;

import xyz.qumn.lb.management.core.pojo.entity.CommodityEntity;

public interface ICommodityService {
    Long save(CommodityEntity commodityEntity);

    void update(CommodityEntity commodityEntity);
}
