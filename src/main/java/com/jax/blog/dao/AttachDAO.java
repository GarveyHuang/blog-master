package com.jax.blog.dao;

import com.jax.blog.dto.AttachDto;
import com.jax.blog.model.Attach;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AttachDAO
 * @Description TODO
 * @Author huangjw
 * @Date 2018/09/13 20:55
 * @Version 1.0
 **/
@Mapper
public interface AttachDAO {
    /**
     * 添加单个附件信息
     * @param attach
     * @return
     */
    int addAttach(Attach attach);

    /**
     * 批量添加附件信息
     * @param list
     * @return
     */
    int batchAddAttach(List<Attach> list);

    /**
     * 根据主键编号删除附件信息
     * @param id
     * @return
     */
    int deleteAttAch(int id);

    /**
     * 更新附件信息
     * @param attach
     * @return
     */
    int updateAttAch(Attach attach);

    /**
     * 根据主键获取附件信息
     * @param id
     * @return
     */
    AttachDto getAttAchById(@Param("id") int id);

    /**
     * 获取所有的附件信息
     * @return
     */
    List<AttachDto> getAtts();

    /**
     * 查找附件的数量
     * @return
     */
    Long getAttsCount();
}
