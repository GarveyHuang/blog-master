package com.jax.blog.dto;

import com.jax.blog.model.Meta;

/**
 * @ClassName MetaDto
 * @Description 标签、分类列表
 * @Author huangjw
 * @Date 2018/09/13 20:43
 * @Version 1.0
 **/
public class MetaDto extends Meta {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
