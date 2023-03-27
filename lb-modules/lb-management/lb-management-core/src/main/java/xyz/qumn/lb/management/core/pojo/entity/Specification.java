package xyz.qumn.lb.management.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@TableName("lb_commodity_spc")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specification {
    @TableId(type = IdType.AUTO)
    Long sid;
    // 价格 以分为单位
    Integer price;
    // 商品id
    Long cid;
    @TableField(exist = false)
    Commodity commodity;
    @TableField(exist = false)
    List<SpecificationAttribute> attributes;
}
