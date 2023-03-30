package xyz.qumn.lb.management.core.dao;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import xyz.qumn.lb.management.core.pojo.entity.Category;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class CategoryMapperTest extends BaseDaoTest {

    CategoryMapper categoryMp;

    @Before
    public void init() {
        categoryMp = ssProvide.getMapper(CategoryMapper.class);
    }

    @Test
    @DataSet("categories.yml")
    public void shouldWorkedSelectByMid() {
        List<Category> categories = categoryMp.selectByMid(1L);
        assert categories.size() == 2;
    }

    @Test
    @DataSet("categories.yml")
    @ExpectedDataSet("expected/categories.yml")
    public void shouldAssertDataBaseUsingExpectedDataSet() {
        Category category = categoryMp.selectById(1L);
        assertThat(category).isNotNull();
        categoryMp.deleteById(1L);
        ssProvide.commit();
    }
}