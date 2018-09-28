package com.jax.blog.dto;

/**
 * @ClassName ArticleRankingDto
 * @Description 文章排行类
 * @Author huangjw
 * @Date 2018/9/28 14:23
 * @Version 1.0
 **/
public class ArticleRankingDto {
    private Integer aid;
    private String title;
    private Integer orderValue;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }
}
