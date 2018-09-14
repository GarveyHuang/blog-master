package com.jax.blog.model;

/**
 * @ClassName Comment
 * @Description 博客评论
 * @Author huangjw
 * @Date 2018/9/5 16:56
 * @Version 1.0
 **/

public class Comment {
    /** comment表主键 */
    private Integer cmid;

    /** article表主键,关联字段 */
    private Integer articleid;

    /** 评论生成时的GMT unix时间戳 */
    private Integer created;

    /** 评论作者 */
    private String author;

    /** 文章所属用户id */
    private Integer authorId;

    /** 评论作者id */
    private Integer ownerId;

    /** 评论者ip地址 */
    private String authorIp;

    /** 评论者邮件 */
    private String email;

    /** 评论者主页 */
    private String homePage;

    /** 评论者客户端 */
    private String agent;

    /** 评论内容 */
    private String content;

    /** 评论状态 */
    private String status;

    /** 父级评论 */
    private Integer parent;

    public Integer getCmid() {
        return cmid;
    }

    public void setCmid(Integer cmid) {
        this.cmid = cmid;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getAuthorIp() {
        return authorIp;
    }

    public void setAuthorIp(String authorIp) {
        this.authorIp = authorIp;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }
}
