package xyz.qumn.lb.management.core.dao;

import org.springframework.data.repository.CrudRepository;
import xyz.qumn.lb.management.core.pojo.entity.MerchantEntity;

import java.util.List;

public interface MerchantDao extends CrudRepository<MerchantEntity, Long> {

    List<MerchantEntity> findByMidAndName(Long mid, String name);
}
