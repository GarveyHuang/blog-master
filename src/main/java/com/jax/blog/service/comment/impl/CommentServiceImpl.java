package com.jax.blog.service.comment.impl;

import com.jax.blog.dao.CommentDAO;
import com.jax.blog.model.Comment;
import com.jax.blog.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:36
 * @Version 1.0
 **/
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDAO commentDAO;

    @Override
    public List<Comment> queryCommentList() throws Exception {
        return null;
    }

    @Override
    public int reviewComment(Integer commentId) throws Exception {
        return 0;
    }

    @Override
    public int deleteComment(Integer commentId) throws Exception {
        return 0;
    }
}
