package xyz.qumn.lb.management.core.pojo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Table(name = "`lb_category`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long catId;
    @Comment("商店id")
    Long mid;
    @Comment("分类名称")
    String name;
    @Comment("排序优先级")
    int priority;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    List<CommodityEntity> commodities;
}
