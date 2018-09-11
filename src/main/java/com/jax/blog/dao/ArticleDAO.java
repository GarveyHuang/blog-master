package com.jax.blog.dao;
/**
 * Created by huangjw on 2018/9/11.
 */

import com.jax.blog.model.Article;
import org.apache.ibatis.annotations.Mapper;

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
     * 查询所有文章信息
     * @return
     */
    List<Article> queryArticleList();

    /**
     * 根据id查询文章
     * @param aid
     * @return
     */
    Article queryArticleById(Integer aid);

    /**
     * 更新文章信息
     * @param article
     * @return
     */
    int updateArticle(Article article);

    /**
     * 根据单个id删除文章
     * @param aid
     * @return
     */
    int deleteArticle(Integer aid);

    /**
     * 批量删除文章
     * @param list
     * @return
     */
    int batchDeleteArticles(List<Integer> list);
}
