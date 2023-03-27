package xyz.qumn.lb.management.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import xyz.qumn.lb.management.core.pojo.entity.Specification;

import java.util.List;

@Repository
public interface SpecificationMapper extends BaseMapper<Specification> {
    List<Specification> selectByCid(Long cid);
    void deleteAllByCid(Long cid);

    int insertBatch(List<Specification> specifications);
}
