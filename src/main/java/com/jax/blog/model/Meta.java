package com.jax.blog.model;

import java.io.Serializable;

/**
 * @ClassName Meta
 * @Description TODO
 * @Author huangjw
 * @Date 2018/09/13 20:37
 * @Version 1.0
 **/
public class Meta implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 项目主键 */
    private Integer mid;

    /** 名称 */
    private String name;

    /** 项目缩略名 */
    private String slug;

    /** 项目类型 */
    private String type;

    /** 对应的文章类型 */
    private String contentType;

    /** 选项描述 */
    private String description;

    /** 项目排序 */
    private Integer orderNum;

    /** 父级项目 */
    private Integer parent;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }
}
