package xyz.qumn.lb.management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityDto {
    Long cid;
    Long mid; // 冗余字段方便查询
    String name;
    String img;
    String desc;
    Date createTime;
    Date updateTime;
}
