package com.jax.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jax.blog.constant.LogActions;
import com.jax.blog.constant.URLMapper;
import com.jax.blog.constant.WebConst;
import com.jax.blog.controller.BaseController;
import com.jax.blog.dto.StatisticsDto;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.model.Article;
import com.jax.blog.model.Comment;
import com.jax.blog.model.Log;
import com.jax.blog.model.User;
import com.jax.blog.service.log.LogService;
import com.jax.blog.service.site.SiteService;
import com.jax.blog.service.user.UserService;
import com.jax.blog.utils.APIResponse;
import com.jax.blog.utils.Commons;
import com.jax.blog.utils.GsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName IndexController
 * @Description 后台主页相关接口
 * @Author huangjw
 * @Date 2018/9/10 10:16
 * @Version 1.0
 **/
@Controller("adminIndexController")
public class IndexController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private SiteService siteService;

    @Autowired
    private LogService logService;

    @Autowired
    private UserService userService;

    /**
     * 后台主页
     * @return
     */
    @GetMapping(value = {"", URLMapper.ADMIN_INDEX})
    public String index(HttpServletRequest request) throws IOException {
        LOGGER.info("Enter admin index method");
        List<Comment> comments = siteService.getComments(5);
        List<Article> articles = siteService.getNewArticles(5);
        StatisticsDto statistics = siteService.getStatistics();
        // 获取最新的20条日志
        PageInfo<Log> logs = logService.getLogs(1, 5);
        List<Log> list = logs.getList();
        request.setAttribute("comments", comments);
        request.setAttribute("articles", articles);
        request.setAttribute("statistics", statistics);
        request.setAttribute("logs", list);

        User user = (User) request.getSession().getAttribute(WebConst.LOGIN_SESSION_KEY);
        String nickname = user.getNickName();
        request.setAttribute("nickname", nickname);

        LOGGER.info("Exit admin index method");
        return URLMapper.ADMIN_INDEX;
    }

    /**
     * 个人设置页
     * @return
     */
    @GetMapping(value = URLMapper.ADMIN_PROFILE)
    public String profile() {
        return URLMapper.ADMIN_PROFILE;
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

    /**
     * 修改密码
     * @param password
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(value = URLMapper.ADMIN_PASSWORD_MODIFY)
    @ResponseBody
    public APIResponse updatePassword(@RequestParam String oldPassword, @RequestParam String password, HttpServletRequest request, HttpSession session) {
        User loginUser = this.user(request);
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(password)) {
            return APIResponse.fail("请确认信息输入完整");
        }

        if (!loginUser.getPassword().equals(oldPassword)) {
            return APIResponse.fail("旧密码输入错误");
        }

        if (password.length() < 6 || password.length() > 14) {
            return APIResponse.fail("请输入6-14位密码");
        }
        try{
            User temp = new User();
            temp.setUid(loginUser.getUid());
            //String pwd = password; //这里先把密码加密，暂未实现
            temp.setPassword(password);
            userService.updateUserInfo(temp);
            logService.addLog(LogActions.UP_PWD.getAction(), GsonUtils.toJsonString(temp), null, null, request.getRemoteAddr(), this.getUid(request), "info");

            //更新session中的数据
            User original = (User) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
            original.setPassword(password);
            session.setAttribute(WebConst.LOGIN_SESSION_KEY, original);
            return APIResponse.success();
        } catch (Exception e) {
            String msg = "密码修改失败";
            if (e instanceof BusinessException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return APIResponse.fail(msg);
        }
    }
}
