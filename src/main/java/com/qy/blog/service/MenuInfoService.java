package com.qy.blog.service;

import com.qy.blog.pojo.MenuInfo;

import java.util.List;

public interface MenuInfoService {

    // 查询一级菜单信息
    List<MenuInfo> selectMenuInfo();

    // 通过父菜单id查询二级菜单信息
    List<MenuInfo> findByMenuId(Integer id);
}
