package xyz.qumn.lb.management.core.dao;

import com.github.database.rider.core.api.dataset.DataSet;
import org.apache.logging.log4j.util.ServiceLoaderUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import xyz.qumn.lb.common.mybatis.test.dao.BaseDaoTest;
import xyz.qumn.lb.management.core.pojo.entity.Order;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class OrderMapperTest extends BaseDaoTest {
    OrderMapper orderMapper;

    @Before
    public void init() {
        orderMapper = ssProvide.getMapper(OrderMapper.class);
    }

    @Test
    @DataSet(value = {"orders.yml", "items.yml"})
    public void shouldWorkSelectById(){
        Order order = orderMapper.selectById(1L);
        assertThat(order)
                .isNotNull();
        assertThat(order.getItems())
                .isNotNull()
                .hasSize(2);
        System.out.println(order);
    }

}
