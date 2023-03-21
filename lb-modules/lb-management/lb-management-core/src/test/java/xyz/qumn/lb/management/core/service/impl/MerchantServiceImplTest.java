package xyz.qumn.lb.management.core.service.impl;


import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CrsRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.qumn.lb.management.core.pojo.entity.MerchantEntity;

@SpringBootTest
class MerchantServiceImplTest {
    @Autowired
    MerchantServiceImpl merchantService;

    @Test
    void add() {
        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity.setName("test");
        Point point = new Point(new G2D(1, 2), CrsRegistry.getCoordinateReferenceSystemForEPSG(4326, null));
        merchantEntity.setGeom(point);
        merchantEntity.setPhone("123456789");
        merchantService.add(merchantEntity);
    }
}