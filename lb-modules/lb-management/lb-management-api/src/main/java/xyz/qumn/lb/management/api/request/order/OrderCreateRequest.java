package xyz.qumn.lb.management.api.request.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderCreateRequest {
    private List<OrderItemCreateRequest> items;
}
