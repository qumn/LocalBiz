package xyz.qumn.lb.management.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;
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
    Double score;
    String introImg;
    @JsonSerialize(using = PointToJsonSerializer.class)
    @JsonDeserialize(using = JsonToPointDeserializer.class)
    Geometry geom;
    String geomDesc;
    String phone;
    Date createTime;
    Date updateTime;
}

