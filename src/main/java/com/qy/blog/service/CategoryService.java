package com.qy.blog.service;

import com.qy.blog.pojo.ArticleInfo;
import com.qy.blog.pojo.CategoryInfo;

import java.util.List;

/**
 * 文章分类接口
 */
public interface CategoryService {

    // 根据id获取分类信息
    List<CategoryInfo> findCategoryById(Integer id);

    // 更新分类
    void updateCategory(CategoryInfo categoryInfo);

    // 删除分类
    void deleteCategoryById(Integer id);

    // 新增分类
    void addCategory(CategoryInfo categoryInfo);
}
