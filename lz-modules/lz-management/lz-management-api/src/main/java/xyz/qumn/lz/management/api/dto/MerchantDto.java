package xyz.qumn.lz.management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantDto {
    Long mid;
    // 名称
    String name;

    String desc;
    String intro_imag;
    Point geom;
    String geomDesc;
    String phone;
    Date createTime;
    Date updateTime;
}

