package com.jax.blog.controller.admin;

import com.jax.blog.constant.URLMapper;
import com.jax.blog.controller.BaseController;
import com.jax.blog.model.User;
import com.jax.blog.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BloggerAdminController
 * @Description 用户信息相关接口
 * @Author huangjw
 * @Date 2018/9/5 16:40
 * @Version 1.0
 **/
@Controller
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @RequestMapping(value = URLMapper.ADMIN_USER_QUERY, method = RequestMethod.POST)
    @ResponseBody
    public User queryUserInfo(HttpServletRequest request) throws Exception {
        User loginUser = user(request);
        if(loginUser == null && loginUser.getUid() == null) {
            return null;
        }
        return userService.getUserInfoById(loginUser.getUid());
    }
}
