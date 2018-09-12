package com.jax.blog.dao;

import com.jax.blog.model.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName TagDAO
 * @Description TODO
 * @Author huangjw
 * @Date 2018/09/12 23:07
 * @Version 1.0
 **/
@Mapper
public interface TagDAO {
    /**
     * 查询标签信息
     * @return
     * @throws Exception
     */
    List<Tag> queryTagList() throws Exception;

    /**
     * 添加标签
     * @param tag
     * @return
     * @throws Exception
     */
    int addTag(Tag tag) throws Exception;

    /**
     * 删除标签
     * @param tagId
     * @return
     * @throws Exception
     */
    int deleteTag(Integer tagId) throws Exception;
}
