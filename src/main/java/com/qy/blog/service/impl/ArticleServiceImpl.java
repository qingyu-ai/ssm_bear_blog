package com.qy.blog.service.impl;

import com.qy.blog.dao.ArticleDao;
import com.qy.blog.pojo.ArticleInfo;
import com.qy.blog.pojo.CategoryInfo;
import com.qy.blog.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台文章操作实现类
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;

    // 根据id更新文章
    @Override
    public void editArticleById(ArticleInfo articleInfo) {
        articleDao.editArticleById(articleInfo);
    }

    // 根据文章id获取分类
    @Override
    public List<CategoryInfo> findCategoryByArticleId(Integer id) {
        return articleDao.findCategoryByArticleId(id);
    }

    // 更新分类和文章id关联表
    @Override
    public void editArticleCategory(ArticleInfo articleInfo) {
        articleDao.editArticleCategory(articleInfo);
    }

    // 通过id删除文章以及和分类相关的表
    @Override
    public void deleteArticleById(Integer id) {
        articleDao.deleteArticleCategoryById(id);
        articleDao.deleteArticleById(id);
    }

    // 新增文章及添加关联表信息
    @Override
    public void addArticle(ArticleInfo articleInfo) {
        articleDao.addArticle(articleInfo);
        articleDao.addCategoryAndArticle(articleInfo);
    }
}
