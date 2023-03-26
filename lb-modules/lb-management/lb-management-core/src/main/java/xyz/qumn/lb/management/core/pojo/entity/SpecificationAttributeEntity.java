package xyz.qumn.lb.management.core.pojo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "`lb_commodity_spc_atb`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationAttributeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long aid;

    @Comment("属性名称")
    @Column(name = "`key`")
    String key;

    @Comment("属性值")
    @Column(name = "`value`")
    String value;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sid")
    SpecificationEntity specification;

    @Override
    public String toString() {
        return "SpecificationAttributeEntity{" +
                "aid=" + aid +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
