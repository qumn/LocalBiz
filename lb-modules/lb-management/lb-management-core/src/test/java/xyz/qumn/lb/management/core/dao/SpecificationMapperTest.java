package xyz.qumn.lb.management.core.dao;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import xyz.qumn.lb.management.core.pojo.entity.Specification;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class SpecificationMapperTest extends BaseDaoTest {

    SpecificationMapper specMp;

    @Before
    public void init() {
        specMp = getSession().getMapper(SpecificationMapper.class);
    }

    @Test
    @DataSet(value = {"specifications.yml", "attributes.yml", "merchants.yml"})
    public void shouldWorkSelectByCid() {
        List<Specification> specifications = specMp.selectByCid(1L);
        assertThat(specifications)
                .isNotNull()
                .hasSize(3);
    }

    @Test
    @DataSet(value = {"specifications.yml", "attributes.yml", "merchants.yml"})
    public void shouldWorkedCorrelation() {
        Specification spec = specMp.selectById(1);
        assertThat(spec)
                .isNotNull();
        assertThat(spec.getAttributes())
                .hasSize(3);
    }

}
