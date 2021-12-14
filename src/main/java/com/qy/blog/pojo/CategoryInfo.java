package com.qy.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章分类实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInfo implements Serializable {

    private Integer id;//主键id
    private String title;//分类名
    private Integer sort;//排序优先级
    private Date createTime;//创建时间
    private Date updateTime;//更新时间

}
