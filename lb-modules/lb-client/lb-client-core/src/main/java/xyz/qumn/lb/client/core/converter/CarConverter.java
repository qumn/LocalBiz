package xyz.qumn.lb.client.core.converter;

import org.mapstruct.Mapper;
import xyz.qumn.lb.client.api.dto.CarDto;
import xyz.qumn.lb.client.api.request.CartCreateRequest;
import xyz.qumn.lb.client.core.pojo.entity.Car;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarConverter {
    CarDto entityToDto(Car car);
    List<CarDto> entityToDtos(List<Car> cars);

    Car requestToEntity(CartCreateRequest carCreateRequest);
    List<Car> requestToEntities(List<CartCreateRequest> cartCreateRequests);
}
