package xyz.qumn.lb.client.core.controller;

import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.qumn.lb.client.api.dto.CarDto;
import xyz.qumn.lb.client.core.dao.CarMapper;
import xyz.qumn.lb.client.core.service.ICarService;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarMapper carMp;

    @Autowired
    ICarService carSv;


    @GetMapping("/{id}")
    public R<CarDto> getCar(@PathVariable("id") Long id) {
        return R.ok(carSv.selectById(id));
    }

}
