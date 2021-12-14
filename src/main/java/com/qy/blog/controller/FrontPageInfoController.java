package com.qy.blog.controller;

import com.github.pagehelper.PageHelper;
import com.qy.blog.pojo.ArticleInfo;
import com.qy.blog.pojo.CategoryInfo;
import com.qy.blog.pojo.SiteInfo;
import com.qy.blog.pojo.SocialInfo;
import com.qy.blog.service.FrontPageInfoService;
import com.qy.blog.entity.PageResult;
import com.qy.blog.entity.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 博客前端个人基本信息controller类
 */
@RestController
@RequestMapping("/bear_blog/common")
public class FrontPageInfoController {

    // 注入service类
    @Resource
    private FrontPageInfoService frontPageInfoService;

    // 获取博客首页文章列表信息（含查询分类下的文章）
    @GetMapping("/articles")
    public Result<PageResult> queryArticlesInfo(@RequestParam(required=true,defaultValue="1") Integer page,  Integer categoryId) {
        // 默认page为1，页面数为5
        PageHelper.startPage(page, 5);
        // 判断id是否为空
        if (StringUtils.isEmpty(categoryId)) {
            // 为空查询博客首页文章列表
            List<ArticleInfo> articleInfos = frontPageInfoService.selectArticlesInfo();
            // 用实体类PageResult封装
            PageResult<ArticleInfo> articleInfoPageResult = new PageResult<>(articleInfos);
            // 返回结果
            return new Result<>(200, "查询文章成功！", articleInfoPageResult);
        }else {
            // 有id值则查询对应分类id下的文章列表
            List<ArticleInfo> articleCategoryById = frontPageInfoService.findArticleCategoryById(categoryId);
            // 用实体类PageResult封装
            PageResult<ArticleInfo> articleInfoPageResult = new PageResult<>(articleCategoryById);
            // 返回结果
            return new Result<>(200, "查询文章成功！", articleInfoPageResult);

        }
    }

    // 获取博客热门文章展示信息
    @GetMapping("/focus/list")
    public Result<PageResult> queryFocusListInfo() {
        // 查询文章信息
        List<ArticleInfo> articleInfos = frontPageInfoService.selectArticlesInfo();
        // 创建list集合
        List<ArticleInfo> focusList = new ArrayList<>();
        // 将前三篇文章存入集合
        focusList.add(articleInfos.get(0));
        focusList.add(articleInfos.get(1));
        focusList.add(articleInfos.get(2));
        // 用实体类PageResult封装
        PageResult<ArticleInfo> articleInfoPageResult = new PageResult<>(focusList);
        // 返回结果
        return new Result<>(200, "查询热门文章成功！", articleInfoPageResult);
    }

    // 获取博客首页个人基本信息
    @GetMapping("/site")
    public Result<SiteInfo> querySiteInfo() {
        // 获取个人信息
        SiteInfo siteInfo = frontPageInfoService.selectSiteInfo();
        // 返回结果
        return new Result<>(200, "查询个人信息成功！", siteInfo);
    }

    // 获取博客首页个人社交信息
    @GetMapping("/social")
    public Result<List<SocialInfo>> querySocialInfo() {
        // 查询社交信息
        List<SocialInfo> socialInfos = frontPageInfoService.selectSocialInfo();
        // 返回结果
        return new Result<>(200, "查询社交信息成功！", socialInfos);
    }

    // 根据文章id获取单篇文章内容
    @GetMapping("/article/{id}")
    public Result<ArticleInfo> queryArticleById(@PathVariable("id")Integer id) {
        // 根据id查询文章
        ArticleInfo articleInfo = frontPageInfoService.findArticleInfoById(id);
        // 返回结果
        return new Result<>(200, "查询文章内容成功！", articleInfo);
    }

    // 获取文章分类
    @GetMapping("/category")
    public Result<List<CategoryInfo>> queryCategoryInfo() {
        // 查询文章分类
        List<CategoryInfo> categoryInfos = frontPageInfoService.selectCategoryInfo();
        // 返回结果
        return new Result<>(200, "查询文章分类成功！", categoryInfos);
    }

    // 文章搜索
    @GetMapping("/search")
    public Result<PageResult> queryArticleByCondition(@RequestParam(required=true,defaultValue="1") Integer page, String search) {

        PageHelper.startPage(page, 5);
        // 获取根据条件查询文章信息
        List<ArticleInfo> articleInfos = frontPageInfoService.searchArticleByCondition(search);
        // 用PageResult封装
        PageResult<ArticleInfo> articleInfoPageResult = new PageResult<>(articleInfos);
        // 返回结果
        return new Result<>(200, "查询成功！", articleInfoPageResult);
    }

}
