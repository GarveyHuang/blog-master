package com.jax.blog.controller.admin;

import com.jax.blog.service.URLMapper;
import com.jax.blog.utils.APIResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName BloggerAdminController
 * @Description 用户信息相关接口
 * @Author huangjw
 * @Date 2018/9/5 16:40
 * @Version 1.0
 **/
@Controller
public class UserController {

    @RequestMapping(value = URLMapper.ADMIN_USER_MODIFY, method = RequestMethod.POST)
    public APIResponse modifyUserInfo() {
        return APIResponse.fail("");
    }

    @RequestMapping(value = URLMapper.ADMIN_PASSWORD_MODIFY, method = RequestMethod.POST)
    public APIResponse modifyPassword() {
        return APIResponse.fail("");
    }
}
