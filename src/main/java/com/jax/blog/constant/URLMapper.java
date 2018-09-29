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
    /** 修改密码 */
    public static final String ADMIN_PASSWORD = "/admin/password";

    /** 文章管理页 */
    public static final String ADMIN_ARTICLE = "/admin/article";
    /** 发布文章 */
    public static final String ADMIN_ARTICLE_PUBLISH = "/admin/article/publish";
    /** 修改文章 */
    public static final String ADMIN_ARTICLE_MODIFY = "/admin/article/modify";
    /** 删除文章 */
    public static final String ADMIN_ARTICLE_DELETE = "/admin/article/delete";

    /** 评论管理页 */
    public static final String ADMIN_COMMENT = "/admin/comment";
    /** 删除评论 */
    public static final String ADMIN_COMMENT_DELETE = "/admin/comment/delete";
    /** 更改评论状态 */
    public static final String ADMIN_COMMENT_STATUS = "/admin/comment/status";

    /** 系统设置页 */
    public static final String ADMIN_SETTING = "/admin/setting";

    /** 友链管理页 */
    public static final String ADMIN_LINKS = "/admin/links";
    /** 新增友链 */
    public static final String ADMIN_LINKS_SAVE = "/admin/links/save";
    /** 删除友链 */
    public static final String ADMIN_LINKS_DELETE = "/admin/links/delete";

    /** 标签和分类 */
    public static final String ADMIN_CATEGORY = "/admin/category";
    /** 保存分类 */
    public static final String ADMIN_CATEGORY_SAVE = "/admin/category/save";
    /** 删除分类 */
    public static final String ADMIN_CATEGORY_DELETE = "/admin/category/delete";

    /** 附件管理页 */
    public static final String ADMIN_ATTACH = "/admin/attach";
    /** 附件上传 */
    public static final String ADMIN_ATTACH_UPLOAD = "/admin/attach/upload";

    /** 博客主页 */
    public static final String BLOG = "/blog";
    public static final String BLOG_INDEX = "/blog/index";

    /** 文章内容页 **/
    public static final String BLOG_ARTICLE = "/blog/article/{aid}";
    /** 新增评论 **/
    public static final String BLOG_COMMENT_ADD = "/blog/comment/add";

    /** 归档页 */
    public static final String BLOG_ARCHIVES = "/blog/archives";
    /** 文章详情 */
    public static final String ARTICLE_DETAIL = "/article/detail";

    /** 添加评论 */
    public static final String COMMENT_ADD = "/comment/add";
}
