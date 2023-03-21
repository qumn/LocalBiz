package xyz.qumn.lb.management.core.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.qumn.lb.management.api.dto.MerchantDto;
import xyz.qumn.lb.management.core.dao.MerchantDao;
import xyz.qumn.lb.management.core.pojo.entity.MerchantEntity;
import xyz.qumn.lb.management.core.service.IMerchantService;

import java.util.List;

@RestController
@RequestMapping("merchant")
public class MerchantController extends BaseController {
    @Autowired
    MerchantDao merchantDao;
    @Autowired
    IMerchantService merchantService;

    /**
     * 获取所有商户列表
     * @return
     */
    @GetMapping("/list")
    public AjaxResult list(){
        return success(merchantDao.findAll());
    }

    /**
     * 获取当前用户的商户列表
     * @return 商户列表
     */
    @GetMapping("/own")
    public R<List<MerchantEntity>> getOwnMerchant() {
        return R.ok(merchantDao.findByOwner(SecurityUtils.getUserId()));
    }

    /**
     * 添加商户
     * @param merchant
     * @return
     */
    @PostMapping
    public AjaxResult add(@RequestBody MerchantDto merchant){
        MerchantEntity merchantEntity = new MerchantEntity();
        BeanUtils.copyProperties(merchant, merchantEntity);
        merchantEntity.setOwner(SecurityUtils.getUserId());
        merchantService.add(merchantEntity);
        return success();
    }

    /**
     * 删除商户
     * @param merchantId
     * @return
     */
    @DeleteMapping("/{merchantId}")
    public AjaxResult delete(@PathVariable Long merchantId){
        merchantDao.deleteById(merchantId);
        return AjaxResult.success();
    }

    /**
     * 更新商户信息
     * @param merchantDao
     * @return
     */
    @PutMapping
    public AjaxResult update(@RequestBody MerchantDao merchantDao){
        MerchantEntity merchantEntity = new MerchantEntity();
        BeanUtils.copyProperties(merchantDao, merchantEntity);
        merchantDao.save(merchantEntity);
        return AjaxResult.success();
    }
}
