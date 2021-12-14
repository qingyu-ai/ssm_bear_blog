package com.qy.blog.service.impl;

import com.qy.blog.dao.CategoryDao;
import com.qy.blog.pojo.CategoryInfo;
import com.qy.blog.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    // 根据id获取分类信息
    @Override
    public List<CategoryInfo> findCategoryById(Integer id) {
        return categoryDao.findCategoryById(id);
    }

    // 更新分类
    @Override
    public void updateCategory(CategoryInfo categoryInfo) {
        categoryDao.updateCategory(categoryInfo);
    }

    // 删除分类
    @Override
    public void deleteCategoryById(Integer id) {
        categoryDao.deleteCategoryById(id);
    }

    // 新增分类
    @Override
    public void addCategory(CategoryInfo categoryInfo) {
        categoryDao.addCategory(categoryInfo);
    }
}
