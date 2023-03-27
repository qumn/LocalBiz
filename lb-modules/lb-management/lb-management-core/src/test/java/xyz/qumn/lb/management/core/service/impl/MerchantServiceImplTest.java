package xyz.qumn.lb.management.core.service.impl;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.qumn.lb.management.core.pojo.entity.Merchant;

@SpringBootTest
class MerchantServiceImplTest {
    @Autowired
    MerchantServiceImpl merchantService;

    @Test
    void add() {
        Merchant merchant = new Merchant();
        merchant.setName("test");
        //Point point = new Point(new G2D(1, 2), CrsRegistry.getCoordinateReferenceSystemForEPSG(4326, null));
        merchant.setPhone("123456789");
        merchantService.add(merchant);
    }
}