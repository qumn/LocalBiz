package xyz.qumn.lb.management.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (OrderItem)实体类
 *
 * @author qumn
 * @since 2023-04-06 14:39:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lb_order_item")
public class OrderItem {
    /**
     * order item id
     */
    @TableId(type = IdType.AUTO)
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

    @TableField(exist = false)
    private Commodity commodity;
    @TableField(exist = false)
    private Specification specification;
}

