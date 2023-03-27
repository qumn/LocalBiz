package xyz.qumn.lb.management.core.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import xyz.qumn.lb.management.core.dao.SpecificationMapper;
import xyz.qumn.lb.management.core.pojo.entity.Specification;

import java.util.List;

@SpringBootTest
class CommodityServiceImplTest {
    @Autowired
    SpecificationMapper specificationMapper;

    @Test
    @Transactional
    void deleteByCidShouldWork(){
        // why not working?
        List<Specification> specifications = specificationMapper.selectByCid(1L);
        specifications.forEach(specification -> {
            specificationMapper.deleteById(specification.getSid());
        });
    }

}