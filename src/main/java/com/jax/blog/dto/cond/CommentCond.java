package com.jax.blog.dto.cond;

/**
 * @ClassName CommentCond
 * @Description 评论的查找参数
 * @Author huangjw
 * @Date 2018/9/13 18:01
 * @Version 1.0
 **/

public class CommentCond {
    /** 状态 */
    private String status;
    /** 开始时间戳 */
    private Integer startTime;
    /** 结束时间戳 */
    private Integer endTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
}
