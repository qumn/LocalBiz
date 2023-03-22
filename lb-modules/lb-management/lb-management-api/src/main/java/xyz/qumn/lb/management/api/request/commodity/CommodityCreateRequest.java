package xyz.qumn.lb.management.api.request.commodity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.qumn.lb.management.api.dto.SpecificationDto;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityCreateRequest {
    Long mid; // 商家id
    Long catId; // 分类id
    String name;
    String img;
    String desc;
    List<SpecificationDto> specifications;
}
