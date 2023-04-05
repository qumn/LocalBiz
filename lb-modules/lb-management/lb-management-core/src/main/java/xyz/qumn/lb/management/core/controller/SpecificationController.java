package xyz.qumn.lb.management.core.controller;

import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.qumn.lb.management.api.dto.SpecificationDto;
import xyz.qumn.lb.management.core.converter.SpecificationConverter;
import xyz.qumn.lb.management.core.dao.SpecificationMapper;
import xyz.qumn.lb.management.core.pojo.entity.Specification;

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
}
