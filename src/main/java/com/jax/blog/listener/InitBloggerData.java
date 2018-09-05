package com.jax.blog.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ClassName InitBloggerData
 * @Description 监听器，用于刚开始加载博客的信息
 * @Author huangjw
 * @Date 2018/9/5 17:52
 * @Version 1.0
 **/
/*@Component
public class InitBloggerData implements ServletContextListener,ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public void setApplicationContextAware(ApplicationContext applicationContext) {
        InitBloggerData.applicationContext = applicationContext;
    }

    public void contextDestroyed(ServletContextEvent arg0) {}

    public void contextInitialized(ServletContextEvent sce) {
        //applicationContext = new ClassPathXmlApplicationContext("");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}*/
