package com.jax.blog.service.blogger;

import com.jax.blog.model.Blogger;

/**
 * @ClassName BloggerService
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:29
 * @Version 1.0
 **/

public interface BloggerService {
    Blogger getBloggerInfoById(Integer uid);
    Blogger login(String username, String password);
    int updateBloggerInfo(Blogger blogger);
}
