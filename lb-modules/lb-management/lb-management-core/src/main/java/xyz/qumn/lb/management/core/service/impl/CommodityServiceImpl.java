package xyz.qumn.lb.management.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.qumn.lb.management.core.dao.CommodityMapper;
import xyz.qumn.lb.management.core.dao.SpecificationAttributeMapper;
import xyz.qumn.lb.management.core.dao.SpecificationMapper;
import xyz.qumn.lb.management.core.pojo.entity.Commodity;
import xyz.qumn.lb.management.core.pojo.entity.Specification;
import xyz.qumn.lb.management.core.pojo.entity.SpecificationAttribute;
import xyz.qumn.lb.management.core.service.ICommodityService;

import java.util.List;

@Service
public class CommodityServiceImpl implements ICommodityService {
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    SpecificationMapper specificationMapper;
    @Autowired
    SpecificationAttributeMapper attributeMapper;

    @Override
    @Transactional
    public Long save(Commodity commodity) {
        commodityMapper.insert(commodity);
        cascadeSave(commodity);
        return commodity.getCid();
    }

    @Override
    @Transactional
    public void update(Commodity commodity) {
        commodityMapper.updateById(commodity);
        Long cid = commodity.getCid();
        List<Specification> specifications = specificationMapper.selectByCid(cid);
        List<Long> sids = specifications.stream().map(Specification::getSid).toList();
        if (sids.size() != 0)
            attributeMapper.deleteBatchSids(sids);
        specificationMapper.deleteAllByCid(cid);
        cascadeSave(commodity);
    }


    private void cascadeSave(Commodity commodity) {
        // TODO: specification id 变化问题
        Long cid = commodity.getCid();
        List<Specification> specifications = commodity.getSpecifications();
        specifications.forEach(spec -> spec.setCid(cid));
        if (commodity.getSpecifications().size() != 0)
            specificationMapper.insertBatch(commodity.getSpecifications());
        for (Specification specification : specifications) {
            List<SpecificationAttribute> attributes = specification.getAttributes();
            attributes.forEach(attr -> attr.setSid(specification.getSid()));
            attributeMapper.insertBatch(attributes);
        }
    }
}
