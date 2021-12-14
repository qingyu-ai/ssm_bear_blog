package com.qy.blog.service.impl;

import com.qy.blog.dao.MenuInfoDao;
import com.qy.blog.pojo.MenuInfo;
import com.qy.blog.service.MenuInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuInfoServiceImpl implements MenuInfoService {

    @Resource
    private MenuInfoDao menuInfoDao;

    // 查询一级菜单信息
    @Override
    public List<MenuInfo> selectMenuInfo() {
        return menuInfoDao.selectMenuInfo();
    }

    // 通过父菜单id查询二级菜单信息
    @Override
    public List<MenuInfo> findByMenuId(Integer id) {
        return menuInfoDao.findByMenuId(id);
    }
}
