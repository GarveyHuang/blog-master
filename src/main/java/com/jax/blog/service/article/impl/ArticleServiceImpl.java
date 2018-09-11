package com.jax.blog.service.article.impl;

import com.jax.blog.dao.ArticleDAO;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.model.Article;
import com.jax.blog.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ArticleServiceImpl
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:34
 * @Version 1.0
 **/
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public List<Article> queryArticleList() throws Exception {
        return articleDAO.queryArticleList();
    }

    @Override
    public Article queryArticleById(Integer aid) throws Exception {
        return articleDAO.queryArticleById(aid);
    }

    @Override
    public int updateArticle(Article article) throws Exception {
        if(article == null || article.getAid() == null) {
            throw BusinessException.withErrorCode("文章信息不能为空");
        }
        return articleDAO.updateArticle(article);
    }

    @Override
    public int deleteArticle(Integer aid) throws Exception {
        return articleDAO.deleteArticle(aid);
    }

    @Override
    public int batchDeleteArticle(List<Integer> list) throws Exception {
        return articleDAO.batchDeleteArticles(list);
    }
}
