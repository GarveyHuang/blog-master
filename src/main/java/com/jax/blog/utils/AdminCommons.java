package com.jax.blog.utils;
/**
 * Created by huangjw on 2018/9/13.
 */

import com.jax.blog.model.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName AdminCommons
 * @Description 后台公共函数
 * @Author huangjw
 * @Date 2018/9/13 10:28
 * @Version 1.0
 **/
@Component
public class AdminCommons {
    /**
     * 判断category和cat的交集
     *
     * @param cats
     * @return
     */
    public static boolean exist_cat(Tag category, String cats) {
        String[] arr = StringUtils.split(cats, ",");
        if (null != arr && arr.length > 0) {
            for (String c : arr) {
                if (c.trim().equals(category.getTagName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final String[] COLORS = {"default", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

    public static String rand_color() {
        int r = Tools.rand(0, COLORS.length - 1);
        return COLORS[r];
    }
}
