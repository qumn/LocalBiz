package xyz.qumn.lb.management.core.service.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.index.hprtree.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.qumn.lb.management.core.dao.SpecificationDao;
import xyz.qumn.lb.management.core.pojo.entity.SpecificationEntity;

import java.util.List;

@SpringBootTest
class CommodityServiceImplTest {
    @Autowired
    SpecificationDao specificationDao;

    @Test
    @Transactional
    void deleteByCidShouldWork(){
        // why not working?
        List<SpecificationEntity> specifications = specificationDao.queryByCid(1L);
        specifications.forEach(specification -> {
            specificationDao.deleteById(specification.getSid());
        });
    }

}