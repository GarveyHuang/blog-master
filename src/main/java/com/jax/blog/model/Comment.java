package com.jax.blog.model;

import java.util.Date;

/**
 * @ClassName Comment
 * @Description 博客评论
 * @Author huangjw
 * @Date 2018/9/5 16:56
 * @Version 1.0
 **/

public class Comment {
    private Integer id;
    private String userIp;      //用户IP
    private String content;     //评论内容
    private Date commentDate;   //评论日期
    private Integer state;      //是否审核通过
    private Blog blog;          //所评论的博客

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
