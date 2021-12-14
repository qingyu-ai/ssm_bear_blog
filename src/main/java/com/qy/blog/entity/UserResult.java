package com.qy.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * 封装后台用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResult implements Serializable {

    private Integer id;//主键id
    private String username;//用户名
    private String avatar;//头像
    private List<String> roleList;//角色表
    private List<String> permissionPaths;//权限路径


}
