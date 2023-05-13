package xyz.qumn.lb.management.core.service;

import xyz.qumn.lb.management.api.dto.OrderDto;
import xyz.qumn.lb.management.core.pojo.entity.Order;

import java.util.List;

public interface IOrderService {
    List<OrderDto> selectByMid(Long mid);

    void accept(Long oid);

    void cancel(Long oid);

    void refuse(Long oid);
    void save(Order order);
}
