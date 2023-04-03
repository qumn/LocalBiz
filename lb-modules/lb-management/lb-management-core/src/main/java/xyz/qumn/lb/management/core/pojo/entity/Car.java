package xyz.qumn.lb.management.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.EnumTypeHandler;
import xyz.qumn.lb.management.api.enums.CarStatusEnum;

import java.util.Date;

/**
 * (Car)实体类
 *
 * @author qumn
 * @since 2023-04-03 13:24:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lb_car")
public class Car {
    /**
     * car id
     */
    @TableId(type = IdType.AUTO)
    private Long carId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 商家id, 冗余字段方便查询
     */
    private Long mid;
    /**
     * 商品id
     */
    private Long cid;
    /**
     * 商品规格id
     */
    private Long sid;
    /**
     * 数量
     */
    private Integer count;
    /**
     * 状态, 是否被选中
     */
    @TableField(typeHandler = EnumTypeHandler.class)
    private CarStatusEnum status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private Commodity commodity;
    @TableField(exist = false)
    private Specification specification;
}

