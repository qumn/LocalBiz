package xyz.qumn.lb.management.api.request.order;

import lombok.Data;

import java.util.Date;

@Data
public class OrderItemCreateRequest {
    private Long sid;
    private Integer count;
}
