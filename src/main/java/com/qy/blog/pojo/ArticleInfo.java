package com.qy.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * 首页文章列表信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleInfo implements Serializable {

    private Integer id;//id主键
    private boolean top;//是否置顶
    private String banner;//文章表面图
    private boolean hot;//是否有热搜标志
    private Date pubTime;//发布时间
    private String title;//文章标题
    private String summary;//文章简介
    private String content;//文章内容
    private String viewsCount;//观看次数
    private String commentsCount;//评论总数
    private Date updateTime;//更新时间
    private String authName;//作者信息
    private String categoryName;//分类名
    private Integer categoryId;//分类id
}
