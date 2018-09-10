package com.jax.blog.controller;

import com.jax.blog.model.User;
import com.jax.blog.utils.MapCache;
import com.jax.blog.utils.TaleUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BaseController
 * @Description
 * @Author huangjw
 * @Date 2018/9/7 11:20
 * @Version 1.0
 **/
public abstract class BaseController {
    protected MapCache cache = MapCache.single();

    public BaseController title(HttpServletRequest request, String title)  {
        request.setAttribute("title", title);
        return this;
    }

    /**
     * 获取blog页面需要的公共数据
     * @param request
     * @param contentCond
     * @return
     */
    /*public BaseController blogBaseData(HttpServletRequest request, ContentCond contentCond) {
        return this;
    }*/

    /**
     * 获取请求绑定的登录对象
     * @param request
     * @return
     */
    public User user(HttpServletRequest request) {
        return TaleUtils.getLoginUser(request);
    }

    public Integer getBid(HttpServletRequest request) {
        return this.user(request).getUid();
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
