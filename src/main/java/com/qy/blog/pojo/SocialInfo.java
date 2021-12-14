package com.qy.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 个人社交信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialInfo {

    private Integer id;//id主键
    private String color;//图标颜色
    private String icon;//图标类型
    private String href;//跳转链接
    private String title;//标题

}
