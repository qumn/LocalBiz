package xyz.qumn.lb.management.api.feign;

import com.ruoyi.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.qumn.lb.management.api.dto.CommodityDetailDto;

@FeignClient(contextId = "remoteCommodityService", value = "lb-management")
public interface RemoteCommodityService {
    @GetMapping("/commodity/{cid}")
    R<CommodityDetailDto> get(@PathVariable("cid") Long cid);
}
