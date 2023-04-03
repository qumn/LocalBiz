package xyz.qumn.lb.management.core.dao;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import xyz.qumn.lb.management.core.pojo.entity.Car;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class CarMapperTest extends BaseDaoTest {
    CarMapper carMp;

    @Before
    public void init() {
        carMp = ssProvide.getMapper(CarMapper.class);
    }

    @Test
    @DataSet(value = {"cars.yml", "commodities.yml", "merchants.yml", "specifications.yml", "attributes.yml"})
    public void shouldWorkedSelectById() {
        Car car = carMp.selectById(1);
        assertThat(car)
                .isNotNull();
        assertThat(car.getSpecification())
                .isNotNull();
        assertThat(car.getCommodity())
                .isNotNull();
        assertThat(car.getSpecification().getAttributes())
                .isNotNull()
                .hasSize(3);
    }
}
