package com.jax.blog.service.attach;

import com.github.pagehelper.PageInfo;
import com.jax.blog.dto.AttachDto;
import com.jax.blog.model.Attach;

import java.util.List;

/**
 * @ClassName AttachService
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:32
 * @Version 1.0
 **/

public interface AttachService {
    /**
     * 添加单个附件信息
     * @param attach
     * @return
     */
    void addAttach(Attach attach);

    /**
     * 批量添加附件信息
     * @param list
     * @return
     */
    void batchAddAttach(List<Attach> list);

    /**
     * 根据主键编号删除附件信息
     * @param id
     * @return
     */
    void deleteAttach(Integer id);

    /**
     * 更新附件信息
     * @param attach
     * @return
     */
    void updateAttach(Attach attach);

    /**
     * 根据主键获取附件信息
     * @param id
     * @return
     */
    AttachDto getAttachById(Integer id);

    /**
     * 获取所有的附件信息
     * @return
     */
    PageInfo<AttachDto> getAtts(int pageNum, int pageSize);
}
