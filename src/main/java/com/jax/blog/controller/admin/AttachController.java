package com.jax.blog.controller.admin;
/**
 * Created by huangjw on 2018/9/14.
 */

import com.github.pagehelper.PageInfo;
import com.jax.blog.api.QiniuCloudService;
import com.jax.blog.constant.ErrorConstant;
import com.jax.blog.constant.Types;
import com.jax.blog.constant.URLMapper;
import com.jax.blog.constant.WebConst;
import com.jax.blog.controller.BaseController;
import com.jax.blog.dto.AttachDto;
import com.jax.blog.exception.BusinessException;
import com.jax.blog.model.Attach;
import com.jax.blog.model.User;
import com.jax.blog.service.attach.AttachService;
import com.jax.blog.utils.Commons;
import com.jax.blog.utils.TaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName AttachController
 * @Description TODO
 * @Author huangjw
 * @Date 2018/9/14 14:12
 * @Version 1.0
 **/
@Controller
public class AttachController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttachController.class);

    public static final String CLASSPATH = TaleUtils.getUploadFilePath();

    @Autowired
    AttachService attachService;

    @GetMapping(value = URLMapper.ADMIN_ATTACH)
    public String attachList(HttpServletRequest request,
                             @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                             @RequestParam(name = "limit", required = false, defaultValue = "15") int limit) {
        PageInfo<AttachDto> atts = attachService.getAtts(page, limit);
        String nickname = this.getNickName(request);
        request.setAttribute("atts", atts);
        request.setAttribute(Types.ATTACH_URL.getType(), Commons.site_option(Types.ATTACH_URL.getType(), Commons.site_url()));
        request.setAttribute("max_file_size", WebConst.MAX_FILE_SIZE / 1024);
        request.setAttribute("nickname", nickname);
        return URLMapper.ADMIN_ATTACH;
    }

    @PostMapping(value = URLMapper.ADMIN_ATTACH_UPLOAD)
    public void fileUploadToTencentCloud(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam(name = "editormd-image-file", required = true) MultipartFile file) {
        try { //文件上传
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");

            String fileName = TaleUtils.getFileKey(file.getOriginalFilename()).replaceFirst("/", "");

            QiniuCloudService.upload(file, fileName);
            Attach attach = new Attach();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
            attach.setAuthorId(user.getUid());
            attach.setFtype(TaleUtils.isImage(file.getInputStream()) ? Types.IMAGE.getType() : Types.FILE.getType());
            attach.setFname(fileName);
            attach.setFurl(QiniuCloudService.QINIU_UPLOAD_SITE + fileName);
            attachService.addAttach(attach);
            response.getWriter().write( "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"" + attach.getFurl() + "\"}" );
        } catch (IOException e) {
            e.printStackTrace();
            try {
                response.getWriter().write("{\"success\": 0}");
            } catch (IOException e1) {
                throw BusinessException.withErrorCode(ErrorConstant.Att.UPLOAD_FILE_FAIL)
                        .withErrorMessageArguments(e.getMessage());
            }
            throw BusinessException.withErrorCode(ErrorConstant.Att.UPLOAD_FILE_FAIL)
                    .withErrorMessageArguments(e.getMessage());
        }
    }
}
