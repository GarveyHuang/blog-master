package com.jax.blog.model;

/**
 * @ClassName Link
 * @Description 友情链接
 * @Author huangjw
 * @Date 2018/9/5 16:56
 * @Version 1.0
 **/

public class Link {
    private Integer id;
    private String linkName;    //友链名称
    private String linkUrl;     //友链地址
    private Integer orderNum;   //优先级

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
