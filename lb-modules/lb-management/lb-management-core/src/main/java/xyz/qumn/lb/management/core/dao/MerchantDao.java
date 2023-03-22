package xyz.qumn.lb.management.core.dao;

import org.springframework.data.repository.ListCrudRepository;
import xyz.qumn.lb.management.core.pojo.entity.MerchantEntity;

import java.util.List;

public interface MerchantDao extends ListCrudRepository<MerchantEntity, Long> {

    List<MerchantEntity> findByMidAndName(Long mid, String name);

    List<MerchantEntity> findByOwner(Long owner);
}
