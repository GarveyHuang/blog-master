package com.jax.blog.model;

/**
 * @ClassName RelationShip
 * @Description 文章关联信息表
 * @Author huangjw
 * @Date 2018/09/13 20:52
 * @Version 1.0
 **/
public class RelationShip {
    /** 文章主键编号 */
    private Integer aid;
    /** 项目编号 */
    private Integer mid;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}
