package xyz.qumn.lb.management.core.dao;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import xyz.qumn.lb.common.mybatis.test.dao.BaseDaoTest;
import xyz.qumn.lb.management.core.pojo.entity.Commodity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class CommodityMapperTest extends BaseDaoTest {

    CategoryMapper categoryMapper;
    CommodityMapper commodityMapper;

    @Before
    public void init() {
        categoryMapper = ssProvide.getMapper(CategoryMapper.class);
        commodityMapper = ssProvide.getMapper(CommodityMapper.class);
    }


    @Test
    @DataSet(value = {"commodities.yml", "categories.yml", "merchants.yml"})
    public void shouldWorkSelectById() {
        Commodity commodity = commodityMapper.selectById(1L);
        assertThat(commodity).isNotNull();
        assertThat(commodity.getCategory()).isNotNull();
        assertThat(commodity.getMid()).isNotNull();
    }

    @Test
    @DataSet(value = {"commodities.yml", "categories.yml", "merchants.yml"})
    public void shouldWorkSelectByMid() {
        List<Commodity> commodities = commodityMapper.selectByMid(1L);
        assertThat(commodities)
                .isNotNull()
                .hasSize(4);
    }
}