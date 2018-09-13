package com.jax.blog.service.option;

import com.jax.blog.model.Options;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OptionService
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/13 10:26
 * @Version 1.0
 **/

public interface OptionService {
    /**
     * 删除网站配置
     * @param name
     * @return
     */
    void deleteOptionByName(String name);

    /**
     * 更新网站配置
     * @param name
     * @param value
     * @return
     */
    void updateOptionByName(String name, String value);

    /**
     * 保存网站配置
     * @param options
     */
    void saveOptions(Map<String, String> options);
    /***
     * 根据名称获取网站配置
     * @param name
     * @return
     */
    Options getOptionByName(String name);

    /**
     * 获取全部网站配置
     * @return
     */
    List<Options> getOptions();
}
