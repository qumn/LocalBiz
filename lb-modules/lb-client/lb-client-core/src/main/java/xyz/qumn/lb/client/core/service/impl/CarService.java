package xyz.qumn.lb.client.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.qumn.lb.client.api.dto.CarDto;
import xyz.qumn.lb.client.core.converter.CarConverter;
import xyz.qumn.lb.client.core.dao.CarMapper;
import xyz.qumn.lb.client.core.pojo.entity.Car;
import xyz.qumn.lb.client.core.service.ICarService;
import xyz.qumn.lb.management.api.dto.SpecificationDto;
import xyz.qumn.lb.management.api.feign.RemoteSpecificationService;

@Service
public class CarService implements ICarService {
    @Autowired
    CarMapper carMp;
    @Autowired
    CarConverter carCvt;
    @Autowired
    RemoteSpecificationService specServ;

    @Override
    public CarDto selectById(Long id) {
        Car car = carMp.selectById(id);
        CarDto carDto = carCvt.entityToDto(car);
        if (car != null) {
            SpecificationDto spec = specServ.get(car.getSid()).getData();
            carDto.setSpecDto(spec);
            if (spec != null) {
                carDto.setCommodity(spec.getCommodity());
                spec.setCommodity(null);
            }
        }
        return carDto;
    }
}
