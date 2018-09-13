package com.jax.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.jax.blog.dao")
@EnableCaching
public class BlogMasterApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BlogMasterApplication.class, args);
	}
}
