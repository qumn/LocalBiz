package xyz.qumn.lb.management.core.converter;

import org.mapstruct.*;
import xyz.qumn.lb.management.api.dto.CommodityDetailDto;
import xyz.qumn.lb.management.api.dto.CommodityDto;
import xyz.qumn.lb.management.api.request.commodity.CommodityCreateRequest;
import xyz.qumn.lb.management.api.request.commodity.CommodityUpdateRequest;
import xyz.qumn.lb.management.core.pojo.entity.Commodity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryConverter.class, SpecificationConverter.class})
public interface CommodityConverter {
    CommodityDto entity2Dto(Commodity commodity);

    List<CommodityDto> entity2Dtos(List<Commodity> commodities);

    CommodityDetailDto entity2DtoDetail(Commodity commodity);

    List<CommodityDetailDto> entity2DtoDetails(List<Commodity> commodity);

    Commodity req2Entity(CommodityCreateRequest commodity);

    Commodity req2Entity(CommodityUpdateRequest commodity);


    @AfterMapping
    default void setSpecificationCommodity(@MappingTarget Commodity commodity) {
        commodity.getSpecifications().forEach(spc -> spc.setCommodity(commodity));
    }
}
