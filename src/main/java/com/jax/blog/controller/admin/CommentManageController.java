package com.jax.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jax.blog.constant.ErrorConstant;
import com.jax.blog.constant.URLMapper;
import com.jax.blog.controller.BaseController;
import com.jax.blog.dto.cond.CommentCond;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.model.Comment;
import com.jax.blog.model.User;
import com.jax.blog.service.comment.CommentService;
import com.jax.blog.utils.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CommentAdminController
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:41
 * @Version 1.0
 **/
@Controller
public class CommentManageController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentManageController.class);

    @Autowired
    CommentService commentService;

    @GetMapping(value = URLMapper.ADMIN_COMMENT)
    public String commentList(
            HttpServletRequest request,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "15") int limit) {
        User user = this.user(request);
        String nickname = user.getNickName();
        PageInfo<Comment> comments = commentService.getCommentsByCond(new CommentCond(), page, limit);
        request.setAttribute("comments", comments);
        request.setAttribute("nickname", nickname);
        return "admin/comment_list";
    }

    @ResponseBody
    @PostMapping(value = URLMapper.ADMIN_COMMENT_DELETE)
    public APIResponse deleteComment(@RequestParam(name = "cmid") Integer cmid) {
        try {
            Comment comment = commentService.getCommentById(cmid);
            if(null == comment) {
                throw BusinessException.withErrorCode(ErrorConstant.Comment.COMMENT_NOT_EXIST);
            }
            commentService.deleteComment(cmid);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }

    @ResponseBody
    @PostMapping(value = URLMapper.ADMIN_COMMENT_STATUS)
    public APIResponse changeCommentStatus(
            @RequestParam(name = "cmid", required = true) Integer cmid,
            @RequestParam(name = "status", required = true) String status) {
        try {
            Comment comment = commentService.getCommentById(cmid);
            if(null != comment) {
                commentService.updateCommentStatus(cmid, status);
            } else {
                return APIResponse.fail("修改评论状态失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }
}
