package com.jax.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jax.blog.constant.LogActions;
import com.jax.blog.constant.Types;
import com.jax.blog.constant.URLMapper;
import com.jax.blog.controller.BaseController;
import com.jax.blog.dto.cond.ArticleCond;
import com.jax.blog.dto.cond.MetaCond;
import com.jax.blog.model.Article;
import com.jax.blog.model.Meta;
import com.jax.blog.service.article.ArticleService;
import com.jax.blog.service.log.LogService;
import com.jax.blog.service.meta.MetaService;
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

    @Autowired
    MetaService metaService;

    @Autowired
    LogService logService;

    @GetMapping(value = URLMapper.ADMIN_ARTICLE)
    public String articleList(HttpServletRequest request,
                              @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(name = "limit", required = false, defaultValue = "15") int limit) {
        PageInfo<Article> articles = articleService.getArticlesByCond(new ArticleCond(), page, limit);
        request.setAttribute("articles", articles);
        return "admin/article_list";
    }

    @GetMapping(value = URLMapper.ADMIN_ARTICLE_PUBLISH)
    public String publishNewArticlePage(HttpServletRequest request) {
        MetaCond metaCond = new MetaCond();
        metaCond.setType(Types.CATEGORY.getType());
        List<Meta> metas = metaService.getMetas(metaCond);
        request.setAttribute("categories", metas);
        return "admin/article_edit";
    }

    @ResponseBody
    @PostMapping(value = URLMapper.ADMIN_ARTICLE_PUBLISH)
    public APIResponse publishArticle(
            HttpServletRequest request,
            @RequestParam(name = "title", required = true) String title,
            @RequestParam(name = "titlePic", required = false) String titlePic,
            @RequestParam(name = "slug", required = false) String slug,
            @RequestParam(name = "content", required = true) String content,
            @RequestParam(name = "type", required = true) String type,
            @RequestParam(name = "status", required = true) String status,
            @RequestParam(name = "tags", required = false) String tags,
            @RequestParam(name = "categories", required = false, defaultValue = "默认分类") String categories,
            @RequestParam(name = "isAllowComment", required = true) Boolean isAllowComment){
        Article article = new Article();
        article.setTitle(title);
        article.setTitlePic(titlePic);
        article.setSlug(slug);
        article.setContent(content);
        article.setType(type);
        article.setStatus(status);
        article.setTags(type.equals(Types.ARTICLE.getType()) ? tags : null);
        // 只允许博客文章分类，防止摄影等作品被收入分类
        article.setCategories(type.equals(Types.ARTICLE.getType()) ? categories : null);
        article.setIsAllowComment(isAllowComment ? 1 : 0);

        articleService.addArticle(article);
        return APIResponse.success();
    }

    @GetMapping(value = URLMapper.ADMIN_ARTICLE + "/{aid}")
    public String editArticle(@PathVariable Integer aid, HttpServletRequest request) {
        Article articles = articleService.getArticleById(aid);
        request.setAttribute("articles", articles);
        MetaCond metaCond = new MetaCond();
        metaCond.setType(Types.CATEGORY.getType());
        List<Meta> categories = metaService.getMetas(metaCond);
        request.setAttribute("categories", categories);
        request.setAttribute("active", "article");
        return "admin/article_edit";
    }

    @ResponseBody
    @PostMapping(value = URLMapper.ADMIN_ARTICLE_MODIFY)
    public APIResponse modifyArticle(
            HttpServletRequest request,
            @RequestParam(name = "aid", required = true) Integer aid,
            @RequestParam(name = "title", required = true) String title,
            @RequestParam(name = "titlePic", required = false) String titlePic,
            @RequestParam(name = "slug", required = false) String slug,
            @RequestParam(name = "content", required = true) String content,
            @RequestParam(name = "type", required = true) String type,
            @RequestParam(name = "status", required = true) String status,
            @RequestParam(name = "tags", required = false) String tags,
            @RequestParam(name = "categories", required = false, defaultValue = "默认分类") String categories,
            @RequestParam(name = "isAllowComment", required = true) Boolean isAllowComment) {
        Article article = new Article();
        article.setAid(aid);
        article.setTitle(title);
        article.setTitlePic(titlePic);
        article.setSlug(slug);
        article.setContent(content);
        article.setType(type);
        article.setCategories(categories);
        article.setStatus(status);
        article.setTags(tags);
        article.setIsAllowComment(isAllowComment ? 1 : 0);

        articleService.updateArticleById(article);
        return APIResponse.success();
    }

    @ResponseBody
    @PostMapping(value = URLMapper.ADMIN_ARTICLE_DELETE)
    public APIResponse deleteArticle(@RequestParam(name = "aid", required = true) Integer aid, HttpServletRequest request) {
        articleService.deleteArticleById(aid);
        logService.addLog(LogActions.DEL_ARTICLE.getAction(), null, null, aid + "", request.getRemoteAddr(), this.getUid(request), "info");
        return APIResponse.success();
    }
}
