package com.qy.blog.dao;

import com.qy.blog.pojo.Role;

import java.util.List;

/**
 * 角色
 */
public interface RoleDao {

    // 提供id查询角色
    List<Role> findByUserId(Integer userId);

    // 查询角色信息
    List<Role> selectRole();

}
