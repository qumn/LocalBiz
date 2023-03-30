package xyz.qumn.lb.management.core.dao;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import xyz.qumn.lb.management.core.pojo.entity.Merchant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(JUnit4.class)
public class MerchantMapperTest extends BaseDaoTest {
    MerchantMapper merchantMapper;

    @Before
    public void init() {
        merchantMapper = ssProvide.getMapper(MerchantMapper.class);
    }

    @Test
    @DataSet("merchants.yml")
    public void shouldWorkSelectByOwnerId() {
        List<Merchant> merchants = merchantMapper.selectByOwner(100L);
        assertThat(merchants).isNotNull()
                .hasSize(2);
        assertThat(merchants.get(0).getGeom()).isNotNull();
    }
    @Test
    @DataSet("merchants.yml")
    public void shouldWorkSelectByOwnerId3() {
        List<Merchant> merchants = merchantMapper.selectByOwner(100L);
        assertThat(merchants).isNotNull()
                .hasSize(2);
        assertThat(merchants.get(0).getGeom()).isNotNull();
    }
    @Test
    @DataSet("merchants.yml")
    public void shouldWorkSelectByOwnerId2() {
        List<Merchant> merchants = merchantMapper.selectByOwner(100L);
        assertThat(merchants).isNotNull()
                .hasSize(2);
        assertThat(merchants.get(0).getGeom()).isNotNull();
    }
    @Test
    @DataSet("merchants.yml")
    public void shouldWorkSelectByOwnerId5() {
        List<Merchant> merchants = merchantMapper.selectByOwner(100L);
        assertThat(merchants).isNotNull()
                .hasSize(2);
        assertThat(merchants.get(0).getGeom()).isNotNull();
    }

    @Test
    @DataSet(value = "merchants.yml", cleanBefore = true)
    public void shouldWorkWithGeometryType() throws ParseException {
        Merchant merchant = merchantMapper.selectById(1L);
        Geometry point = new WKTReader().read("POINT(112 32)");
        assertThat(merchant)
                .isNotNull();

        assertThat(merchant.getGeom()).isNotNull().isEqualTo(point);
    }

    @Test
    @DataSet(value = "merchants.yml", cleanBefore = true)
    public void shouldWorkSelectByMidAndName() {
        List<Merchant> merchants = merchantMapper.selectByMidAndName(null, null);
        assertThat(merchants)
                .isNotNull()
                .hasSize(3);
        merchants = merchantMapper.selectByMidAndName(1L, null);
        assertThat(merchants)
                .isNotNull()
                .hasSize(1);
        merchants = merchantMapper.selectByMidAndName(null, "张三的店铺1");
        assertThat(merchants)
                .isNotNull()
                .hasSize(1);

        merchants = merchantMapper.selectByMidAndName(1L, "张三的店铺1");
        assertThat(merchants)
                .isNotNull()
                .hasSize(1);
    }


}