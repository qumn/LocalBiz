package xyz.qumn.lb.management.api.dto;

import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.qumn.lb.management.api.enums.OrderStatus;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    /**
     * 订单id
     */
    private Long oid;
    /**
     * 用户id
     */
    private Long uid;
    private SysUser user;

    private Long mid;
    private MerchantDto merchant;
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

    private List<OrderItemDto> items;
}
