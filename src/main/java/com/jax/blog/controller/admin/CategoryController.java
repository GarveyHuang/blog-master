package com.jax.blog.controller.admin;

import com.jax.blog.constant.Types;
import com.jax.blog.constant.URLMapper;
import com.jax.blog.constant.WebConst;
import com.jax.blog.controller.BaseController;
import com.jax.blog.dto.MetaDto;
import com.jax.blog.exception.BusinessException;
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
 * @ClassName CategoryController
 * @Description 分类和标签
 * @Author huangjw
 * @Date 2018/9/14 12:17
 * @Version 1.0
 **/
@Controller
public class CategoryController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private MetaService metaService;

    @GetMapping(value = URLMapper.ADMIN_CATEGORY)
    public String categoryList(HttpServletRequest request) {
        List<MetaDto> categories = metaService.getMetaList(Types.CATEGORY.getType(), null, WebConst.MAX_POSTS);
        List<MetaDto> tags = metaService.getMetaList(Types.TAG.getType(), null, WebConst.MAX_POSTS);
        String nickname = this.getNickName(request);
        request.setAttribute("categories", categories);
        request.setAttribute("tags", tags);
        request.setAttribute("nickname", nickname);
        return "admin/category";
    }

    @ResponseBody
    @PostMapping(value = URLMapper.ADMIN_CATEGORY_SAVE)
    public APIResponse saveCategory(@RequestParam(name = "cname", required = true) String cname,
                                    @RequestParam(name = "slug", required = false) String slug,
                                    @RequestParam(name = "parent", required = false) Integer parent,
                                    @RequestParam(name = "mid", required = true) Integer mid) {
        try {
            metaService.saveMeta(Types.CATEGORY.getType(), cname, slug, parent, mid);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = "保存分类失败";
            if(e instanceof BusinessException) {
                BusinessException ex = (BusinessException) e;
                msg = ex.getErrorCode();
            }
            LOGGER.error(msg, e);
            return APIResponse.fail(msg);
        }
        return APIResponse.success();
    }

    @ResponseBody
    @PostMapping(value = URLMapper.ADMIN_CATEGORY_DELETE)
    public APIResponse deleteCategory(@RequestParam(name = "mid", required = true) Integer mid) {
        try {
            metaService.deleteMetaById(mid);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());

            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }
}
