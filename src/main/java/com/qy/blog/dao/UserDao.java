package com.qy.blog.dao;

import com.qy.blog.pojo.User;

import java.util.List;

/**
 * 用户信息
 */
public interface UserDao {

    // 通过用户名查询用户信息
    User findByUsername(String username);

    // 获取用户总量
    Integer getUserCount();

    // 查询用户信息
    List<User> findUserList();
}
