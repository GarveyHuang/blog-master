package com.jax.blog.utils;

import com.google.gson.Gson;

/**
 * @ClassName GsonUtils
 * @Description json转换工具
 * @Author huangjw
 * @Date 2018/9/13 12:14
 * @Version 1.0
 **/
public class GsonUtils {
    private static final Gson gson = new Gson();

    public static String toJsonString(Object object) {
        return object==null ? null : gson.toJson(object);
    }
}
