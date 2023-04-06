package xyz.qumn.lb.management.core.controller;

import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/{oid}")
    public R<Order> get(@PathVariable("oid") Long oid) {
        return R.ok(orderMp.selectById(oid));
    }

    @GetMapping("/list/{mid}")
    public R<List<Order>> getList(@PathVariable("mid")Long mid){
        return R.ok(orderMp.selectByMid(mid));
    }
}
