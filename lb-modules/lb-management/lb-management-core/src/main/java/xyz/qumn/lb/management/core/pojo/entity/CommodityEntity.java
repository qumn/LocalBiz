package xyz.qumn.lb.management.core.pojo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`lb_commodity`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long cid;
    @Comment("商店id")
    Long mid; // 冗余字段方便查询
    @Comment("分类id")
    Long catId;
    @Comment("商品名称")
    String name;
    @Comment("商品图片")
    String img;
    @Comment("商品简介")
    @Column(name = "`desc`")
    String desc;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catId", insertable = false, updatable = false)
    CategoryEntity category;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "commodity", cascade = CascadeType.PERSIST)
    List<SpecificationEntity> specifications;
    @Comment("创建时间")
    @UpdateTimestamp
    Date createTime;
    @Comment("修改时间")
    @CreationTimestamp
    Date updateTime;


    @Override
    public String toString() {
        return "CommodityEntity{" +
                "cid=" + cid +
                ", mid=" + mid +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", desc='" + desc + '\'' +
                ", specifications=" + specifications +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
