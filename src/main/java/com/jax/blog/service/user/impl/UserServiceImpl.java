package com.jax.blog.service.user.impl;

import com.jax.blog.constant.ErrorConstant;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.dao.UserDAO;
import com.jax.blog.model.User;
import com.jax.blog.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:33
 * @Version 1.0
 **/
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO dao;

    @Override
    public User getUserInfoById(Integer uid) {
        return dao.getUserInfoById(uid);
    }

    @Override
    public User login(String username, String password) {
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);
        }

        //String pwd = TaleUtils.MD5encode(username + password); //md5 加密，暂时不实现
        User user = dao.getUserInfoByCond(username, password);
        if(null == user) {
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);
        }
        return user;
    }

    @Transactional
    @Override
    public int updateUserInfo(User user) {
        if(null == user.getUid()) {
            throw BusinessException.withErrorCode("用户编号不能为空");
        }
        return dao.updateUserInfo(user);
    }
}
