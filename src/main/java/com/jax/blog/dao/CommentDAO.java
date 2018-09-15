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
     * 新增评论
     * @param commentDomain
     * @return
     */
    int addComment(Comment commentDomain);

    /**
     * 删除评论
     * @param cmid
     * @return
     */
    int deleteComment(@Param("cmid") Integer cmid);

    /**
     * 更新评论的状态
     * @param cmid
     * @return
     */
    int updateCommentStatus(@Param("cmid") Integer cmid, @Param("status") String status);

    /**
     * 获取单条评论
     * @param cmid
     * @return
     */
    Comment getCommentById(@Param("cmid") Integer cmid);
    /**
     * 根据文章编号获取评论列表
     * @param aid
     * @return
     */
    List<Comment> getCommentsByAId(@Param("aid") Integer aid);

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
