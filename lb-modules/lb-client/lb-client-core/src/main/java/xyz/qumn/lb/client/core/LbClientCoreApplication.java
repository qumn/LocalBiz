package xyz.qumn.lb.client.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("xyz.qumn.lb.client.core.dao")
@EnableFeignClients(basePackages = {"com.ruoyi", "xyz.qumn"})
public class LbClientCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(LbClientCoreApplication.class, args);
    }

}
