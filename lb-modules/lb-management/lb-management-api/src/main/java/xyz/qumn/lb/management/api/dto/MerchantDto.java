package xyz.qumn.lb.management.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import xyz.qumn.lb.management.api.serializer.JsonToPointDeserializer;
import xyz.qumn.lb.management.api.serializer.PointToJsonSerializer;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantDto {
    Long mid;
    // 名称
    String name;
    String password;
    String desc;
    String introImg;
    @JsonSerialize(using = PointToJsonSerializer.class)
    @JsonDeserialize(using = JsonToPointDeserializer.class)
    Point<G2D> geom;
    String geomDesc;
    String phone;
    Date createTime;
    Date updateTime;
}

