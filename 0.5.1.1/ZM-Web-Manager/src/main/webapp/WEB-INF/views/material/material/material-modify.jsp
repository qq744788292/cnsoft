<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/fileuploadtag.tld" prefix="u" %>
<html>
<head>
    <title>material-modify.jsp</title><!-- 素材素材 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
		<c:import url="/WEB-INF/views/config/jquery-validation.jsp" charEncoding="UTF-8"/>
    <link rel="stylesheet" type="text/css" href="/resources/wangEditor-2.1.20/css/wangEditor.min.css">
    <script type="text/javascript" src="/resources/wangEditor-2.1.20/js/wangEditor.min.js"></script>


</head>
<body >

<form method="post" action="/70202050?token=${token}" id="addOrModifyForm">
    <input type="hidden" name="mode" value="${mode}">
    <input type="hidden" name="puk" value="${data.puk}" id="puk">
    <table>
		<tr class="trparam" >
            <td>
                <table >
                    <tr>
                        <td><input type="button" class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()"></td>
                        <td align="right"><input type="submit" class="btn btn-primary btnright" value="保存" ></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr class="pageht">
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>
                <table class="table-striped table-hover">
                    <tr >
                        <td class="tdparam" style="text-align: center;width:300px;">封面图:</td>
                        <td class="tdparam">作者:</td>
                        <td style="width:300px;"><input type="text" class="form-control" name="author" value="${data.author}"></td>
                        <td class="tdparam">发布时间:</td>
                        <td style="width:100px;"><input type="text" class="form-control" name="onlineTime" value="${data.onlineTime}"></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr class="pageht">
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="6">
                            <table>
                                <tr>
                                    <td rowspan="4" style="text-align: center;width:300px;">
                                        <input type="hidden" name="lookPicUrl" id="lookPicUrl" value="${data.lookPicUrl}">
                                        <u:fileupload height="268" width="202" id="upload1" type="1" token="${token}" target="lookPicUrl" value="${data.lookPicUrl}"/>
                                    </td>
                                    <td class="tdparam">标题:</td>
                                    <td colspan="3"><input type="text" class="form-control" name="title" id="title" value="${data.title}"></td>
                                    <td class="tdblank120">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td class="tdparam">简介:</td>
                                    <td colspan="3">
                                        <textarea name="synopsis" cols="30" rows="5" class="form-control">${data.synopsis}</textarea>
                                    </td>
                                    <td>&nbsp;</td>
                                </tr>

                                <tr>
                                    <td class="tdparam">素材分类:</td>
                                    <td style="width:300px;">
                                        <select  class="form-control" style="width:200px;" name="classify">
                                            <option value="">--请选择--</option>
                                            <option value="1">单集</option>
                                            <option value="2">合集</option>
                                        </select>
                                    </td>
                                    <td class="tdparam">素材类型:</td>
                                    <td>
                                        <select class="form-control" style="width:200px;" name="type" id="type" onchange="updateType();">
                                            <option value="">-- 请选择 --</option>
                                            <option value="1">图文</option>
                                            <option value="2">音频</option>
                                            <option value="3">视频</option>
                                            <option value="9">第三方</option>
                                        </select>
                                    </td>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr height="400" id="content">
            <td colspan="4">
                <table>
                    <tr>
                        <td style="width:5px;">&nbsp;</td>
                        <td>
                            <%--  <e:editor token="${token}" id="textarea1" name="content" style="height:400px;max-height:400px;" value="${data.content}"></e:editor> --%>
                            <textarea id="textarea1"  name="content" style="height:400px;max-height:400px;">
                                ${data.content}
                            </textarea>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="display: none;" id="materialUrl">
            <td colspan="4">
                <table>
                    <tr height="40px">
                        <td style="text-align: center;width:300px;">&nbsp;</td>
                        <td align="center" class="tdparam">素材地址:</td>
                        <td><input type="text" class="form-control" name="materialUrl" value="${data.materialUrl}"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    <c:if test="${mode == 1}">
    parent.changeNavbar('添加素材','首页 > 资源管理 > 文章管理 > 素材管理 > 添加素材');
    </c:if>
    <c:if test="${mode == 2}">
    parent.changeNavbar('素材素材','首页 > 资源管理 > 文章管理 > 素材管理 > 素材素材');
    <%--$("#classifyIdSelect").val('${data.classifyId}');--%>
    </c:if>

    $("#addOrModifyForm").validate({
        //调试模式,验证成功了也不会发生跳转
        //debug: true,
        rules: {
            classifyId : "required",
            tagName : {
                required:true,
                remote:{
                    type:"POST",
                    url:"/70202090", //请求地址
                    data:{
                        tagName:function(){ return $("#tagName").val(); },
                        currentPuk:function(){ return $("#puk").val(); },
                        mode:"${mode}",
                        token: "${token}"
                    }
                }
            },
            weight : "required",
            tagUrl : "required",
        },
        messages: {
            classifyId:"不能为空,请重新输入!",
            tagName:{
                required:"不能为空,请重新输入!",
                remote:"已存在,请重新输入!"
            },
            weight:"不能为空,请重新输入!",
            tagUrl:"不能为空,请重新输入!",
        },
        errorClass: "text-danger",
        highlight: function (element, errorClass) {
            //给输入框添加红色外框
            $(element).closest("div.form-group").addClass("has-error");
        },
        unhighlight: function (element, errorClass) {
            $(element).closest("div.form-group").removeClass("has-error");
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
</script>
<script>
    var editor = new wangEditor('textarea1');

    // 颜色
    editor.config.colors = {
        '#000000': '黑色',
        '#696969 ': '标准灰',
        '#ff8c00': 'logo橙'
    };
    // 字体
    editor.config.familys = [
        '宋体', '黑体', '楷体', '微软雅黑',
        'Arial', 'Verdana', 'Georgia'
    ];
    // 上传图片服务地址
    editor.config.uploadImgUrl = '99999012';
    editor.config.uploadImgFileName = 'upFile';
    // 配置自定义参数（举例）
    editor.config.uploadParams = {
        token: '${token}',
        jobId: '2'
    };
    editor.config.uploadHeaders = {
        'Accept-Type' : 'multipart/form-data'
    };
    // 隐藏掉插入网络图片功能。该配置，只有在你正确配置了图片上传功能之后才可用。
    //editor.config.hideLinkImg = true;
    editor.config.pasteFilter = true;
    //      editor.config.pasteText = true;
    editor.create();
</script>
</body>
</html>
