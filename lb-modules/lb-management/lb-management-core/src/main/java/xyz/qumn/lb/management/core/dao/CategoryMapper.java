package xyz.qumn.lb.management.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import xyz.qumn.lb.management.core.pojo.entity.Category;

import java.util.List;


@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> selectByMid(Long mid);
}
