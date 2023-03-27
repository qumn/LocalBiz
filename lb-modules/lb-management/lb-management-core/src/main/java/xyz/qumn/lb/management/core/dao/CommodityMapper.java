package xyz.qumn.lb.management.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import xyz.qumn.lb.management.core.pojo.entity.Commodity;

import java.util.List;

@Repository
public interface CommodityMapper extends BaseMapper<Commodity> {
    Commodity selectById(Long cid);
    List<Commodity> selectByMid(Long mid);
}
