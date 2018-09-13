package com.jax.blog.service.option.impl;

import com.jax.blog.constant.ErrorConstant;
import com.jax.blog.dao.OptionDAO;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.model.Options;
import com.jax.blog.service.option.OptionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OptionServiceImpl
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/13 10:26
 * @Version 1.0
 **/
@Service("optionService")
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionDAO optionDao;

    @Override
    @CacheEvict(value={"optionsCache","optionCache"},allEntries=true,beforeInvocation=true)
    public void deleteOptionByName(String name) {
        if(StringUtils.isBlank(name))
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        optionDao.deleteOptionByName(name);
    }

    @Override
    @Transactional
    @CacheEvict(value={"optionsCache","optionCache"},allEntries=true,beforeInvocation=true)
    public void updateOptionByName(String name, String value) {
        if(StringUtils.isBlank(name))
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        Options option = new Options();
        option.setName(name);
        option.setValue(value);
        optionDao.updateOptionByName(option);
    }

    @Override
    @Transactional
    @CacheEvict(value={"optionsCache","optionCache"},allEntries=true,beforeInvocation=true)
    public void saveOptions(Map<String, String> options) {
        if (null != options && !options.isEmpty()) {
            options.forEach(this::updateOptionByName);
        }
    }

    @Override
    @Cacheable(value = "optionCache", key = "'optionByName_' + #p0")
    public Options getOptionByName(String name) {
        if(StringUtils.isBlank(name))
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        return optionDao.getOptionByName(name);
    }

    @Override
    @Cacheable(value = "optionsCache", key = "'options_'")
    public List<Options> getOptions() {
        return optionDao.getOptions();
    }
}
