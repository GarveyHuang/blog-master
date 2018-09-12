package com.jax.blog.model;

/**
 * @ClassName ArticleType
 * @Description 文章类型
 * @Author huangjw
 * @Date 2018/09/12 22:34
 * @Version 1.0
 **/
public class ArticleType {
    /** id */
    private Integer id;

    /** 名称 */
    private String typeName;

    /** 排序 */
    private String orderNum;

    /** 描述 */
    private String description;

    /** 父级id */
    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
