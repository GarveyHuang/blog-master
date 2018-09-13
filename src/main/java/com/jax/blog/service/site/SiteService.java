package com.jax.blog.service.site;
/**
 * Created by huangjw on 2018/9/13.
 */

import com.jax.blog.dto.ArchiveDto;
import com.jax.blog.dto.MetaDto;
import com.jax.blog.dto.StatisticsDto;
import com.jax.blog.dto.cond.ArticleCond;
import com.jax.blog.model.Article;
import com.jax.blog.model.Comment;

import java.util.List;

/**
 * @ClassName SiteService
 * @Description 站点服务
 * @Author huangjw
 * @Date 2018/9/13 11:15
 * @Version 1.0
 **/

public interface SiteService {
    /**
     * 获取评论列表
     * @param limit
     * @return
     */
    List<Comment> getComments(int limit);

    /**
     * 获取最新的文章
     * @param limit
     * @return
     */
    List<Article> getNewArticles(int limit);

    /**
     * 获取单条评论
     * @param cmid
     * @return
     */
    Comment getComment(Integer cmid);

    /**
     * 获取 后台统计数据
     * @return
     */
    StatisticsDto getStatistics();

    /**
     * 获取归档列表 - 只是获取日期和数量
     * @param articleCond
     * @return
     */
    List<ArchiveDto> getArchivesSimple(ArticleCond articleCond);

    /**
     * 获取归档列表
     * @param articleCond 查询条件（只包含开始时间和结束时间）
     * @return
     */
    List<ArchiveDto> getArchives(ArticleCond articleCond);



    /**
     * 获取分类/标签列表
     * @param type
     * @param orderBy
     * @param limit
     * @return
     */
    List<MetaDto> getMetas(String type, String orderBy, int limit);
}
