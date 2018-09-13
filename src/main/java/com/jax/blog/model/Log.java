package com.jax.blog.model;

import java.io.Serializable;

/**
 * @ClassName Log
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/13 11:35
 * @Version 1.0
 **/

public class Log implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 日志主键 */
    private Integer id;

    /** 产生的动作 */
    private String action;

    /** 产生的数据 */
    private String data;

    /** 传入的参数 */
    private String parameter;

    /** 详情 */
    private String detail;

    /** 发生人id */
    private Integer authorId;

    /** 日志产生的ip */
    private String ip;

    /** 日志创建时间 */
    private Integer created;

    /** 日志等级 */
    private String logLevel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
