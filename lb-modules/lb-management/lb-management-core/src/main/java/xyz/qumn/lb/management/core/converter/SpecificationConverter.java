package xyz.qumn.lb.management.core.converter;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import xyz.qumn.lb.management.api.dto.SpecificationAttributeDto;
import xyz.qumn.lb.management.api.dto.SpecificationDto;
import xyz.qumn.lb.management.core.pojo.entity.Specification;
import xyz.qumn.lb.management.core.pojo.entity.SpecificationAttribute;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommodityConverter.class})
public interface SpecificationConverter {
    SpecificationDto entity2Dto(Specification specification);

    List<SpecificationDto> entity2Dtos(List<Specification> specification);

    Specification dto2Entity(SpecificationDto specification);

    List<Specification> dto2Entities(List<SpecificationDto> specification);

    SpecificationAttributeDto atbEntity2Dto(SpecificationAttribute specificationAttribute);

    List<SpecificationAttributeDto> atbEntity2Dtos(List<SpecificationAttribute> specificationAttribute);


    @AfterMapping
    default void setSpecificationAtb(@MappingTarget Specification specification) {
        specification.getAttributes().forEach(atb -> atb.setSpecification(specification));
    }
}
