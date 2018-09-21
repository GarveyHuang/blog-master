package com.jax.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jax.blog.constant.LogActions;
import com.jax.blog.constant.URLMapper;
import com.jax.blog.constant.WebConst;
import com.jax.blog.controller.BaseController;
import com.jax.blog.dto.StatisticsDto;
import com.jax.blog.dto.cond.CommentCond;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.model.Article;
import com.jax.blog.model.Comment;
import com.jax.blog.model.Log;
import com.jax.blog.model.User;
import com.jax.blog.service.log.LogService;
import com.jax.blog.service.site.SiteService;
import com.jax.blog.service.user.UserService;
import com.jax.blog.utils.APIResponse;
import com.jax.blog.utils.GsonUtils;
import com.jax.blog.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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

    @Value("${user.salt}")
    private String salt;

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
    @GetMapping(value = {"/admin/", URLMapper.ADMIN_INDEX})
    public String index(HttpServletRequest request) throws IOException {
        LOGGER.info("Enter admin index method");
        List<Comment> comments = siteService.getComments(5, new CommentCond());
        List<Article> articles = siteService.getNewArticles(5);
        StatisticsDto statistics = siteService.getStatistics();
        // 获取最新的20条日志
        PageInfo<Log> logs = logService.getLogs(1, 5);
        List<Log> list = logs.getList();
        request.setAttribute("comments", comments);
        request.setAttribute("articles", articles);
        request.setAttribute("statistics", statistics);
        request.setAttribute("logs", list);

        String nickname = this.getNickName(request);
        request.setAttribute("nickname", nickname);

        LOGGER.info("Exit admin index method");
        return URLMapper.ADMIN_INDEX;
    }

    /**
     * 个人设置页
     * @return
     */
    @GetMapping(value = URLMapper.ADMIN_PROFILE)
    public String profile(HttpServletRequest request) {
        String nickname = this.getNickName(request);
        request.setAttribute("nickname", nickname);
        return URLMapper.ADMIN_PROFILE;
    }

    /**
     * 保存个人信息
     * @param nickName
     * @param email
     * @param request
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping(value = URLMapper.ADMIN_PROFILE)
    public APIResponse saveProfile(@RequestParam String nickName,
                                   @RequestParam String email,
                                   HttpServletRequest request,
                                   HttpSession session) {
        User user = this.user(request);
        if (StringUtils.isNotBlank(nickName) && StringUtils.isNotBlank(email)) {
            User temp = new User();
            temp.setUid(user.getUid());
            temp.setNickName(nickName);
            temp.setEmail(email);
            userService.updateUserInfo(temp);
            logService.addLog(LogActions.UP_INFO.getAction(), null, null,
                    GsonUtils.toJsonString(temp), request.getRemoteAddr(), this.getUid(request), "info");

            // 更新session中的数据
            User original = (User) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
            original.setNickName(nickName);
            original.setEmail(email);
            session.setAttribute(WebConst.LOGIN_SESSION_KEY, original);
        }
        return APIResponse.success();
    }

    /**
     * 修改密码
     * @param password
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(value = URLMapper.ADMIN_PASSWORD)
    @ResponseBody
    public APIResponse updatePassword(@RequestParam String oldPassword,
                                      @RequestParam String password,
                                      HttpServletRequest request, HttpSession session)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        User loginUser = this.user(request);
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(password)) {
            return APIResponse.fail("请确认信息输入完整");
        }

        String temp1 = loginUser.getUsername() + oldPassword;
        String oldEncryptedPwd = TaleUtils.PBKDF2encode(temp1, this.salt);
        if (!loginUser.getPassword().equals(oldEncryptedPwd)) {
            return APIResponse.fail("旧密码输入错误");
        }

        if (password.length() < 6 || password.length() > 14) {
            return APIResponse.fail("请输入6-14位密码");
        }
        try {
            User temp = new User();
            temp.setUid(loginUser.getUid());
            String pwd = TaleUtils.PBKDF2encode(loginUser.getUsername() + password, this.salt); // 加密
            temp.setPassword(pwd);
            userService.updateUserInfo(temp);
            logService.addLog(LogActions.UP_PWD.getAction(), GsonUtils.toJsonString(temp), null, null, request.getRemoteAddr(), this.getUid(request), "info");

            //更新session中的数据
            User original = (User) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
            original.setPassword(pwd);
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
