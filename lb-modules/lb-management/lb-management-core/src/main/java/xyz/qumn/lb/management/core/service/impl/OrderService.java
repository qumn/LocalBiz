package xyz.qumn.lb.management.core.service.impl;

import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.qumn.lb.management.api.dto.MerchantDto;
import xyz.qumn.lb.management.api.dto.OrderDto;
import xyz.qumn.lb.management.api.enums.OrderStatus;
import xyz.qumn.lb.management.api.feign.RemoteMerchantService;
import xyz.qumn.lb.management.api.request.order.OrderCreateRequest;
import xyz.qumn.lb.management.api.request.order.OrderItemCreateRequest;
import xyz.qumn.lb.management.core.converter.OrderConverter;
import xyz.qumn.lb.management.core.dao.OrderItemMapper;
import xyz.qumn.lb.management.core.dao.OrderMapper;
import xyz.qumn.lb.management.core.dao.SpecificationMapper;
import xyz.qumn.lb.management.core.pojo.entity.Order;
import xyz.qumn.lb.management.core.pojo.entity.OrderItem;
import xyz.qumn.lb.management.core.pojo.entity.Specification;
import xyz.qumn.lb.management.core.service.IMerchantService;
import xyz.qumn.lb.management.core.service.IOrderService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderMapper orderMp;
    @Autowired
    OrderConverter orderCvt;
    @Autowired
    OrderItemMapper orderItemMp;

    @Autowired
    SpecificationMapper specificationMapper;
    @Autowired
    IMerchantService merchantService;

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
        List<OrderDto> orderDtos = orderCvt.entity2Dtos(orders);
        Map<Long, List<OrderDto>> mid2OrderDto = orderDtos.stream().filter(o -> o.getMid() != null).collect(Collectors.groupingBy(OrderDto::getMid));
        List<MerchantDto> merchants = merchantService.selectBatchIds(mid2OrderDto.keySet());

        for (MerchantDto merchant : merchants) {
            Long mid = merchant.getMid();
            mid2OrderDto.get(mid).forEach(o -> o.setMerchant(merchant));
        }

        return orderDtos;
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
        order.setStatus(OrderStatus.NONPAYMENT);
        orderMp.insert(order);
    }

    /***
     * 一个订单中的每一个订单项都需要属于同一个商家
     * @param orderCreateRequest
     */
    @Override
    @Transactional
    public void create(OrderCreateRequest orderCreateRequest) {
        Long uid = SecurityUtils.getUserId();
        Map<Long, OrderItemCreateRequest> sidToItem = orderCreateRequest.getItems().stream().collect(Collectors.toMap(OrderItemCreateRequest::getSid, Function.identity()));
        Set<Long> sids = sidToItem.keySet();

        List<Specification> specifications = specificationMapper.selectByIds(sids);

        Map<Long, List<Specification>> mid2Spec = specifications.stream().collect(Collectors.groupingBy(s -> s.getCommodity().getMid()));

        for (Map.Entry<Long, List<Specification>> mid2SpecEntry : mid2Spec.entrySet()) {
            int totalPrice = mid2SpecEntry.getValue().stream().mapToInt(s -> s.getPrice() * sidToItem.get(s.getSid()).getCount()).sum();

            Order order = new Order();
            order.setUid(uid);
            order.setMid(mid2SpecEntry.getKey());
            order.setStatus(OrderStatus.NONPAYMENT);
            order.setTotalAmount(totalPrice);
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());
            orderMp.insert(order);

            List<OrderItem> orderItems = mid2SpecEntry.getValue().stream().map(spec -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setSid(spec.getSid());
                orderItem.setCount(sidToItem.get(spec.getSid()).getCount());
                orderItem.setAppointmentTime(new Date());
                orderItem.setOid(order.getOid());
                return orderItem;
            }).toList();

            for (OrderItem orderItem : orderItems) {
                orderItemMp.insert(orderItem);
            }
        }

    }
}
