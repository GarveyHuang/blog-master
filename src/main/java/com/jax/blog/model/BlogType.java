package com.jax.blog.model;

/**
 * @ClassName BlogType
 * @Description 博客类型
 * @Author huangjw
 * @Date 2018/9/5 16:55
 * @Version 1.0
 **/

public class BlogType {
    private Integer id;         //id
    private String typeName;    //类型名称
    private Integer orderNum;   //排序优先级
    private Integer blogCount;  //博客总数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }
}
