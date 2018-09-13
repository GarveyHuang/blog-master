package com.jax.blog.service.comment;

import com.github.pagehelper.PageInfo;
import com.jax.blog.dto.cond.CommentCond;
import com.jax.blog.model.Comment;

import java.util.List;

/**
 * @ClassName CommentService
 * @Description 评论服务层
 * @Author huangjw
 * @Date 2018/9/5 16:31
 * @Version 1.0
 **/

public interface CommentService {
    /**
     * 新增评论
     * @param comment 评论的实体
     * @return
     */
    void addComment(Comment comment);

    /**
     * 删除评论
     * @param cmid 评论的主键编号
     * @return
     */
    void deleteComment(Integer cmid);

    /**
     * 更新评论的状态
     * @param cmid 评论的主键编号
     * @param status 状态
     * @return
     */
    void updateCommentStatus(Integer cmid, String status);


    /**
     * 查找单条评论
     * @param cmid
     * @return
     */
    Comment getCommentById(Integer cmid);

    /**
     * 根据文章编号获取评论列表--只显示通过审核的评论-正常状态的
     * @param aid 文章主键编号
     * @return
     */
    List<Comment> getCommentsByAId(Integer aid);

    /**
     * 根据条件获取评论列表
     * @param commentCond 查询条件
     * @param pageNum 分页参数 第几页
     * @param pageSize 分页参数 每页条数
     * @return
     */
    PageInfo<Comment> getCommentsByCond(CommentCond commentCond, int pageNum, int pageSize);
}
