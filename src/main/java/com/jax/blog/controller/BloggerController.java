package com.jax.blog.controller;

import com.jax.blog.model.Blogger;
import com.jax.blog.service.URLMapper;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BloggerController
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 17:50
 * @Version 1.0
 **/
@Controller
@RequestMapping("/blogger")
public class BloggerController {

    /*@RequestMapping(value = URLMapper.login, method = RequestMethod.GET)
    public String login(Blogger blogger, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject(); //获取主体
        UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUsername(), blogger.getPassword()); //将账号密码存入token
        try {
            subject.login(token); //尝试登录
            return "redirect:/admin/main.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("blogger", blogger);
            request.setAttribute("errorInfo", "用户名或密码错误！");
            return "login";
        }
    }*/
}
