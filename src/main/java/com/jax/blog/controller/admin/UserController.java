package com.jax.blog.controller.admin;

import com.jax.blog.constant.WebConst;
import com.jax.blog.model.User;
import com.jax.blog.service.URLMapper;
import com.jax.blog.service.user.UserService;
import com.jax.blog.utils.APIResponse;
import com.jax.blog.utils.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @ClassName BloggerAdminController
 * @Description 用户信息相关接口
 * @Author huangjw
 * @Date 2018/9/5 16:40
 * @Version 1.0
 **/
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = URLMapper.ADMIN_USER_QUERY, method = RequestMethod.POST)
    @ResponseBody
    public User queryUserInfo(HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute(WebConst.LOGIN_SESSION_KEY);
        if(loginUser == null && loginUser.getUid() == null) {
            return null;
        }
        return userService.getUserInfoById(loginUser.getUid());
    }

    @RequestMapping(value = URLMapper.ADMIN_USER_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    public APIResponse updateUserInfo(@RequestParam("imageFile") MultipartFile imageFile, User user, HttpServletRequest request) throws Exception {
        if(! imageFile.isEmpty()) {
            String filePath = request.getServletContext().getRealPath("/"); // 获取服务器根路径
            String imageName = Commons.getCurrentDateStr() + "." + imageFile.getOriginalFilename().split("\\.")[1]; // 获取头像名
            imageFile.transferTo(new File(filePath + "images\\upload\\userImages\\" + imageName)); // 复制到新地址
            user.setImageName(imageName);
        }

        int resultTotal = userService.updateUserInfo(user);
        if(resultTotal > 0) {
            request.getSession().getServletContext().setAttribute(WebConst.LOGIN_SESSION_KEY, userService.getUserInfoById(user.getUid()));
            return APIResponse.success();
        }
        return APIResponse.fail("更新用户信息失败");
    }

    @RequestMapping(value = URLMapper.ADMIN_PASSWORD_MODIFY, method = RequestMethod.POST)
    @ResponseBody
    public APIResponse updatePassword(@RequestParam("password") String password, HttpServletRequest request) throws Exception {
        User user = new User();
        user.setPassword(password);
        User loginUser = (User) request.getSession().getAttribute(WebConst.LOGIN_SESSION_KEY); // 获取登录用户的uid
        int resultTotal;
        if(loginUser != null && loginUser.getUid() != null) {
            user.setUid(loginUser.getUid());
            resultTotal = userService.updateUserInfo(user);
            if(resultTotal > 0) {
                request.getSession().getServletContext().setAttribute(WebConst.LOGIN_SESSION_KEY, userService.getUserInfoById(loginUser.getUid()));
                return APIResponse.success();
            }
        }
        return APIResponse.fail("修改密码发生错误");
    }
}
