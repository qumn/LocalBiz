package xyz.qumn.lb.management.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.qumn.lb.management.api.enums.CommodityType;

import java.time.LocalDateTime;
import java.util.List;

@TableName("lb_commodity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {
    @TableId(type = IdType.AUTO)
    Long cid;
    // 商店id
    Long mid; // 冗余字段方便查询
    // 分类id
    Long catId;
    // 商品名称
    String name;
    // 商品图片
    String img;
    CommodityType type;
    // 商品简介
    @TableField("`desc`")
    String desc;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createTime;
    // 修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime updateTime;

    @TableField(exist = false)
    Category category;
    @TableField(exist = false)
    List<Specification> specifications;
}
