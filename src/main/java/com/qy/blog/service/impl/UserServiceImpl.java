package com.qy.blog.service.impl;

import com.qy.blog.dao.PermissionDao;
import com.qy.blog.dao.RoleDao;
import com.qy.blog.dao.UserDao;
import com.qy.blog.pojo.Permission;
import com.qy.blog.pojo.Role;
import com.qy.blog.pojo.User;
import com.qy.blog.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionDao permissionDao;

    // 根据用户名查询数据库获取用户信息和关联的角色信息，同时需要查询角色关联的权限信息
    @Override
    public User findByUsername(String username) {

        User user = userDao.findByUsername(username); // 查询用户基本信息，不包含用户角色
        if (user == null){
            return null;
        }

        Integer userId = user.getId();
        // 根据用户id查询对应的角色
        List<Role> roles = roleDao.findByUserId(userId);
        for (Role role : roles){
            Integer roleId = role.getId();
            // 根据角色id查询关联权限
            List<Permission> permissions = permissionDao.findByRoleId(roleId);
            if(permissions != null && permissions.size() > 0){
                role.setPermissions(permissions);
            }
        }

        user.setRoles(roles);

        return user;
    }

    // 查询用户总量
    @Override
    public Integer getUserCount() {
        return userDao.getUserCount();
    }

    // 查询用户信息
    @Override
    public List<User> findUserList() {
        return userDao.findUserList();
    }

}
