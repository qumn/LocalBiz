package xyz.qumn.lb.management.core.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.qumn.lb.management.api.dto.OrderDto;
import xyz.qumn.lb.management.api.request.order.OrderCreateRequest;
import xyz.qumn.lb.management.core.converter.OrderConverter;
import xyz.qumn.lb.management.core.dao.OrderMapper;
import xyz.qumn.lb.management.core.pojo.entity.Order;
import xyz.qumn.lb.management.core.service.IOrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderServ;
    @Autowired
    OrderMapper orderMp;
    @Autowired
    OrderConverter orderCvt;

    @GetMapping("/{oid}")
    public R<Order> get(@PathVariable("oid") Long oid) {
        return R.ok(orderMp.selectById(oid));
    }

    @GetMapping("/list")
    public R<List<OrderDto>> getListByUid() {
        Long uid = SecurityUtils.getUserId();
        return R.ok(orderServ.selectByUid(uid, null));
    }

    @GetMapping("/list/{mid}")
    public R<List<OrderDto>> getList(@PathVariable("mid") Long mid) {
        return R.ok(orderServ.selectByMid(mid));
    }

    @PostMapping
    public R<Void> createOrder(@RequestBody OrderCreateRequest orderCreateRequest){
        Long uid = SecurityUtils.getUserId();
        orderServ.create(orderCreateRequest);
        return R.ok();
    }

    @PutMapping("/accept/{oid}")
    public R<Void> accept(@PathVariable("oid") Long oid) {
        orderServ.accept(oid);
        return R.ok();
    }

    @PutMapping("/cancel/{oid}")
    public R<Void> cancel(@PathVariable("oid") Long oid) {
        orderServ.cancel(oid);
        return R.ok();
    }

    @PutMapping("/refuse/{oid}")
    public R<Void> refuse(@PathVariable("oid") Long oid) {
        orderServ.refuse(oid);
        return R.ok();
    }
}
