package xyz.qumn.lb.management.core.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.qumn.lb.management.api.dto.MerchantDto;
import xyz.qumn.lb.management.api.dto.SpecificationDto;
import xyz.qumn.lb.management.core.converter.MerchantConverter;
import xyz.qumn.lb.management.core.dao.MerchantMapper;
import xyz.qumn.lb.management.core.pojo.entity.Merchant;
import xyz.qumn.lb.management.core.service.IMerchantService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("merchant")
public class MerchantController extends BaseController {
    @Autowired
    MerchantMapper merchantMapper;
    @Autowired
    IMerchantService merchantService;
    @Autowired
    MerchantConverter merchantCvt;
    /**
     * 获取所有商户列表
     * @return
     */
    @GetMapping("/list")
    public AjaxResult list(){
        startPage();
        return success(merchantMapper.selectList(null));
    }

    @GetMapping("/list/ids")
    R<List<MerchantDto>> getMerchantByMids(@RequestParam("mids") Collection<Long> mids){
        List<Merchant> merchants = merchantMapper.selectBatchIds(mids);
        return R.ok(merchantCvt.entity2Dtos(merchants));
    }

    @GetMapping("/{merchantId}")
    public AjaxResult get(@PathVariable Long merchantId){
        return success(merchantMapper.selectById(merchantId));
    }

    /**
     * 获取当前用户的商户列表
     * @return 商户列表
     */
    @GetMapping("/own")
    public R<List<Merchant>> getOwnMerchant() {
        return R.ok(merchantMapper.selectByOwner(SecurityUtils.getUserId()));
    }

    /**
     * 添加商户
     * @param merchantDto
     * @return
     */
    @PostMapping
    public AjaxResult add(@RequestBody MerchantDto merchantDto){
        Merchant merchant = merchantCvt.dto2Entity(merchantDto);
        merchant.setOwner(SecurityUtils.getUserId());
        merchantService.add(merchant);
        return success();
    }

    /**
     * 删除商户
     * @param merchantId
     * @return
     */
    @DeleteMapping("/{merchantId}")
    public AjaxResult delete(@PathVariable Long merchantId){
        merchantMapper.deleteById(merchantId);
        return AjaxResult.success();
    }

    /**
     * 更新商户信息
     * @param merchantMapper
     * @return
     */
    @PutMapping
    public AjaxResult update(@RequestBody MerchantMapper merchantMapper){
        Merchant merchant = new Merchant();
        BeanUtils.copyProperties(merchantMapper, merchant);
        merchantMapper.insert(merchant);
        return AjaxResult.success();
    }
}
