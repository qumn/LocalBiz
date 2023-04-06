package xyz.qumn.lb.client.core.converter;

import org.mapstruct.Mapper;
import xyz.qumn.lb.client.api.dto.CarDto;
import xyz.qumn.lb.client.core.pojo.entity.Car;

@Mapper(componentModel = "spring")
public interface CarConverter {
    CarDto entityToDto(Car car);
}