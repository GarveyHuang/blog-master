package com.jax.blog.service;

import com.jax.blog.model.Blogger;

/**
 * @ClassName BloggerService
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:29
 * @Version 1.0
 **/

public interface BloggerService {
    Blogger getBloggerById(Integer bloggerId);
}
