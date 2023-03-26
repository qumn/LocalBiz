package xyz.qumn.lb.management.core.converter;

import org.mapstruct.*;
import xyz.qumn.lb.management.api.dto.CommodityDetailDto;
import xyz.qumn.lb.management.api.dto.CommodityDto;
import xyz.qumn.lb.management.api.request.commodity.CommodityCreateRequest;
import xyz.qumn.lb.management.api.request.commodity.CommodityUpdateRequest;
import xyz.qumn.lb.management.core.pojo.entity.CommodityEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryConverter.class, SpecificationConverter.class})
public interface CommodityConverter {
    CommodityDto entity2Dto(CommodityEntity commodity);

    List<CommodityDto> entity2Dtos(List<CommodityEntity> commodities);

    CommodityDetailDto entity2DtoDetail(CommodityEntity commodity);

    List<CommodityDetailDto> entity2DtoDetails(List<CommodityEntity> commodity);

    CommodityEntity req2Entity(CommodityCreateRequest commodity);

    CommodityEntity req2Entity(CommodityUpdateRequest commodity);


    @AfterMapping
    default void setSpecificationCommodity(@MappingTarget CommodityEntity commodityEntity) {
        commodityEntity.getSpecifications().forEach(spc -> spc.setCommodity(commodityEntity));
    }
}
