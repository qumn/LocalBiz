package xyz.qumn.lb.management.api.request.commodity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.qumn.lb.management.api.dto.SpecificationDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityUpdateRequest {
    Long cid;
    Long mid;
    Long catId;
    String name;
    String img;
    String desc;
    List<SpecificationDto> specifications;
}
