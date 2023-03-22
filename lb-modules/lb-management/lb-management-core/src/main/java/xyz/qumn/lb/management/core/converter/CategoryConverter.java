package xyz.qumn.lb.management.core.converter;


import com.ruoyi.common.core.utils.SpringUtils;
import org.mapstruct.*;
import xyz.qumn.lb.management.api.dto.CategoryDto;
import xyz.qumn.lb.management.api.request.category.CategoryCreateRequest;
import xyz.qumn.lb.management.api.request.category.CategoryUpdateRequest;
import xyz.qumn.lb.management.core.pojo.entity.CategoryEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryConverter {

    @Mappings({
            @Mapping(target = "commodities", expression = "java(getCommodityConverter().entity2Dtos(category.getCommodities()))")
    })
    @Named("entity2Dto")
    CategoryDto entity2Dto(CategoryEntity category);

    @Mappings({
            @Mapping(target = "commodities", ignore = true)
    })
    CategoryDto entity2DtoWithOutCommodities(CategoryEntity category);


    @IterableMapping(qualifiedByName = "entity2Dto")
    List<CategoryDto> entity2Dtos(List<CategoryEntity> entities);

    CategoryEntity req2Entity(CategoryCreateRequest category);
    CategoryEntity req2Entity(CategoryUpdateRequest category);

    default CommodityConverter getCommodityConverter() {
        return SpringUtils.getBean(CommodityConverter.class);
    }
}
