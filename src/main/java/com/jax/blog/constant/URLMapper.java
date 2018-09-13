package com.jax.blog.constant;

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
    public static final String ADMIN_INDEX = "/admin/index";
    /** 个人设置页 */
    public static final String ADMIN_PROFILE = "/admin/profile";

    /** 查询用户信息 */
    public static final String ADMIN_USER_QUERY = "/admin/user/query.do";
    /** 更新用户信息 */
    public static final String ADMIN_USER_UPDATE = "/admin/user/update.do";
    /** 修改密码 */
    public static final String ADMIN_PASSWORD_MODIFY = "/admin/password/modify.do";

    /** 文章管理页 */
    public static final String ADMIN_ARTICLE = "/admin/article";
    /** 查询文章信息 */
    public static final String ADMIN_ARTICLE_QUERYALL = "/admin/article/queryAll.do";
    public static final String ADMIN_ARTICLE_QUERY = "/admin/article/query.do";
    /** 修改文章信息 */
    public static final String ADMIN_ARTICLE_MODIFY = "/admin/article/modify.do";
    /** 删除文章 */
    public static final String ADMIN_ARTICLE_DELETE = "/admin/article/delete.do";
    public static final String ADMIN_ARTICLE_BATCHDELETE = "/admin/article/batchDelete.do";

    /** 查询标签 */
    public static final String ADMIN_TAG_QUERY = "/admin/tag/query.do";
    /** 添加标签 */
    public static final String ADMIN_TAG_ADD = "/admin/tag/add.do";
    /** 修改标签 */
    public static final String ADMIN_TAG_UPDATE = "/admin/tag/update.do";
    /** 删除标签 */
    public static final String ADMIN_TAG_DELETE = "/admin/tag/delete.do";

    /** 查询友链信息 */
    public static final String ADMIN_LINK_QUERY = "/admin/meta/query.do";
    /** 添加友链信息 */
    public static final String ADMIN_LINK_ADD = "/admin/meta/add.do";
    /** 修改友链信息 */
    public static final String ADMIN_LINK_UPDATE = "/admin/meta/update.do";
    /** 删除友链信息 */
    public static final String ADMIN_LINK_DELETE = "/admin/meta/delete.do";

    /** 查询评论 */
    public static final String ADMIN_COMMENT_QUERY = "/admin/comment/query.do";
    /** 审核评论 */
    public static final String ADMIN_COMMENT_REVIEW = "/admin/comment/review.do";
    /** 删除评论 */
    public static final String ADMIN_COMMENT_DELETE = "/admin/comment/delete.do";

    /** 查询日志信息 */
    public static final String ADMIN_LOG_QUERY = "/admin/log/query.do";

    /** 博客主页 */
    public static final String INDEX = "/index";

    /** 查询文章 **/
    public static final String ARTICLE = "/article";
    /** 文章详情 */
    public static final String ARTICLE_DETAIL = "/article/detail";

    /** 添加评论 */
    public static final String COMMENT_ADD = "/comment/add";
}
