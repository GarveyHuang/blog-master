package com.jax.blog.service.article;

import com.github.pagehelper.PageInfo;
import com.jax.blog.dto.cond.ArticleCond;
import com.jax.blog.model.Article;

import java.util.List;

/**
 * @ClassName ArticleService
 * @Description 文章服务层
 * @Author huangjw
 * @Date 2018/9/5 16:29
 * @Version 1.0
 **/

public interface ArticleService {
    /**
     * 添加文章
     * @param article
     * @return
     */
    void addArticle(Article article);

    /**
     * 根据编号删除文章
     * @param aid
     * @return
     */
    void deleteArticleById(Integer aid);

    /**
     * 更新文章
     * @param article
     * @return
     */
    void updateArticleById(Article article);

    /**
     * 更新分类
     * @param ordinal
     * @param newCategory
     */
    void updateCategory(String ordinal, String newCategory);



    /**
     * 添加文章点击量
     * @param content
     */
    void updateArticleByAid(Article content);

    /**
     * 根据编号获取文章
     * @param aid
     * @return
     */
    Article getArticleById(Integer aid);

    /**
     * 根据条件获取文章列表
     * @param contentCond
     * @return
     */
    PageInfo<Article> getArticlesByCond(ArticleCond contentCond, int pageNum, int pageSize);

    /**
     * 获取最近的文章（只包含id和title）
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Article> getRecentlyArticle(int pageNum, int pageSize);

    /**
     * 搜索文章
     * @param param
     * @param pageNun
     * @param pageSize
     * @return
     */
    PageInfo<Article> searchArticle(String param, int pageNun, int pageSize);
}
