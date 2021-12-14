package com.qy.blog.service;

import com.qy.blog.pojo.User;

import java.util.List;

public interface UserService {

    // 通过用户名查询用户信息
    User findByUsername(String username);

    // 查询用户总量
    Integer getUserCount();

    // 查询用户信息
    List<User> findUserList();
}
