package com.jax.blog.controller;

import com.github.pagehelper.PageInfo;
import com.jax.blog.constant.Types;
import com.jax.blog.constant.URLMapper;
import com.jax.blog.constant.WebConst;
import com.jax.blog.dto.cond.ArticleCond;
import com.jax.blog.dto.cond.CommentCond;
import com.jax.blog.model.Article;
import com.jax.blog.model.Comment;
import com.jax.blog.service.article.ArticleService;
import com.jax.blog.service.comment.CommentService;
import com.jax.blog.service.meta.MetaService;
import com.jax.blog.service.option.OptionService;
import com.jax.blog.service.site.SiteService;
import com.jax.blog.utils.APIResponse;
import com.jax.blog.utils.IPKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = {"", URLMapper.BLOG, URLMapper.BLOG_INDEX } )
    public String blogIndex(HttpServletRequest request,
                            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(name = "limit", required = false, defaultValue = "11") int limit) {
        page = page < 0 || page > WebConst.MAX_PAGE ? 1 : page;
        ArticleCond articleCond = new ArticleCond();
        articleCond.setType(Types.ARTICLE.getType());
        articleCond.setStatus(Types.PUBLISH.getType());
        // 文章
        PageInfo<Article> articles = articleService.getArticlesByCond(articleCond, page, limit);
        request.setAttribute("articles", articles);
        request.setAttribute("types", "articles");
        request.setAttribute("active", "blog");

        CommentCond commentCond = new CommentCond();
        commentCond.setStatus("approved");
        blogBaseData(request, commentCond);

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
        request.setAttribute("comments", comments);
        request.setAttribute("active", "blog");

        CommentCond commentCond = new CommentCond();
        commentCond.setStatus("approved");
        blogBaseData(request, commentCond);
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
    @PostMapping(value = URLMapper.BLOG_COMMENT_ADD)
    public APIResponse addComment(HttpServletRequest request,
                                  @RequestParam(name = "articleId") Integer articleId,
                                  @RequestParam(name = "author") String author,
                                  @RequestParam(name = "content") String content,
                                  @RequestParam(name = "articleTitle") String articleTitle) {
        String agent = request.getHeader("user-agent");
        String ip = IPKit.getIpAddrByRequest(request);
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setArticleTitle(articleTitle);
        comment.setContent(content);
        comment.setAuthor(author);
        comment.setAgent(agent);
        comment.setAuthorIp(ip);
        comment.setType(Types.COMMENT.getType());
        try {
            commentService.addComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.fail(e.getMessage());
        }

        return APIResponse.success();
    }
}
