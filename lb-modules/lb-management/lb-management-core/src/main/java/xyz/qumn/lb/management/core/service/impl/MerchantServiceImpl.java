package xyz.qumn.lb.management.core.service.impl;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.enums.UserType;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.qumn.lb.management.api.dto.MerchantDto;
import xyz.qumn.lb.management.core.converter.MerchantConverter;
import xyz.qumn.lb.management.core.dao.MerchantMapper;
import xyz.qumn.lb.management.core.pojo.entity.Merchant;
import xyz.qumn.lb.management.core.service.IMerchantService;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@Log
public class MerchantServiceImpl implements IMerchantService {
    @Autowired
    RemoteUserService userService;
    @Autowired
    MerchantMapper merchantMapper;
    @Autowired
    MerchantConverter merchantCvt;

    @Override
    public void add(Merchant merchant) {
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
        merchantMapper.insert(merchant);
    }

    @Override
    public List<MerchantDto> selectBatchIds(Collection<Long> mids) {
        List<Merchant> merchants = merchantMapper.selectBatchIds(mids);
        return merchantCvt.entity2Dtos(merchants);
    }
}
