package xyz.qumn.lb.client.core.service;

import com.ruoyi.common.core.domain.R;
import xyz.qumn.lb.client.api.dto.CarDto;

public interface ICarService {
    CarDto selectById(Long id);
}
