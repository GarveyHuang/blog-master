package com.jax.blog.model;

/**
 * @ClassName Article
 * @Description 博客实体
 * @Author huangjw
 * @Date 2018/9/5 16:55
 * @Version 1.0
 **/

public class Article {
    /** 文章id */
    private Integer aid;
    /** 文章标题 */
    private String title;
    /** 标题图片 */
    private String titlePic;
    /** 缩略名 */
    private String slug;
    /** 内容生成时的GMT unix时间戳 */
    private Integer created;
    /** 内容更改的GMT unix时间戳 */
    private Integer modified;
    /** 内容文字 */
    private String content;
    /** 内容所属用户id */
    private Integer authorId;
    /** 内容类别 */
    private String type;
    /** 状态*/
    private String status;
    /** 标签列表 */
    private String tags;
    /** 分类列表 */
    private String categories;
    /** 点击数 */
    private Integer hits;
    /** 评论数 */
    private Integer commentsNum;
    /** 是否允许评论 */
    private Integer isAllowComment;
    private Integer isAllowPing;
    private Integer isAllowFeed;

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

    public String getTitlePic() {
        return titlePic;
    }

    public void setTitlePic(String titlePic) {
        this.titlePic = titlePic;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getModified() {
        return modified;
    }

    public void setModified(Integer modified) {
        this.modified = modified;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    public Integer getIsAllowComment() {
        return isAllowComment;
    }

    public void setIsAllowComment(Integer isAllowComment) {
        this.isAllowComment = isAllowComment;
    }

    public Integer getIsAllowPing() {
        return isAllowPing;
    }

    public void setIsAllowPing(Integer isAllowPing) {
        this.isAllowPing = isAllowPing;
    }

    public Integer getIsAllowFeed() {
        return isAllowFeed;
    }

    public void setIsAllowFeed(Integer isAllowFeed) {
        this.isAllowFeed = isAllowFeed;
    }
}
