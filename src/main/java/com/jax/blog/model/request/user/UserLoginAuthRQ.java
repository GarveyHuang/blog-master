package com.jax.blog.model.request.user;

/**
 * @ClassName UserLoginAuthRQ
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/10 9:35
 * @Version 1.0
 **/

public class UserLoginAuthRQ {
    private String username;
    private String password;
    private String isRemember;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsRemember() {
        return isRemember;
    }

    public void setIsRemember(String isRemember) {
        this.isRemember = isRemember;
    }
}
