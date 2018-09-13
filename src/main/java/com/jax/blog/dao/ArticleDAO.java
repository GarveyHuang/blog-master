package com.jax.blog.dao;
/**
 * Created by huangjw on 2018/9/11.
 */

import com.jax.blog.dto.ArchiveDto;
import com.jax.blog.dto.cond.ArticleCond;
import com.jax.blog.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ArticleDAO
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/11 16:23
 * @Version 1.0
 **/
@Mapper
public interface ArticleDAO {
    /**
     * 批量删除文章
     * @param list
     * @return
     */
    int batchDeleteArticles(List<Integer> list);

    /**
     * 添加文章
     * @param article
     * @return
     */
    int addArticle(Article article);

    /**
     * 根据编号删除文章
     * @param cid
     * @return
     */
    int deleteArticleById(@Param("cid") Integer cid);

    /**
     * 更新文章
     * @param article
     * @return
     */
    int updateArticleById(Article article);

    /**
     * 更新文章的评论数
     * @param cid
     * @param commentsNum
     * @return
     */
    int updateArticleCommentCountById(@Param("cid") Integer cid, @Param("commentsNum") Integer commentsNum);

    /**
     * 根据编号获取文章
     * @param aid
     * @return
     */
    Article getArticleById(@Param("aid") Integer aid);

    /**
     * 根据条件获取文章列表
     * @param articleCond
     * @return
     */
    List<Article> getArticlesByCond(ArticleCond articleCond);

    /**
     * 获取文章总数量
     * @return
     */
    Long getArticleCount();

    /**
     * 获取归档数据
     * @param articleCond 查询条件（只包含开始时间和结束时间）
     * @return
     */
    List<ArchiveDto> getArchive(ArticleCond articleCond);

    /**
     * 获取最近的文章（只包含id和title）
     * @return
     */
    List<Article> getRecentlyArticle();

    /**
     * 搜索文章-根据标题 或 内容匹配
     * @param param
     * @return
     */
    List<Article> searchArticle(@Param("param") String param);
}
