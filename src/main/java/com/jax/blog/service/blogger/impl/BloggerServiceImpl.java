package com.jax.blog.service.blogger.impl;

import com.jax.blog.constant.ErrorConstant;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.dao.BloggerDAO;
import com.jax.blog.model.Blogger;
import com.jax.blog.service.blogger.BloggerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName BloggerServiceImpl
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:33
 * @Version 1.0
 **/
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {
    @Autowired
    private BloggerDAO dao;

    @Override
    public Blogger getBloggerInfoById(Integer uid) {
        return dao.getBloggerInfoById(uid);
    }

    @Override
    public Blogger login(String username, String password) {
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);
        }

        //String pwd = TaleUtils.MD5encode(username + password); //md5 加密，暂时不实现
        Blogger blogger = dao.getBloggerInfoByCond(username, password);
        if(null == blogger) {
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);
        }
        return blogger;
    }

    @Transactional
    @Override
    public int updateBloggerInfo(Blogger blogger) {
        if(null == blogger.getId()) {
            throw BusinessException.withErrorCode("用户编号不能为空");
        }
        return dao.updateBloggerInfo(blogger);
    }
}
