package com.qy.blog.service;

import com.qy.blog.pojo.Role;

import java.util.List;
import java.util.Set;

/**
 * 角色
 */
public interface RoleService {

    // 查询角色信息
    List<Role> selectRole();
}
