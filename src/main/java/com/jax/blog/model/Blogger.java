package com.jax.blog.model;

/**
 * @ClassName Blogger
 * @Description 用户
 * @Author huangjw
 * @Date 2018/9/5 16:54
 * @Version 1.0
 **/

public class Blogger {
    private Integer id;         //id
    private String username;    //用户名
    private String password;    //密码
    private String profile;     //个人介绍
    private String nickname;    //博主昵称
    private String truename;    //博主姓名
    private String sign;        //签名
    private String imagename;   //头像地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }
}
