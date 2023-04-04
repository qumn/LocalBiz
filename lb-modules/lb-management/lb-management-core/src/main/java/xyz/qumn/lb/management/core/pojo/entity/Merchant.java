package xyz.qumn.lb.management.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;
import xyz.qumn.lb.common.mybatis.GeometryTypeHandler;
import xyz.qumn.lb.management.api.serializer.JsonToPointDeserializer;
import xyz.qumn.lb.management.api.serializer.PointToJsonSerializer;

import java.time.LocalDateTime;

@TableName("`lb_merchant`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {
    @TableId(type = IdType.AUTO)
    Long mid;

    // 店主的id
    Long owner;
    // 名称
    // 商店名称")
    String name;
    @TableField("`desc`")
    // 商铺简介
    String desc;
    // 封面图片
    String introImg;
    // 经纬度
    @JsonSerialize(using = PointToJsonSerializer.class)
    @JsonDeserialize(using = JsonToPointDeserializer.class)
    @TableField(typeHandler = GeometryTypeHandler.class)
    Geometry geom;
    // 地址描述
    String geomDesc;
    // 手机号
    String phone;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createTime;
    // 修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime updateTime;

}
