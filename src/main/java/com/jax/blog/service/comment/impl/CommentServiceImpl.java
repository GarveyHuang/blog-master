package com.jax.blog.service.comment.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jax.blog.constant.ErrorConstant;
import com.jax.blog.dao.CommentDAO;
import com.jax.blog.dto.cond.CommentCond;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.model.Article;
import com.jax.blog.model.Comment;
import com.jax.blog.service.article.ArticleService;
import com.jax.blog.service.comment.CommentService;
import com.jax.blog.utils.DateKit;
import com.jax.blog.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    private CommentDAO commentDAO;

    @Autowired
    private ArticleService articleService;

    private static final Map<String,String> STATUS_MAP = new ConcurrentHashMap<>();

    /**
     * 评论状态：正常
     */
    private static final String STATUS_NORMAL = "approved";
    /**
     * 评论状态：不显示
     */
    private static final String STATUS_BLANK = "not_audit";

    static {
        STATUS_MAP.put("approved",STATUS_NORMAL);
        STATUS_MAP.put("not_audit",STATUS_BLANK);
    }

    @Override
    @Transactional
    @CacheEvict(value = "commentCache", allEntries = true)
    public void addComment(Comment comment) {
        String msg = null;
        if (null == comment) {
            msg = "评论对象为空";
        }
        if (StringUtils.isBlank(comment.getAuthor())) {
            comment.setAuthor("热心网友");
        }
        /*if (StringUtils.isNotBlank(comment.getEmail()) && !TaleUtils.isEmail(comment.getEmail())) {
            msg =  "请输入正确的邮箱格式";
        }*/
        if (StringUtils.isBlank(comment.getContent())) {
            msg = "评论内容不能为空";
        }
        if (comment.getContent().length() < 3 || comment.getContent().length() > 2000) {
            msg = "评论字数在5-2000个字符";
        }
        if (null == comment.getArticleid()) {
            msg = "评论文章不能为空";
        }
        if (msg != null)
            throw BusinessException.withErrorCode(msg);
        Article article = articleService.getArticleById(comment.getArticleid());
        if (null == article)
            throw BusinessException.withErrorCode("该文章不存在");
        comment.setOwnerId(article.getAuthorId());
        comment.setStatus(STATUS_MAP.get(STATUS_BLANK));
        comment.setCreated(DateKit.getCurrentUnixTime());
        commentDAO.addComment(comment);

        Article temp = new Article();
        temp.setAid(article.getAid());
        Integer count = article.getCommentsNum();
        if (null == count){
            count = 0;
        }
        temp.setCommentsNum(count + 1);
        articleService.updateArticleByAid(temp);
    }

    @Override
    @Transactional
    @CacheEvict(value = "commentCache", allEntries = true)
    public void deleteComment(Integer cmid) {
        if (null == cmid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        // 如果删除的评论存在子评论，一并删除
        //查找当前评论是否有子评论
        CommentCond commentCond = new CommentCond();
        commentCond.setParent(cmid);
        Comment comment = commentDAO.getCommentById(cmid);
        List<Comment> childComments = commentDAO.getCommentsByCond(commentCond);
        Integer count = 0;
        //删除子评论
        if (null != childComments && childComments.size() > 0){
            for (int i = 0; i < childComments.size(); i++) {
                commentDAO.deleteComment(childComments.get(i).getCmid());
                count++;
            }
        }
        //删除当前评论
        commentDAO.deleteComment(cmid);
        count++;

        //更新当前文章的评论数
        Article contentDomain = articleService.getArticleById(comment.getArticleid());
        if (null != contentDomain
                && null != contentDomain.getCommentsNum()
                && contentDomain.getCommentsNum() != 0){
            contentDomain.setCommentsNum(contentDomain.getCommentsNum() - count);
            articleService.updateArticleByAid(contentDomain);
        }
    }

    @Override
    @CacheEvict(value= "commentCache", allEntries = true)
    public void updateCommentStatus(Integer cmid, String status) {
        if (null == cmid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        commentDAO.updateCommentStatus(cmid, status);
    }

    @Override
    @Cacheable(value = "commentCache", key = "'commentById_' + #p0")
    public Comment getCommentById(Integer cmid) {
        if (null == cmid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);

        return commentDAO.getCommentById(cmid);
    }

    @Override
    @Cacheable(value = "commentCache", key = "'commentsByAId_' + #p0")
    public List<Comment> getCommentsByAId(Integer aid) {
        if (null == aid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        return commentDAO.getCommentsByAId(aid);
    }

    @Override
    @Cacheable(value = "commentCache", key = "'commentsByCond_' + #p1")
    public PageInfo<Comment> getCommentsByCond(CommentCond commentCond, int pageNum, int pageSize) {
        if (null == commentCond)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = commentDAO.getCommentsByCond(commentCond);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        return pageInfo;
    }
}
