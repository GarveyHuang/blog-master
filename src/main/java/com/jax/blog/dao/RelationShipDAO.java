package com.jax.blog.dao;

import com.jax.blog.model.RelationShip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName RelationShipDAO
 * @Description 中间表
 * @Author huangjw
 * @Date 2018/09/13 20:59
 * @Version 1.0
 **/
@Mapper
public interface RelationShipDAO {
    /**
     * 添加
     * @param relationShip
     * @return
     */
    int addRelationShip(RelationShip relationShip);

    /**
     * 根据文章编号和meta编号删除关联
     * @param aid
     * @param mid
     * @return
     */
    int deleteRelationShipById(@Param("aid") Integer aid, @Param("mid") Integer mid);

    /**
     * 根据文章编号删除关联
     * @param aid
     * @return
     */
    int deleteRelationShipByAid(@Param("aid") Integer aid);

    /**
     * 根据meta编号删除关联
     * @param mid
     * @return
     */
    int deleteRelationShipByMid(@Param("mid") Integer mid);

    /**
     * 更新
     * @param relationShip
     * @return
     */
    int updateRelationShip(RelationShip relationShip);

    /**
     * 根据文章主键获取关联
     * @param aid
     * @return
     */
    List<RelationShip> getRelationShipByAid(@Param("aid") Integer aid);

    /**
     * 根据meta编号获取关联
     * @param mid
     * @return
     */
    List<RelationShip> getRelationShipByMid(@Param("mid") Integer mid);

    /**
     * 获取数量
     * @param aid
     * @param mid
     * @return
     */
    Long getCountById(@Param("aid") Integer aid, @Param("mid") Integer mid);
}
