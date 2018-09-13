package com.jax.blog.model;

/**
 * @ClassName Attach
 * @Description 网站图片文件相关
 * @Author huangjw
 * @Date 2018/9/5 16:56
 * @Version 1.0
 **/

public class Attach {
    /** id */
    private Integer fid;

    /** 文件名称 */
    private String fname;

    /** 文件类型 */
    private String ftype;

    /** 文件的地址 */
    private String furl;

    /** 创建人id */
    private Integer authorId;

    /** 创建的时间戳 */
    private Integer created;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }
}
