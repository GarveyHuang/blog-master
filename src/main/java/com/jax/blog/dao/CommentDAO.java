package com.jax.blog.dao;

import com.jax.blog.model.Comment;
import org.apache.ibatis.annotations.Mapper;

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
    List<Comment> queryComment() throws Exception;

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
}
