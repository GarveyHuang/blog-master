package com.jax.blog.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName Content
 * @Description 博客实体
 * @Author huangjw
 * @Date 2018/9/5 16:55
 * @Version 1.0
 **/

public class Content {
    /** 文章id */
    private Integer cmid;

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

    /** 正文内容 */
    private String content;

    /** 博文类别id */
    private Integer typeid;

    /** 博文类别 */
    private String type;

    /** 博文状态 */
    private String status;

    /** 标签 */
    private String tags;

    /** 分类 */
    private String categories;

    /** 点击数 */
    private Integer hits;

    /** 评论数 */
    private Integer commentsNum;

    /** 是否允许评论 */
    private Integer allowComment;

    /** 是否允许ping */
    private Integer allowPing;

    /** 允许出现在聚合中 */
    private Integer allowFeed;

    public Integer getCmid() {
        return cmid;
    }

    public void setCmid(Integer cmid) {
        this.cmid = cmid;
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

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
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

    public Integer getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Integer allowComment) {
        this.allowComment = allowComment;
    }

    public Integer getAllowPing() {
        return allowPing;
    }

    public void setAllowPing(Integer allowPing) {
        this.allowPing = allowPing;
    }

    public Integer getAllowFeed() {
        return allowFeed;
    }

    public void setAllowFeed(Integer allowFeed) {
        this.allowFeed = allowFeed;
    }
}
