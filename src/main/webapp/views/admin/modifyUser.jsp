<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.jax.blog.model.User" %>
<%@ page import="com.jax.blog.constant.WebConst" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>修改个人信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/demo/demo.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/plugins/ueditor1_4_3_3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/plugins/ueditor1_4_3_3/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/plugins/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>

    <script type="text/javascript">
        function submitData() {

            $("#fm").form("submit",{
                url: "${pageContext.request.contextPath}/admin/user/modify.do",
                onSubmit: function() {
                    var profile = UE.getEditor("profile").getContent();
                    $("#pf").val(profile); //将UEditor编辑器中的内容放到隐藏域中提交到后台
                    return $(this).form("validate");
                }, //进行验证，通过才让提交
                success: function(result) {
                    var result = eval("(" + result + ")"); //将json格式的result转换成js对象
                    if(result.success) {
                        $.messager.alert("系统提示", "个人信息更新成功");
                    } else {
                        $.messager.alert("系统提示", "个人信息更新失败");
                        return;
                    }
                }
            });
        }
    </script>
</head>
<%
    User user = (User) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
%>
<body style="margin: 5px; height:100%; font-family: 'microsoft yahei';">
    <div id="p" class="easyui-panel" title="修改个人信息" style="padding: 5px; width: auto;">
        <form id="modifyForm" action="" method="post" enctype="multipart/form-data">
            <table cellspacing="20px">
                <tr>
                    <td width="80px">用户名：</td>
                    <td>
                        <input type="hidden" id="uid" name="uid" value="<%=user.getUid()%>"/>
                        <input type="text" id="username" name="username" style="width:200px; border: 0;" readonly="readonly"
                               value="<%=user.getUsername()%>"/>
                    </td>
                </tr>
                <tr>
                    <td>昵&nbsp;&nbsp;&nbsp;称：</td>
                    <td>
                        <input type="text" id="nickname" name="nickname" style="width:200px"
                               class="easyui-validatebox" value="<%=user.getNickName()%>"/>
                    </td>
                </tr>
                <tr>
                    <td>邮&nbsp;&nbsp;&nbsp;箱：</td>
                    <td>
                        <input type="text" id="email" name="email" style="width:400px" class="easyui-validatebox"
                               value="<%=null==user.getEmail() ? "" : user.getEmail()%>" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td>主&nbsp;&nbsp;&nbsp;页：</td>
                    <td>
                        <input type="text" id="homeUrl" name="homeUrl" style="width:400px" class="easyui-validatebox"
                               value="<%=null==user.getHomeUrl() ? "" : user.getHomeUrl()%>" />
                    </td>
                </tr>
                <tr>
                    <td>签&nbsp;&nbsp;&nbsp;名：</td>
                    <td>
                        <input type="text" id="sign" name="sign" style="width:400px" class="easyui-validatebox"
                               value="<%=null==user.getSign() ? "" : user.getSign()%>" />
                    </td>
                </tr>
                <tr>
                    <td>头&nbsp;&nbsp;&nbsp;像：</td>
                    <td>
                        <input type="file" id="imageFile" name="imageFile"/>
                    </td>
                </tr>
                <tr>
                    <td>简&nbsp;&nbsp;&nbsp;介：</td>
                    <td>
                        <%--<script id="profile" type="text/plain" style="width:80%; height:500px;"></script>--%>
                        <input type="hidden" id="profile" name="profile" /> <%-- UEditor不能作为表单的一部分提交，所以用这种隐藏域的方式 --%>
                    </td>
                </tr>
                <tr><td></td>
                    <td><a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">提交</a></td>
                </tr>
            </table>
        </form>
    </div>

    <%-- 实例化编辑器 --%>
    <script type="text/javascript">
        var ue = UE.getEditor('profile');
        ue.addListener("ready", function(){
            //通过UE自己封装的ajax请求数据
            UE.ajax.request("${pageContext.request.contextPath}/admin/blogger/findBlogger.do",
                    {
                        method: "post",
                        async: false,
                        data: {},
                        onsuccess: function(result) { //
                            result = eval("(" + result.responseText + ")");
                            $("#nickname").val(result.nickname);
                            $("#sign").val(result.sign);
                            UE.getEditor('profile').setContent(result.profile);
                        }
                    });
        });
    </script>
</body>
</html>