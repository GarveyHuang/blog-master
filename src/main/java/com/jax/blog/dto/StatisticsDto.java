package com.jax.blog.dto;

/**
 * @ClassName StatisticsDto
 * @Description 后台统计对象
 * @Author huangjw
 * @Date 2018/9/13 11:16
 * @Version 1.0
 **/

public class StatisticsDto {
    /** 学习记录文章数 */
    private Long articlesCount;
    /** 生活随笔文章数 */
    private Long shareCount;
    /** 评论数 */
    private Long commentsCount;
    /** 友链数 */
    private Long linksCount;
    /** 附件数 */
    private Long attachsCount;

    public Long getArticlesCount() {
        return articlesCount;
    }

    public void setArticlesCount(Long articlesCount) {
        this.articlesCount = articlesCount;
    }

    public Long getShareCount() {
        return shareCount;
    }

    public void setShareCount(Long shareCount) {
        this.shareCount = shareCount;
    }

    public Long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Long commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Long getLinksCount() {
        return linksCount;
    }

    public void setLinksCount(Long linksCount) {
        this.linksCount = linksCount;
    }

    public Long getAttachsCount() {
        return attachsCount;
    }

    public void setAttachsCount(Long attachsCount) {
        this.attachsCount = attachsCount;
    }
}
