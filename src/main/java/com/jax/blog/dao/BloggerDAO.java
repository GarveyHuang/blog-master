package com.jax.blog.dao;

import com.jax.blog.model.Blogger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @ClassName BloggerMapper
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/6 11:52
 * @Version 1.0
 **/
@Mapper
@Component
public interface BloggerDAO {
    /**
     * 根据主键编号获取用户信息
     * @param uid
     * @return
     */
    Blogger getBloggerInfoById(@Param("uid") Integer uid);

    /**
     * 根据用户名和密码获取用户信息
     * @param username
     * @param password
     * @return
     */
    Blogger getBloggerInfoByCond(@Param("username") String username, @Param("password") String password);

    /**
     * 更新用户信息
     * @param blogger
     * @return
     */
    int updateBloggerInfo(Blogger blogger);
}
