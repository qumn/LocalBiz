package xyz.qumn.lb.management.core.service;

import xyz.qumn.lb.management.core.pojo.entity.Commodity;

public interface ICommodityService {
    Long save(Commodity commodity);

    void update(Commodity commodity);
}
