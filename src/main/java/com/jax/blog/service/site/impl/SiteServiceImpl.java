package com.jax.blog.service.site.impl;

import com.github.pagehelper.PageHelper;
import com.jax.blog.dao.ArticleDAO;
import com.jax.blog.dao.CommentDAO;
import com.jax.blog.dto.StatisticsDto;
import com.jax.blog.dto.cond.CommentCond;
import com.jax.blog.model.Article;
import com.jax.blog.model.Comment;
import com.jax.blog.service.site.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SiteServiceImpl
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/13 11:29
 * @Version 1.0
 **/
@Service("siteService")
public class SiteServiceImpl implements SiteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteServiceImpl.class);

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private ArticleDAO articleDAO;

    @Override
    @Cacheable(value = "siteCache", key = "'comments_' + #p0")
    public List<Comment> getComments(int limit) {
        LOGGER.debug("Enter recentComments method:limit={}", limit);
        if(limit < 0 || limit>10) {
            limit = 10;
        }
        PageHelper.startPage(1, limit);
        List<Comment> rs = commentDAO.getCommentsByCond(new CommentCond());
        LOGGER.debug("Exit recentComments method");
        return rs;
    }

    @Override
    @Cacheable(value = "siteCache", key = "'newArticles_' + #p0")
    public List<Article> getNewArticles(int limit) {
        LOGGER.debug("Enter recentArticles method:limit={}", limit);
        if (limit < 0 || limit > 10)
            limit = 10;
        PageHelper.startPage(1, limit);
        List<Article> rs = articleDAO.getArticlesByCond(new ACond());
        LOGGER.debug("Exit recentArticles method");
        return rs;
    }

    @Override
    public Comment getComment(Integer cmid) {
        return null;
    }

    @Override
    public StatisticsDto getStatistics() {
        return null;
    }
}
