package com.jax.blog.constant;
/**
 * Created by huangjw on 2018/9/13.
 */

/**
 * @ClassName LogActions
 * @Description 日志表的action字段
 * @Author huangjw
 * @Date 2018/9/13 11:09
 * @Version 1.0
 **/

public enum  LogActions {
    LOGIN("登录后台"), UP_PWD("修改密码"), UP_INFO("修改个人信息"),
    DEL_ARTICLE("删除文章"), DEL_PAGE("删除页面"), DEL_COMMENT("删除评论"),
    SYS_BACKUP("系统备份"), SYS_SETTING("保存系统设置"), INIT_SITE("初始化站点");

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    LogActions(String action) {
        this.action = action;
    }
}
