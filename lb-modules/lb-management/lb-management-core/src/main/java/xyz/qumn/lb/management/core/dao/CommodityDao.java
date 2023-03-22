package xyz.qumn.lb.management.core.dao;

import org.springframework.data.repository.ListCrudRepository;
import xyz.qumn.lb.management.core.pojo.entity.CommodityEntity;

import java.util.List;

public interface CommodityDao extends ListCrudRepository<CommodityEntity, Long> {
    List<CommodityEntity> findByMid(Long mid);
}
