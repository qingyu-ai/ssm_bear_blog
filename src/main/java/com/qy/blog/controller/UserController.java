package com.qy.blog.controller;

import com.github.pagehelper.PageHelper;
import com.qy.blog.entity.PageResult;
import com.qy.blog.entity.Result;
import com.qy.blog.entity.UserResult;
import com.qy.blog.pojo.MenuInfo;
import com.qy.blog.pojo.User;
import com.qy.blog.service.MenuInfoService;
import com.qy.blog.service.UserService;
import com.qy.blog.utils.RedisUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户操作
 */
@RestController
@RequestMapping("/bear_blog")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private MenuInfoService menuInfoService;

    @Resource
    private RedisUtil redisUtil;

    //获取token解析传值
    //@PreAuthorize("hasAuthority('ARTICLE_ADD')")
    @GetMapping("/commonAdmin/userInfo/{token}")
    public Result getUserInfo(@PathVariable("token") String token){

        UserResult userResult = redisUtil.getCacheObject("userInfo");

        return new Result(200,"获取用户信息成功", userResult);

    }

    //@PreAuthorize("hasAuthority('ARTICLE_ADD')")
    @GetMapping("/commonAdmin/findAllMenus")
    public Result getAllMenus(){

        List<MenuInfo> menuInfos = menuInfoService.selectMenuInfo();

        for (MenuInfo str : menuInfos) {
            List<MenuInfo> children = menuInfoService.findByMenuId(str.getId());
            str.setChildren(children);
        }

        return new Result(200,"获取用户信息成功", menuInfos);

    }

    // 获取用户信息
    @GetMapping("/user/findUserList")
    public Result<PageResult>  findUserList(@RequestParam(required = true, defaultValue = "1") Integer pageNum){

        // 默认page为1，页面数为5
        PageHelper.startPage(pageNum, 5);

        List<User> userList = userService.findUserList();

        PageResult<User> userPageResult = new PageResult<>(userList);
        return new Result<>(200, "查询成功！", userPageResult);

    }




}
