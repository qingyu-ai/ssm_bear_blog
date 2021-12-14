package com.qy.blog.dao;

import com.qy.blog.pojo.Permission;

import java.util.List;
import java.util.Set;

/**
 * 权限
 */
public interface PermissionDao {

    // 提供角色id查询权限
    List<Permission> findByRoleId(int roleId);

}
