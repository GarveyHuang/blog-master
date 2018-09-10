package com.jax.blog.controller.admin;

import com.jax.blog.constant.WebConst;
import com.jax.blog.controller.BaseController;
import com.jax.blog.model.User;
import com.jax.blog.service.URLMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

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
    public String homePage() {
        return URLMapper.ADMIN_MAIN;
    }
}
