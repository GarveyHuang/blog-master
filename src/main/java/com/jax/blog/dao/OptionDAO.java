package com.jax.blog.dao;

import com.jax.blog.model.Options;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName OptionDAO
 * @Description 网站配置DAO
 * @Author huangjw
 * @Date 2018/9/13 10:49
 * @Version 1.0
 **/
@Mapper
public interface OptionDAO {
    /**
     * 删除网站配置
     * @param name
     * @return
     */
    int deleteOptionByName(@Param("name") String name);

    /**
     * 更新网站配置
     * @param options
     * @return
     */
    int updateOptionByName(Options options);

    /***
     * 根据名称获取网站配置
     * @param name
     * @return
     */
    Options getOptionByName(@Param("name") String name);

    /**
     * 获取全部网站配置
     * @return
     */
    List<Options> getOptions();
}
