package com.jax.blog.service.meta.impl;

import com.jax.blog.constant.ErrorConstant;
import com.jax.blog.constant.Types;
import com.jax.blog.constant.WebConst;
import com.jax.blog.dao.MetaDAO;
import com.jax.blog.dao.RelationShipDAO;
import com.jax.blog.dto.MetaDto;
import com.jax.blog.dto.cond.MetaCond;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.model.Article;
import com.jax.blog.model.Meta;
import com.jax.blog.model.RelationShip;
import com.jax.blog.service.article.ArticleService;
import com.jax.blog.service.meta.MetaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MetaServiceImpl
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/5 16:36
 * @Version 1.0
 **/
@Service("metaService")
public class MetaServiceImpl implements MetaService {
    @Autowired
    private MetaDAO metaDAO;

    @Autowired
    private RelationShipDAO relationShipDAO;

    @Autowired
    private ArticleService articleService;

    @Override
    @Transactional
    @CacheEvict(value = {"metaCaches", "metaCache"}, allEntries = true, beforeInvocation = true)
    public void addMeta(Meta meta) {
        if (null == meta) {
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        metaDAO.addMeta(meta);
    }

    @Override
    @CacheEvict(value = {"metaCaches", "metaCache"}, allEntries = true, beforeInvocation = true)
    public void saveMeta(String type, String name, String slug, Integer parent, Integer mid) {
        if (mid != null) {
            if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(name)) {
                Meta metaDomain = new Meta();
                metaDomain.setName(name);
                metaDomain.setSlug(slug);
                metaDomain.setParent(parent);
                Meta meta = metaDAO.getMetaById(mid);
                if (null != meta) {
                    metaDomain.setMid(mid);
                }
                metaDAO.updateMeta(metaDomain);
                //更新原有的文章分类
                articleService.updateCategory(meta.getName(), name);
            }
        } else {
            if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(name)) {
                MetaCond metaCond = new MetaCond();
                metaCond.setName(name);
                metaCond.setType(type);
                List<Meta> metas = metaDAO.getMetasByCond(metaCond);
                if (null == metas || metas.size() == 0) {
                    Meta metaDomain = new Meta();
                    metaDomain.setName(name);
                    metaDomain.setSlug(slug);
                    metaDomain.setParent(parent);
                    metaDomain.setType(type);
                    metaDAO.addMeta(metaDomain);
                } else {
                    throw BusinessException.withErrorCode(ErrorConstant.Meta.META_IS_EXIST);
                }
            }
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = {"metaCaches","metaCache"}, allEntries = true, beforeInvocation = true)
    public void addMetas(Integer aid, String names, String type) {
        if (null == aid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);

        if (StringUtils.isNotBlank(names) && StringUtils.isNotBlank(type)) {
            String[] nameArr = StringUtils.split(names, ",");
            for (String name : nameArr) {
                this.saveOrUpdate(aid, name, type);
            }
        }
    }

    @Override
    @CacheEvict(value = {"metaCaches","metaCache"}, allEntries = true, beforeInvocation = true)
    public void saveOrUpdate(Integer aid, String name, String type) {
        MetaCond metaCond = new MetaCond();
        metaCond.setName(name);
        metaCond.setType(type);
        List<Meta> metas = this.getMetas(metaCond);

        int mid;
        Meta metaDomain;
        if (metas.size() == 1){
            Meta meta = metas.get(0);
            mid = meta.getMid();
        }else if (metas.size() > 1){
            throw BusinessException.withErrorCode(ErrorConstant.Meta.NOT_ONE_RESULT);
        } else {
            metaDomain = new Meta();
            metaDomain.setSlug(name);
            metaDomain.setName(name);
            metaDomain.setType(type);
            this.addMeta(metaDomain);
            mid = metaDomain.getMid();
        }
        if (mid != 0){
            Long count = relationShipDAO.getCountById(aid, mid);
            if (count == 0){
                RelationShip relationShip = new RelationShip();
                relationShip.setAid(aid);
                relationShip.setMid(mid);
                relationShipDAO.addRelationShip(relationShip);
            }
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = {"metaCaches","metaCache"}, allEntries = true, beforeInvocation = true)
    public void deleteMetaById(Integer mid) {
        if (null == mid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);

        Meta meta = metaDAO.getMetaById(mid);
        if (null != meta){
            String type = meta.getType();
            String name = meta.getName();
            metaDAO.deleteMetaById(mid);
            //需要把相关的数据删除
            List<RelationShip> relationShips = relationShipDAO.getRelationShipByMid(mid);
            if (null != relationShips && relationShips.size() > 0){
                for (RelationShip relationShip : relationShips) {
                    Article article = articleService.getArticleById(relationShip.getAid());
                    if (null != article){
                        Article temp = new Article();
                        temp.setAid(relationShip.getAid());
                        if (type.equals(Types.CATEGORY.getType())) {
                            temp.setCategories(reMeta(name, article.getCategories()));
                        }
                        if (type.equals(Types.TAG.getType())) {
                            temp.setTags(reMeta(name, article.getTags()));
                        }
                        //将删除的资源去除
                        articleService.updateArticleById(temp);
                    }
                }
                relationShipDAO.deleteRelationShipByMid(mid);
            }
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = {"metaCaches","metaCache"}, allEntries = true, beforeInvocation = true)
    public void updateMeta(Meta meta) {
        if (null == meta || null == meta.getMid())
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        metaDAO.updateMeta(meta);
    }

    @Override
    @Cacheable(value = "metaCache", key = "'metaById_' + #p0")
    public Meta getMetaById(Integer mid) {
        if (null == mid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        return metaDAO.getMetaById(mid);
    }

    @Override
    @Cacheable(value = "metaCache", key = "'metas_' + #p0")
    public List<Meta> getMetas(MetaCond metaCond) {
        return metaDAO.getMetasByCond(metaCond);
    }

    @Override
    @Cacheable(value = "metaCaches", key = "'metaList_' + #p0")
    public List<MetaDto> getMetaList(String type, String orderby, int limit) {
        if (StringUtils.isNotBlank(type)){
            if (StringUtils.isBlank(orderby)) {
                orderby = "count desc, a.mid desc";
            }
            if (limit < 1 || limit > WebConst.MAX_POSTS) {
                limit = 10;
            }
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("type", type);
            paraMap.put("order", orderby);
            paraMap.put("limit", limit);
            return metaDAO.selectFromSql(paraMap);
        }
        return null;
    }

    private String reMeta(String name, String metas) {
        String[] ms = StringUtils.split(metas, ",");
        StringBuilder sbuf = new StringBuilder();
        for (String m : ms) {
            if (!name.equals(m)) {
                sbuf.append(",").append(m);
            }
        }
        if (sbuf.length() > 0) {
            return sbuf.substring(1);
        }
        return "";
    }
}
