package com.qy.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    private Integer id;
    private String name; // 角色名称
    private String keyword; // 角色关键字，用于权限控制
    private List<User> users;
    private List<Permission> permissions;
    private Date createTime;//创建时间
    private Date updateTime;//更新时间

}
