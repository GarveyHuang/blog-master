package com.jax.blog.service;

/**
 * @ClassName URLMapper
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 12:20
 * @Version 1.0
 **/

public class URLMapper {
    /** 登录页 */
    public static final String ADMIN_LOGIN = "/admin/login";
    /** 注销 */
    public static final String ADMIN_LOGOUT = "/admin/logout";
    /** 后台主页 */
    public static final String ADMIN_MAIN = "/admin/main";

    /** 查询用户信息 */
    public static final String ADMIN_USER_QUERY = "/admin/user/query.do";
    /** 更新用户信息 */
    public static final String ADMIN_USER_UPDATE = "/admin/user/update.do";
    /** 修改密码 */
    public static final String ADMIN_PASSWORD_MODIFY = "/admin/password/modify.do";

    /** 查询所有文章信息 */
    public static final String ADMIN_ARTICLE_QUERYALL = "/admin/article/queryAll.do";
    public static final String ADMIN_ARTICLE_QUERY = "/admin/article/query.do";
    /** 修改文章信息 */
    public static final String ADMIN_ARTICLE_MODIFY = "/admin/article/modify.do";
    /** 删除文章 */
    public static final String ADMIN_ARTICLE_DELETE = "/admin/article/delete.do";
    public static final String ADMIN_ARTICLE_BATCHDELETE = "/admin/article/batchDelete.do";


    /** 博客主页 */
    public static final String HOME = "/home";
}
