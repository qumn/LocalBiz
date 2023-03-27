package xyz.qumn.lb.management.core.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.qumn.lb.management.core.pojo.entity.Merchant;

import java.util.List;

@SpringBootTest
class MerchantMapperTest {
    @Autowired
    MerchantMapper merchantMapper;

    @Test
    public void findById_should_work() {
        List<Merchant> byOwner = merchantMapper.findByOwner(100L);
        System.out.println(byOwner);
    }

}