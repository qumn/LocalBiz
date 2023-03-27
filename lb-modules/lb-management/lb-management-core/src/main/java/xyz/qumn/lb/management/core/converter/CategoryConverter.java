package xyz.qumn.lb.management.core.converter;


import org.mapstruct.*;
import xyz.qumn.lb.management.api.dto.CategoryDto;
import xyz.qumn.lb.management.api.request.category.CategoryCreateRequest;
import xyz.qumn.lb.management.api.request.category.CategoryUpdateRequest;
import xyz.qumn.lb.management.core.pojo.entity.Category;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommodityConverter.class})
public interface CategoryConverter {

    @Named("entity2Dto")
    CategoryDto entity2Dto(Category category);

    @Mappings({
            @Mapping(target = "commodities", ignore = true)
    })
    CategoryDto entity2DtoWithOutCommodities(Category category);


    @IterableMapping(qualifiedByName = "entity2Dto")
    List<CategoryDto> entity2Dtos(List<Category> entities);

    Category req2Entity(CategoryCreateRequest category);

    Category req2Entity(CategoryUpdateRequest category);
}
