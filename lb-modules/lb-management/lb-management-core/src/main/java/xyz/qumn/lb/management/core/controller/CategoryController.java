package xyz.qumn.lb.management.core.controller;


import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.qumn.lb.management.api.dto.CategoryDto;
import xyz.qumn.lb.management.api.request.category.CategoryCreateRequest;
import xyz.qumn.lb.management.api.request.category.CategoryUpdateRequest;
import xyz.qumn.lb.management.core.converter.CategoryConverter;
import xyz.qumn.lb.management.core.dao.CategoryMapper;
import xyz.qumn.lb.management.core.pojo.entity.Category;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryConverter categoryConverter;

    @GetMapping("/list/{mid}")
    public R<List<CategoryDto>> list(@PathVariable("mid") Long mid) {
        return R.ok(categoryConverter.entity2Dtos(categoryMapper.selectByMid(mid)));
    }

    @PostMapping
    public R<Void> save(@RequestBody CategoryCreateRequest category) {
        Category categoryEntity = categoryConverter.req2Entity(category);
        categoryMapper.insert(categoryEntity);
        return R.ok();
    }

    @DeleteMapping("/{cid}")
    public R<Void> delete(@PathVariable("cid") Long cid) {
        categoryMapper.deleteById(cid);
        return R.ok();
    }

    @PutMapping
    public R<Void> update(@RequestBody CategoryUpdateRequest category) {
        Category categoryEntity = categoryConverter.req2Entity(category);
        categoryMapper.insert(categoryEntity);
        return R.ok();
    }
}
