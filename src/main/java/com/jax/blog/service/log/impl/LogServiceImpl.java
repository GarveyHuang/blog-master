package com.jax.blog.service.log.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jax.blog.dao.LogDAO;
import com.jax.blog.model.Log;
import com.jax.blog.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName LogServiceImpl
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/13 10:55
 * @Version 1.0
 **/
@Service("logService")
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDAO logDAO;

    @Override
    public void addLog(String action, String parameter, String detail, String data, String ip, Integer authorId, String logLevel) {
        Log log = new Log();
        log.setAction(action);
        log.setParameter(parameter);
        log.setDetail(detail);
        log.setData(data);
        log.setIp(ip);
        log.setAuthorId(authorId);
        logDAO.addLog(log);
    }

    @Override
    public void deleteLogById(Integer id) {
        logDAO.deleteLogById(id);
    }

    @Override
    public PageInfo<Log> getLogs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Log> logs = logDAO.getLogs();
        PageInfo<Log> pageInfo = new PageInfo<>(logs);
        return pageInfo;
    }
}
