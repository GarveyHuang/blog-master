package com.jax.blog.service.article.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jax.blog.constant.ErrorConstant;
import com.jax.blog.constant.Types;
import com.jax.blog.constant.WebConst;
import com.jax.blog.dao.ArticleDAO;
import com.jax.blog.dao.CommentDAO;
import com.jax.blog.dao.RelationShipDAO;
import com.jax.blog.dto.cond.ArticleCond;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.model.Article;
import com.jax.blog.model.Comment;
import com.jax.blog.model.RelationShip;
import com.jax.blog.service.article.ArticleService;
import com.jax.blog.service.meta.MetaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private MetaService metaService;

    @Autowired
    private RelationShipDAO relationShipDAO;

    @Override
    @Transactional
    @CacheEvict(value = {"articleCache","articleCaches"}, allEntries = true, beforeInvocation = true)
    public void addArticle(Article article) {
        if (null == article)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        if (StringUtils.isBlank(article.getTitle()))
            throw BusinessException.withErrorCode(ErrorConstant.Article.TITLE_CAN_NOT_EMPTY);
        if (article.getTitle().length() > WebConst.MAX_TITLE_COUNT)
            throw BusinessException.withErrorCode(ErrorConstant.Article.TITLE_IS_TOO_LONG);
        if (StringUtils.isBlank(article.getContent()))
            throw BusinessException.withErrorCode(ErrorConstant.Article.CONTENT_CAN_NOT_EMPTY);
        if (article.getContent().length() > WebConst.MAX_TEXT_COUNT)
            throw BusinessException.withErrorCode(ErrorConstant.Article.CONTENT_IS_TOO_LONG);

        //标签和分类
        String tags = article.getTags();
        String categories = article.getCategories();

        articleDAO.addArticle(article);

        int aid = article.getAid();
        metaService.addMetas(aid, tags, Types.TAG.getType());
        metaService.addMetas(aid, categories, Types.CATEGORY.getType());
    }

    @Override
    @Transactional
    @CacheEvict(value = {"articleCache","articleCaches"}, allEntries = true, beforeInvocation = true)
    public void deleteArticleById(Integer aid) {
        if (null == aid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        articleDAO.deleteArticleById(aid);
        //同时也要删除该文章下的所有评论
        List<Comment> comments = commentDAO.getCommentsByAId(aid);
        if (null != comments && comments.size() > 0){
            comments.forEach(comment ->{
                commentDAO.deleteComment(comment.getCmid());
            });
        }
        //删除标签和分类关联
        List<RelationShip> relationShips = relationShipDAO.getRelationShipByAid(aid);
        if (null != relationShips && relationShips.size() > 0){
            relationShipDAO.deleteRelationShipByAid(aid);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = {"articleCache","articleCaches"}, allEntries = true, beforeInvocation = true)
    public void updateArticleById(Article article) {
        //标签和分类
        String tags = article.getTags();
        String categories = article.getCategories();

        articleDAO.updateArticleById(article);
        int aid = article.getAid();
        relationShipDAO.deleteRelationShipByAid(aid);
        metaService.addMetas(aid, tags, Types.TAG.getType());
        metaService.addMetas(aid, categories, Types.CATEGORY.getType());
    }

    @Override
    @Transactional
    @CacheEvict(value = {"articleCache","articleCaches"}, allEntries = true, beforeInvocation = true)
    public void updateCategory(String ordinal, String newCategory) {
        ArticleCond cond = new ArticleCond();
        cond.setCategory(ordinal);
        List<Article> articles = articleDAO.getArticlesByCond(cond);
        articles.forEach(atricle -> {
            atricle.setCategories(atricle.getCategories().replace(ordinal, newCategory));
            articleDAO.updateArticleById(atricle);
        });
    }

    @Override
    @CacheEvict(value = {"articleCache","articleCaches"}, allEntries = true, beforeInvocation = true)
    public void updateArticleByAid(Article article) {
        if (null != article && null != article.getAid()) {
            articleDAO.updateArticleById(article);
        }
    }

    @Override
    @Cacheable(value = "articleCache", key = "'atricleById_' + #p0")
    public Article getArticleById(Integer aid) {
        if (null == aid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        return articleDAO.getArticleById(aid);
    }

    @Override
    @Cacheable(value = "articleCaches", key = "'articlesByCond_' + #p1 + 'type_' + #p0.type")
    public PageInfo<Article> getArticlesByCond(ArticleCond contentCond, int pageNum, int pageSize) {
        if (null == contentCond)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        PageHelper.startPage(pageNum, pageSize);
        List<Article> contents = articleDAO.getArticlesByCond(contentCond);
        PageInfo<Article> pageInfo = new PageInfo<>(contents);
        return pageInfo;
    }

    @Override
    @Cacheable(value = "articleCaches", key = "'recentlyArticle_' + #p0")
    public PageInfo<Article> getRecentlyArticle(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> recentlyArticle = articleDAO.getRecentlyArticle();
        PageInfo<Article> pageInfo = new PageInfo<>(recentlyArticle);
        return pageInfo;
    }

    @Override
    public PageInfo<Article> searchArticle(String param, int pageNun, int pageSize) {
        PageHelper.startPage(pageNun,pageSize);
        List<Article> articles = articleDAO.searchArticle(param);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        return pageInfo;
    }
}
