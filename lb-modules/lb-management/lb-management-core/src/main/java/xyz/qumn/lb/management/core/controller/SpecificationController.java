package xyz.qumn.lb.management.core.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.qumn.lb.management.api.dto.SpecificationDto;
import xyz.qumn.lb.management.core.converter.SpecificationConverter;
import xyz.qumn.lb.management.core.dao.SpecificationMapper;
import xyz.qumn.lb.management.core.pojo.entity.Specification;

import java.util.List;

@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @Autowired
    SpecificationConverter specificationCvt;
    @Autowired
    SpecificationMapper specificationMp;

    @GetMapping("/{sid}")
    public R<SpecificationDto> get(@PathVariable("sid") Long sid) {
        Specification specification = specificationMp.selectById(sid);
        return R.ok(specificationCvt.entity2Dto(specification));
    }

    @GetMapping("/list")

    public R<List<SpecificationDto>> getSpecByids(@RequestParam("sids") List<Long> sids) {
        List<Specification> specs = specificationMp.selectByIds(sids);
        return R.ok(specificationCvt.entity2Dtos(specs));
    }


}