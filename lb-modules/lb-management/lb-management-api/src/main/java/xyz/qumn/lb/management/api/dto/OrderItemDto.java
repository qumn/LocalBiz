package xyz.qumn.lb.management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    /**
     * order item id
     */
    private Long oiid;
    /**
     * order id
     */
    private Long oid;
    /**
     * specification id
     */
    private Long sid;
    /**
     * 商品数量
     */
    private Integer count;
    /**
     * 预约时间(physical类型商品为null)
     */
    private Date appointmentTime;

    private CommodityDto commodity;
    private SpecificationDto specification;
}

