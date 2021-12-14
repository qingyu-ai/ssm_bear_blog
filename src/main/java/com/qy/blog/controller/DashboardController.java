package com.qy.blog.controller;

import com.qy.blog.entity.Result;
import com.qy.blog.service.FrontPageInfoService;
import com.qy.blog.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * dashboard界面
 */
@RestController
@RequestMapping("/bear_blog/commonAdmin")
public class DashboardController {

    @Resource
    private UserService userService;

    @Resource
    private FrontPageInfoService frontPageInfoService;

    // 获取用户总量
    @GetMapping("/userCount")
    public Result getUserCount(){
        // 获取用户总量
        Integer userCount = userService.getUserCount();
        // 返回结果
        return new Result(200,"获取用户总数量成功", userCount);
    }

    // 获取文章总量
    @GetMapping("/articleCount")
    public Result getArticleCount(){
        // 获取文章总量
        Integer articleCount = frontPageInfoService.getArticleCount();
        // 返回结果
        return new Result(200,"获取用户信息成功", articleCount);
    }

    // 获取文章分类总量
    @GetMapping("/categoryCount")
    public Result getCategoryCount(){
        // 获取文章分类总量
        Integer categoryCount = frontPageInfoService.getCategoryCount();
        // 返回结果
        return new Result(200,"获取用户信息成功", categoryCount);
    }
}
