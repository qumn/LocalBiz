package xyz.qumn.lb.management.core.pojo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.awt.*;
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
    // 名称
    @Comment("商店名称")
    String name;

    @Comment("密码")
    String password;

    @Column(name = "`desc`")
    @Comment("商铺简介")
    String desc;
    @Comment("封面图片")
    String intro_imag;
    @Column(columnDefinition = "geometry")
    @Comment("经纬度")
    Point geom;
    @Comment("地址描述")
    String geomDesc;
    @Comment("手机号")
    String phone;
    @Comment("创建时间")
    Date createTime;
    @Comment("修改时间")
    Date updateTime;
}
