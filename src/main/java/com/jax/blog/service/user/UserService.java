package com.jax.blog.service.user;

import com.jax.blog.model.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:29
 * @Version 1.0
 **/

public interface UserService {
    User getUserInfoById(Integer uid) throws Exception;

    User login(String username, String password) throws Exception;

    int updateUserInfo(User user) throws Exception;
}
