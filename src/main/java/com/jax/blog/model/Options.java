package com.jax.blog.model;

/**
 * @ClassName Options
 * @Description 网站配置项
 * @Author huangjw
 * @Date 2018/9/13 10:45
 * @Version 1.0
 **/

public class Options {
    /** 名称 */
    private String name;
    /** 内容 */
    private String value;
    /** 备注 */
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
