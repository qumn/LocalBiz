package xyz.qumn.lb.management.core.dao;

import org.springframework.data.repository.CrudRepository;
import xyz.qumn.lb.management.core.pojo.entity.CategoryEntity;

public interface CategoryDao extends CrudRepository<CategoryEntity, Long> {
}
