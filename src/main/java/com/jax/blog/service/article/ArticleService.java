package com.jax.blog.service.article;

import com.jax.blog.model.Article;

import java.util.List;

/**
 * @ClassName ArticleService
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:29
 * @Version 1.0
 **/

public interface ArticleService {
    List<Article> queryArticleList() throws Exception;

    Article queryArticleById(Integer aid) throws Exception;

    int updateArticle(Article article) throws Exception;

    int deleteArticle(Integer aid) throws Exception;

    int batchDeleteArticle(List<Integer> list) throws Exception;
}
