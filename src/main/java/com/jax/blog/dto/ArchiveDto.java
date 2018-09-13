package com.jax.blog.dto;

import com.jax.blog.model.Article;

import java.util.List;

/**
 * @ClassName ArchiveDto
 * @Description 文章归档类
 * @Author huangjw
 * @Date 2018/09/13 20:58
 * @Version 1.0
 **/
public class ArchiveDto {
    private String date;
    private String count;
    private List<Article> articles;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
