package xyz.qumn.lb.management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityDetailDto {
    Long cid;
    Long mid; // 冗余字段方便查询
    String name;
    String img;
    String desc;
    Date createTime;
    Date updateTime;

    CategoryDto category;
    List<SpecificationDto> specifications;
}
