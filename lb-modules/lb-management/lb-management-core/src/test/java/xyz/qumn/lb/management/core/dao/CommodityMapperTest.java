package xyz.qumn.lb.management.core.dao;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import xyz.qumn.lb.management.core.pojo.entity.Category;
import xyz.qumn.lb.management.core.pojo.entity.Commodity;

import java.util.List;

@SpringBootTest
@Log
class CommodityMapperTest {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CommodityMapper commodityMapper;


    @Test
    void saveShouldWork(){
        Category category = new Category();
        category.setName("category1");
        category.setPriority(1);
        category.setMid(5L);
        categoryMapper.insert(category);
        log.info("success save mid: " + category.getMid());
        Commodity commodity = new Commodity();
        commodity.setMid(category.getMid());
        //commodityEntity.

        //SpecificationEntity spcEntity = new SpecificationEntity();

    }
    @Test
    @Transactional
    void selectShouldWork(){
        Iterable<Category> all = categoryMapper.selectList(null);
        for (Category category : all) {
            System.out.println(category);
        }
    }

    @Test
    void shouldWork(){
        List<Commodity> x = commodityMapper.selectByMid(1L);
        System.out.println(x);
    }

}