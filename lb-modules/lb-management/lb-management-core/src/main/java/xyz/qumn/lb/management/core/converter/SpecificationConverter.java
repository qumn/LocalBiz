package xyz.qumn.lb.management.core.converter;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import xyz.qumn.lb.management.api.dto.SpecificationAttributeDto;
import xyz.qumn.lb.management.api.dto.SpecificationDto;
import xyz.qumn.lb.management.core.pojo.entity.SpecificationAttributeEntity;
import xyz.qumn.lb.management.core.pojo.entity.SpecificationEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecificationConverter {
    SpecificationDto entity2Dto(SpecificationEntity specification);

    List<SpecificationDto> entity2Dtos(List<SpecificationEntity> specification);

    SpecificationEntity dto2Entity(SpecificationDto specification);

    List<SpecificationEntity> dto2Entities(List<SpecificationDto> specification);

    SpecificationAttributeDto atbEntity2Dto(SpecificationAttributeEntity specificationAttribute);

    List<SpecificationAttributeDto> atbEntity2Dtos(List<SpecificationAttributeEntity> specificationAttribute);


    @AfterMapping
    default void setSpecificationAtb(@MappingTarget SpecificationEntity specification) {
        specification.getAttributes().forEach(atb -> atb.setSpecification(specification));
    }
}
