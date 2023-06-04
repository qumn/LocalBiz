package xyz.qumn.lb.client.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.EnumTypeHandler;
import xyz.qumn.lb.client.api.enums.CarStatusEnum;

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
    private Long uid;
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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}

