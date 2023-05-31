package xyz.qumn.lb.client.core.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.qumn.lb.client.api.dto.CarDto;
import xyz.qumn.lb.client.core.dao.CarMapper;
import xyz.qumn.lb.client.core.service.ICarService;
import xyz.qumn.lb.management.api.dto.MerchantDto;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarMapper carMp;

    @Autowired
    ICarService carSv;

    @GetMapping
    public R<List<CarDto>> getCars(){
        Long uid = SecurityUtils.getUserId();
        List<CarDto> carDtos = carSv.selectByUid(uid);
        return R.ok(carDtos);
    }

    @GetMapping("/{id}")
    public R<CarDto> getCar(@PathVariable("id") Long id) {
        return R.ok(carSv.selectById(id));
    }

}
