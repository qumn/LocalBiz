package xyz.qumn.lb.management.core.service;

import xyz.qumn.lb.management.api.dto.MerchantDto;
import xyz.qumn.lb.management.core.pojo.entity.Merchant;

import java.util.Collection;
import java.util.List;

public interface IMerchantService {
    void add(Merchant merchant);

    List<MerchantDto> selectBatchIds(Collection<Long> mids);
}
