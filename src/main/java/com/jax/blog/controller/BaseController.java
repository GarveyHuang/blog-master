package com.jax.blog.controller;

import com.jax.blog.constant.Types;
import com.jax.blog.dto.MetaDto;
import com.jax.blog.dto.StatisticsDto;
import com.jax.blog.dto.cond.CommentCond;
import com.jax.blog.model.Comment;
import com.jax.blog.model.User;
import com.jax.blog.service.site.SiteService;
import com.jax.blog.utils.MapCache;
import com.jax.blog.utils.TaleUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName BaseController
 * @Description
 * @Author huangjw
 * @Date 2018/9/7 11:20
 * @Version 1.0
 **/
public abstract class BaseController {
    protected MapCache cache = MapCache.single();

    @Autowired
    private SiteService siteService;

    public BaseController title(HttpServletRequest request, String title)  {
        request.setAttribute("title", title);
        return this;
    }

    /**
     * 获取blog页面需要的公共数据
     * @param request
     * @return
     */
    public BaseController blogBaseData(HttpServletRequest request, CommentCond commentCond) {
        // 最新评论
        List<Comment> latestComments = siteService.getComments(5, commentCond);

        // 标签
        List<MetaDto> tags = siteService.getMetas(Types.TAG.getType(), "count", 100);

        // 分类
        List<MetaDto> categories = siteService.getMetas(Types.CATEGORY.getType(), "count", 100);

        // 后台统计数据
        StatisticsDto statisticsDto = siteService.getStatistics();
        Long articlesCount = statisticsDto.getArticlesCount();
        Long commentsCount = statisticsDto.getCommentsCount();

        request.setAttribute("latestComments", latestComments);
        request.setAttribute("articlesCount", articlesCount);
        request.setAttribute("commentsCount", commentsCount);
        request.setAttribute("tags", tags);
        request.setAttribute("categories", categories);
        return this;
    }

    /**
     * 获取请求绑定的登录对象
     * @param request
     * @return
     */
    public User user(HttpServletRequest request) {
        return TaleUtils.getLoginUser(request);
    }

    public Integer getUid(HttpServletRequest request) {
        return this.user(request).getUid();
    }

    public String getNickName(HttpServletRequest request) {
        return this.user(request).getNickName();
    }

    /**
     * 数组转字符串
     * @param arr
     * @return
     */
    public String join(String[] arr) {
        StringBuilder ret = new StringBuilder();

        for (String item : arr) {
            ret.append(',').append(item);
        }
        return ret.length() > 0 ? ret.substring(1) : ret.toString();
    }
}
