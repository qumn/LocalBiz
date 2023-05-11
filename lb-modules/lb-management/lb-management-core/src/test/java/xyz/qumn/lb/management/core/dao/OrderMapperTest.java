package xyz.qumn.lb.management.core.dao;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import xyz.qumn.lb.common.mybatis.test.dao.BaseDaoTest;
import xyz.qumn.lb.management.api.enums.OrderStatus;
import xyz.qumn.lb.management.core.pojo.entity.Order;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class OrderMapperTest extends BaseDaoTest {
    OrderMapper orderMapper;

    @Before
    public void init() {
        orderMapper = ssProvide.getMapper(OrderMapper.class);
    }

    @Test
    @DataSet(value = {"orders.yml", "items.yml", "commodities.yml", "merchants.yml", "specifications.yml", "attributes.yml"})
    public void shouldWorkSelectById() {
        Order order = orderMapper.selectById(1L);
        assertThat(order).isNotNull();
        assertThat(order.getItems()).isNotNull().hasSize(2);
        System.out.println(order);
    }

    @Test
    @DataSet(value = {"orders.yml", "items.yml"})
    public void shouldWorkChangeOrderStatus() {
        int i = orderMapper.modifyStatus(1L, OrderStatus.PENDING);
        assertThat(i).isEqualTo(1);
        Order order = orderMapper.selectById(1);
        assertThat(order).isNotNull();
        assertThat(order.getStatus()).isEqualTo(OrderStatus.PENDING);
    }

    @Test
    @DataSet(value = {"orders.yml", "items.yml"})
    public void shouldWorkSelectByMid() {
        List<Order> orders = orderMapper.selectByMid(1L);
        assertThat(orders).isNotNull().hasSize(2);
    }



}
