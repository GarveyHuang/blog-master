package com.jax.blog.dao;

import com.jax.blog.dto.MetaDto;
import com.jax.blog.dto.cond.MetaCond;
import com.jax.blog.model.Meta;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MetaDAO
 * @Description TODO
 * @Author huangjw
 * @Date 2018/09/11 22:57
 * @Version 1.0
 **/
@Mapper
public interface MetaDAO {
    /**
     * 添加项目
     * @param meta
     * @return
     */
    int addMeta(Meta meta);

    /**
     * 删除项目
     * @param mid
     * @return
     */
    int deleteMetaById(@Param("mid") Integer mid);

    /**
     * 更新项目
     * @param meta
     * @return
     */
    int updateMeta(Meta meta);

    /**
     * 根据编号获取项目
     * @param mid
     * @return
     */
    Meta getMetaById(@Param("mid") Integer mid);


    /**
     * 根据条件查询
     * @param metaCond
     * @return
     */
    List<Meta> getMetasByCond(MetaCond metaCond);

    /**
     * 根据类型获取meta数量
     * @param type
     * @return
     */
    Long getMetasCountByType(@Param("type") String type);

    /**
     * 根据sql查询
     * @param paraMap
     * @return
     */
    List<MetaDto> selectFromSql(Map<String, Object> paraMap);
}
