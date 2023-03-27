package xyz.qumn.lb.management.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import xyz.qumn.lb.management.core.pojo.entity.SpecificationAttribute;

import java.util.List;

@Repository
public interface SpecificationAttributeMapper extends BaseMapper<SpecificationAttribute> {
    int insertBatch(List<SpecificationAttribute> attributes);
    int insertOrUpdateBatch(List<SpecificationAttribute> attributes);

    void deleteBatchSids(List<Long> sids);
}
