package com.qy.blog.service;

import com.qy.blog.pojo.ArticleInfo;
import com.qy.blog.pojo.CategoryInfo;
import com.qy.blog.pojo.SiteInfo;
import com.qy.blog.pojo.SocialInfo;

import java.util.List;

/**
 * 首页信息业务接口
 */
public interface FrontPageInfoService {

    // 查询文章简略信息
    List<ArticleInfo> selectArticlesInfo();

    // 获取博客单篇文章内容
    ArticleInfo findArticleInfoById(Integer id);

    // 获取首页博主信息
    SiteInfo selectSiteInfo();

    // 获取首页个人社交信息
    List<SocialInfo> selectSocialInfo();

    // 获取文章分类
    List<CategoryInfo> selectCategoryInfo();

    // 根据分类id获取文章
    List<ArticleInfo> findArticleCategoryById(Integer id);

    // 搜索文章
    List<ArticleInfo> searchArticleByCondition(String search);

    // 获取文章数量
    Integer getArticleCount();

    // 获取文章分类数量
    Integer getCategoryCount();
}
