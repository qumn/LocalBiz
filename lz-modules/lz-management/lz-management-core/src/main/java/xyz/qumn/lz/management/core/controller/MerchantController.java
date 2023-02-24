package xyz.qumn.lz.management.core.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.qumn.lz.management.api.dto.MerchantDto;
import xyz.qumn.lz.management.core.dao.MerchantDao;
import xyz.qumn.lz.management.core.pojo.entity.MerchantEntity;

@RestController
@RequestMapping("merchant")
public class MerchantController extends BaseController {
    @Autowired
    MerchantDao merchantDao;

    @GetMapping("/list")
    public AjaxResult list(){
        return success(merchantDao.findAll());
    }

    @PostMapping
    public AjaxResult add(@RequestBody MerchantDto merchant){
        MerchantEntity merchantEntity = new MerchantEntity();
        BeanUtils.copyProperties(merchant, merchantEntity);
        merchantDao.save(merchantEntity);
        return success();
    }

    @DeleteMapping("/{merchantId}")
    public AjaxResult delete(@PathVariable Long merchantId){
        merchantDao.deleteById(merchantId);
        return AjaxResult.success();
    }

    @PutMapping
    public AjaxResult update(@RequestBody MerchantDao merchantDao){
        MerchantEntity merchantEntity = new MerchantEntity();
        BeanUtils.copyProperties(merchantDao, merchantEntity);
        merchantDao.save(merchantEntity);
        return AjaxResult.success();
    }
}
