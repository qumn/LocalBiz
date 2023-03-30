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
public class DataBaseRider extends BaseDaoTest {
    CategoryMapper categoryMp;

    @Before
    public void init() {
        categoryMp = ssProvide.getMapper(CategoryMapper.class);
    }

    @Test
    @DataSet("categories.yml")
    public void shouldListCategory() {
        List<Category> categories = categoryMp.selectByMid(1L);
        assert categories.size() == 2;
    }

    @Test
    @DataSet("categories.yml")
    public void shouldDeleteUser() {
        Category category = categoryMp.selectById(1);
        assertThat(category).isNotNull();
        categoryMp.deleteById(1);
        List<Category> rst = categoryMp.selectList(null);
        assertThat(rst).isNotNull().hasSize(1);
        ssProvide.rollback();
        List<Category> rstAftRb = categoryMp.selectList(null);
        assertThat(rstAftRb).isNotNull().hasSize(2);
    }

    @Test
    @DataSet("categories.yml")
    @ExpectedDataSet("expectedCategories.yml")
    public void shouldAssertDataBaseUsingExpectedDataSet() {
        Category category = categoryMp.selectById(1L);
        assertThat(category).isNotNull();
        categoryMp.deleteById(1L);
        ssProvide.commit();
    }


}
