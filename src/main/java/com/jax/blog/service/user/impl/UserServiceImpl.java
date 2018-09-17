package com.jax.blog.service.user.impl;

import com.jax.blog.constant.ErrorConstant;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.dao.UserDAO;
import com.jax.blog.model.User;
import com.jax.blog.service.user.UserService;
import com.jax.blog.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:33
 * @Version 1.0
 **/
@Service("userService")
public class UserServiceImpl implements UserService {
    @Value("${user.salt}")
    private String salt;

    @Autowired
    private UserDAO dao;

    @Override
    public User getUserInfoById(Integer uid) {
        return dao.getUserInfoById(uid);
    }

    @Override
    public User login(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);
        }

        String salt = this.salt;
        if (StringUtils.isBlank(salt)) {
            throw BusinessException.withErrorCode("获取用户登录信息失败，请联系管理员");
        }
        String pwd = TaleUtils.PBKDF2encode(username + password, salt); //PBKDF2 加密
        User user = dao.getUserInfoByCond(username, pwd);
        if (null == user) {
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);
        }
        return user;
    }

    @Transactional
    @Override
    public int updateUserInfo(User user) {
        if(user == null || null == user.getUid()) {
            throw BusinessException.withErrorCode("用户信息不能为空");
        }
        return dao.updateUserInfo(user);
    }
}
