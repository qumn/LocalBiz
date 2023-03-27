package xyz.qumn.lb.management.core.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.qumn.lb.management.api.dto.CommodityDetailDto;
import xyz.qumn.lb.management.api.request.commodity.CommodityCreateRequest;
import xyz.qumn.lb.management.api.request.commodity.CommodityUpdateRequest;
import xyz.qumn.lb.management.core.converter.CommodityConverter;
import xyz.qumn.lb.management.core.dao.CommodityMapper;
import xyz.qumn.lb.management.core.pojo.entity.Commodity;
import xyz.qumn.lb.management.core.service.ICommodityService;

import java.util.List;

@RestController
@RequestMapping("/commodity")
public class CommodityController {
    @Autowired
    CommodityConverter commodityCvt;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    ICommodityService commodityService;

    @GetMapping("/list")
    public R<List<CommodityDetailDto>> list(@RequestParam(value = "mid", required = false) Long mid, @RequestParam(value = "catId", required = false) Long catId) {
        Commodity commodity = new Commodity();
        commodity.setMid(mid);
        commodity.setCatId(catId);
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>(commodity);
        return R.ok(commodityCvt.entity2DtoDetails(commodityMapper.selectList(queryWrapper)));
    }

    @GetMapping("/{cid}")
    public R<CommodityDetailDto> get(@PathVariable("cid") Long cid) {
        return R.ok(commodityCvt.entity2DtoDetail(commodityMapper.selectById(cid)));
    }

    @PostMapping
    public R<Long> save(@RequestBody CommodityCreateRequest commodity) {
        Commodity commodityEntity = commodityCvt.req2Entity(commodity);
        Long cid = commodityService.save(commodityEntity);
        return R.ok(cid);
    }

    @PutMapping
    public R<Void> update(@RequestBody CommodityUpdateRequest commodity) {
        Commodity commodityEntity = commodityCvt.req2Entity(commodity);
        commodityService.update(commodityEntity);
        return R.ok();
    }

    @DeleteMapping("/{cid}")
    public R<Void> delete(@PathVariable Long cid) {
        commodityMapper.deleteById(cid);
        return R.ok();
    }

}
