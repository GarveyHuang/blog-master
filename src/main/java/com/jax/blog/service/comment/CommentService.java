package com.jax.blog.service.comment;

import com.jax.blog.model.Comment;

import java.util.List;

/**
 * @ClassName CommentService
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:31
 * @Version 1.0
 **/

public interface CommentService {
    List<Comment> queryCommentList() throws Exception;

    int reviewComment(Integer commentId) throws Exception;

    int deleteComment(Integer commentId) throws Exception;
}
