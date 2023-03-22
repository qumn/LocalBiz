package xyz.qumn.lb.management.core.service.impl;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.enums.UserType;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.qumn.lb.management.core.dao.MerchantDao;
import xyz.qumn.lb.management.core.pojo.entity.MerchantEntity;
import xyz.qumn.lb.management.core.service.IMerchantService;

import java.util.Objects;

@Service
@Log
public class MerchantServiceImpl implements IMerchantService {
    @Autowired
    RemoteUserService userService;
    @Autowired
    MerchantDao merchantDao;

    @Override
    public void add(MerchantEntity merchant) {
        Long userId = SecurityUtils.getUserId();
        log.info("当前用户ID为：" + userId);
        // 切换当前用户类型为商家
        userService.updateUserType(userId, UserType.MERCHANT);
        // name can not be empty
        if (StringUtils.isEmpty(merchant.getName())) {
            throw new RuntimeException("商家名称不能为空");
        }
        if (Objects.isNull(merchant.getGeom())){
            throw new RuntimeException("商家位置不能为空");
        }
        // 保存商家信息
        merchantDao.save(merchant);
    }
}
