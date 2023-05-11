package xyz.qumn.lb.management.core.service;

import xyz.qumn.lb.management.api.dto.OrderDto;

import java.util.List;

public interface IOrderService {
    List<OrderDto> selectByMid(Long mid);

    void accept(Long oid);

    void cancel(Long oid);

    void refuse(Long oid);
}
