function log(n) {
    try {
        console.log(n)
    } catch (t) {
    }
}

function OpenWindow(n, t, i, r) {
    var u = (screen.width - t) / 2 - r, f = (screen.height - i) / 2 - r,
        e = window.open(n, "_blank", "width=" + t + ",height=" + i + ",toolbars=0,resizable=1,left=" + u + ",top=" + f);
    e.focus()
}

function hide_links() {
    document.getElementById("mini_nav_more").style.display = "none";
    document.getElementById("mini_nav_more_link_div").className = "mini_nav_more_link_hide"
}

function show_links() {
    document.getElementById("mini_nav_more").style.display = "block";
    document.getElementById("mini_nav_more_link_div").className = "mini_nav_more_link"
}

function WarpClass(eID, tID, fID, ev) {
    var eObj = document.getElementById(eID), tObj = document.getElementById(tID), fObj = document.getElementById(fID);
    eObj && tObj && (tObj.style.display && tObj.style.display != "block" ? (tObj.style.display = "block", eObj.className = "UnWarp", ev && eval(ev), fObj && (fObj.style.display = "block")) : (tObj.style.display = "none", eObj.className = "Warp", fObj && (fObj.style.display = "none")))
}

function PutInWz() {
    var n = 480, t = 360, i = (screen.width - n) / 2, r = (screen.height - t) / 2, u = document,
        f = document.getElementsByTagName("title")[0].innerHTML;
    window.open("http://wz.cnblogs.com/create?t=" + encodeURIComponent(f) + "&u=" + encodeURIComponent(u.location.href) + "&c=" + encodeURIComponent("") + "&i=0", "_blank", "width=" + n + ",height=" + t + ",toolbars=0,resizable=1,left=" + i + ",top=" + r)
}

function AddToWz(n) {
    var r = 480, u = 400, e = (screen.width - r) / 2, o = (screen.height - u) / 2, s = document,
        t = document.getElementsByTagName("title")[0].innerHTML, f = 1, i;
    try {
        t = window.btoa(unescape(encodeURIComponent(t)))
    } catch (h) {
        t = encodeURIComponent(t.replace(/</g, "&lt;").replace(/>/g, "&gt;"));
        f = 0
    }
    i = "http://wz.cnblogs.com/create?t=" + t + "&u=" + encodeURIComponent(s.location.href) + "&c=" + encodeURIComponent("") + "&bid=" + n + "&i=0";
    f == 1 && (i += "&base64=1");
    window.open(i, "_blank", "width=" + r + ",height=" + u + ",toolbars=0,resizable=1,left=" + e + ",top=" + o)
}

function GetMeta(n) {
    for (var i = document.getElementsByTagName("meta"), t = 0; t < i.length; t++) if (i[t].name.toLowerCase() == n) return i[t].content;
    return ""
}

function AjaxPost(n, t, i) {
    $.ajax({
        url: n,
        data: t,
        type: "post",
        dataType: "json",
        contentType: "application/json; charset=utf8",
        success: function (n) {
            i(n.d)
        },
        error: function () {
        }
    })
}

function escapeHTML(n) {
    var t = document.createElement("div"), i = document.createTextNode(n);
    return t.appendChild(i), t.innerHTML
}

function open_link(n) {
    return window.open(n), !1
}

function login(n) {
    var t = location.href, i = t.indexOf("#");
    return n && i > 0 && (t = t.substr(0, i)), t = t + "#" + n, location.href = "https://passport.cnblogs" + getHostPostfix() + "/login.aspx?ReturnUrl=" + encodeURIComponent(t), !1
}

function logout() {
    return confirm("确认退出吗？") && (location.href = "https://passport.cnblogs" + getHostPostfix() + "/logout.aspx?ReturnUrl=" + location.href), !1
}

function register() {
    return location.href = "https://passport.cnblogs" + getHostPostfix() + "/register.aspx?ReturnUrl=" + location.href, !1
}

function getHostPostfix() {
    var n = location.hostname;
    return n.substring(n.lastIndexOf("."), n.length)
}

function GetJobList() {
    try {
        $("#job_list").html("数据加载中...");
        $.ajax({
            url: "/ws/BlogAjaxService.asmx/GetJobList",
            data: "{}",
            type: "post",
            dataType: "json",
            contentType: "application/json; charset=utf8",
            success: function (n) {
                $("#job_list").html(n.d)
            }
        })
    } catch (n) {
    }
}

function shBushPathPrepare() {
    for (var t = arguments, i = [], n = 0; n < t.length; n++) i.push(t[n].replace("@", location.protocol + "//common.cnblogs.com/script/sh/"));
    return i
}

function cb_CodeHighlight() {
    var n = !1, t = $("pre");
    (t.length && $.each(t, function () {
        var t = $(this).attr("class");
        t && t.indexOf("brush:") >= 0 && (n = !0, t.indexOf("gutter:true;") >= 0 && $(this).parent().addClass("sh-gutter"))
    }), n) && (SyntaxHighlighter.autoloader.apply(null, shBushPathPrepare("applescript\t\t\t    @shBrushAppleScript.js", "actionscript3 as3\t\t@shBrushAS3.js", "bash shell\t\t\t\t@shBrushBash.js", "coldfusion cf\t\t\t@shBrushColdFusion.js", "cpp c\t\t\t\t\t@shBrushCpp.js", "c# c-sharp csharp\t\t@shBrushCSharp.js", "css\t\t\t\t\t    @shBrushCss.js", "delphi pascal pas\t\t@shBrushDelphi.js", "diff patch       \t\t@shBrushDiff.js", "erl erlang\t\t\t\t@shBrushErlang.js", "groovy\t\t\t\t\t@shBrushGroovy.js", "java\t\t\t\t\t    @shBrushJava.js", "jfx javafx\t\t\t\t@shBrushJavaFX.js", "js jscript javascript\t@shBrushJScript.js", "perl pl Perl\t\t\t\t@shBrushPerl.js", "php\t\t\t\t\t    @shBrushPhp.js", "text plain\t\t\t\t@shBrushPlain.js", "py python\t\t\t\t@shBrushPython.js", "ruby rails ror rb\t\t@shBrushRuby.js", "sass scss\t\t\t\t@shBrushSass.js", "scala\t\t\t\t\t@shBrushScala.js", "sql\t\t\t\t\t    @shBrushSql.js", "vb vbnet\t\t\t\t    @shBrushVb.js", "xml xhtml xslt html\t    @shBrushXml.js?id=20150508", "objc obj-c               @shBrushObjectiveC.js", "f# f-sharp fsharp        @shBrushFSharp.js", "xpp dynamics-xpp         @shBrushDynamics.js", "r s splus                @shBrushR.js", "matlab                   @shBrushMatlab.js", "swift                    @shBrushSwift.js", "go golang                @shBrushGo.js", "mathematica              @shBrushMathematica.js")), SyntaxHighlighter.config.strings.expandSource = "+ View Code", SyntaxHighlighter.vars.discoveredBrushes = null, SyntaxHighlighter.defaults["auto-links"] = !1, SyntaxHighlighter.defaults["quick-code"] = !1, SyntaxHighlighter.all())
}

function cb_mathjax_render(n) {
    if (cb_enable_mathjax) {
        var t = document.getElementById(n);
        MathJax.Hub.Queue(["Typeset", MathJax.Hub, t])
    }
}

function votePost(n, t, i) {
    if (!n) {
        $("#digg_tips").html("推荐出错误！postId不正确");
        return
    }
    i || (i = !1);
    var r = {blogApp: currentBlogApp, postId: n, voteType: t, isAbandoned: i};
    $("#digg_tips").css("color", "red").html("提交中...");
    $.ajax({
        url: "/mvc/vote/VoteBlogPost.aspx",
        type: "post",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(r),
        success: function (n) {
            if (n.IsSuccess) {
                var i = $("#" + t.toLowerCase() + "_count");
                r.isAbandoned ? $(i).html(parseInt($(i).html()) - 1) : $(i).html(parseInt($(i).html()) + 1)
            }
            $("#digg_tips").html(n.Message)
        },
        error: function (n) {
            n.status > 0 && (n.status == 500 ? $("#digg_tips").html("抱歉！发生了错误！麻烦反馈至contact@cnblogs.com") : $("#digg_tips").html(n.responseText))
        }
    })
}

function DiggIt(n, t, i) {
    i == 1 && votePost(n, "Digg", !1)
}

function voteComment(n, t, i) {
    if (!n) {
        $(i).css("color", "red").html("推荐出错！commentId不正确");
        return
    }
    var r = {commentId: n, voteType: t};
    return $(i).html($(i).html().replace(/\d+/g, function (n) {
        return parseInt(n) + 1
    })), $.ajax({
        url: "/mvc/vote/VoteComment.aspx",
        type: "post",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(r),
        success: function (n) {
            n.IsSuccess || (n.Message ? n.Message.indexOf("System.") >= 0 ? $(i).css("color", "red").html("抱歉！发生了错误！麻烦反馈至contact@cnblogs.com") : $(i).css("color", "red").html(n.Message) : $(i).css("color", "red").html("抱歉！发生了错误！麻烦反馈至contact@cnblogs.com"))
        },
        error: function (n) {
            n.status > 0 && (n.status == 500 ? $(i).css("color", "red").html("抱歉！发生了错误！麻烦反馈至contact@cnblogs.com") : $(i).css("color", "red").html(n.responseText))
        }
    }), !1
}

function clt_enter(n) {
    return n.ctrlKey && n.keyCode == 13 ? (PostComment(), !1) : !0
}

function CancelCommentEdit() {
    confirm("确认取消修改吗？") && ResetCommentBox()
}

function ResetCommentBox() {
    $("#btn_comment_submit").val("提交");
    $("#comment_edit_id").html("");
    $("#span_comment_canceledit").css("display", "none");
    $("#tbCommentBody").val("")
}

function UpdateComment(n) {
    var t = {};
    t.commentId = $("#comment_edit_id").html();
    t.parentId = $("#span_parent_id").html();
    t.content = $("#tbCommentBody").val();
    t.blogId = n;
    AjaxPost("/ws/CommentService.asmx/UpdateComment", JSON.stringify(t), OnUpdateComment)
}

function OnUpdateComment(n) {
    var r, t, i;
    n ? (r = $("#comment_edit_id").html(), t = $("#tbCommentBody").val(), t = escapeHTML(t), t = t.replace(/\n/g, "<br/>"), t = t.replace(/\[quote\]/g, '<fieldset class="comment_quote"><legend>引用<\/legend>'), t = t.replace(/\[\/quote\]/g, "<\/fieldset>"), $("#comment_body_" + r).html(t + " <span style='color:red'>修改成功！<\/span>"), i = location.href, i.indexOf("#") > 0 && (i = i.substring(0, i.indexOf("#"))), location.href = i + "#" + r, ResetCommentBox(), ShowCommentMsg("修改成功！")) : alert("修改失败！")
}

function CommentNotify(n) {
    var t = $("#span_comment_replyto").html();
    $.ajax({
        url: "/ws/CommentService.asmx/SendCommentNotify",
        data: '{id:"' + t + '",commentId:' + n + "}",
        type: "post",
        dataType: "json",
        contentType: "application/json; charset=utf8"
    })
}

function InsertCodeToEditor(n) {
    $("#tbCommentBody").focus();
    $("#tbCommentBody").val($("#tbCommentBody").val() + n)
}

