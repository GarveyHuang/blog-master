package com.jax.blog.dao;

import com.jax.blog.dto.cond.CommentCond;
import com.jax.blog.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName CommentDAO
 * @Description TODO
 * @Author huangjw
 * @Date 2018/09/11 22:51
 * @Version 1.0
 **/
@Mapper
public interface CommentDAO {
    /**
     * 查询评论信息
     * @return
     * @throws Exception
     */
    List<Comment> getComments() throws Exception;

    /**
     * 添加评论
     * @param comment
     * @return
     * @throws Exception
     */
    int addComment(Comment comment) throws Exception;

    /**
     * 修改评论
     * @param comment
     * @return
     * @throws Exception
     */
    int updateComment(Comment comment) throws Exception;

    /**
     * 删除评论
     * @param id
     * @return
     * @throws Exception
     */
    int deleteComment(Integer id) throws Exception;

    /**
     * 根据博文id删除对应评论
     * @param articleId
     * @return
     * @throws Exception
     */
    int deleteCommentByArticleId(Integer articleId) throws Exception;

    /**
     * 获取单条评论
     * @param cmid
     * @return
     */
    Comment getCommentById(@Param("cmid") Integer cmid);
    /**
     * 根据文章编号获取评论列表
     * @param cid
     * @return
     */
    List<Comment> getCommentsByCId(@Param("cid") Integer cid);

    /**
     * 根据条件获取评论列表
     * @param commentCond
     * @return
     */
    List<Comment> getCommentsByCond(CommentCond commentCond);

    /**
     * 获取文章数量
     * @return
     */
    Long getCommentsCount();
}
