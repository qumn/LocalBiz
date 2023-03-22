package xyz.qumn.lb.management.core.converter;

import com.ruoyi.common.core.utils.SpringUtils;
import org.mapstruct.*;
import xyz.qumn.lb.management.api.dto.CommodityDetailDto;
import xyz.qumn.lb.management.api.dto.CommodityDto;
import xyz.qumn.lb.management.api.request.commodity.CommodityCreateRequest;
import xyz.qumn.lb.management.api.request.commodity.CommodityUpdateRequest;
import xyz.qumn.lb.management.core.pojo.entity.CommodityEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommodityConverter {
    CommodityDto entity2Dto(CommodityEntity commodity);

    List<CommodityDto> entity2Dtos(List<CommodityEntity> commodities);

    @Mappings({
            @Mapping(target = "category", expression = "java(getCategoryConverter().entity2DtoWithOutCommodities(commodity.getCategory()))"),
            @Mapping(target = "specifications", expression = "java(getSpecificationConverter().entity2Dtos(commodity.getSpecifications()))")
    })
    CommodityDetailDto entity2DtoDetail(CommodityEntity commodity);

    List<CommodityDetailDto> entity2DtoDetails(List<CommodityEntity> commodity);

    @Mappings({
            @Mapping(target = "specifications", expression = "java(getSpecificationConverter().dto2Entities(commodity.getSpecifications()))")
    })
    CommodityEntity req2Entity(CommodityCreateRequest commodity);

    CommodityEntity req2Entity(CommodityUpdateRequest commodity);


    @AfterMapping
    default void setSpecificationCommodity(@MappingTarget CommodityEntity commodityEntity) {
        commodityEntity.getSpecifications().forEach(spc -> spc.setCommodity(commodityEntity));
    }

    default SpecificationConverter getSpecificationConverter() {
        return SpringUtils.getBean(SpecificationConverter.class);
    }

    default CategoryConverter getCategoryConverter() {
        return SpringUtils.getBean(CategoryConverter.class);
    }
}
