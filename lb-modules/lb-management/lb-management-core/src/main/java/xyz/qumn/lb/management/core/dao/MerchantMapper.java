package xyz.qumn.lb.management.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import xyz.qumn.lb.management.core.pojo.entity.Merchant;

import java.util.List;

@Repository
public interface MerchantMapper extends BaseMapper<Merchant> {

    List<Merchant> findByMidAndName(Long mid, String name);

    List<Merchant> findByOwner(Long owner);
}
