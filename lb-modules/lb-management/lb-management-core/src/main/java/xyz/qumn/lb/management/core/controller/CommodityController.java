package xyz.qumn.lb.management.core.controller;

import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import xyz.qumn.lb.management.api.dto.CommodityDetailDto;
import xyz.qumn.lb.management.api.request.commodity.CommodityCreateRequest;
import xyz.qumn.lb.management.api.request.commodity.CommodityUpdateRequest;
import xyz.qumn.lb.management.core.converter.CommodityConverter;
import xyz.qumn.lb.management.core.dao.CommodityDao;
import xyz.qumn.lb.management.core.pojo.entity.CommodityEntity;
import xyz.qumn.lb.management.core.service.ICommodityService;

import java.util.List;

@RestController
@RequestMapping("/commodity")
public class CommodityController {
    @Autowired
    CommodityConverter commodityCvt;
    @Autowired
    CommodityDao commodityDao;
    @Autowired
    ICommodityService commodityService;

    @GetMapping("/list")
    public R<List<CommodityDetailDto>> list(@RequestParam(value = "mid", required = false) Long mid, @RequestParam(value = "catId", required = false) Long catId) {
        CommodityEntity commodity = new CommodityEntity();
        commodity.setMid(mid);
        commodity.setCatId(catId);
        return R.ok(commodityCvt.entity2DtoDetails(commodityDao.findAll(Example.of(commodity))));
    }

    @GetMapping("/{cid}")
    public R<CommodityDetailDto> get(@PathVariable("cid") Long cid) {
        return R.ok(commodityCvt.entity2DtoDetail(commodityDao.findById(cid).orElse(null)));
    }

    @PostMapping
    public R<Long> save(@RequestBody CommodityCreateRequest commodity) {
        CommodityEntity commodityEntity = commodityCvt.req2Entity(commodity);
        Long cid = commodityService.save(commodityEntity);
        return R.ok(cid);
    }

    @PutMapping
    public R<Void> update(@RequestBody CommodityUpdateRequest commodity) {
        CommodityEntity commodityEntity = commodityCvt.req2Entity(commodity);
        commodityService.update(commodityEntity);
        return R.ok();
    }

    @DeleteMapping("/{cid}")
    public R<Void> delete(@PathVariable Long cid) {
        commodityDao.deleteById(cid);
        return R.ok();
    }

}
