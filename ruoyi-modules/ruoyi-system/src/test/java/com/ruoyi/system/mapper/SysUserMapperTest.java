package com.ruoyi.system.mapper;

import com.ruoyi.system.api.domain.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SysUserMapperTest {

    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    void selectUserList() {
        List<SysUser> sysUsers = sysUserMapper.selectUserList(new SysUser());
        sysUsers.forEach(System.out::println);
    }
}