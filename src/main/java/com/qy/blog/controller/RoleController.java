package com.qy.blog.controller;

import com.github.pagehelper.PageHelper;
import com.qy.blog.entity.PageResult;
import com.qy.blog.entity.Result;
import com.qy.blog.pojo.Role;
import com.qy.blog.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/**
 * 后台角色信息控制类
 */
@RestController
@RequestMapping("/bear_blog")
public class RoleController {

    @Resource
    private RoleService roleService;

    // 获取分类信息
    @GetMapping("role/selectRolePermission")
    public Result<PageResult> selectCategories(@RequestParam(required = true, defaultValue = "1") Integer pageNum) {
        // 默认page为1，页面数为5
        PageHelper.startPage(pageNum, 5);
        // 获取分类信息
        List<Role> roles = roleService.selectRole();
        // 用PageResult封装
        PageResult<Role> articleInfoPageResult = new PageResult<>(roles);
        // 返回结果
        return new Result<>(200, "分类查询成功！", articleInfoPageResult);
    }
}
