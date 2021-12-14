package com.qy.blog.service.impl;

import com.qy.blog.dao.PermissionDao;
import com.qy.blog.dao.RoleDao;
import com.qy.blog.pojo.Permission;
import com.qy.blog.pojo.Role;
import com.qy.blog.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionDao permissionDao;

    // 查询角色信息
    @Override
    public List<Role> selectRole() {
        List<Role> roleList = roleDao.selectRole();
        for (Role role : roleList){
            List<Permission> permissions = permissionDao.findByRoleId(role.getId());
            role.setPermissions(permissions);
        }
        // 返回结果
        return roleList;
    }
}
