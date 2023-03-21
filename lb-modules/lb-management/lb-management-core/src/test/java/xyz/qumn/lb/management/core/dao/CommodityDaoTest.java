package xyz.qumn.lb.management.core.dao;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.qumn.lb.management.core.pojo.entity.CategoryEntity;
import xyz.qumn.lb.management.core.pojo.entity.CommodityEntity;
import xyz.qumn.lb.management.core.pojo.entity.SpecificationEntity;

import java.util.Arrays;

@SpringBootTest
@Log
class CommodityDaoTest {

    @Autowired
    CategoryDao categoryDao;
    @Autowired
    CommodityDao commodityDao;


    @Test
    void saveShouldWork(){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("category1");
        categoryEntity.setPriority(1);
        categoryEntity.setMid(5L);
        categoryEntity = categoryDao.save(categoryEntity);
        log.info("success save mid: " + categoryEntity.getMid());
        CommodityEntity commodityEntity = new CommodityEntity();
        commodityEntity.setMid(categoryEntity.getMid());
        //commodityEntity.

        //SpecificationEntity spcEntity = new SpecificationEntity();

    }
    @Test
    @Transactional
    void selectShouldWork(){
//        Iterable<CommodityEntity> all = commodityDao.findAll();
//        System.out.println(all);
        Iterable<CategoryEntity> all = categoryDao.findAll();
        for (CategoryEntity categoryEntity : all) {
            //System.out.println(Arrays.toString(categoryEntity.getCommodities().stream().map(CommodityEntity::getCid).toArray()));
            System.out.println(categoryEntity);
        }
    }

}