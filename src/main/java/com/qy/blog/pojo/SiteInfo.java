package com.qy.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 博客基本信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteInfo {

    private Integer id;//id主键
    private String avatar;//头像
    private String slogan;//网站标语
    private String notice;//注意
    private String desc;//网站简述
    private String name;//博主名

}
