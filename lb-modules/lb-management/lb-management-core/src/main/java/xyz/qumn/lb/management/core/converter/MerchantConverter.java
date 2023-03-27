package xyz.qumn.lb.management.core.converter;

import org.mapstruct.Mapper;
import xyz.qumn.lb.management.api.dto.MerchantDto;
import xyz.qumn.lb.management.core.pojo.entity.Merchant;

@Mapper(componentModel = "spring")
public interface MerchantConverter {
    Merchant dto2Entity(MerchantDto merchantDto);
}
