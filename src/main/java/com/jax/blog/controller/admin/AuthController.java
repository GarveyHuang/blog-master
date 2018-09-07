package com.jax.blog.controller.admin;

import com.jax.blog.constant.WebConst;
import com.jax.blog.controller.BaseController;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.model.Blogger;
import com.jax.blog.service.blogger.BloggerService;
import com.jax.blog.service.URLMapper;
import com.jax.blog.utils.APIResponse;
import com.jax.blog.utils.TaleUtils;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName HomeController
 * @Description 登录相关接口
 * @Author huangjw
 * @Date 2018/9/6 11:06
 * @Version 1.0
 **/
@Controller
public class AuthController extends BaseController {
    @Autowired
    private BloggerService bloggerService;

    @ResponseBody
    @RequestMapping(value = "/admin/getBlogger", method = RequestMethod.GET)
    public Blogger getBloggerById(@RequestParam("id") Integer bloggerId) {
        return bloggerService.getBloggerInfoById(bloggerId);
    }

    /**
     * 跳转登录页
     * @return
     */
    @RequestMapping(value = URLMapper.ADMIN_LOGIN, method = RequestMethod.GET)
    public String loginHomepage() {
        return URLMapper.ADMIN_LOGIN;
    }

    /**
     * 登录验证
     * @param request
     * @param response
     * @param username
     * @param password
     * @param remember_me
     * @return
     */
    @RequestMapping(value = URLMapper.ADMIN_LOGIN, method = RequestMethod.POST)
    public APIResponse doLogin(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(name = "username") String username,
                                 @RequestParam(name = "password") String password,
                                 @RequestParam(name = "remember_me") String remember_me
                                 ) {
        Integer error_count = cache.get("login_error_count");
        try {
            Blogger bloggerInfo = bloggerService.login(username, password);
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, bloggerInfo);
            if(StringUtils.isNoneBlank(remember_me)) {
                TaleUtils.setCookie(response, bloggerInfo.getId());
            }
        } catch (Exception e) {
            error_count = null == error_count ? 1 : error_count + 1;
            if(error_count > 3) {
                return APIResponse.fail("您已经连续超过3次输入错误密码，请10分钟后尝试！");
            }
            cache.set("login_error_count", error_count, 10 * 60);
            String msg = "登录失败";
            if(e instanceof BusinessException) {
                msg = e.getMessage();
            } else {
                //LOGGER.error(msg, e);
            }
            return APIResponse.fail(msg);
        }
        return APIResponse.success();
    }

    /**
     * 注销
     * @param session
     * @param response
     * @param request
     */
    @RequestMapping(value = URLMapper.LOGOUT)
    public void logout(HttpSession session, HttpServletResponse response, org.apache.catalina.servlet4preview.http.HttpServletRequest request) {
        session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
        Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
        cookie.setValue(null);
        cookie.setMaxAge(0); // 立即销毁cookie
        cookie.setPath("/");
        try {
            response.sendRedirect(URLMapper.ADMIN_LOGIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
