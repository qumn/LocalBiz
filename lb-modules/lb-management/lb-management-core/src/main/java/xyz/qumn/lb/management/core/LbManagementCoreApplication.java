package xyz.qumn.lb.management.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.ruoyi")
@SpringBootApplication
public class LbManagementCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(LbManagementCoreApplication.class, args);
    }
}
