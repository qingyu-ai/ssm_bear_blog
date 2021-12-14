package com.qy.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 菜单栏实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuInfo {

    private Integer id;//菜单id
    private String menusName;//菜单名
    private String path;//菜单路径
    private List<MenuInfo> children;//二级菜单
}
