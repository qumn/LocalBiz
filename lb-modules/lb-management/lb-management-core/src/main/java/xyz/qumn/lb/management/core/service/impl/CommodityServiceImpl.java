package xyz.qumn.lb.management.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.qumn.lb.management.core.dao.CommodityDao;
import xyz.qumn.lb.management.core.dao.SpecificationDao;
import xyz.qumn.lb.management.core.pojo.entity.CommodityEntity;
import xyz.qumn.lb.management.core.service.ICommodityService;

@Service
public class CommodityServiceImpl implements ICommodityService {
    @Autowired
    CommodityDao commodityDao;
    @Autowired
    SpecificationDao specificationDao;
    @Override
    public Long save(CommodityEntity commodityEntity) {
        return commodityDao.save(commodityEntity).getCid();
    }

    @Override
    @Transactional
    public void update(CommodityEntity commodity){
        Long cid = commodity.getCid();
        specificationDao.deleteAllByCid(cid);
        save(commodity);
    }
}
