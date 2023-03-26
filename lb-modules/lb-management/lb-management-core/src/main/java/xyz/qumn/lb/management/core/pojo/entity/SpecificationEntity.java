package xyz.qumn.lb.management.core.pojo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Table(name = "`lb_commodity_spc`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long sid;
    // 以分为单位
    @Comment("价格")
    Integer price;

    @Comment("商品id")
    Long cid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid", updatable = false, insertable = false)
    CommodityEntity commodity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "specification", cascade = CascadeType.ALL)
    List<SpecificationAttributeEntity> attributes;

    @Override
    public String toString() {
        return "SpecificationEntity{" +
                "sid=" + sid +
                ", price=" + price +
                ", attributes=" + attributes +
                '}';
    }
}
