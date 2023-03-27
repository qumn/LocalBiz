package xyz.qumn.lb.management.core.converter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.qumn.lb.management.core.dao.CategoryMapper;
import xyz.qumn.lb.management.core.pojo.entity.Category;
import xyz.qumn.lb.management.core.pojo.entity.Commodity;

import java.util.List;


@SpringBootTest
class CategoryConverterTest {

    @Autowired
    CategoryConverter categoryConverter;
    @Autowired
    CommodityConverter commodityConverter;
    @Autowired
    CategoryMapper categoryMapper;

    @Test
    void converterShouldWork(){
        List<Category> categoryEntities = categoryMapper.selectList(null);
        for (Category category : categoryEntities) {
            for (Commodity entity : category.getCommodities()) {
                System.out.println(commodityConverter.entity2DtoDetail(entity));
            }
        }

    }

}