package com.jax.blog.controller;

import com.github.pagehelper.PageInfo;
import com.jax.blog.constant.Types;
import com.jax.blog.constant.URLMapper;
import com.jax.blog.constant.WebConst;
import com.jax.blog.dto.MetaDto;
import com.jax.blog.dto.StatisticsDto;
import com.jax.blog.dto.cond.ArticleCond;
import com.jax.blog.dto.cond.CommentCond;
import com.jax.blog.model.Article;
import com.jax.blog.model.Comment;
import com.jax.blog.service.article.ArticleService;
import com.jax.blog.service.comment.CommentService;
import com.jax.blog.service.meta.MetaService;
import com.jax.blog.service.option.OptionService;
import com.jax.blog.service.site.SiteService;
import com.jax.blog.utils.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private MetaService metaService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private OptionService optionService;

    /**
     * 博客首页
     * @return
     */
    @GetMapping(value = {"", URLMapper.BLOG, URLMapper.BLOG_INDEX } )
    public String blogIndex(HttpServletRequest request,
                        @RequestParam(name = "limit", required = false, defaultValue = "11") int limit) {
        return this.blogIndex(request, 1, limit);
    }

    /**
     * 主页-分页
     * @param request
     * @param p
     * @param limit
     * @return
     */
    @GetMapping(value = URLMapper.BLOG_PAGE)
    public String blogIndex(HttpServletRequest request,
                            @PathVariable("p") int p,
                            @RequestParam(name = "limit", required = false, defaultValue = "11") int limit) {
        p = p < 0 || p > WebConst.MAX_PAGE ? 1 : p;
        ArticleCond articleCond = new ArticleCond();
        articleCond.setType(Types.ARTICLE.getType());
        // 文章
        PageInfo<Article> articles = articleService.getArticlesByCond(articleCond, p, limit);

        // 最新评论
        PageInfo<Comment> latestComments = commentService.getCommentsByCond(new CommentCond(), 1, 5);

        // 标签
        List<MetaDto> tags = siteService.getMetas(Types.TAG.getType(), "count", limit);

        // 分类
        List<MetaDto> categories = siteService.getMetas(Types.CATEGORY.getType(), "count", limit);

        // 后台统计数据
        StatisticsDto statisticsDto = siteService.getStatistics();
        Long articlesCount = statisticsDto.getArticlesCount();
        Long commentsCount = statisticsDto.getCommentsCount();
        request.setAttribute("articles", articles);
        request.setAttribute("types", "articles");
        request.setAttribute("active", "blog");
        request.setAttribute("latestComments", latestComments);
        request.setAttribute("articlesCount", articlesCount);
        request.setAttribute("commentsCount", commentsCount);
        request.setAttribute("tags", tags);
        request.setAttribute("categories", categories);
        return "site/index";
    }

    /**
     * 文章内容页
     * @param aid
     * @param request
     * @return
     */
    @GetMapping(value = URLMapper.BLOG_ARTICLE)
    public String articlePost(@PathVariable("aid") Integer aid, HttpServletRequest request) {
        Article article = articleService.getArticleById(aid);
        request.setAttribute("article", article);
        ArticleCond articleCond = new ArticleCond();
        articleCond.setType(Types.ARTICLE.getType());
        // 更新文章的阅读量
        this.updateArticleHit(article.getAid(), article.getHits());
        List<Comment> comments = commentService.getCommentsByAId(aid);
        PageInfo<Comment> latestComments = commentService.getCommentsByCond(new CommentCond(), 1, 5);
        StatisticsDto statisticsDto = siteService.getStatistics();
        Long articlesCount = statisticsDto.getArticlesCount();
        Long commentsCount = statisticsDto.getCommentsCount();
        request.setAttribute("comments", comments);
        request.setAttribute("active", "blog");
        request.setAttribute("latestComments", latestComments);
        request.setAttribute("articlesCount", articlesCount);
        request.setAttribute("commentsCount", commentsCount);
        return "site/article-detail";
    }

    /**
     * 更新文章阅读量
     * @param aid
     * @param curHits
     */
    private void updateArticleHit(Integer aid, Integer curHits) {
        Integer hits = cache.hget("article", "hits");
        if (curHits == null) {
            curHits = 0;
        }
        hits = null == hits ? 1 : hits + 1;
        if (hits >= WebConst.HIT_EXCEED) {
            Article temp = new Article();
            temp.setAid(aid);
            temp.setHits(hits + curHits);
            articleService.updateArticleByAid(temp);
            cache.hset("article", "hits", 1);
        } else {
            cache.hset("article", "hits", hits);
        }
    }

    @ResponseBody
    @GetMapping(value = "/blog/test")
    public Map<String, Object> test(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Map<String, Object> model = new HashMap<>();
        String encryptedPwd = PasswordEncryption.getEncryptedPassword(password, "Mvw61W9elSYzAlek");
        model.put("encryptedPwd", encryptedPwd);
        return model;
    }
}
