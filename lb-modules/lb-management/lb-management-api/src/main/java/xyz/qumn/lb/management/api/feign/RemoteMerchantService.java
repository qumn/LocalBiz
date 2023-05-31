package xyz.qumn.lb.management.api.feign;

import com.ruoyi.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.qumn.lb.management.api.dto.MerchantDto;

import java.util.Collection;
import java.util.List;

@FeignClient(contextId = "remoteMerchantService", value = "lb-management")
public interface RemoteMerchantService {
    @GetMapping("/merchant/list/ids")
    R<List<MerchantDto>> getMerchantByMids(@RequestParam("mids") Collection<Long> mids);
}
