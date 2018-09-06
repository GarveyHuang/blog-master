package com.jax.blog.mapper;

import com.jax.blog.model.Blogger;
import org.apache.ibatis.annotations.Mapper;
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
public interface BloggerMapper {
    public Blogger getBloggerById(Integer bloggerId);
}
