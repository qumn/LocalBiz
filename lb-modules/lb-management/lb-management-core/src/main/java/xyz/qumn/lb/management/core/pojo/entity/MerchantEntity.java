package xyz.qumn.lb.management.core.pojo.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import xyz.qumn.lb.management.api.serializer.JsonToPointDeserializer;
import xyz.qumn.lb.management.api.serializer.PointToJsonSerializer;

import java.util.Date;

@Entity
@Data
@Table(name = "`lb_merchant`")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long mid;

    @Comment("店主的id")
    Long owner;
    // 名称
    @Comment("商店名称")
    String name;
    @Column(name = "`desc`")
    @Comment("商铺简介")
    String desc;
    @Comment("封面图片")
    String introImg;
    @Column(columnDefinition = "geometry")
    @Comment("经纬度")
    @JsonSerialize(using = PointToJsonSerializer.class)
    @JsonDeserialize(using = JsonToPointDeserializer.class)
    Point<G2D> geom;
    @Comment("地址描述")
    String geomDesc;
    @Comment("手机号")
    String phone;
    @Comment("创建时间")
    @UpdateTimestamp
    Date createTime;
    @Comment("修改时间")
    @CreationTimestamp
    Date updateTime;
}
