package com.jax.blog.dao;

import com.jax.blog.model.ArticleType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName ArticleTypeDAO
 * @Description TODO
 * @Author huangjw
 * @Date 2018/09/11 22:57
 * @Version 1.0
 **/
@Mapper
public interface ArticleTypeDAO {
    /**
     * 查询文章类型
     * @return
     * @throws Exception
     */
    List<ArticleType> queryArticleTypeList() throws Exception;

    /**
     * 添加文章类型
     * @return
     * @throws Exception
     */
    int addArticleType(ArticleType articleType) throws Exception;

    /**
     * 删除文章类型
     * @param typeId
     * @return
     * @throws Exception
     */
    int deleteArticleType(Integer typeId) throws Exception;
}
