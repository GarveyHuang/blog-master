package com.jax.blog.dao;

import com.jax.blog.model.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName LogDAO
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/13 14:33
 * @Version 1.0
 **/
@Mapper
public interface LogDAO {
    /**
     *  添加日志
     * @param log
     * @return
             */
    int addLog(Log log);

    /**
     * 删除日志
     * @param id
     * @return
     */
    int deleteLogById(@Param("id") Integer id);

    /**
     * 获取日志
     * @return
     */
    List<Log> getLogs();
}
