package xyz.qumn.lb.management.core.dao;

import org.springframework.data.repository.ListCrudRepository;
import xyz.qumn.lb.management.core.pojo.entity.SpecificationEntity;

import java.util.List;

public interface SpecificationDao extends ListCrudRepository<SpecificationEntity, Long> {
    List<SpecificationEntity> queryByCid(Long cid);
    void deleteAllByCid(Long cid);
}
