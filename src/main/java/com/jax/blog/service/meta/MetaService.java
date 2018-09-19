package com.jax.blog.service.meta;

import com.jax.blog.dto.MetaDto;
import com.jax.blog.dto.cond.MetaCond;
import com.jax.blog.model.Meta;

import java.util.List;

/**
 * @ClassName MetaService
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:31
 * @Version 1.0
 **/

public interface MetaService {
    /**
     * 添加项目
     * @param meta
     * @return
     */
    void addMeta(Meta meta);

    /**
     * 添加
     * @param type
     * @param name
     * @param slug
     * @param parent
     * @param mid
     */
    void saveMeta(String type, String name, String slug, Integer parent, Integer mid);



    /**
     * 批量添加
     * @param aid
     * @param names
     * @param type
     */
    void addMetas(Integer aid, String names, String type);



    /**
     * 添加或者更新
     * @param aid
     * @param name
     * @param type
     */
    void saveOrUpdate(Integer aid, String name, String type);

    /**
     * 删除项目
     * @param mid
     * @return
     */
    void deleteMetaById(Integer mid);

    /**
     * 更新项目
     * @param meta
     * @return
     */
    void updateMeta(Meta meta);

    /**
     * 根据编号获取项目
     * @param mid
     * @return
     */
    Meta getMetaById(Integer mid);

    /**
     * 获取所有的项目
     * @param metaCond 查询条件
     * @return
     */
    List<Meta> getMetas(MetaCond metaCond);

    /**
     * 根据类型查询项目列表，带项目下面的文章数
     * @param type
     * @param orderby
     * @param limit
     * @return
     */
    List<MetaDto> getMetaList(String type, String orderby, int limit);
}
