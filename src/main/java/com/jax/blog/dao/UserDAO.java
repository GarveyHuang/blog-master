package com.jax.blog.dao;

import com.jax.blog.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/6 11:52
 * @Version 1.0
 **/
@Mapper
public interface UserDAO {
    /**
     * 根据主键编号获取用户信息
     * @param uid
     * @return
     */
    User getUserInfoById(@Param("uid") Integer uid);

    /**
     * 根据主键获取盐值
     * @param uid
     * @return
     */
    String getUserSaltById(@Param("uid") Integer uid);

    /**
     * 根据用户名和密码获取用户信息
     * @param username
     * @param password
     * @return
     */
    User getUserInfoByCond(@Param("username") String username, @Param("password") String password);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUserInfo(User user);
}
