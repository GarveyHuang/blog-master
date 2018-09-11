<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>博文管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        function formatTitle(val, row) {
            return "<a target='_blank' href='${pageContext.request.contextPath}/admin/articles/"+row.id+".html'>"+val+"</a>";
        }

        function formatArticleType(val, row) {
            return val.typeName;
        }

        function searchArticle() {
            $("#dg").datagrid("load", {
                "title":$("#s_title").val()
            });
        }

        function deleteArticle() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if(selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据");
                return;
            }
            var idsStr = [];
            for(var i = 0; i < selectedRows.length; i++) {
                idsStr.push(selectedRows[i].id);
            }
            var ids = idsStr.join(","); //1,2,3,4
            $.messager.confirm("系统提示", "<font color=red>您确定要删除选中的" + selectedRows.length + "条数据么？</font>", function(r) {
                if(r) {
                    $.post("${pageContext.request.contextPath}/admin/article/delete.do",
                            {ids: ids}, function(result){
                                if(result.success) {
                                    $.messager.alert("系统提示", "数据删除成功！");
                                    $("#dg").datagrid("reload");
                                } else {
                                    $.messager.alert("系统提示", "数据删除失败！");
                                }
                            }, "json");
                }
            });
        }

        function openArticleModifyTab() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if(selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一个要修改的博客");
                return;
            }
            var row = selectedRows[0];
            window.parent.openTab("修改博客","modifyArticle.jsp?aid=" + row.id, "icon-writeblog");
        }

        function reload() {
            $("#dg").datagrid("reload");
        }
    </script>
</head>
<body style="margin: 1px; font-family: microsoft yahei">
<table id="dg" title="博文管理" class="easyui-datagrid" fitColumns="true" pagination="true"
       url="${pageContext.request.contextPath}/admin/article/queryAll.do" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="aid" width="20" align="center">编号</th>
        <th field="title" width="200" formatter="formatTitle">标题</th>
        <th field="created" width="100" align="center">发布时间</th>
        <th field="type" width="100" align="center" formatter="formatArticleType">博客类型</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        &nbsp;标题&nbsp;<input type="text" id="s_title" size="20" onkeydown="if(event.keyCode==13) searchArticle()">
        <a href="javascript:searchArticle()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
        <a href="javascript:deleteArticle()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        <a href="javascript:openArticleModifyTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:reload()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
    </div>
</div>
</body>
</html>