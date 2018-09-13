package com.jax.blog.service.log;

import com.github.pagehelper.PageInfo;
import com.jax.blog.model.Log;

/**
 * @ClassName LogService
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/13 10:55
 * @Version 1.0
 **/

public interface LogService {
    /**
     * 添加
     * @param action
     * @param parameter
     * @param detail
     * @param data
     * @param ip
     * @param authorId
     * @param logLevel
     */
    void addLog(String action, String parameter, String detail, String data, String ip, Integer authorId, String logLevel);

    /**
     * 删除日志
     * @param id
     * @return
     */
    void deleteLogById(Integer id);

    /**
     * 获取日志
     * @return
     */
    PageInfo<Log> getLogs(int pageNum, int pageSize);
}
