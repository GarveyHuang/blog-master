package com.jax.blog.service.site.impl;

import com.github.pagehelper.PageHelper;
import com.jax.blog.constant.ErrorConstant;
import com.jax.blog.constant.Types;
import com.jax.blog.constant.WebConst;
import com.jax.blog.dao.ArticleDAO;
import com.jax.blog.dao.AttachDAO;
import com.jax.blog.dao.CommentDAO;
import com.jax.blog.dao.MetaDAO;
import com.jax.blog.dto.ArchiveDto;
import com.jax.blog.dto.MetaDto;
import com.jax.blog.dto.StatisticsDto;
import com.jax.blog.dto.cond.ArticleCond;
import com.jax.blog.dto.cond.CommentCond;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.model.Article;
import com.jax.blog.model.Comment;
import com.jax.blog.service.site.SiteService;
import com.jax.blog.utils.DateKit;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private MetaDAO metaDAO;

    @Autowired
    private AttachDAO attachDAO;

    @Override
    @Cacheable(value = "siteCache", key = "'comments_' + #p0")
    public List<Comment> getComments(int limit, CommentCond commentCond) {
        LOGGER.debug("Enter recentComments method:limit={}", limit);
        if (limit < 0 || limit>10) {
            limit = 10;
        }
        PageHelper.startPage(1, limit);
        List<Comment> rs = commentDAO.getCommentsByCond(commentCond);
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
        List<Article> rs = articleDAO.getArticlesByCond(new ArticleCond());
        LOGGER.debug("Exit recentArticles method");
        return rs;
    }

    @Override
    @Cacheable(value = "siteCache", key = "'comment_' + #p0")
    public Comment getComment(Integer cmid) {
        LOGGER.debug("Enter recentComment method");
        if (null == cmid) {
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        Comment comment = commentDAO.getCommentById(cmid);
        LOGGER.debug("Exit recentComment method");
        return comment;
    }

    @Override
    @Cacheable(value = "siteCache", key = "'statistics_' + #p0")
    public StatisticsDto getStatistics() {
        LOGGER.debug("Enter recentStatistics method");
        // 文章总数
        Long articlesCount = articleDAO.getArticleCount();

        Long commentsCount = commentDAO.getCommentsCount();

        Long linksCount = metaDAO.getMetasCountByType(Types.LINK.getType());

        Long attachsCount = attachDAO.getAttsCount();

        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setArticlesCount(articlesCount);
        statisticsDto.setCommentsCount(commentsCount);
        statisticsDto.setLinksCount(linksCount);
        statisticsDto.setAttachsCount(attachsCount);

        LOGGER.debug("Exit recentStatistics method");
        return statisticsDto;
    }

    @Override
    @Cacheable(value = "siteCache", key = "'archivesSimple_' + #p0")
    public List<ArchiveDto> getArchivesSimple(ArticleCond articleCond) {
        LOGGER.debug("Enter getArchives method");
        List<ArchiveDto> archives = articleDAO.getArchive(articleCond);
        LOGGER.debug("Exit getArchives method");
        return archives;
    }

    @Override
    @Cacheable(value = "siteCache", key = "'archives_' + #p0")
    public List<ArchiveDto> getArchives(ArticleCond articleCond) {
        LOGGER.debug("Enter getArchives method");
        List<ArchiveDto> archives = articleDAO.getArchive(articleCond);
        parseArchives(archives, articleCond);
        LOGGER.debug("Exit getArchives method");
        return archives;
    }



    private void parseArchives(List<ArchiveDto> archives, ArticleCond articleCond) {
        if (null != archives){
            archives.forEach(archive -> {
                String date = archive.getDate();
                Date sd = DateKit.dateFormat(date, "yyyy年MM月");
                int start = DateKit.getUnixTimeByDate(sd);
                int end = DateKit.getUnixTimeByDate(DateKit.dateAdd(DateKit.INTERVAL_MONTH, sd, 1)) - 1;
                ArticleCond cond = new ArticleCond();
                cond.setStartTime(start);
                cond.setEndTime(end);
                cond.setType(articleCond.getType());
                List<Article> contentss = articleDAO.getArticlesByCond(cond);
                archive.setArticles(contentss);
            });
        }
    }

    @Override
    @Cacheable(value = "siteCache", key = "'metas_' + #p0")
    public List<MetaDto> getMetas(String type, String orderBy, int limit) {
        LOGGER.debug("Enter metas method:type={},order={},limit={}", type, orderBy, limit);
        List<MetaDto> retList=null;
        if (StringUtils.isNotBlank(type)) {
            if(StringUtils.isBlank(orderBy)){
                orderBy = "count desc, a.mid desc";
            }
            if(limit < 1 || limit > WebConst.MAX_POSTS){
                limit = 10;
            }
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("type", type);
            paraMap.put("order", orderBy);
            paraMap.put("limit", limit);
            retList= metaDAO.selectFromSql(paraMap);
        }
        LOGGER.debug("Exit metas method");
        return retList;
    }
}
