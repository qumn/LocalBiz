package xyz.qumn.lb.management.core.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.qumn.lb.management.core.pojo.entity.Category;

import java.util.List;

@SpringBootTest
class CategoryMapperTest {
    @Autowired
    CategoryMapper categoryMapper;
    @Test
    void selectShouldWork(){
        List<Category> categories = categoryMapper.selectByMid(1L);
        System.out.println(categories);
    }

}