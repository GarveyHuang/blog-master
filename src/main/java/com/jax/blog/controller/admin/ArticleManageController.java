package com.jax.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jax.blog.controller.BaseController;
import com.jax.blog.dto.cond.ArticleCond;
import com.jax.blog.model.Article;
import com.jax.blog.constant.URLMapper;
import com.jax.blog.service.article.ArticleService;
import com.jax.blog.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName BlogAdminController
 * @Description 博客文章信息相关接口
 * @Author huangjw
 * @Date 2018/9/5 16:40
 * @Version 1.0
 **/
@Controller
public class ArticleManageController extends BaseController {
    @Autowired
    ArticleService articleService;

    @GetMapping(value = URLMapper.ADMIN_ARTICLE)
    public String articleList(HttpServletRequest request,
                              @RequestParam(name = "page", required = false, defaultValue = "1")
                              int page,
                              @RequestParam(name = "limit", required = false, defaultValue = "15")
                              int limit) {
        PageInfo<Article> articles = articleService.getArticlesByCond(new ArticleCond(), page, limit);
        request.setAttribute("articles", articles);
        return "/admin/article_list";
    }
}
