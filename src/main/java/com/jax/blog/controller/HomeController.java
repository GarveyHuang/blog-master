package com.jax.blog.controller;

import com.jax.blog.constant.URLMapper;
import com.jax.blog.service.article.ArticleService;
import com.jax.blog.service.comment.CommentService;
import com.jax.blog.service.meta.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName HomeController
 * @Description 博客网站首页控制器
 * @Author huangjw
 * @Date 2018/9/5 12:17
 * @Version 1.0
 **/
@Controller
public class HomeController extends BaseController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MetaService linkService;

    /**
     * 博客首页
     * @return
     */
    @RequestMapping(value = URLMapper.INDEX, method = RequestMethod.GET)
    public String index() {
        return URLMapper.INDEX;
    }
}
