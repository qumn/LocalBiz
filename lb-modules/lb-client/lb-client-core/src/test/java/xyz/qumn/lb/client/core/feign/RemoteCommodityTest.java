package xyz.qumn.lb.client.core.feign;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.qumn.lb.management.api.feign.RemoteCommodityService;

@SpringBootTest
public class RemoteCommodityTest {
    @Autowired
    RemoteCommodityService commodityServ;

    @Test
    public void test() {
        System.out.println(commodityServ.get(1L).getData());
    }
}
