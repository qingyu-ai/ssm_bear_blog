package com.qy.blog.service;

import com.qy.blog.pojo.ArticleInfo;
import com.qy.blog.pojo.CategoryInfo;

import java.util.List;

/**
 * 后台文章操作服务类
 */
public interface ArticleService {

    // 根据id更新文章
    void editArticleById(ArticleInfo articleInfo);

    // 根据文章id获取分类
    List<CategoryInfo> findCategoryByArticleId(Integer id);

    // 更新分类和文章id关联表
    void editArticleCategory(ArticleInfo articleInfo);

    // 通过id删除文章以及和分类相关的表
    void deleteArticleById(Integer id);

    // 新增文章
    void addArticle(ArticleInfo articleInfo);

}
