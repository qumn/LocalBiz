package xyz.qumn.lb.management.core.converter;

import org.mapstruct.Mapper;
import xyz.qumn.lb.management.api.dto.OrderDto;
import xyz.qumn.lb.management.api.dto.OrderItemDto;
import xyz.qumn.lb.management.api.request.order.OrderCreateRequest;
import xyz.qumn.lb.management.core.pojo.entity.Order;
import xyz.qumn.lb.management.core.pojo.entity.OrderItem;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommodityConverter.class, SpecificationConverter.class})
public interface OrderConverter {
    OrderDto entity2Dto(Order order);
    Order dto2Entity(OrderDto orderDto);

    List<OrderDto> entity2Dtos(List<Order> orders);

    OrderItemDto entity2Dto(OrderItem orderItem);
    OrderItem dto2Entity(OrderItemDto orderItemDto);

    Order request2Entity(OrderCreateRequest orderCreateRequest);
}
