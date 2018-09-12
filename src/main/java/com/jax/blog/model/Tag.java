package com.jax.blog.model;

/**
 * @ClassName Tag
 * @Description 文章标签
 * @Author huangjw
 * @Date 2018/9/5 16:55
 * @Version 1.0
 **/

public class Tag {

    /** 标签id */
    private Integer tid;

    /** 名称 */
    private String tagName;

    /** 描述 */
    private String description;

    /** 热度 */
    private Integer popular;

    /** 项目排序 */
    private Integer sort;

    /** 父级id */
    private Integer parentid;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPopular() {
        return popular;
    }

    public void setPopular(Integer popular) {
        this.popular = popular;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}