function RefreshPage() {
    return location.reload();
}

function AddParamToUrl(n, t, i) {
    var r = n.indexOf("?");
    return r > 0 && (n = n.substring(0, r)), n + "?" + t + "=" + i
}

function OpenImageUploadWindow() {
    var n = location.protocol + "//upload.cnblogs" + location.hostname.substring(location.hostname.lastIndexOf(".")) + "/imageuploader/upload?host=www.cnblogs.com&editor=0#tbCommentBody";
    document.domain = "cnblogs." + location.hostname.substring(location.hostname.lastIndexOf(".") + 1, location.hostname.length);
    OpenWindow(n, 450, 120, 200)
}

function insertIndent(n) {
    var t = $("#" + n).selection();
    t == "" ? $("#" + n).parseHtml("　　") : $("#" + n).parseHtml("　　" + t)
}

function insertUbbUrl(n) {
    var i = prompt("显示链接的文本.\n如果为空，那么将只显示超级链接地址", ""), t;
    i != null && (t = prompt("http:// 超级链接", "http://"), t != "" && t != "http://" && (i != "" ? $("#" + n).parseHtml("[url=" + t + "]" + i + "[/url]") : $("#" + n).parseHtml("[url]" + t + "[/url]")))
}

function insertUbbImg(n) {
    var t = prompt("请先将图片上传到您的图库中，然后将图片地址拷下粘贴在此：", "http://");
    t != null && $.trim(t) != "" && t.toLowerCase() != "http://" && $("#" + n).parseHtml("[img]" + t + "[/img]")
}

function insertUploadImg(n) {
    $("#tbCommentBody").parseHtml("[img]" + n + "[/img]\n");
    $("#tbCommentBody").focus()
}

function insertUbbCode() {
    var n = 450, t = 400, r = (screen.width - n) / 2, u = (screen.height - t) / 2, i;
    document.domain = "cnblogs." + location.hostname.substring(location.hostname.lastIndexOf(".") + 1, location.hostname.length);
    i = window.open("/SyntaxHighlighter.htm", "_blank", "width=" + n + ",height=" + t + ",toolbars=0,resizable=1,left=" + r + ",top=" + u);
    i.focus()
}

function cnblogs_code_collapse(n) {
    n.children("div.cnblogs_code_open").css("display") != "none" ? (n.children("div.cnblogs_code_open").css("display", "none"), n.children("img.code_img_opened").css("display", "none"), n.children("img.code_img_closed").css("display", "inline")) : (n.children("div.cnblogs_code_open").css("display", "block"), n.children("img.code_img_opened").css("display", "inline"), n.children("img.code_img_closed").css("display", "none"))
}

function cnblogs_code_show(n) {
    var t = $("#cnblogs_code_open_" + n);
    t.css("display") == "none" && (t.parent().find("span.cnblogs_code_collapse").hide(), t.show(), $("#code_img_opened_" + n).show(), $("#code_img_closed_" + n).hide(), $(t).find("span.cnblogs_code_copy").length || showCopyCode(t))
}

function cnblogs_code_hide(n, t) {
    if ($("#cnblogs_code_open_" + n).css("display") != "none") {
        var i = $("#cnblogs_code_open_" + n);
        i.hide();
        $("#code_img_opened_" + n).hide();
        $("#code_img_closed_" + n).show();
        i.parent().find("span.cnblogs_code_collapse").show();
        t.stopPropagation ? t.stopPropagation() : window.event && (window.event.cancelBubble = !0)
    }
}

function code_collapse_toggle(n) {
    $(n).toggle();
    var t = n.id;
    IsCodeCollapseNode(t, "_Open_Image") ? ($("#" + t.replace("_Open_", "_Closed_")).toggle(), $("#" + t.replace("_Open_Image", "_Open_Text")).toggle(), $("#" + t.replace("_Open_Image", "_Closed_Text")).toggle()) : IsCodeCollapseNode(t, "_Closed_Image") && ($("#" + t.replace("_Closed_", "_Open_")).toggle(), $("#" + t.replace("_Closed_Image", "_Open_Text")).toggle(), $("#" + t.replace("_Closed_Image", "_Closed_Text")).toggle())
}

function fix_code_collapse_img(n) {
    if (IsCodeCollapseNode(n.id, "_Open_Image")) {
        var t = n.id.replace("_Open_Image", "_Closed_Image"), i = n.id.replace("_Open_Image", "_Open_Text"),
            r = n.id.replace("_Open_Image", "_Closed_Text");
        n.onclick = function () {
            $(this).hide();
            $("#" + t + "").show();
            $("#" + i + "").hide();
            $("#" + r + "").show()
        }
    } else if (IsCodeCollapseNode(n.id, "_Closed_Image")) {
        var t = n.id.replace("_Closed_Image", "_Open_Image"), i = n.id.replace("_Closed_Image", "_Open_Text"),
            r = n.id.replace("_Closed_Image", "_Closed_Text");
        n.onclick = function () {
            $(this).hide();
            $("#" + t + "").show();
            $("#" + i + "").show();
            $("#" + r + "").hide()
        }
    }
}

function IsCodeCollapseNode(n, t) {
    return n.indexOf(t) >= 0
}

function fix_code_collapse_span(n) {
    var t;
    /Codehighlighter1_\d+_\d+_Closed_Text/ig.test(n.id) && $(n).hide();
    t = /Codehighlighter1_\d+_\d+_Open_Text/ig;
    t.test(n.id) && $(n).show()
}

function change_onclick(element, clickCode) {
    if (clickCode) {
        var newclick = eval("(function(){" + clickCode + "});");
        $(element).attr("onclick", "").click(newclick)
    }
}

function showRemoveLineNumber(n) {
    $(n).append('<div class="cnblogs_code_toolbar"><span class="cnblogs_code_copy"><a href="javascript:void(0);" onclick="removeLineNumber(this);return false;">消除行号<\/a><\/span>')
}

function showCopyCode(n) {
    if ($(n).height() > 120) {
        var t = '<div class="cnblogs_code_toolbar"><span class="cnblogs_code_copy"><a href="javascript:void(0);" onclick="copyCnblogsCode(this)" title="复制代码"><img src="//common.cnblogs.com/images/copycode.gif" alt="复制代码"/><\/a><\/span>';
        $(n).prepend(t).append(t)
    }
}

