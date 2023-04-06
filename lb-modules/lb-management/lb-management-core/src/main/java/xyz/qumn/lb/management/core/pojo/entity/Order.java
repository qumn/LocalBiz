package xyz.qumn.lb.management.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.qumn.lb.management.api.enums.OrderStatus;

import java.util.Date;
import java.util.List;

/**
 * (Order)实体类
 *
 * @author qumn
 * @since 2023-04-06 14:32:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lb_order")
public class Order {
    /**
     * 订单id
     */
    @TableId(type = IdType.AUTO)
    private Long oid;
    /**
     * 用户id
     */
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

    @TableField(exist = false)
    private List<OrderItem> items;
}

