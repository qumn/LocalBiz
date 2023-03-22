package xyz.qumn.lb.management.core.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import xyz.qumn.lb.management.core.pojo.entity.CategoryEntity;

import java.util.List;

public interface CategoryDao extends ListCrudRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByMid(Long mid);
}
