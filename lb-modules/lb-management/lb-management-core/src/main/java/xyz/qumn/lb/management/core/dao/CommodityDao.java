package xyz.qumn.lb.management.core.dao;

import org.springframework.data.repository.CrudRepository;
import xyz.qumn.lb.management.core.pojo.entity.CommodityEntity;

public interface CommodityDao extends CrudRepository<CommodityEntity, Long> {
}