function removeLineNumber(n) {
    var t = $(n).parent().parent().parent(),
        i = $(t).html().replace(/<span style=\"color: #008080;?\">\s*(&nbsp;)?(\d+)<\/span>/gi, "");
    $(t).html(i)
}

function loadEncoderJs() {
    var n = document.createElement("script"), t;
    n.type = "text/javascript";
    n.src = location.protocol + "//common.cnblogs.com/script/encoder.js";
    t = document.getElementsByTagName("script")[0];
    t.parentNode.insertBefore(n, t)
}

function copyCnblogsCode(n) {
    var i = getCnblogsCodeContainer(n), u = getCnblogsCodeText(i), t = document.createElement("textarea"), r;
    $(t).val(u);
    $(t).css("width", $(i).width());
    r = $(i).height() * .8;
    r > 600 && (r = 600);
    $(t).css("height", r);
    $(t).css("font-family", "Courier New");
    $(t).css("font-size", "12px");
    $(t).css("line-height", "1.5");
    $(i).html(t);
    $(t).select();
    $("<div>按 Ctrl+C 复制代码<\/div>").insertBefore($(t));
    $("<div>按 Ctrl+C 复制代码<\/div>").insertAfter($(t))
}

function getCnblogsCodeContainer(n) {
    var t = $(n).closest("pre");
    return t.length == 0 && (t = $(n).closest("div.cnblogs_code")), t
}

function getCnblogsCodeText(n) {
    var t = "\n" + $(n).html().replace(/&nbsp;/g, " ").replace(/<br\s*\/?>/ig, "\n").replace(/<[^>]*>/g, "");
    return t = t.replace(/\n(\s*\d+\s)/ig, "\n"), t = t.replace(/\r\n/g, "\n"), t = t.replace(/\nView Code/g, ""), typeof Encoder != undefined && (t = Encoder.htmlDecode(t)), $.trim(t)
}

function showRunCode(n) {
    var t = $(n).find("div.cnblogs_code_toolbar");
    t.length && $(t).append('<span class="cnblogs_code_runjs"><a href="javascript:void(0);" onclick="runJsCode(this)">运行代码<\/a><\/span>')
}

function runJsCode(n) {
    var i = getCnblogsCodeContainer(n), r = getCnblogsCodeText(i), t = window.open("", "_blank", "");
    t.document.open("text/html", "replace");
    t.opener = null;
    t.document.write(r);
    t.document.close()
}

function zzk_go() {
    var n = encodeURIComponent("blog:" + currentBlogApp + " " + document.getElementById("q").value);
    window.location = "http://zzk.cnblogs.com/s?w=" + n
}

function zzk_go_enter(n) {
    if (n.keyCode == 13) return zzk_go(), !1
}

function google_go() {
    return location.href = "http://www.google.com/search?q=" + encodeURIComponent("site:www.cnblogs.com/" + currentBlogApp + "/ " + document.getElementById("google_q").value), !1
}

function google_go_enter(n) {
    if (n.keyCode == 13) return google_go(), !1
}

function green_channel_success(n, t) {
    $(n).replaceWith('<span style="color:red">' + t + "<\/span>")
}

function LoadPostInfoBlock(n, t, i, r) {
    $.ajax({
        url: "/mvc/blog/CategoriesTags.aspx",
        type: "get",
        contentType: "application/json; charset=utf-8",
        data: {blogApp: i, blogId: n, postId: t},
        cache: !1,
        dataType: "json",
        timeout: 1e4,
        success: function (n) {
            n && (n.Categories && $("#BlogPostCategory").html(n.Categories), n.Tags && $("#EntryTag").html(n.Tags))
        }
    });
    $.ajax({
        url: "/mvc/blog/BlogPostInfo.aspx",
        type: "get",
        contentType: "application/json; charset=utf-8",
        data: {blogId: n, postId: t, blogApp: i, blogUserGuid: r},
        cache: !1,
        dataType: "text",
        timeout: 1e4,
        success: function (n) {
            var t = $("#blog_post_info");
            $(t).html().length < 20 && $(t).html(n)
        },
        error: function () {
        }
    })
}

function GetPrevNextPost(n, t, i, r) {
    $.get("/post/prevnext", {postId: n, blogId: t, dateCreated: i, postType: r}, function (n) {
        n && $("#post_next_prev").html(n)
    }, "html")
}

function GetHistoryToday(n, t, i) {
    $.ajax({
        url: "/mvc/blog/HistoryToday.aspx",
        data: {blogId: n, blogApp: t, dateCreated: i},
        type: "get",
        dataType: "text",
        timeout: 1e4,
        success: function (n) {
            n && $("#HistoryToday").html(n)
        }
    })
}

function tsina_a() {
    var n = screen, t = document, i = encodeURIComponent, r = "http://v.t.sina.com.cn/share/share.php?",
        u = t.location.href, f = ["url=", i(u), "&title=", i(t.title)].join("");
    window.open([r, f].join(""), "mb", ["toolbar=0,status=0,resizable=1,width=620,height=450,left=", (n.width - 620) / 2, ",top=", (n.height - 450) / 2].join("")) || (u.href = [r, f].join(""))
}

function ShareToTsina() {
    /Firefox/.test(navigator.userAgent) ? setTimeout(tsina_a, 0) : tsina_a()
}

function outFromAggHome() {
    $.ajax({
        url: "/mvc/Blog/RemoveFromSiteHome.aspx",
        data: JSON.stringify({blogApp: currentBlogApp, postId: cb_entryId}),
        type: "post",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (n) {
            n && $("#site_editor_opt").html("<span style='color:red'>操作成功！<\/span>")
        }
    })
}

function loadNewsAndKb() {
    $.ajax({
        url: "/mvc/Blog/UnderPostNews.aspx", type: "get", dataType: "text", success: function (n) {
            $("#under_post_news").html(n)
        }
    });
    $.ajax({
        url: "/mvc/Blog/UnderPostKb.aspx", type: "get", dataType: "text", success: function (n) {
            $("#under_post_kb").html(n)
        }
    })
}

function loadOptUnderPost() {
    isLogined && $.ajax({
        url: "/mvc/Blog/OptUnderPost.aspx",
        type: "get",
        data: {postId: cb_entryId},
        dataType: "text",
        success: function (n) {
            $("#opt_under_post").html(n)
        }
    })
}

function loadAdUnderPost() {
}

function loadBlogSignature() {
    $.ajax({
        url: "/mvc/blog/signature.aspx",
        data: {blogId: cb_blogId, blogApp: cb_blogApp},
        type: "get",
        dataType: "text",
        success: function (n) {
            n && $("#MySignature").html(n).show()
        }
    })
}

function loadPageBeginHtml() {
    currentBlogApp && $.ajax({
        url: "/mvc/blog/PageBeginHtml.aspx",
        data: '{"blogApp":"' + currentBlogApp + '"}',
        type: "post",
        dataType: "text",
        contentType: "application/json; charset=utf-8",
        success: function (n) {
            n && (n.indexOf("<script") > -1 ? $.getScript(location.protocol + "//common.cnblogs.com/script/jquery.writeCapture-min.js", function () {
                $("#page_begin_html").writeCapture().html(n).show()
            }) : $("#page_begin_html").html(n).show())
        }
    })
}

function loadPageEndHtml() {
    currentBlogApp && $.ajax({
        url: "/mvc/blog/PageEndHtml.aspx",
        data: '{"blogApp":"' + currentBlogApp + '"}',
        type: "post",
        dataType: "text",
        contentType: "application/json; charset=utf-8",
        success: function (n) {
            n && (n.indexOf("<script") > -1 ? $.getScript(location.protocol + "//common.cnblogs.com/script/jquery.writeCapture-min.js", function () {
                $("#page_end_html").writeCapture().html(n).show()
            }) : $("#page_end_html").html(n).show())
        }
    })
}

function loadBlogNews() {
    $.ajax({
        url: "#",
        data: {blogApp: currentBlogApp},
        type: "get",
        dataType: "text",
        success: function (n) {
            n && (n.indexOf("<script") < n.indexOf("<script>getFollowStatus") ? $.getScript(location.protocol + "//common.cnblogs.com/script/jquery.writeCapture-min.js", function () {
                $("#blog-news").writeCapture().html(n).show()
            }) : n.indexOf("错误提示：发生了异常") < 0 && $("#blog-news").html(n).show())
        }
    })
}

function loadBlogCalendar(n) {
    $.ajax({
        url: "/mvc/blog/calendar.aspx",
        data: {blogApp: currentBlogApp, dateStr: n},
        type: "get",
        dataType: "text",
        success: function (n) {
            n && ($("#blog-calendar").html(n), $("#blog-calendar").show())
        }
    })
}

function loadBlogSideColumn() {
    $("#blog-sidecolumn").length && $.ajax({
        url: "/" + currentBlogApp + "/mvc/blog/sidecolumn.aspx",
        data: {blogApp: currentBlogApp},
        type: "get",
        dataType: "text",
        success: function (n) {
            n && ($("#blog-sidecolumn").html(n), loadBlogSideBlocks())
        }
    })
}

function loadBlogSideBlocks() {
    var n = [];
    document.getElementById("RecentCommentsBlock") && n.push("ShowRecentComment");
    document.getElementById("TopViewPostsBlock") && n.push("ShowTopViewPosts");
    document.getElementById("TopFeedbackPostsBlock") && n.push("ShowTopFeedbackPosts");
    document.getElementById("TopDiggPostsBlock") && n.push("ShowTopDiggPosts");
    $.ajax({
        url: "/mvc/Blog/GetBlogSideBlocks.aspx",
        data: {blogApp: currentBlogApp, showFlag: n.join(",")},
        type: "get",
        dataType: "json",
        success: function (n) {
            n && (n.RecentComments ? $("#RecentCommentsBlock").html(n.RecentComments) : $("#recent_comments_wrap").hide(), n.TopViewPosts ? $("#TopViewPostsBlock").html(n.TopViewPosts) : $("#topview_posts_wrap").hide(), n.TopFeedbackPosts ? $("#TopFeedbackPostsBlock").html(n.TopFeedbackPosts) : $("#topfeedback_posts_wrap").hide(), n.TopDiggPosts ? $("#TopDiggPostsBlock").html(n.TopDiggPosts) : $("#topdigg_posts_wrap").hide())
        }
    })
}

function blogCommentManager() {
    var t = 0;
    this.getCurrentPage = function () {
        return t
    };
    this.PageSize = function () {
        return pageSize
    };
    var n = function (n) {
        setTimeout(function () {
            location.hash = "#";
            location.hash = n
        }, 100)
    }, r = function () {
        var n = window.location.hash, t, i;
        return n && /#(\d+)/g.test(n) ? (t = $('#blog-comments-placeholder a.layer[href="' + n + '"]'), $(t).length ? (i = $(t).offset(), window.scrollTo(i.left, i.top), !0) : !1) : !0
    }, i = function () {
        if (!allowComments) {
            $("#comment_form_container").html("（评论功能已被禁用）");
            return
        }
        var t = {};
        t.postId = cb_entryId;
        t.blogApp = currentBlogApp;
        $("#comment_form_container").html('<span style="color:green">努力加载评论框中...<\/span>');
        $.ajax({
            url: "/mvc/Blog/CommentForm.aspx",
            data: t,
            dataType: "html",
            cache: !1,
            type: "get",
            success: function (t) {
                if (t) {
                    var i = $("#comment_form_container");
                    i.html(t);
                    $("#tbCommentBody").bind("keydown", function (n) {
                        commentManager.ctlEnterPost(n)
                    });
                    $("#btn_comment_submit").bind("click", function () {
                        return commentManager.postComment(), !1
                    });
                    $("#tbCommentBody").focus(function () {
                        $("#tbCommentBody").mention({typeaheadOpts: {under: !0}})
                    });
                    location.hash == "#commentform" && n("#commentform")
                }
            },
            error: function () {
                $("#comment_form_container").html("<span style='color:red'>评论框加载失败，请与管理员联系(contact@cnblogs.com)。<\/span>")
            }
        })
    };
    this.postComment = function () {
        $("#btn_comment_submit").val() == "修改" && $("#comment_edit_id").html != "" ? commentManager.UpdateComment() : commentManager.PostNewComment()
    };
    this.ctlEnterPost = function (n) {
        return n.ctrlKey && n.keyCode == 13 ? (commentManager.postComment(), !1) : !0
    };
    this.UpdateComment = function () {
        var n = {};
        n.commentId = parseInt($("#comment_edit_id").html());
        n.body = $("#tbCommentBody").val();
        $.ajax({
            url: "/blog/comment/update",
            data: JSON.stringify(n),
            type: "post",
            dataType: "json",
            contentType: "application/json; charset=utf8",
            success: function (t) {
                if (t) t.IsSuccess ? (ShowCommentMsg("修改成功"), $("#comment_body_" + n.commentId).html(t.Message), commentManager.ResetCommentBox()) : ShowCommentMsg(t.Message); else ShowCommentMsg("抱歉！评论修改失败！请与管理员联系(contact@cnblogs.com)。")
            },
            error: function (n) {
                ShowCommentMsg("抱歉！评论修改失败！错误信息：" + n.responseText)
            }
        })
    };
    this.Subscribe = function () {
        if (confirm("确认订阅吗？订阅后有新评论时会邮件通知您")) {
            var n = cb_entryId, t = cb_blogId;
            $("#commentbox_opt_sub").html("提交中...");
            $("#commentbox_opt_sub").css("color", "red");
            $("#commentbox_opt_sub").removeAttr("onclick");
            $.ajax({
                url: "/mvc/Subscribe/SubscribeComment.aspx",
                data: '{"blogId":' + t + ',"postId":' + n + "}",
                type: "post",
                dataType: "json",
                contentType: "application/json; charset=utf8",
                success: function (n) {
                    n ? $("#commentbox_opt_sub").html("订阅成功") : $("#commentbox_opt_sub").html("订阅失败")
                }
            })
        }
    };
    this.Unsubscribe = function () {
        var n = cb_entryId;
        $("#commentbox_opt_unsub").html("提交中...");
        $("#commentbox_opt_unsub").css("color", "red");
        $("#commentbox_opt_unsub").removeAttr("onclick");
        $.ajax({
            url: "/mvc/Subscribe/UnsubscribeComment.aspx",
            data: '{"postId":' + n + "}",
            type: "post",
            dataType: "json",
            contentType: "application/json; charset=utf8",
            success: function (n) {
                n ? $("#commentbox_opt_unsub").html("取消订阅成功") : $("#commentbox_opt_unsub").html("取消订阅失败")
            }
        })
    };
    this.ResetCommentBox = function () {
        $("#btn_comment_submit").val("提交评论");
        $("#comment_edit_id").html("");
        $("#tbCommentBody").val("")
    };
    this.loadMailSubscribeOperation = function () {
        $("#commentbox_opt").append('<a href="">订阅回复<\/a>')
    };
    this.renderComments = function (r, u, f) {
        var e, o, s;
        t = r;
        e = !1;
        o = 0;
        typeof f != "undefined" && (o = f, e = !0);
        !e && r == 0 && location.hash && /^#\d+$/g.test(location.hash) && (e = !0, o = location.hash.substring(1));
        r > 0 && (location.hash = "#!comments");
        s = {postId: cb_entryId, blogApp: currentBlogApp, pageIndex: r, anchorCommentId: o};
        $("#blog-comments-placeholder").html('<div style="color:green;margin:50px 0;font-weight:bold;">努力加载评论中...<\/div>');
        $("#comment_form_container").html("");
        $.ajax({
            url: "/mvc/blog/GetComments.aspx",
            data: s,
            type: "get",
            dataType: "json",
            cache: !1,
            success: function (t) {
                t ? ($("#post_comment_count").html(t.commentCount), t.commentCount > 0 ? ($("#blog-comments-placeholder").html(t.commentsHtml), e ? n("#" + o) : (location.hash == "#!comments" || location.hash == "#comments_pager_top") && n("#!comments"), comment_maxId = $("#comment-maxId").html(), comment_maxDate = $("#comment-maxDate").html()) : $("#blog-comments-placeholder").html("")) : $("#blog-comments-placeholder").html("");
                i();
                cb_CodeHighlight();
                cb_mathjax_render("#blog-comments-placeholder")
            },
            error: function (n) {
                n.status > 0 && $("#blog-comments-placeholder").html("抱歉！发生了错误！麻烦反馈至contact@cnblogs.com")
            }
        })
    }
}

function SubscribeComment() {
    return $("#<%= lnkSubscribe.ClientID %>").html("<span style='color:red'>订阅操作中...<\/span>"), AjaxPost("/ws/CommentService.asmx/SubscribeComment", "{entryId:" + cb_entryId + ",blogId:" + cb_blogId + "}", OnSubscribeSuccess), !1
}

function OnSubscribeSuccess(n) {
    n ? ($("#<%= lnkSubscribe.ClientID %>").html("<span style='color:red'>订阅成功<\/span>"), $("#<%= lnkSubscribe.ClientID %>").removeAttr("href"), $("#<%= lnkSubscribe.ClientID %>").removeAttr("onclick")) : $("#<%= lnkSubscribe.ClientID %>").html("<span style='color:red'>订阅失败<\/span>")
}

function CancelCommentSubscribe() {
    return $("#<%= lnkSubscribe.ClientID %>").html("<span style='color:red'>取消操作中...<\/span>"), AjaxPost("/ws/CommentService.asmx/CancelCommentSubscribe", "{entryId:" + cb_entryId + "}", OnCancelSubscribeSuccess), !1
}

function OnCancelSubscribeSuccess(n) {
    n ? ($("#<%= lnkSubscribe.ClientID %>").html("<span style='color:red'>取消成功<\/span>"), $("#<%= lnkSubscribe.ClientID %>").removeAttr("href"), $("#<%= lnkSubscribe.ClientID %>").removeAttr("onclick")) : $("#<%= lnkSubscribe.ClientID %>").html("<span style='color:red'>取消操作失败<\/span>")
}

function RefreshCommentList() {
    return $("#tip_comment").html(""), $("#span_refresh_tips").show(), $("#span_refresh_tips").html("正在刷新..."), $("#span_refresh_tips").css("color", "red"), $("#lnk_RefreshComments").hide(), comment_maxDate || (comment_maxDate = $("#post-date").html()), loadNewComments(cb_entryId, comment_maxDate, comment_maxId), !1
}

function loadNewComments(n, t, i) {
    var r = {parentId: n, startDateStr: t, startId: i};
    $.ajax({
        url: "/mvc/comment/NewComments.aspx", data: JSON.stringify(r), dataType: "text", success: function (n) {
            if (n) {
                var t = $("#divCommentShow div.comment_my_posted");
                $(t).length && $(t).remove();
                $("#divCommentShow").html(n)
            } else $("#tip_comment").html("暂无新评论");
            $("#span_refresh_tips").hide();
            $("#lnk_RefreshComments").show()
        }
    })
}

function ReplyComment(n, t) {
    var i = $("#a_comment_author_" + n).text();
    return $("#tbCommentBody").focus(), $("#tbCommentBody").val("@" + i + "\n" + $("#tbCommentBody").val()), $("#span_parentcomment_id").html(n), $("#span_comment_replyto").html(t), !1
}

function QuoteComment(n, t) {
    return $("#tip_comment").html("正在加载引用内容..."), $("#span_parentcomment_id").html(n), $("#span_comment_replyto").html(t), GetQuoteComment(n), !1
}

function GetQuoteComment(n) {
    var i = $("#comment_body_" + n + " div.syntaxhighlighter"), t, r;
    $(i).length && $(i).remove();
    t = $("#comment_body_" + n).html();
    t = t.replace(/\n/g, "");
    t = t.replace(/<br\/?>/ig, "\n");
    t = t.replace(/<fieldset class=\"comment_quote\">((\w|\W)*?)<\/fieldset>/ig, "[quote]$1[/quote]");
    t = t.replace(/<[^>]*>/g, "");
    t.length > 300 && (t = t.replace(/<fieldset class=\"comment_quote\">((\w|\W)*?)<\/fieldset>/ig, "[quote]$1[/quote]"), t = t.substring(0, 300) + "...");
    t.length > 0 && (t = "[quote]\n" + t + "\n[/quote]\n");
    r = $("#a_comment_author_" + n).text();
    $("#tbCommentBody").focus();
    $.getScript(location.protocol + "//common.cnblogs.com/script/encoder.js", function () {
        t = Encoder.htmlDecode(t);
        $("#tbCommentBody").val($("#tbCommentBody").val() + "@" + r + "\n" + t);
        $("#tip_comment").html("")
    })
}

function GetCommentBody(n) {
    return ShowCommentMsg("评论内容加载中..."), $.ajax({
        url: "/mvc/comment/GetCommentBody.aspx",
        type: "post",
        data: '{"commentId":' + n + "}",
        dataType: "text",
        success: function (t) {
            t && ($("#comment_edit_id").html(n), $("#tbCommentBody").focus(), $("#tbCommentBody").val(t), $("#btn_comment_submit").val("修改"), $("#span_comment_canceledit").css("display", "inline"));
            ShowCommentMsg("")
        }
    }), !1
}

function DelComment(n, t, i) {
    return confirm("确认要删除该评论吗?") && (currentDelElement = t, currentCommentID = n, $(currentDelElement).html("<span style='color:red'>正在删除...<\/span>"), $(currentDelElement).removeAttr("href"), $(currentDelElement).removeAttr("onclick"), $.ajax({
        url: "/mvc/comment/DeleteComment.aspx",
        type: "post",
        data: JSON.stringify({commentId: currentCommentID, pageIndex: commentManager.getCurrentPage(), parentId: i}),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (n) {
            n ? ($("#comment_body_" + currentCommentID).html(""), document.getElementById("comment_anchor_" + currentCommentID) != null && (document.getElementById("comment_anchor_" + currentCommentID).parentNode.innerHTML = ""), currentDelElement.parentNode != null && (currentDelElement.parentNode.innerHTML = "<span style='color:red'>删除成功!<\/span>")) : $(currentDelElement).html("删除失败！")
        }
    })), !1
}

function showImages() {
}

function showCodeBlock(n) {
    var t = $(n).children(".cnblogs_code_hide").first();
    t.css("display") == "none" ? (t.parent().find("span.cnblogs_code_collapse").hide(), t.show(), $(n).find(".code_img_opened").show(), $(n).find(".code_img_closed").hide(), $(t).find("span.cnblogs_code_copy").length || showCopyCode(t)) : (t.parent().find("span.cnblogs_code_collapse").show(), t.hide(), $(n).find(".code_img_opened").hide(), $(n).find(".code_img_closed").show())
}

function fixPostBody() {
    cb_entryId > 5928739 && $("div.cnblogs_code").each(function () {
        var t, n;
        this.onclick || (t = $(this).children(".cnblogs_code_hide"), t.length && (n = this, $(this).find(".code_img_closed").unbind().bind("click", function () {
            showCodeBlock(n)
        }), $(this).find(".code_img_opened").unbind().bind("click", function () {
            showCodeBlock(n)
        })))
    });
    $("div.cnblogs_Highlighter pre").each(function () {
        $(this).html().indexOf("<span") >= 0 && $(this).text($(this).text())
    });
    showImages("cnblogs_post_body")
}

function fixPostListBodyFormat() {
}

function canShowAdsense() {
    var n = $("#cb_post_title_url");
    if (n.length) {
        var t = n.html(),
            i = ["破解", "序列号", "crack", "下载", "激活", "keygen", "逆向工程", "注册", "汉化版", "密钥"].filter(function (n) {
                return t.indexOf(n) > -1
            });
        return i.length == 0
    }
    return cb_entryId == 1489405 || cb_entryId == 1873020 || cb_entryId == 3658314 ? !1 : !0
}

function loadViewCount(n) {
    $.ajax({
        url: "/mvc/blog/ViewCountCommentCout.aspx",
        data: {postId: n},
        type: "get",
        contentType: "text",
        success: function (n) {
            $("#post_view_count").html(n)
        }
    })
}

function incrementViewCount(n) {
    n && $.ajax({
        url: location.protocol + "//counter.cnblogs.com/blog/post/" + n,
        type: "get",
        dataType: "script",
        cache: !0
    })
}

function runJsCode(n) {
    var i = document.getElementById(n), t = window.open("about:blank", "runWindow");
    t.opener = null;
    t.document.open();
    t.document.write(i.value);
    t.document.close()
}

function getBlogPostBody(n) {
    $.ajax({
        url: "/postbody/fulltext.aspx",
        data: JSON.stringify({blogapp: currentBlogApp, postId: n}),
        type: "post",
        contentType: "application/json",
        dataType: "text",
        success: function (t) {
            if (t) {
                var i = "#postlist_postbody_" + n;
                $(i).html(t);
                cb_CodeHighlight();
                cb_mathjax_render(i);
                fixPostListBodyFormat();
                n > 3861237 && $.getScript(location.protocol + "//common.cnblogs.com/highlight/9.1.0/highlight.min.js?id=20160127", function () {
                    hljs.initHighlightingOnLoad()
                })
            } else $("#postlist_postbody_" + n).html("")
        }
    })
}

function load_page_begin_html() {
    $.ajax({
        url: "/mvc/blog/pagebeginhtml.aspx",
        type: "get",
        data: {blogApp: currentBlogApp},
        dataType: "text",
        success: function (n) {
            n && (n.indexOf("<script") > -1 ? $.getScript(location.protocol + "//common.cnblogs.com/script/jquery.writeCapture-min.js", function () {
                $("#page_begin_html").writeCapture().html(n)
            }) : $("#page_begin_html").html(n))
        }
    })
}

function google_analytics(n, t, i, r, u, f, e) {
    n.GoogleAnalyticsObject = u;
    n[u] = n[u] || function () {
        (n[u].q = n[u].q || []).push(arguments)
    };
    n[u].l = 1 * new Date;
    f = t.createElement(i);
    e = t.getElementsByTagName(i)[0];
    f.async = 1;
    f.src = r;
    e.parentNode.insertBefore(f, e)
}

function google_ga() {
    try {
        google_analytics(window, document, "script", "https://www.google-analytics.com/analytics.js", "ga");
        ga("create", "UA-476124-1", "auto");
        ga("send", "pageview")
    } catch (n) {
    }
}

function deliverAdT2() {
    $.ajax({
        url: location.protocol + "//a1.cnblogs.com/units/text/T2/creative",
        type: "get",
        dataType: "html",
        cache: !1,
        success: function (n) {
            var t, i, r;
            if (n) {
                if (t = /(<a [^>]+>[^{<]+)(\{(.*?)\})(<\/a><br\/>)/g, i = t.exec(n), i && i.length && (r = $("#cb_post_title_url"), r.length)) {
                    var u = r.html(), f = i[3].split(","), e = f.filter(function (n) {
                        return new RegExp(n, "i").test(u)
                    });
                    n = e.length ? n.replace(t, "$1$4") : n.replace(t, "")
                }
                $("#ad_t2").html(n)
            }
        }
    })
}

function deliverAdC1() {
    try {
        deliverDirectAdC1()
    } catch (n) {
        deliverDirectAdC1()
    }
}

function deliverDirectAdC1() {
    $.ajax({
        url: location.protocol + "//a1.cnblogs.com/units/image/C1/creative",
        type: "get",
        dataType: "html",
        success: function (n) {
            $("#cnblogs_c1").html(n)
        }
    })
}

function deliverGoogleAdC1() {
    $("#cnblogs_c1").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"><\/script>     <ins class="adsbygoogle"     style="display:inline-block;width:300px;height:250px"     data-ad-client="ca-pub-4210569241504288"     data-ad-slot="5457903683"><\/ins><script>(adsbygoogle = window.adsbygoogle || []).push({});<\/script>')
}

function deliverAdC2() {
    $.ajax({
        url: location.protocol + "//a1.cnblogs.com/units/image/C2/creative",
        type: "get",
        dataType: "html",
        success: function (n) {
            $("#cnblogs_c2").html(n)
        }
    })
}

function markdown_highlight() {
    $("#cnblogs_post_body pre code").each(function (n, t) {
        hljs.highlightBlock(t)
    })
}

function shareOnWechat() {
    var n = document.location.href, t = /^https?:\/\/www(\.cnblogs\.com)\/([^/]+)(\/.+)$/gi;
    t.test(n) && (n = n.replace(t, "https://$2$1$3"));
    var i = 420, r = 330, u = (window.screen.availHeight - 30 - r) / 2, f = (window.screen.availWidth - 10 - i) / 2;
    window.open("//common.cnblogs.com/qrcode.html?url=" + encodeURIComponent(n), "_blank", "location=no,top=" + u + ",left=" + f + ", toolbar=no, directories=no, titlebar=no, status=no, menubar=no, scrollbars=no,status=no, resizable=no, copyhistory=no, width=" + i + ", height=" + r + "")
}

function follow(n) {
    loadLink(location.protocol + "//common.cnblogs.com/scripts/artDialog/ui-dialog.css", function () {
        loadScript(location.protocol + "//common.cnblogs.com/scripts/artDialog/dialog-min.js", function () {
            if (!isLogined) {
                login();
                return
            }
            if (c_has_follwed) {
                var t = dialog({content: "你已经关注过该博主！"});
                return t.show(), setTimeout(function () {
                    t.close().remove()
                }, 2e3), !1
            }
            n || (n = cb_blogUserGuid);
            $("#author_profile_follow").html("<span class='color:red'>正在处理中...<\/span>");
            $.ajax({
                url: "/mvc/Follow/FollowBlogger.aspx",
                data: '{"blogUserGuid":"' + n + '"}',
                dataType: "text",
                type: "post",
                contentType: "application/json; charset=utf-8",
                success: function (t) {
                    t == "未登录" ? login() : (showFollowMsg(t), t == "关注成功" && followByGroup(n, !0))
                },
                error: function (n) {
                    n.status > 0 && showFollowMsg("抱歉！发生了错误！麻烦反馈至contact@cnblogs.com")
                }
            })
        })
    })
}

function followByGroup(n, t) {
    loadScript(location.protocol + "//common.cnblogs.com/scripts/artDialog/dialog-plus-min.js", function () {
        var r = $("#author_profile_detail").children("a").first().text(), i = dialog({
            width: 480,
            height: t ? 450 : 420,
            title: "关注成功，设置备注名称与分组",
            url: "https://home.cnblogs.com/follow_group/Index/?enableSetRemark=" + t,
            oniframeload: function () {
                var u = this.iframeNode.contentWindow;
                u.postMessage({name: r, currentUserId: n, remark: "", enableSetRemark: t}, "https://home.cnblogs.com");
                window.addEventListener("message", function (n) {
                    if (n.data.IsSucceed != undefined && n.data.IsSucceed != null && n.data.IsSucceed) {
                        var t = dialog({content: "保存成功！"});
                        t.show();
                        setTimeout(function () {
                            t.close().remove()
                        }, 2e3)
                    }
                    i.close().remove()
                }, !1)
            }
        });
        i.show()
    })
}

function showFollowMsg(n) {
    $("#author_profile_follow").html('<span style="color:red">' + n + "<\/span>");
    $("#p_b_follow").html('<span style="color:red">' + n + "<\/span>");
    green_channel_success($("#green_channel_follow"), n)
}

function unfollow(n) {
    loadLink(location.protocol + "//common.cnblogs.com/scripts/artDialog/ui-dialog.css", function () {
        loadScript(location.protocol + "//common.cnblogs.com/scripts/artDialog/dialog-min.js", function () {
            var t = dialog({
                title: "取消关注", content: "您确定要取消关注吗？", okValue: "不关注了", ok: function () {
                    $("#author_profile_follow").html("<span style='color:red'>正在处理中...<\/span>");
                    $.ajax({
                        url: "/mvc/Follow/RemoveFollow.aspx",
                        data: '{"blogUserGuid":"' + n + '"}',
                        dataType: "text",
                        type: "post",
                        contentType: "application/json; charset=utf-8",
                        success: function (n) {
                            n == "未登录" ? login() : showFollowMsg(n)
                        },
                        error: function (n) {
                            n.status > 0 && showFollowMsg("抱歉！发生了错误！麻烦反馈至contact@cnblogs.com")
                        }
                    })
                }, cancelValue: "再考虑一下", cancel: function () {
                }
            });
            t.show()
        })
    })
}

function getFollowStatus(n) {
    $.ajax({
        url: "/mvc/Follow/GetFollowStatus.aspx",
        data: {blogUserGuid: n},
        cache: !1,
        dataType: "text",
        type: "get",
        success: function (n) {
            $("#p_b_follow").html(n)
        }
    })
}

function loadScript(n, t) {
    if (document.getElementById(n)) {
        t();
        return
    }
    var i = document.createElement("script");
    i.id = n;
    i.type = "text/javascript";
    i.readyState ? i.onreadystatechange = function () {
        (i.readyState == "loaded" || i.readyState == "complete") && (i.onreadystatechange = null, t())
    } : i.onload = function () {
        t()
    };
    i.src = n;
    document.getElementsByTagName("head")[0].appendChild(i)
}

function loadLink(n, t) {
    if (document.getElementById(n)) {
        t();
        return
    }
    var i = document.createElement("link");
    i.id = n;
    i.rel = "stylesheet";
    i.readyState ? i.onreadystatechange = function () {
        (i.readyState == "loaded" || i.readyState == "complete") && (i.onreadystatechange = null, t())
    } : i.onload = function () {
        t()
    };
    i.href = n;
    document.getElementsByTagName("head")[0].appendChild(i)
}

var JSON, currentDelElement, currentCommentID, insertUBB;
JSON || (JSON = {}), function () {
    "use strict";

    function i(n) {
        return n < 10 ? "0" + n : n
    }

    function o(n) {
        return e.lastIndex = 0, e.test(n) ? '"' + n.replace(e, function (n) {
            var t = s[n];
            return typeof t == "string" ? t : "\\u" + ("0000" + n.charCodeAt(0).toString(16)).slice(-4)
        }) + '"' : '"' + n + '"'
    }

    function u(i, f) {
        var s, l, h, a, v = n, c, e = f[i];
        e && typeof e == "object" && typeof e.toJSON == "function" && (e = e.toJSON(i));
        typeof t == "function" && (e = t.call(f, i, e));
        switch (typeof e) {
            case"string":
                return o(e);
            case"number":
                return isFinite(e) ? String(e) : "null";
            case"boolean":
            case"null":
                return String(e);
            case"object":
                if (!e) return "null";
                if (n += r, c = [], Object.prototype.toString.apply(e) === "[object Array]") {
                    for (a = e.length, s = 0; s < a; s += 1) c[s] = u(s, e) || "null";
                    return h = c.length === 0 ? "[]" : n ? "[\n" + n + c.join(",\n" + n) + "\n" + v + "]" : "[" + c.join(",") + "]", n = v, h
                }
                if (t && typeof t == "object") for (a = t.length, s = 0; s < a; s += 1) typeof t[s] == "string" && (l = t[s], h = u(l, e), h && c.push(o(l) + (n ? ": " : ":") + h)); else for (l in e) Object.prototype.hasOwnProperty.call(e, l) && (h = u(l, e), h && c.push(o(l) + (n ? ": " : ":") + h));
                return h = c.length === 0 ? "{}" : n ? "{\n" + n + c.join(",\n" + n) + "\n" + v + "}" : "{" + c.join(",") + "}", n = v, h
        }
    }

    typeof Date.prototype.toJSON != "function" && (Date.prototype.toJSON = function () {
        return isFinite(this.valueOf()) ? this.getUTCFullYear() + "-" + i(this.getUTCMonth() + 1) + "-" + i(this.getUTCDate()) + "T" + i(this.getUTCHours()) + ":" + i(this.getUTCMinutes()) + ":" + i(this.getUTCSeconds()) + "Z" : null
    }, String.prototype.toJSON = Number.prototype.toJSON = Boolean.prototype.toJSON = function () {
        return this.valueOf()
    });
    var f = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        e = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        n, r, s = {"\b": "\\b", "\t": "\\t", "\n": "\\n", "\f": "\\f", "\r": "\\r", '"': '\\"', "\\": "\\\\"}, t;
    typeof JSON.stringify != "function" && (JSON.stringify = function (i, f, e) {
        var o;
        if (n = "", r = "", typeof e == "number") for (o = 0; o < e; o += 1) r += " "; else typeof e == "string" && (r = e);
        if (t = f, f && typeof f != "function" && (typeof f != "object" || typeof f.length != "number")) throw new Error("JSON.stringify");
        return u("", {"": i})
    });
    typeof JSON.parse != "function" && (JSON.parse = function (text, reviver) {
        function walk(n, t) {
            var r, u, i = n[t];
            if (i && typeof i == "object") for (r in i) Object.prototype.hasOwnProperty.call(i, r) && (u = walk(i, r), u !== undefined ? i[r] = u : delete i[r]);
            return reviver.call(n, t, i)
        }

        var j;
        if (text = String(text), f.lastIndex = 0, f.test(text) && (text = text.replace(f, function (n) {
                return "\\u" + ("0000" + n.charCodeAt(0).toString(16)).slice(-4)
            })), /^[\],:{}\s]*$/.test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, "]").replace(/(?:^|:|,)(?:\s*\[)+/g, ""))) return j = eval("(" + text + ")"), typeof reviver == "function" ? walk({"": j}, "") : j;
        throw new SyntaxError("JSON.parse");
    })
}();
eval(function (n, t, i, r, u, f) {
    if (u = function (n) {
            return (n < t ? "" : u(parseInt(n / t))) + ((n = n % t) > 35 ? String.fromCharCode(n + 29) : n.toString(36))
        }, !"".replace(/^/, String)) {
        while (i--) f[u(i)] = r[i] || u(i);
        r = [function (n) {
            return f[n]
        }];
        u = function () {
            return "\\w+"
        };
        i = 1
    }
    while (i--) r[i] && (n = n.replace(new RegExp("\\b" + u(i) + "\\b", "g"), r[i]));
    return n
}('K M;I(M)1S 2U("2a\'t 4k M 4K 2g 3l 4G 4H");(6(){6 r(f,e){I(!M.1R(f))1S 3m("3s 15 4R");K a=f.1w;f=M(f.1m,t(f)+(e||""));I(a)f.1w={1m:a.1m,19:a.19?a.19.1a(0):N};H f}6 t(f){H(f.1J?"g":"")+(f.4s?"i":"")+(f.4p?"m":"")+(f.4v?"x":"")+(f.3n?"y":"")}6 B(f,e,a,b){K c=u.L,d,h,g;v=R;5K{O(;c--;){g=u[c];I(a&g.3r&&(!g.2p||g.2p.W(b))){g.2q.12=e;I((h=g.2q.X(f))&&h.P===e){d={3k:g.2b.W(b,h,a),1C:h};1N}}}}5v(i){1S i}5q{v=11}H d}6 p(f,e,a){I(3b.Z.1i)H f.1i(e,a);O(a=a||0;a<f.L;a++)I(f[a]===e)H a;H-1}M=6(f,e){K a=[],b=M.1B,c=0,d,h;I(M.1R(f)){I(e!==1d)1S 3m("2a\'t 5r 5I 5F 5B 5C 15 5E 5p");H r(f)}I(v)1S 2U("2a\'t W 3l M 59 5m 5g 5x 5i");e=e||"";O(d={2N:11,19:[],2K:6(g){H e.1i(g)>-1},3d:6(g){e+=g}};c<f.L;)I(h=B(f,c,b,d)){a.U(h.3k);c+=h.1C[0].L||1}Y I(h=n.X.W(z[b],f.1a(c))){a.U(h[0]);c+=h[0].L}Y{h=f.3a(c);I(h==="[")b=M.2I;Y I(h==="]")b=M.1B;a.U(h);c++}a=15(a.1K(""),n.Q.W(e,w,""));a.1w={1m:f,19:d.2N?d.19:N};H a};M.3v="1.5.0";M.2I=1;M.1B=2;K C=/\\$(?:(\\d\\d?|[$&`\'])|{([$\\w]+)})/g,w=/[^5h]+|([\\s\\S])(?=[\\s\\S]*\\1)/g,A=/^(?:[?*+]|{\\d+(?:,\\d*)?})\\??/,v=11,u=[],n={X:15.Z.X,1A:15.Z.1A,1C:1r.Z.1C,Q:1r.Z.Q,1e:1r.Z.1e},x=n.X.W(/()??/,"")[1]===1d,D=6(){K f=/^/g;n.1A.W(f,"");H!f.12}(),y=6(){K f=/x/g;n.Q.W("x",f,"");H!f.12}(),E=15.Z.3n!==1d,z={};z[M.2I]=/^(?:\\\\(?:[0-3][0-7]{0,2}|[4-7][0-7]?|x[\\29-26-f]{2}|u[\\29-26-f]{4}|c[A-3o-z]|[\\s\\S]))/;z[M.1B]=/^(?:\\\\(?:0(?:[0-3][0-7]{0,2}|[4-7][0-7]?)?|[1-9]\\d*|x[\\29-26-f]{2}|u[\\29-26-f]{4}|c[A-3o-z]|[\\s\\S])|\\(\\?[:=!]|[?*+]\\?|{\\d+(?:,\\d*)?}\\??)/;M.1h=6(f,e,a,b){u.U({2q:r(f,"g"+(E?"y":"")),2b:e,3r:a||M.1B,2p:b||N})};M.2n=6(f,e){K a=f+"/"+(e||"");H M.2n[a]||(M.2n[a]=M(f,e))};M.3c=6(f){H r(f,"g")};M.5l=6(f){H f.Q(/[-[\\]{}()*+?.,\\\\^$|#\\s]/g,"\\\\$&")};M.5e=6(f,e,a,b){e=r(e,"g"+(b&&E?"y":""));e.12=a=a||0;f=e.X(f);H b?f&&f.P===a?f:N:f};M.3q=6(){M.1h=6(){1S 2U("2a\'t 55 1h 54 3q")}};M.1R=6(f){H 53.Z.1q.W(f)==="[2m 15]"};M.3p=6(f,e,a,b){O(K c=r(e,"g"),d=-1,h;h=c.X(f);){a.W(b,h,++d,f,c);c.12===h.P&&c.12++}I(e.1J)e.12=0};M.57=6(f,e){H 6 a(b,c){K d=e[c].1I?e[c]:{1I:e[c]},h=r(d.1I,"g"),g=[],i;O(i=0;i<b.L;i++)M.3p(b[i],h,6(k){g.U(d.3j?k[d.3j]||"":k[0])});H c===e.L-1||!g.L?g:a(g,c+1)}([f],0)};15.Z.1p=6(f,e){H J.X(e[0])};15.Z.W=6(f,e){H J.X(e)};15.Z.X=6(f){K e=n.X.1p(J,14),a;I(e){I(!x&&e.L>1&&p(e,"")>-1){a=15(J.1m,n.Q.W(t(J),"g",""));n.Q.W(f.1a(e.P),a,6(){O(K c=1;c<14.L-2;c++)I(14[c]===1d)e[c]=1d})}I(J.1w&&J.1w.19)O(K b=1;b<e.L;b++)I(a=J.1w.19[b-1])e[a]=e[b];!D&&J.1J&&!e[0].L&&J.12>e.P&&J.12--}H e};I(!D)15.Z.1A=6(f){(f=n.X.W(J,f))&&J.1J&&!f[0].L&&J.12>f.P&&J.12--;H!!f};1r.Z.1C=6(f){M.1R(f)||(f=15(f));I(f.1J){K e=n.1C.1p(J,14);f.12=0;H e}H f.X(J)};1r.Z.Q=6(f,e){K a=M.1R(f),b,c;I(a&&1j e.58()==="3f"&&e.1i("${")===-1&&y)H n.Q.1p(J,14);I(a){I(f.1w)b=f.1w.19}Y f+="";I(1j e==="6")c=n.Q.W(J,f,6(){I(b){14[0]=1f 1r(14[0]);O(K d=0;d<b.L;d++)I(b[d])14[0][b[d]]=14[d+1]}I(a&&f.1J)f.12=14[14.L-2]+14[0].L;H e.1p(N,14)});Y{c=J+"";c=n.Q.W(c,f,6(){K d=14;H n.Q.W(e,C,6(h,g,i){I(g)5b(g){24"$":H"$";24"&":H d[0];24"`":H d[d.L-1].1a(0,d[d.L-2]);24"\'":H d[d.L-1].1a(d[d.L-2]+d[0].L);5a:i="";g=+g;I(!g)H h;O(;g>d.L-3;){i=1r.Z.1a.W(g,-1)+i;g=1Q.3i(g/10)}H(g?d[g]||"":"$")+i}Y{g=+i;I(g<=d.L-3)H d[g];g=b?p(b,i):-1;H g>-1?d[g+1]:h}})})}I(a&&f.1J)f.12=0;H c};1r.Z.1e=6(f,e){I(!M.1R(f))H n.1e.1p(J,14);K a=J+"",b=[],c=0,d,h;I(e===1d||+e<0)e=5D;Y{e=1Q.3i(+e);I(!e)H[]}O(f=M.3c(f);d=f.X(a);){I(f.12>c){b.U(a.1a(c,d.P));d.L>1&&d.P<a.L&&3b.Z.U.1p(b,d.1a(1));h=d[0].L;c=f.12;I(b.L>=e)1N}f.12===d.P&&f.12++}I(c===a.L){I(!n.1A.W(f,"")||h)b.U("")}Y b.U(a.1a(c));H b.L>e?b.1a(0,e):b};M.1h(/\\(\\?#[^)]*\\)/,6(f){H n.1A.W(A,f.2S.1a(f.P+f[0].L))?"":"(?:)"});M.1h(/\\((?!\\?)/,6(){J.19.U(N);H"("});M.1h(/\\(\\?<([$\\w]+)>/,6(f){J.19.U(f[1]);J.2N=R;H"("});M.1h(/\\\\k<([\\w$]+)>/,6(f){K e=p(J.19,f[1]);H e>-1?"\\\\"+(e+1)+(3R(f.2S.3a(f.P+f[0].L))?"":"(?:)"):f[0]});M.1h(/\\[\\^?]/,6(f){H f[0]==="[]"?"\\\\b\\\\B":"[\\\\s\\\\S]"});M.1h(/^\\(\\?([5A]+)\\)/,6(f){J.3d(f[1]);H""});M.1h(/(?:\\s+|#.*)+/,6(f){H n.1A.W(A,f.2S.1a(f.P+f[0].L))?"":"(?:)"},M.1B,6(){H J.2K("x")});M.1h(/\\./,6(){H"[\\\\s\\\\S]"},M.1B,6(){H J.2K("s")})})();1j 2e!="1d"&&(2e.M=M);K 1v=6(){6 r(a,b){a.1l.1i(b)!=-1||(a.1l+=" "+b)}6 t(a){H a.1i("3e")==0?a:"3e"+a}6 B(a){H e.1Y.2A[t(a)]}6 p(a,b,c){I(a==N)H N;K d=c!=R?a.3G:[a.2G],h={"#":"1c",".":"1l"}[b.1o(0,1)]||"3h",g,i;g=h!="3h"?b.1o(1):b.5u();I((a[h]||"").1i(g)!=-1)H a;O(a=0;d&&a<d.L&&i==N;a++)i=p(d[a],b,c);H i}6 C(a,b){K c={},d;O(d 2g a)c[d]=a[d];O(d 2g b)c[d]=b[d];H c}6 w(a,b,c,d){6 h(g){g=g||1P.5y;I(!g.1F){g.1F=g.52;g.3N=6(){J.5w=11}}c.W(d||1P,g)}a.3g?a.3g("4U"+b,h):a.4y(b,h,11)}6 A(a,b){K c=e.1Y.2j,d=N;I(c==N){c={};O(K h 2g e.1U){K g=e.1U[h];d=g.4x;I(d!=N){g.1V=h.4w();O(g=0;g<d.L;g++)c[d[g]]=h}}e.1Y.2j=c}d=e.1U[c[a]];d==N&&b!=11&&1P.1X(e.13.1x.1X+(e.13.1x.3E+a));H d}6 v(a,b){O(K c=a.1e("\\n"),d=0;d<c.L;d++)c[d]=b(c[d],d);H c.1K("\\n")}6 u(a,b){I(a==N||a.L==0||a=="\\n")H a;a=a.Q(/<\/g,"&1y;");a=a.Q(/ {2,}/g,6(c){O(K d="",h=0;h<c.L-1;h++)d+=e.13.1W;H d+" "});I(b!=N)a=v(a,6(c){I(c.L==0)H"";K d="";c=c.Q(/^(&2s;| )+/,6(h){d=h;H""});I(c.L==0)H d;H d+\'<17 1g="\'+b+\'">\'+c+"<\/17>"});H a}6 n(a,b){a.1e("\\n");O(K c="",d=0;d<50;d++)c+="                    ";H a=v(a,6(h){I(h.1i("\\t")==-1)H h;O(K g=0;(g=h.1i("\\t"))!=-1;)h=h.1o(0,g)+c.1o(0,b-g%b)+h.1o(g+1,h.L);H h})}6 x(a){H a.Q(/^\\s+|\\s+$/g,"")}6 D(a,b){I(a.P<b.P)H-1;Y I(a.P>b.P)H 1;Y I(a.L<b.L)H-1;Y I(a.L>b.L)H 1;H 0}6 y(a,b){6 c(k){H k[0]}O(K d=N,h=[],g=b.2D?b.2D:c;(d=b.1I.X(a))!=N;){K i=g(d,b);I(1j i=="3f")i=[1f e.2L(i,d.P,b.23)];h=h.1O(i)}H h}6 E(a){K b=/(.*)((&1G;|&1y;).*)/;H a.Q(e.3A.3M,6(c){K d="",h=N;I(h=b.X(c)){c=h[1];d=h[2]}H\'<a 2h="\'+c+\'">\'+c+"<\/a>"+d})}6 z(){O(K a=1E.36("1k"),b=[],c=0;c<a.L;c++)a[c].3s=="20"&&b.U(a[c]);H b}6 f(a){a=a.1F;K b=p(a,".20",R);a=p(a,".3O",R);K c=1E.4i("3t");I(!(!a||!b||p(a,"3t"))){B(b.1c);r(b,"1m");O(K d=a.3G,h=[],g=0;g<d.L;g++)h.U(d[g].4z||d[g].4A);h=h.1K("\\r");c.39(1E.4D(h));a.39(c);c.2C();c.4C();w(c,"4u",6(){c.2G.4E(c);b.1l=b.1l.Q("1m","")})}}I(1j 3F!="1d"&&1j M=="1d")M=3F("M").M;K e={2v:{"1g-27":"","2i-1s":1,"2z-1s-2t":11,1M:N,1t:N,"42-45":R,"43-22":4,1u:R,16:R,"3V-17":R,2l:11,"41-40":R,2k:11,"1z-1k":11},13:{1W:"&2s;",2M:R,46:11,44:11,34:"4n",1x:{21:"4o 1m",2P:"?",1X:"1v\\n\\n",3E:"4r\'t 4t 1D O: ",4g:"4m 4B\'t 51 O 1z-1k 4F: ",37:\'<!4T 1z 4S "-//4V//3H 4W 1.0 4Z//4Y" "1Z://2y.3L.3K/4X/3I/3H/3I-4P.4J"><1z 4I="1Z://2y.3L.3K/4L/5L"><3J><4N 1Z-4M="5G-5M" 6K="2O/1z; 6J=6I-8" /><1t>6L 1v<\/1t><\/3J><3B 1L="25-6M:6Q,6P,6O,6N-6F;6y-2f:#6x;2f:#6w;25-22:6v;2O-3D:3C;"><T 1L="2O-3D:3C;3w-32:1.6z;"><T 1L="25-22:6A-6E;">1v<\/T><T 1L="25-22:.6C;3w-6B:6R;"><T>3v 3.0.76 (72 73 3x)<\/T><T><a 2h="1Z://3u.2w/1v" 1F="38" 1L="2f:#3y">1Z://3u.2w/1v<\/a><\/T><T>70 17 6U 71.<\/T><T>6T 6X-3x 6Y 6D.<\/T><\/T><T>6t 61 60 J 1k, 5Z <a 2h="6u://2y.62.2w/63-66/65?64=5X-5W&5P=5O" 1L="2f:#3y">5R<\/a> 5V <2R/>5U 5T 5S!<\/T><\/T><\/3B><\/1z>\'}},1Y:{2j:N,2A:{}},1U:{},3A:{6n:/\\/\\*[\\s\\S]*?\\*\\//2c,6m:/\\/\\/.*$/2c,6l:/#.*$/2c,6k:/"([^\\\\"\\n]|\\\\.)*"/g,6o:/\'([^\\\\\'\\n]|\\\\.)*\'/g,6p:1f M(\'"([^\\\\\\\\"]|\\\\\\\\.)*"\',"3z"),6s:1f M("\'([^\\\\\\\\\']|\\\\\\\\.)*\'","3z"),6q:/(&1y;|<)!--[\\s\\S]*?--(&1G;|>)/2c,3M:/\\w+:\\/\\/[\\w-.\\/?%&=:@;]*/g,6a:{18:/(&1y;|<)\\?=?/g,1b:/\\?(&1G;|>)/g},69:{18:/(&1y;|<)%=?/g,1b:/%(&1G;|>)/g},6d:{18:/(&1y;|<)\\s*1k.*?(&1G;|>)/2T,1b:/(&1y;|<)\\/\\s*1k\\s*(&1G;|>)/2T}},16:{1H:6(a){6 b(i,k){H e.16.2o(i,k,e.13.1x[k])}O(K c=\'<T 1g="16">\',d=e.16.2x,h=d.2X,g=0;g<h.L;g++)c+=(d[h[g]].1H||b)(a,h[g]);c+="<\/T>";H c},2o:6(a,b,c){H\'<2W><a 2h="#" 1g="6e 6h\'+b+" "+b+\'">\'+c+"<\/a><\/2W>"},2b:6(a){K b=a.1F,c=b.1l||"";b=B(p(b,".20",R).1c);K d=6(h){H(h=15(h+"6f(\\\\w+)").X(c))?h[1]:N}("6g");b&&d&&e.16.2x[d].2B(b);a.3N()},2x:{2X:["21","2P"],21:{1H:6(a){I(a.V("2l")!=R)H"";K b=a.V("1t");H e.16.2o(a,"21",b?b:e.13.1x.21)},2B:6(a){a=1E.6j(t(a.1c));a.1l=a.1l.Q("47","")}},2P:{2B:6(){K a="68=0";a+=", 18="+(31.30-33)/2+", 32="+(31.2Z-2Y)/2+", 30=33, 2Z=2Y";a=a.Q(/^,/,"");a=1P.6Z("","38",a);a.2C();K b=a.1E;b.6W(e.13.1x.37);b.6V();a.2C()}}}},35:6(a,b){K c;I(b)c=[b];Y{c=1E.36(e.13.34);O(K d=[],h=0;h<c.L;h++)d.U(c[h]);c=d}c=c;d=[];I(e.13.2M)c=c.1O(z());I(c.L===0)H d;O(h=0;h<c.L;h++){O(K g=c[h],i=a,k=c[h].1l,j=3W 0,l={},m=1f M("^\\\\[(?<2V>(.*?))\\\\]$"),s=1f M("(?<27>[\\\\w-]+)\\\\s*:\\\\s*(?<1T>[\\\\w-%#]+|\\\\[.*?\\\\]|\\".*?\\"|\'.*?\')\\\\s*;?","g");(j=s.X(k))!=N;){K o=j.1T.Q(/^[\'"]|[\'"]$/g,"");I(o!=N&&m.1A(o)){o=m.X(o);o=o.2V.L>0?o.2V.1e(/\\s*,\\s*/):[]}l[j.27]=o}g={1F:g,1n:C(i,l)};g.1n.1D!=N&&d.U(g)}H d},1M:6(a,b){K c=J.35(a,b),d=N,h=e.13;I(c.L!==0)O(K g=0;g<c.L;g++){b=c[g];K i=b.1F,k=b.1n,j=k.1D,l;I(j!=N){I(k["1z-1k"]=="R"||e.2v["1z-1k"]==R){d=1f e.4l(j);j="4O"}Y I(d=A(j))d=1f d;Y 6H;l=i.3X;I(h.2M){l=l;K m=x(l),s=11;I(m.1i("<![6G[")==0){m=m.4h(9);s=R}K o=m.L;I(m.1i("]]\\>")==o-3){m=m.4h(0,o-3);s=R}l=s?m:l}I((i.1t||"")!="")k.1t=i.1t;k.1D=j;d.2Q(k);b=d.2F(l);I((i.1c||"")!="")b.1c=i.1c;i.2G.74(b,i)}}},2E:6(a){w(1P,"4k",6(){e.1M(a)})}};e.2E=e.2E;e.1M=e.1M;e.2L=6(a,b,c){J.1T=a;J.P=b;J.L=a.L;J.23=c;J.1V=N};e.2L.Z.1q=6(){H J.1T};e.4l=6(a){6 b(j,l){O(K m=0;m<j.L;m++)j[m].P+=l}K c=A(a),d,h=1f e.1U.5Y,g=J,i="2F 1H 2Q".1e(" ");I(c!=N){d=1f c;O(K k=0;k<i.L;k++)(6(){K j=i[k];g[j]=6(){H h[j].1p(h,14)}})();d.28==N?1P.1X(e.13.1x.1X+(e.13.1x.4g+a)):h.2J.U({1I:d.28.17,2D:6(j){O(K l=j.17,m=[],s=d.2J,o=j.P+j.18.L,F=d.28,q,G=0;G<s.L;G++){q=y(l,s[G]);b(q,o);m=m.1O(q)}I(F.18!=N&&j.18!=N){q=y(j.18,F.18);b(q,j.P);m=m.1O(q)}I(F.1b!=N&&j.1b!=N){q=y(j.1b,F.1b);b(q,j.P+j[0].5Q(j.1b));m=m.1O(q)}O(j=0;j<m.L;j++)m[j].1V=c.1V;H m}})}};e.4j=6(){};e.4j.Z={V:6(a,b){K c=J.1n[a];c=c==N?b:c;K d={"R":R,"11":11}[c];H d==N?c:d},3Y:6(a){H 1E.4i(a)},4c:6(a,b){K c=[];I(a!=N)O(K d=0;d<a.L;d++)I(1j a[d]=="2m")c=c.1O(y(b,a[d]));H J.4e(c.6b(D))},4e:6(a){O(K b=0;b<a.L;b++)I(a[b]!==N)O(K c=a[b],d=c.P+c.L,h=b+1;h<a.L&&a[b]!==N;h++){K g=a[h];I(g!==N)I(g.P>d)1N;Y I(g.P==c.P&&g.L>c.L)a[b]=N;Y I(g.P>=c.P&&g.P<d)a[h]=N}H a},4d:6(a){K b=[],c=2u(J.V("2i-1s"));v(a,6(d,h){b.U(h+c)});H b},3U:6(a){K b=J.V("1M",[]);I(1j b!="2m"&&b.U==N)b=[b];a:{a=a.1q();K c=3W 0;O(c=c=1Q.6c(c||0,0);c<b.L;c++)I(b[c]==a){b=c;1N a}b=-1}H b!=-1},2r:6(a,b,c){a=["1s","6i"+b,"P"+a,"6r"+(b%2==0?1:2).1q()];J.3U(b)&&a.U("67");b==0&&a.U("1N");H\'<T 1g="\'+a.1K(" ")+\'">\'+c+"<\/T>"},3Q:6(a,b){K c="",d=a.1e("\\n").L,h=2u(J.V("2i-1s")),g=J.V("2z-1s-2t");I(g==R)g=(h+d-1).1q().L;Y I(3R(g)==R)g=0;O(K i=0;i<d;i++){K k=b?b[i]:h+i,j;I(k==0)j=e.13.1W;Y{j=g;O(K l=k.1q();l.L<j;)l="0"+l;j=l}a=j;c+=J.2r(i,k,a)}H c},49:6(a,b){a=x(a);K c=a.1e("\\n");J.V("2z-1s-2t");K d=2u(J.V("2i-1s"));a="";O(K h=J.V("1D"),g=0;g<c.L;g++){K i=c[g],k=/^(&2s;|\\s)+/.X(i),j=N,l=b?b[g]:d+g;I(k!=N){j=k[0].1q();i=i.1o(j.L);j=j.Q(" ",e.13.1W)}i=x(i);I(i.L==0)i=e.13.1W;a+=J.2r(g,l,(j!=N?\'<17 1g="\'+h+\' 5N">\'+j+"<\/17>":"")+i)}H a},4f:6(a){H a?"<4a>"+a+"<\/4a>":""},4b:6(a,b){6 c(l){H(l=l?l.1V||g:g)?l+" ":""}O(K d=0,h="",g=J.V("1D",""),i=0;i<b.L;i++){K k=b[i],j;I(!(k===N||k.L===0)){j=c(k);h+=u(a.1o(d,k.P-d),j+"48")+u(k.1T,j+k.23);d=k.P+k.L+(k.75||0)}}h+=u(a.1o(d),c()+"48");H h},1H:6(a){K b="",c=["20"],d;I(J.V("2k")==R)J.1n.16=J.1n.1u=11;1l="20";J.V("2l")==R&&c.U("47");I((1u=J.V("1u"))==11)c.U("6S");c.U(J.V("1g-27"));c.U(J.V("1D"));a=a.Q(/^[ ]*[\\n]+|[\\n]*[ ]*$/g,"").Q(/\\r/g," ");b=J.V("43-22");I(J.V("42-45")==R)a=n(a,b);Y{O(K h="",g=0;g<b;g++)h+=" ";a=a.Q(/\\t/g,h)}a=a;a:{b=a=a;h=/<2R\\s*\\/?>|&1y;2R\\s*\\/?&1G;/2T;I(e.13.46==R)b=b.Q(h,"\\n");I(e.13.44==R)b=b.Q(h,"");b=b.1e("\\n");h=/^\\s*/;g=4Q;O(K i=0;i<b.L&&g>0;i++){K k=b[i];I(x(k).L!=0){k=h.X(k);I(k==N){a=a;1N a}g=1Q.4q(k[0].L,g)}}I(g>0)O(i=0;i<b.L;i++)b[i]=b[i].1o(g);a=b.1K("\\n")}I(1u)d=J.4d(a);b=J.4c(J.2J,a);b=J.4b(a,b);b=J.49(b,d);I(J.V("41-40"))b=E(b);1j 2H!="1d"&&2H.3S&&2H.3S.1C(/5s/)&&c.U("5t");H b=\'<T 1c="\'+t(J.1c)+\'" 1g="\'+c.1K(" ")+\'">\'+(J.V("16")?e.16.1H(J):"")+\'<3Z 5z="0" 5H="0" 5J="0">\'+J.4f(J.V("1t"))+"<3T><3P>"+(1u?\'<2d 1g="1u">\'+J.3Q(a)+"<\/2d>":"")+\'<2d 1g="17"><T 1g="3O">\'+b+"<\/T><\/2d><\/3P><\/3T><\/3Z><\/T>"},2F:6(a){I(a===N)a="";J.17=a;K b=J.3Y("T");b.3X=J.1H(a);J.V("16")&&w(p(b,".16"),"5c",e.16.2b);J.V("3V-17")&&w(p(b,".17"),"56",f);H b},2Q:6(a){J.1c=""+1Q.5d(1Q.5n()*5k).1q();e.1Y.2A[t(J.1c)]=J;J.1n=C(e.2v,a||{});I(J.V("2k")==R)J.1n.16=J.1n.1u=11},5j:6(a){a=a.Q(/^\\s+|\\s+$/g,"").Q(/\\s+/g,"|");H"\\\\b(?:"+a+")\\\\b"},5f:6(a){J.28={18:{1I:a.18,23:"1k"},1b:{1I:a.1b,23:"1k"},17:1f M("(?<18>"+a.18.1m+")(?<17>.*?)(?<1b>"+a.1b.1m+")","5o")}}};H e}();1j 2e!="1d"&&(2e.1v=1v);', 62, 441, "||||||function|||||||||||||||||||||||||||||||||||||return|if|this|var|length|XRegExp|null|for|index|replace|true||div|push|getParam|call|exec|else|prototype||false|lastIndex|config|arguments|RegExp|toolbar|code|left|captureNames|slice|right|id|undefined|split|new|class|addToken|indexOf|typeof|script|className|source|params|substr|apply|toString|String|line|title|gutter|SyntaxHighlighter|_xregexp|strings|lt|html|test|OUTSIDE_CLASS|match|brush|document|target|gt|getHtml|regex|global|join|style|highlight|break|concat|window|Math|isRegExp|throw|value|brushes|brushName|space|log|vars|http|syntaxhighlighter|expandSource|size|css|case|font|Fa|name|htmlScript|dA|can|handler|gm|td|exports|color|in|href|first|discoveredBrushes|light|collapse|object|cache|getButtonHtml|trigger|pattern|getLineHtml|nbsp|numbers|parseInt|defaults|com|items|www|pad|highlighters|execute|focus|func|all|getDiv|parentNode|navigator|INSIDE_CLASS|regexList|hasFlag|Match|useScriptTags|hasNamedCapture|text|help|init|br|input|gi|Error|values|span|list|250|height|width|screen|top|500|tagName|findElements|getElementsByTagName|aboutDialog|_blank|appendChild|charAt|Array|copyAsGlobal|setFlag|highlighter_|string|attachEvent|nodeName|floor|backref|output|the|TypeError|sticky|Za|iterate|freezeTokens|scope|type|textarea|alexgorbatchev|version|margin|2010|005896|gs|regexLib|body|center|align|noBrush|require|childNodes|DTD|xhtml1|head|org|w3|url|preventDefault|container|tr|getLineNumbersHtml|isNaN|userAgent|tbody|isLineHighlighted|quick|void|innerHTML|create|table|links|auto|smart|tab|stripBrs|tabs|bloggerMode|collapsed|plain|getCodeLinesHtml|caption|getMatchesHtml|findMatches|figureOutLineNumbers|removeNestedMatches|getTitleHtml|brushNotHtmlScript|substring|createElement|Highlighter|load|HtmlScript|Brush|pre|expand|multiline|min|Can|ignoreCase|find|blur|extended|toLowerCase|aliases|addEventListener|innerText|textContent|wasn|select|createTextNode|removeChild|option|same|frame|xmlns|dtd|twice|1999|equiv|meta|htmlscript|transitional|1E3|expected|PUBLIC|DOCTYPE|on|W3C|XHTML|TR|EN|Transitional||configured|srcElement|Object|after|run|dblclick|matchChain|valueOf|constructor|default|switch|click|round|execAt|forHtmlScript|token|gimy|functions|getKeywords|1E6|escape|within|random|sgi|another|finally|supply|MSIE|ie|toUpperCase|catch|returnValue|definition|event|border|imsx|constructing|one|Infinity|from|when|Content|cellpadding|flags|cellspacing|try|xhtml|Type|spaces|2930402|hosted_button_id|lastIndexOf|donate|active|development|keep|to|xclick|_s|Xml|please|like|you|paypal|cgi|cmd|webscr|bin|highlighted|scrollbars|aspScriptTags|phpScriptTags|sort|max|scriptScriptTags|toolbar_item|_|command|command_|number|getElementById|doubleQuotedString|singleLinePerlComments|singleLineCComments|multiLineCComments|singleQuotedString|multiLineDoubleQuotedString|xmlComments|alt|multiLineSingleQuotedString|If|https|1em|000|fff|background|5em|xx|bottom|75em|Gorbatchev|large|serif|CDATA|continue|utf|charset|content|About|family|sans|Helvetica|Arial|Geneva|3em|nogutter|Copyright|syntax|close|write|2004|Alex|open|JavaScript|highlighter|July|02|replaceChild|offset|83".split("|"), 0, {}));
eval(function (n, t, i, r, u, f) {
    if (u = function (n) {
            return (n < t ? "" : u(parseInt(n / t))) + ((n = n % t) > 35 ? String.fromCharCode(n + 29) : n.toString(36))
        }, !"".replace(/^/, String)) {
        while (i--) f[u(i)] = r[i] || u(i);
        r = [function (n) {
            return f[n]
        }];
        u = function () {
            return "\\w+"
        };
        i = 1
    }
    while (i--) r[i] && (n = n.replace(new RegExp("\\b" + u(i) + "\\b", "g"), r[i]));
    return n
}('(2(){1 h=5;h.I=2(){2 n(c,a){4(1 d=0;d<c.9;d++)i[c[d]]=a}2 o(c){1 a=r.H("J"),d=3;a.K=c;a.M="L/t";a.G="t";a.u=a.v=2(){6(!d&&(!8.7||8.7=="F"||8.7=="z")){d=q;e[c]=q;a:{4(1 p y e)6(e[p]==3)B a;j&&5.C(k)}a.u=a.v=x;a.D.O(a)}};r.N.R(a)}1 f=Q,l=h.P(),i={},e={},j=3,k=x,b;5.T=2(c){k=c;j=q};4(b=0;b<f.9;b++){1 m=f[b].w?f[b]:f[b].S(/\\s+/),g=m.w();n(m,g)}4(b=0;b<l.9;b++)6(g=i[l[b].E.A]){e[g]=3;o(g)}}})();', 56, 56, "|var|function|false|for|SyntaxHighlighter|if|readyState|this|length|||||||||||||||||true|document||javascript|onload|onreadystatechange|pop|null|in|complete|brush|break|highlight|parentNode|params|loaded|language|createElement|autoloader|script|src|text|type|body|removeChild|findElements|arguments|appendChild|split|all".split("|"), 0, {}));
$.ajaxSetup({type: "post", dataType: "json", contentType: "application/json; charset=utf-8"});
var isSyntaxHighlighted = !1, c_has_follwed = !1, comment_maxId = 0, comment_maxDate = "";
window.alert = function (n) {
    try {
        console.log("alert: " + n)
    } catch (t) {
    }
    return !0
};
document.open = function () {
};
$.fn.extend({
    selection: function () {
        var n = "", u = this.get(0).document, t, i, r;
        return u ? (t = u.selection.createRange(), t.text.length > 0 && (n = t.text)) : (this.get(0).selectionStart || this.get(0).selectionStart == "0") && (i = this.get(0).selectionStart, r = this.get(0).selectionEnd, i != r && (n = this.get(0).value.substring(i, r))), $.trim(n)
    }, parseHtml: function (n) {
        var t = this.get(0).document;
        if (t) this.get(0).focus(), t.selection.createRange().collapse, this.get(0).document.selection.createRange().text = n; else if (this.get(0).selectionStart || this.get(0).selectionStart == "0") {
            var r = this.get(0).selectionStart, u = this.get(0).selectionEnd, i = this.get(0).value,
                f = i.substring(0, r), e = i.substring(u);
            this.get(0).value = f + n + e
        }
    }
});
insertUBB = function (n, t) {
    var i = $("#" + n).selection(), r;
    t != "quote" || i || (window.getSelection ? i = window.getSelection().toString() : document.getSelection ? i = document.getSelection().toString() : document.selection && (i = document.selection.createRange().text), $("#" + n).focus());
    i ? (r = t, t.indexOf("=") >= 0 && (r = t.substring(0, t.indexOf("="))), $("#" + n).parseHtml("[" + t + "]" + i + "[/" + r + "]")) : $("#tip_comment").html("请选择文字")
};
$(function () {
    var n = $("#cnblogs_post_body div.cnblogs_code"), i = /^\s*1/gi, t = /<br\s*\/?>/gi;
    n.length && (loadEncoderJs(), $.each(n, function () {
        if (i.test($(this).text())) {
            var n = $(this).html();
            navigator.userAgent.search("MSIE") >= 0 && n.indexOf("<pre>") > -1 && t.test(n) && (n = n.replace(t, "\r\n"), $(this).html(n))
        }
        showCopyCode(this)
    }))
});
$(function () {
    google_ga()
});