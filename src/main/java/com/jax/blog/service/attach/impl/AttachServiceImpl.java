package com.jax.blog.service.attach.impl;

import com.github.pagehelper.PageInfo;
import com.jax.blog.dto.AttachDto;
import com.jax.blog.model.Attach;
import com.jax.blog.service.attach.AttachService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AttachServiceImpl
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:37
 * @Version 1.0
 **/
@Service("attachService")
public class AttachServiceImpl implements AttachService {
    @Override
    public void addAttach(Attach attach) {

    }

    @Override
    public void batchAddAttach(List<Attach> list) {

    }

    @Override
    public void deleteAttach(Integer id) {

    }

    @Override
    public void updateAttach(Attach attach) {

    }

    @Override
    public AttachDto getAttachById(Integer id) {
        return null;
    }

    @Override
    public PageInfo<AttachDto> getAtts(int pageNum, int pageSize) {
        return null;
    }
}
