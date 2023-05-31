package xyz.qumn.lb.management.api.feign;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.domain.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.qumn.lb.management.api.dto.SpecificationDto;

import java.util.Collection;
import java.util.List;

@FeignClient(contextId = "remoteSpecificationService", value = "lb-management")
public interface RemoteSpecificationService {
    @GetMapping("/specification/{sid}")
    R<SpecificationDto> get(@PathVariable("sid") Long sid);

    @GetMapping("/specification/list")
    R<List<SpecificationDto>> getSpecByIds(@RequestParam("sids") Collection<Long> sids);
}
