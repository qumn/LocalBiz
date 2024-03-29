package xyz.qumn.lb.management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.qumn.lb.management.api.enums.CommodityType;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityDto {
    Long cid;
    Long mid; // 冗余字段方便查询
    String name;
    String img;
    String desc;
    CommodityType type;
    Date createTime;
    Date updateTime;
    List<SpecificationDto> specifications;
}
