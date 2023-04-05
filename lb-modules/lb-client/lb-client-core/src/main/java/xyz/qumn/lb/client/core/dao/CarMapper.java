package xyz.qumn.lb.client.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import xyz.qumn.lb.client.core.pojo.entity.Car;

import java.util.List;


@Repository
public interface CarMapper extends BaseMapper<Car> {
    List<Car> selectByUserId(Long uid);
}
