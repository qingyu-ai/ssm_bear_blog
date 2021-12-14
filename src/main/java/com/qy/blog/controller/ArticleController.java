package com.qy.blog.controller;

import com.github.pagehelper.PageHelper;
import com.qy.blog.entity.PageResult;
import com.qy.blog.entity.Result;
import com.qy.blog.pojo.ArticleInfo;
import com.qy.blog.pojo.CategoryInfo;
import com.qy.blog.service.ArticleService;
import com.qy.blog.service.FrontPageInfoService;
import com.qy.blog.utils.QiniuUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bear_blog/article")
public class ArticleController {

    @Resource
    private FrontPageInfoService frontPageInfoService;

    @Resource
    private ArticleService articleService;

    // 获取博客首页文章列表信息（含查询分类下的文章）
    @GetMapping("/selectArticles")
    public Result<PageResult> queryArticlesInfo(@RequestParam(required = true, defaultValue = "1") Integer pageNum) {
        // 默认page为1，页面数为5
        PageHelper.startPage(pageNum, 5);

        // 查询博客首页文章列表
        List<ArticleInfo> articleInfos = frontPageInfoService.selectArticlesInfo();
        // 遍历文章信息
        for (ArticleInfo info : articleInfos) {
            // 根据文章id查询分类信息
            List<CategoryInfo> categoryByArticleId = articleService.findCategoryByArticleId(info.getId());
            // 遍历分类信息
            for (CategoryInfo infos : categoryByArticleId) {
                // 插入文章作者和分类名
                info.setAuthName("admin");
                info.setCategoryName(infos.getTitle());
            }
        }
        // 用PageResult封装
        PageResult<ArticleInfo> articleInfoPageResult = new PageResult<>(articleInfos);
        // 返回结果
        return new Result<>(200, "查询文章信息成功！", articleInfoPageResult);
    }

    // 更新图片封面
    @PostMapping("/updateImageArticle/{id}")
    public Result upload(@RequestParam("file") MultipartFile file, @PathVariable("id") Integer id) {

        try {
            //获取原始文件名
            String originalFilename = file.getOriginalFilename();
            int lastIndexOf = originalFilename.lastIndexOf(".");
            //获取文件后缀
            String suffix = originalFilename.substring(lastIndexOf - 1);
            //使用UUID随机产生文件名称，防止同名文件覆盖
            String fileName = UUID.randomUUID().toString() + suffix;
            // 上传图片
            QiniuUtils.upload2Qiniu(file.getBytes(), fileName);
            // 拼接完整的图片名
            String picName = "http://pic.co2qy.top/" + fileName;
            ArticleInfo articleInfo = new ArticleInfo();
            articleInfo.setId(id);
            articleInfo.setBanner(picName);
            // 存入数据库
            articleService.editArticleById(articleInfo);
            //图片上传成功
            return new Result(200, "图片上传成功", picName);

        } catch (IOException e) {
            e.printStackTrace();
            //图片上传失败
            return new Result(100, "图片上传失败");
        }

    }

    // 根据id查询文章
    @GetMapping("/selectArticle/{id}")
    public Result selectArticle(@PathVariable("id") Integer id) {
        // 根据id查询文章
        ArticleInfo articleInfoById = frontPageInfoService.findArticleInfoById(id);
        // 返回结果
        return new Result(200, "文章信息查询成功", articleInfoById);
    }

    // 查询分类
    @GetMapping("/selectCategories")
    public Result selectCategories() {
        // 查询分类
        List<CategoryInfo> categoryInfos = frontPageInfoService.selectCategoryInfo();
        // 返回结果
        return new Result(200, "分类信息查询成功", categoryInfos);

    }

    // 根据id更新文章内容
    @PutMapping("/updateSendArticle")
    public Result editArticleById(@RequestBody ArticleInfo articleInfo) {
        // 根据id更新文章内容
        articleService.editArticleById(articleInfo);
        // 更新文章与分类的关联表
        articleService.editArticleCategory(articleInfo);
        // 返回结果
        return new Result(200, "文章更新成功");
    }

    // 根据id更新文章hot和top状态
    @PutMapping("/updateArticle")
    public Result updateArticle(@RequestBody ArticleInfo articleInfo) {
        // 根据id更新文章hot和top状态
        articleService.editArticleById(articleInfo);
        // 返回结果
        return new Result(200, "状态更新成功");
    }

    // 根据id删除文章
    @DeleteMapping("/deleteArticle/{id}")
    public Result deleteArticle(@PathVariable("id") Integer id) {
        // 根据id删除文章
        articleService.deleteArticleById(id);
        // 返回结果
        return new Result(200, "文章删除成功");
    }

    // 新增文章
    @PostMapping("/insertArticle")
    public Result addArticle(ArticleInfo articleInfo) {
        // 新增文章
        articleService.addArticle(articleInfo);
        // 返回结果
        return new Result(200, "文章添加成功");
    }
}
