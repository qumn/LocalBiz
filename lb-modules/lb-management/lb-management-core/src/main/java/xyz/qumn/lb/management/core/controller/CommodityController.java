package xyz.qumn.lb.management.core.controller;

import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.qumn.lb.management.api.dto.CategoryDto;
import xyz.qumn.lb.management.api.dto.CommodityDetailDto;
import xyz.qumn.lb.management.api.request.commodity.CommodityCreateRequest;
import xyz.qumn.lb.management.api.request.commodity.CommodityUpdateRequest;
import xyz.qumn.lb.management.core.converter.CommodityConverter;
import xyz.qumn.lb.management.core.dao.CommodityDao;
import xyz.qumn.lb.management.core.pojo.entity.CommodityEntity;

import java.util.List;

@RestController
@RequestMapping("/commodity")
public class CommodityController {
    @Autowired
    CommodityConverter commodityCvt;
    @Autowired
    CommodityDao commodityDao;

    @GetMapping("/list/{mid}")
    public R<List<CommodityDetailDto>> list(@PathVariable("mid") Long mid) {
        return R.ok(commodityCvt.entity2DtoDetails(commodityDao.findByMid(mid)));
    }

    @GetMapping("/{cid}")
    public R<CommodityDetailDto> get(@PathVariable("cid") Long cid) {
        return R.ok(commodityCvt.entity2DtoDetail(commodityDao.findById(cid).orElse(null)));
    }

    @PostMapping
    public R<Long> save(@RequestBody CommodityCreateRequest commodity) {
        CommodityEntity commodityEntity = commodityCvt.req2Entity(commodity);
        Long cid = commodityDao.save(commodityEntity).getCid();
        return R.ok(cid);
    }

    @PutMapping
    public R<Void> update(@RequestBody CommodityUpdateRequest commodity) {
        CommodityEntity commodityEntity = commodityCvt.req2Entity(commodity);
        commodityDao.save(commodityEntity);
        return R.ok();
    }

    @DeleteMapping("/{cid}")
    public R<Void> delete(@PathVariable Long cid) {
        commodityDao.deleteById(cid);
        return R.ok();
    }

}
