<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <title>博文管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        function formatTitle(val, row) {
            return "<a target='_blank' href='${pageContext.request.contextPath}/admin/articles/" + row.id + ".html'>" + val + "</a>";
        }

        function formatCreated(val) {
            var date = new Date();
            date.setTime(val * 1000);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? ('0' + m) : m;
            var d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            var h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            var minute = date.getMinutes();
            var second = date.getSeconds();
            minute = minute < 10 ? ('0' + minute) : minute;
            second = second < 10 ? ('0' + second) : second;
            return y + '-' + m + '-' + d +' '+ h +':'+ minute + ':' + second;
        }

        function operate(val, row) {
            var str = "<a href='javascript:openArticleModifyTab(" + row.aid + ")' name='modify' class='easyui-linkbutton'>修改</a>" +
                    "&nbsp;&nbsp;&nbsp;<a href='javascript:deleteArticle(" + row.aid + ")' name='delete' class='easyui-linkbutton'>删除</a>";
            return str;
        }

        function searchArticle() {
            $("#dg").datagrid("load", {
                "title": $("#s_title").val()
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

        function openArticleModifyTab(id) {
            /*var selectedRows = $("#dg").datagrid("getSelections");
            if(selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一个要修改的文章");
                return;
            }
            var row = selectedRows[0];*/
            window.parent.openTab("修改文章", "modifyArticle.jsp?id=" + id, "icon-writeblog");
        }

        function reload() {
            $("#dg").datagrid("reload");
        }
    </script>
</head>
<body style="margin: 1px; font-family: microsoft yahei;">
<div style="width: 100%; height: auto; min-width: 1200px;">
    <div id="tb">
        <div>
            &nbsp;标题&nbsp;<input type="text" id="s_title" size="20" onkeydown="if(event.keyCode==13) searchArticle()">
            <a href="javascript:searchArticle()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
            <a href="javascript:deleteArticle()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
            <!--<a href="javascript:openArticleModifyTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>-->
            <a href="javascript:reload()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
        </div>
    </div>

    <table id="dg" title="博文管理" class="easyui-datagrid" width="100%" style="height: auto;"
           data-options="rownumbers:true, pagination:true, rownumbers:true, striped:true, url:'${pageContext.request.contextPath}/admin/article/queryAll.do', toolbar:'#tb'">
        <thead>
        <tr>
            <th data-options="field:'cb', checkbox:true, align:'center'"></th>
            <th data-options="field:'operate', align:'center', width:$(this).width()*0.1, formatter:operate">操作</th>
            <th data-options="field:'aid', width:20, align:'center'">ID</th>
            <th data-options="field:'title', width:200, formatter:formatTitle">标题</th>
            <th data-options="field:'created', width:100, align:'center', formatter:formatCreated">发布时间</th>
            <th data-options="field:'hits', width:50, align:'right'">阅读数</th>
            <th data-options="field:'type', width:100, align:'center'">博客类型</th>
            <th data-options="field:'status', width:100, align:'center'">文章状态</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>