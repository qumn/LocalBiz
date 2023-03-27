package xyz.qumn.lb.management.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lb_category")
public class Category {
    @TableId
    Long catId;
    // 商店id
    Long mid;
    // 分类名称
    String name;
    // 排序优先级
    int priority;
    @TableField(exist = false)
    List<Commodity> commodities;
}
