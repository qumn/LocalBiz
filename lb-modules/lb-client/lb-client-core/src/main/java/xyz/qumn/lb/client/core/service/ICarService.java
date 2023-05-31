package xyz.qumn.lb.client.core.service;

import com.ruoyi.common.core.domain.R;
import xyz.qumn.lb.client.api.dto.CarDto;
import xyz.qumn.lb.management.api.dto.MerchantDto;

import java.util.List;
import java.util.Map;

public interface ICarService {
    CarDto selectById(Long id);

    List<CarDto> selectByUid(Long uid);
}
