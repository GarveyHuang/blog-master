package com.jax.blog.controller.admin;

import com.jax.blog.model.Blogger;
import com.jax.blog.service.BloggerService;
import com.jax.blog.service.URLMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName HomeController
 * @Description 登录相关接口
 * @Author huangjw
 * @Date 2018/9/6 11:06
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class AuthController {
    @Autowired
    private BloggerService bloggerService;

    @ResponseBody
    @RequestMapping(value = "/getBlogger", method = RequestMethod.GET)
    public Blogger getBloggerById(@RequestParam("id") Integer bloggerId) {
        return bloggerService.getBloggerById(bloggerId);
    }

    @RequestMapping(value = URLMapper.login, method = RequestMethod.GET)
    public String loginHomepage() {
        return URLMapper.login;
    }

    /**
     * 通过shiro进行登陆验证
     * @param blogger
     * @param request
     * @return
     */
    @RequestMapping(value = URLMapper.loginAuth, method = RequestMethod.GET)
    public String loginAuth(Blogger blogger, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject(); //获取主体
        UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUsername(), blogger.getPassword()); //将账号密码存入token
        try {
            subject.login(token); //尝试登录
            return "redirect:/admin/main.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("blogger", blogger);
            request.setAttribute("errorInfo", "用户名或密码错误！");
            return URLMapper.login;
        }
    }
}
