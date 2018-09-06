package com.jax.blog.controller;

import com.jax.blog.model.Blogger;
import com.jax.blog.service.URLMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BloggerController
 * @Description 通过shiro进行登陆验证
 * @Author huangjw
 * @Date 2018/9/5 17:50
 * @Version 1.0
 **/
@Controller
@RequestMapping("/blogger")
public class BloggerController {
}
