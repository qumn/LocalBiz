package xyz.qumn.lb.management.api.feign;

import com.ruoyi.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.qumn.lb.management.api.dto.SpecificationDto;

@FeignClient(contextId = "remoteSpecificationService", value = "lb-management")
public interface RemoteSpecificationService {
    @GetMapping("/specification/{sid}")
    R<SpecificationDto> get(@PathVariable("sid") Long sid);
}
