package xyz.qumn.lb.management.api.request.order;

import xyz.qumn.lb.management.api.enums.OrderStatus;

import java.util.Date;
import java.util.List;

public class OrderCreateRequest {

    private Long uid;

    private Long mid;
    /**
     * 订单总金额
     */
    private Integer totalAmount;
    /**
     * 订单状态
     */
    private OrderStatus status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    private List<OrderItemCreateRequest> items;
}
