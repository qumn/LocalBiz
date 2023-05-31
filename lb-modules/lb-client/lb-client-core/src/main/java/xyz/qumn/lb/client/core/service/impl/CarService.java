package xyz.qumn.lb.client.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.qumn.lb.client.api.dto.CarDto;
import xyz.qumn.lb.client.core.converter.CarConverter;
import xyz.qumn.lb.client.core.dao.CarMapper;
import xyz.qumn.lb.client.core.pojo.entity.Car;
import xyz.qumn.lb.client.core.service.ICarService;
import xyz.qumn.lb.management.api.dto.MerchantDto;
import xyz.qumn.lb.management.api.dto.SpecificationDto;
import xyz.qumn.lb.management.api.feign.RemoteMerchantService;
import xyz.qumn.lb.management.api.feign.RemoteSpecificationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarService implements ICarService {
    @Autowired
    CarMapper carMp;
    @Autowired
    CarConverter carCvt;
    @Autowired
    RemoteSpecificationService specServ;
    @Autowired
    RemoteMerchantService merchantServ;


    @Override
    public CarDto selectById(Long id) {
        Car car = carMp.selectById(id);
        CarDto carDto = carCvt.entityToDto(car);
        if (car != null) {
            SpecificationDto spec = specServ.get(car.getSid()).getData();
            carDto.setSpecification(spec);
            if (spec != null) {
                carDto.setCommodity(spec.getCommodity());
                spec.setCommodity(null);
            }
        }
        return carDto;
    }

    @Override
    public List<CarDto> selectByUid(Long uid) {
        List<Car> cars = carMp.selectByUid(uid);
        List<CarDto> carDtos = carCvt.entityToDtos(cars);
        setSpecification(carDtos);
        return carDtos;
    }

    private void setSpecification(List<CarDto> carDtos) {
        Set<Long> sids = carDtos.stream().map(CarDto::getSid).collect(Collectors.toSet());
        List<SpecificationDto> specs = specServ.getSpecByIds(sids).getData();
        Map<Long, SpecificationDto> specMap = specs.stream().collect(Collectors.toMap(SpecificationDto::getSid, spec -> spec));

        // set specification
        for (CarDto dto : carDtos) {
            SpecificationDto spec = specMap.get(dto.getSid());
            dto.setSpecification(spec);
            if (spec != null) {
                dto.setCommodity(spec.getCommodity());
                spec.setCommodity(null);
            }
        }

        // set commodity
        Set<Long> mids = carDtos.stream().map(CarDto::getMid).collect(Collectors.toSet());
        List<MerchantDto> merchants = merchantServ.getMerchantByMids(mids).getData();
        Map<Long, MerchantDto> merchantMap = merchants.stream().collect(Collectors.toMap(MerchantDto::getMid, merchant -> merchant));
        for (CarDto carDto : carDtos) {
            Long mid = carDto.getMid();
            carDto.setMerchant(merchantMap.get(mid));
        }
    }
}
