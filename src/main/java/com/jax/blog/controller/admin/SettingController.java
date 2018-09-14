package com.jax.blog.controller.admin;

import com.jax.blog.constant.LogActions;
import com.jax.blog.constant.URLMapper;
import com.jax.blog.constant.WebConst;
import com.jax.blog.controller.BaseController;
import com.jax.blog.model.Options;
import com.jax.blog.service.log.LogService;
import com.jax.blog.service.option.OptionService;
import com.jax.blog.utils.APIResponse;
import com.jax.blog.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SystemAdminController
 * @Description 系统设置
 * @Author huangjw
 * @Date 2018/9/5 16:42
 * @Version 1.0
 **/
@Controller
public class SettingController extends BaseController {
    @Autowired
    private OptionService optionService;

    @Autowired
    private LogService logService;

    @GetMapping(value = URLMapper.ADMIN_SETTING)
    public String setting(HttpServletRequest request) {
        List<Options> optionsList = optionService.getOptions();
        Map<String, String> map = new HashMap<>();
        optionsList.forEach((option) -> {
            map.put(option.getName(), option.getValue());
        });
        request.setAttribute("options", map);
        return URLMapper.ADMIN_SETTING;
    }

    @ResponseBody
    @PostMapping(value = URLMapper.ADMIN_SETTING)
    public APIResponse saveSetting(HttpServletRequest request) {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Map<String, String> querys = new HashMap<>();
            parameterMap.forEach((key, value) -> {
                querys.put(key, join(value));
            });
            optionService.saveOptions(querys);
            WebConst.initConfig = querys;

            logService.addLog(LogActions.SYS_SETTING.getAction(), null, null, GsonUtils.toJsonString(querys), request.getRemoteAddr(), this.getUid(request), "info");
            return APIResponse.success();
        } catch (Exception e) {
            String msg = "保存设置失败";
            return APIResponse.fail(msg);
        }
    }
}
