package com.jax.blog.controller.admin;

import com.jax.blog.controller.BaseController;
import com.jax.blog.model.Comment;
import com.jax.blog.service.URLMapper;
import com.jax.blog.service.comment.CommentService;
import com.jax.blog.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName CommentAdminController
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:41
 * @Version 1.0
 **/
@Controller
public class CommentManageController extends BaseController {
    @Autowired
    CommentService commentService;

    @ResponseBody
    @RequestMapping(value = URLMapper.ADMIN_COMMENT_QUERY, method = RequestMethod.GET)
    public List<Comment> queryCommentList() {
        return null;
    }

    @ResponseBody
    @RequestMapping(value = URLMapper.ADMIN_COMMENT_REVIEW, method = RequestMethod.POST)
    public APIResponse reviewComment(@RequestParam("commentId") Integer commentId) {
        return APIResponse.fail("执行审核评论操作失败");
    }

    @ResponseBody
    @RequestMapping(value = URLMapper.ADMIN_COMMENT_DELETE, method = RequestMethod.POST)
    public APIResponse deleteComment(@RequestParam("commentId") Integer commentId) {
        return APIResponse.fail("执行删除评论操作失败");
    }
}
