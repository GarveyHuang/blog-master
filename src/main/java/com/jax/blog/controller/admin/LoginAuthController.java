package com.jax.blog.controller.admin;

import com.jax.blog.constant.WebConst;
import com.jax.blog.controller.BaseController;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.model.User;
import com.jax.blog.model.request.user.UserLoginAuthRQ;
import com.jax.blog.service.user.UserService;
import com.jax.blog.service.URLMapper;
import com.jax.blog.utils.APIResponse;
import com.jax.blog.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
public class LoginAuthController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 跳转登录页
     * @return
     */
    @RequestMapping(value = URLMapper.ADMIN_LOGIN, method = RequestMethod.GET)
    public String loginPage() {
        return URLMapper.ADMIN_LOGIN;
    }

    /**
     * 登录验证
     * @param request
     * @param response
     * @param userLoginAuthRQ
     *  {
     *      username: username,
     *      password: password,
     *      isRemember: isRemember
     *  }
     * @return
     */
    @ResponseBody
    @RequestMapping(value = URLMapper.ADMIN_LOGIN, method = RequestMethod.POST)
    public APIResponse doLogin(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestBody UserLoginAuthRQ userLoginAuthRQ) {
        String username = userLoginAuthRQ.getUsername();
        String password = userLoginAuthRQ.getPassword();
        String isRemember = userLoginAuthRQ.getIsRemember();
        Integer error_count = cache.get("login_error_count");
        try {
            User userInfo = userService.login(username, password);
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, userInfo);
            if(StringUtils.isNoneBlank(isRemember)) {
                TaleUtils.setCookie(response, userInfo.getUid());
            }
        } catch (Exception e) {
            error_count = null == error_count ? 1 : error_count + 1;
            if(error_count > 3) {
                return APIResponse.fail("您已经连续超过3次输入错误密码，请10分钟后尝试！");
            }
            cache.set("login_error_count", error_count, 60);
            String msg = "登录失败";
            if(e instanceof BusinessException) {
                msg = e.getMessage();
            } else {
                //LOGGER.error(msg, e);
            }
            return APIResponse.fail(msg);
        }
        return APIResponse.success(userLoginAuthRQ);
    }

    /**
     * 注销
     * @param session
     * @param response
     * @param request
     */
    @RequestMapping(value = URLMapper.ADMIN_LOGOUT)
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
