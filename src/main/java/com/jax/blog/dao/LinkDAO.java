package com.jax.blog.dao;

import com.jax.blog.model.Link;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName LinkDAO
 * @Description TODO
 * @Author huangjw
 * @Date 2018/09/11 22:53
 * @Version 1.0
 **/
@Mapper
public interface LinkDAO {
    /**
     * 查询所有友情链接
     * @return
     * @throws Exception
     */
    List<Link> queryLinkAll() throws Exception;

    /**
     * 添加友情链接
     * @return
     * @throws Exception
     */
    int addLink(Link link) throws Exception;

    /**
     * 更新友情链接
     * @param link
     * @return
     * @throws Exception
     */
    int updateLink(Link link) throws Exception;

    /**
     * 删除友情链接
     * @param id
     * @return
     * @throws Exception
     */
    int deleteLink(Integer id) throws Exception;
}
