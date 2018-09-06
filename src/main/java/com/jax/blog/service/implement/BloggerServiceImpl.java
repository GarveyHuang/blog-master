package com.jax.blog.service.implement;

import com.jax.blog.mapper.BloggerMapper;
import com.jax.blog.model.Blogger;
import com.jax.blog.service.BloggerService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName BloggerServiceImpl
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:33
 * @Version 1.0
 **/
@MapperScan("com.jax.blog.mapper.BloggerMapper")
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {
    @Resource
    private BloggerMapper mapper;

    @Override
    public Blogger getBloggerById(Integer bloggerId) {
        return mapper.getBloggerById(bloggerId);
    }
}
