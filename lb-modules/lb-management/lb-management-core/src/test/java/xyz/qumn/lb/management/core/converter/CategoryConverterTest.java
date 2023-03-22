package xyz.qumn.lb.management.core.converter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.qumn.lb.management.core.dao.CategoryDao;
import xyz.qumn.lb.management.core.pojo.entity.CategoryEntity;
import xyz.qumn.lb.management.core.pojo.entity.CommodityEntity;

import java.util.List;


@SpringBootTest
class CategoryConverterTest {

    @Autowired
    CategoryConverter categoryConverter;
    @Autowired
    CommodityConverter commodityConverter;
    @Autowired
    CategoryDao categoryDao;

    @Test
    void converterShouldWork(){
        List<CategoryEntity> categoryEntities = categoryDao.findAll();
        for (CategoryEntity categoryEntity : categoryEntities) {
            for (CommodityEntity entity : categoryEntity.getCommodities()) {
                System.out.println(commodityConverter.entity2DtoDetail(entity));
            }
        }

    }

}