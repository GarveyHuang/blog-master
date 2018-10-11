package com.jax.blog.controller.admin;

import com.jax.blog.constant.LogActions;
import com.jax.blog.constant.URLMapper;
import com.jax.blog.constant.WebConst;
import com.jax.blog.controller.BaseController;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.model.User;
import com.jax.blog.service.log.LogService;
import com.jax.blog.service.user.UserService;
import com.jax.blog.utils.APIResponse;
import com.jax.blog.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class LoginAuthController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(LoginAuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    /**
     * 跳转登录页
     * @return
     */
    @RequestMapping(value = URLMapper.ADMIN_LOGIN, method = RequestMethod.GET)
    public String login() {
        return "admin/login";
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
    @ResponseBody
    @RequestMapping(value = URLMapper.ADMIN_LOGIN, method = RequestMethod.POST)
    public APIResponse doLogin(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(name = "username", required = true) String username,
                               @RequestParam(name = "password", required = true) String password,
                               @RequestParam(name = "remember_me", required = false) String remember_me) {
        Integer error_count = cache.get("login_error_count");
        try {
            User userInfo = userService.login(username, password);
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, userInfo);
            if(StringUtils.isNoneBlank(remember_me)) {
                TaleUtils.setCookie(response, userInfo.getUid());
            }
            logService.addLog(LogActions.LOGIN.getAction(), null, null, null, request.getRemoteAddr(), userInfo.getUid(), "info");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            error_count = null == error_count ? 1 : error_count + 1;
            if(error_count > 3) {
                return APIResponse.fail("您已经连续超过3次输入错误密码，请10分钟后尝试！");
            }
            cache.set("login_error_count", error_count, 60);
            String msg = "登录失败";
            if(e instanceof BusinessException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return APIResponse.fail(msg);
        }
        return APIResponse.success();
    }

    /**
     * 注销
     * @param session
     * @param response
     */
    @RequestMapping(value = URLMapper.ADMIN_LOGOUT)
    public void logout(HttpSession session, HttpServletResponse response) {
        session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
        Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
        cookie.setValue(null);
        cookie.setMaxAge(0); // 立即销毁cookie
        cookie.setPath("/");
        try {
            response.sendRedirect("login");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("注销失败", e);
        }
    }
}
