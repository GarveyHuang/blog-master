package com.jax.blog.constant;

/**
 * @ClassName Types
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/13 10:42
 * @Version 1.0
 **/

public enum Types {
    TAG("tag"),
    CATEGORY("category"),
    ARTICLE("article"),
    MESSAGE("message"),
    COMMENT("comment"),
    PUBLISH("publish"),
    PAGE("page"),
    DRAFT("draft"),
    LINK("meta"),
    IMAGE("image"),
    FILE("file"),
    CSRF_TOKEN("csrf_token"),
    COMMENTS_FREQUENCY("comments:frequency"),
    PHOTO("photo"),

    /**
     * 附件存放的URL，默认为网站地址，如集成第三方则为第三方CDN域名
     */
    ATTACH_URL("attach_url"),

    /**
     * 网站要过滤，禁止访问的ip列表
     */
    BLOCK_IPS("site_block_ips");


    private String type;

    public java.lang.String getType() {
        return type;
    }

    public void setType(java.lang.String type) {
        this.type = type;
    }

    Types(java.lang.String type) {
        this.type = type;
    }
}
