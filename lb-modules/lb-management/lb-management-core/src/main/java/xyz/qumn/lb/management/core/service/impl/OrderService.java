package xyz.qumn.lb.management.core.service.impl;

import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.qumn.lb.management.api.dto.OrderDto;
import xyz.qumn.lb.management.api.enums.OrderStatus;
import xyz.qumn.lb.management.core.converter.OrderConverter;
import xyz.qumn.lb.management.core.dao.OrderMapper;
import xyz.qumn.lb.management.core.pojo.entity.Order;
import xyz.qumn.lb.management.core.service.IOrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderMapper orderMp;
    @Autowired
    OrderConverter orderCvt;
    @Autowired
    RemoteUserService userService;

    @Override
    public List<OrderDto> selectByMid(Long mid) {
        Order order = new Order();
        order.setMid(mid);
        List<Order> orders = orderMp.query(order);
        List<OrderDto> orderDtos = orderCvt.entity2Dtos(orders);
        Map<Long, List<OrderDto>> uid2OrderDto = orderDtos.stream().collect(Collectors.groupingBy(OrderDto::getUid));
        Set<Long> uids = uid2OrderDto.keySet();
        List<SysUser> users = userService.getUserInfoByIds(uids).getDataOrDefault(new ArrayList<>());
        for (SysUser user : users) {
            uid2OrderDto.get(user.getUserId()).forEach(orderDto -> orderDto.setUser(user));
        }
        return orderDtos;
    }

    @Override
    public List<OrderDto> selectByUid(Long uid, OrderStatus status) {
        Order order = new Order();
        order.setStatus(status);
        order.setUid(uid);
        List<Order> orders = orderMp.query(order);
        return orderCvt.entity2Dtos(orders);
    }

    @Override
    public void accept(Long oid) {
        orderMp.modifyStatus(oid, OrderStatus.SHIPPING);
    }

    @Override
    public void cancel(Long oid) {
        orderMp.modifyStatus(oid, OrderStatus.CANCELLED);
    }

    @Override
    public void refuse(Long oid) {
        orderMp.modifyStatus(oid, OrderStatus.REFUSED);
    }

    @Override
    public void save(Order order) {
        orderMp.insert(order);
    }
}
