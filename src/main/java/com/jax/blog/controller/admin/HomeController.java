package com.jax.blog.controller.admin;

import com.jax.blog.constant.WebConst;
import com.jax.blog.controller.BaseController;
import com.jax.blog.model.User;
import com.jax.blog.service.URLMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName HomeController
 * @Description 后台主页相关接口
 * @Author huangjw
 * @Date 2018/9/10 10:16
 * @Version 1.0
 **/
@Controller
public class HomeController extends BaseController {

    /**
     * 后台主页
     * @return
     */
    @RequestMapping(value = URLMapper.ADMIN_MAIN, method = RequestMethod.GET)
    public String homePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User loginUser = (User) request.getSession().getAttribute(WebConst.LOGIN_SESSION_KEY);
        if(loginUser == null) {
            return URLMapper.ADMIN_LOGIN;
        }
        return URLMapper.ADMIN_MAIN;
    }
}
