package com.qy.blog.dao;

import com.qy.blog.pojo.ArticleInfo;
import com.qy.blog.pojo.CategoryInfo;

import java.util.List;

/**
 * 后台文章操作数据类
 */
public interface ArticleDao {

    // 根据id更新文章
    void editArticleById(ArticleInfo articleInfo);

    // 根据文章id获取分类
    List<CategoryInfo> findCategoryByArticleId(Integer id);

    // 更新分类和文章id关联表
    void editArticleCategory(ArticleInfo articleInfo);

    // 根据id删除文章
    void deleteArticleById(Integer id);

    // 根据id删除文章和分类的关联信息
    void deleteArticleCategoryById(Integer id);

    // 新增文章
    void addArticle(ArticleInfo articleInfo);

    // 新增文章与分类关联信息
    void addCategoryAndArticle(ArticleInfo articleInfo);

}
