package com.jax.blog.controller.admin;

import com.jax.blog.constant.ErrorConstant;
import com.jax.blog.constant.Types;
import com.jax.blog.constant.URLMapper;
import com.jax.blog.controller.BaseController;
import com.jax.blog.dto.cond.MetaCond;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.model.Meta;
import com.jax.blog.service.meta.MetaService;
import com.jax.blog.utils.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName LinksController
 * @Description 友链接口
 * @Author huangjw
 * @Date 2018/9/14 11:55
 * @Version 1.0
 **/
@Controller
public class LinksController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinksController.class);

    @Autowired
    private MetaService metaService;

    @GetMapping(value = URLMapper.ADMIN_LINKS)
    public String linksList(HttpServletRequest request) {
        MetaCond metaCond = new MetaCond();
        metaCond.setType(Types.LINK.getType());
        List<Meta> metas = metaService.getMetas(metaCond);
        String nickname = this.getNickName(request);
        request.setAttribute("links", metas);
        request.setAttribute("nickname", nickname);
        return "admin/links";
    }

    @ResponseBody
    @PostMapping(value = URLMapper.ADMIN_LINKS_SAVE)
    public APIResponse saveLink(
            @RequestParam(name = "title", required = true) String title,
            @RequestParam(name = "url", required = true) String url,
            @RequestParam(name = "logo", required = false) String logo,
            @RequestParam(name = "mid", required = false) Integer mid,
            @RequestParam(name = "orderNum", required = false, defaultValue = "0") int orderNum) {
        try {
            Meta meta = new Meta();
            meta.setName(title);
            meta.setSlug(url);
            meta.setDescription(logo);
            meta.setOrderNum(orderNum);
            meta.setType(Types.LINK.getType());
            if(null != mid) {
                meta.setMid(mid);
            } else {
                metaService.addMeta(meta);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw BusinessException.withErrorCode(ErrorConstant.Meta.ADD_META_FAIL);
        }
        return APIResponse.success();
    }

    @ResponseBody
    @PostMapping(value = URLMapper.ADMIN_LINKS_DELETE)
    public APIResponse deleteLink(@RequestParam(name = "mid", required = true) int mid) {
        try {
            metaService.deleteMetaById(mid);
        } catch (Exception e) {
            e.printStackTrace();
            throw BusinessException.withErrorCode(ErrorConstant.Meta.DELETE_META_FAIL);
        }
        return APIResponse.success();
    }
}
