package com.qy.blog.controller;

import com.github.pagehelper.PageHelper;
import com.qy.blog.entity.PageResult;
import com.qy.blog.entity.Result;
import com.qy.blog.pojo.ArticleInfo;
import com.qy.blog.pojo.CategoryInfo;
import com.qy.blog.service.ArticleService;
import com.qy.blog.service.CategoryService;
import com.qy.blog.service.FrontPageInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/bear_blog")
public class CategoryController {

    @Resource
    private FrontPageInfoService frontPageInfoService;

    @Resource
    private CategoryService categoryService;

    // 获取分类信息
    @GetMapping("/commonAdmin/selectCategories")
    public Result<PageResult> selectCategories(@RequestParam(required = true, defaultValue = "1") Integer pageNum) {
        // 默认page为1，页面数为5
        PageHelper.startPage(pageNum, 5);
        // 获取分类信息
        List<CategoryInfo> categoryInfos = frontPageInfoService.selectCategoryInfo();
        // 用PageResult封装
        PageResult<CategoryInfo> articleInfoPageResult = new PageResult<>(categoryInfos);
        // 返回结果
        return new Result<>(200, "分类查询成功！", articleInfoPageResult);
    }

    // 根据id获取分类信息
    @GetMapping("/category/categoryById/{id}")
    public Result queryCategoryById(@PathVariable("id") Integer id) {
        // 根据id获取分类信息
        List<CategoryInfo> categoryById = categoryService.findCategoryById(id);
        // 返回结果
        return new Result<>(200, "分类查询成功！", categoryById);
    }

    // 更新分类
    @PutMapping("/category/updateCategory")
    public Result updateCategory(@RequestBody CategoryInfo categoryInfo){
        // 更新分类
        categoryService.updateCategory(categoryInfo);
        // 返回结果
        return new Result<>(200, "分类更新成功！");
    }

    // 根据id删除分类
    @DeleteMapping("/category/deleteCategory/{id}")
    public Result deleteCategoryById(@PathVariable("id") Integer id){
        // 根据id删除分类
        categoryService.deleteCategoryById(id);
        // 返回结果
        return new Result<>(200, "分类删除成功！");
    }

    // 新增分类
    @PostMapping("/category/insertCategory")
    public Result addCategory(CategoryInfo categoryInfo){
        // 新增分类
        categoryService.addCategory(categoryInfo);
        // 返回结果
        return new Result<>(200, "分类添加成功！");
    }

}
