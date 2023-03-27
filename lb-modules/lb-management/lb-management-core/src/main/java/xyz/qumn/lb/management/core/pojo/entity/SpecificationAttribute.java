package xyz.qumn.lb.management.core.pojo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lb_commodity_spc_atb")
public class SpecificationAttribute {
    @TableId(type = IdType.AUTO)
    Long aid;
    // 规格id
    Long sid;

    // 属性名称
    @TableField("`key`")
    String key;

    // 属性值
    @TableField("`value`")
    String value;

    @TableField(exist = false)
    Specification specification;
}
