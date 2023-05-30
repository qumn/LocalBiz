package xyz.qumn.lb.management.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.qumn.lb.management.api.enums.OrderStatus;
import xyz.qumn.lb.management.core.pojo.entity.Order;

import java.util.List;

@Repository
public interface OrderMapper extends BaseMapper<Order> {
    int modifyStatus(Long oid, OrderStatus status);
    List<Order> query(@Param("order") Order order);
}
