package com.qy.blog.service.impl;

import com.qy.blog.dao.FrontPageInfoDao;
import com.qy.blog.pojo.ArticleInfo;
import com.qy.blog.pojo.CategoryInfo;
import com.qy.blog.pojo.SiteInfo;
import com.qy.blog.pojo.SocialInfo;
import com.qy.blog.service.FrontPageInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页信息业务实现接口
 */
@Service
@Transactional
public class FrontPageInfoServiceImpl implements FrontPageInfoService {

    @Resource
    private FrontPageInfoDao frontPageInfoDao;

    // 获取首页文章列表信息
    @Override
    public List<ArticleInfo> selectArticlesInfo() {
        return frontPageInfoDao.selectArticlesInfo();
    }

    // 获取博客单篇文章内容
    @Override
    public ArticleInfo findArticleInfoById(Integer id) {
        return frontPageInfoDao.findArticleInfoById(id);
    }

    // 获取首页博主信息
    @Override
    public SiteInfo selectSiteInfo() {
        return frontPageInfoDao.selectSiteInfo();
    }

    // 获取首页个人社交信息
    @Override
    public List<SocialInfo> selectSocialInfo() {
        return frontPageInfoDao.selectSocialInfo();
    }

    // 获取文章分类
    @Override
    public List<CategoryInfo> selectCategoryInfo() {
        return frontPageInfoDao.selectCategoryInfo();
    }

    // 根据分类id获取文章
    @Override
    public List<ArticleInfo> findArticleCategoryById(Integer id) {
        return frontPageInfoDao.findArticleCategoryById(id);
    }


    // 搜索文章
    @Override
    public List<ArticleInfo> searchArticleByCondition(String search) {
        return frontPageInfoDao.searchArticleByCondition(search);
    }

    // 获取文章总数
    @Override
    public Integer getArticleCount() {
        return frontPageInfoDao.getArticleCount();
    }

    // 获取文章分类总数
    @Override
    public Integer getCategoryCount() {
        return frontPageInfoDao.getCategoryCount();
    }
}
