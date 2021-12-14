package com.qy.blog.dao;

import com.qy.blog.pojo.MenuInfo;

import java.util.List;

/**
 * 菜单信息
 */
public interface MenuInfoDao {

    // 查询一级菜单信息
    List<MenuInfo> selectMenuInfo();

    // 通过父菜单id查询二级菜单信息
    List<MenuInfo> findByMenuId(Integer id);

}
