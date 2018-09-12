package com.jax.blog.controller;

import com.jax.blog.service.URLMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 12:17
 * @Version 1.0
 **/
@Controller
public class IndexController extends BaseController {

    @RequestMapping(value = URLMapper.INDEX, method = RequestMethod.GET)
    public String index() {
        return URLMapper.INDEX;
    }
}
