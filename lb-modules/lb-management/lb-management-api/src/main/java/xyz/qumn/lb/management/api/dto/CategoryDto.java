package xyz.qumn.lb.management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    Long catId;
    Long mid;
    String name;
    int priority;

    List<CommodityDto> commodities;
}
