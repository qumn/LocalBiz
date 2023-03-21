package xyz.qumn.lb.management.core.feign;

import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.enums.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FeignTest {
    @Autowired
    RemoteUserService userService;

    @Test
    void remoteCallShouldWork(){
        userService.updateUserType(1L, UserType.MERCHANT);
    }
}
