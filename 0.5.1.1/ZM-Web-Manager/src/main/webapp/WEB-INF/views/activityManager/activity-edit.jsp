<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<%@ taglib uri="/WEB-INF/tag/fileuploadtag.tld" prefix="u"%>

<html>
<head>
    <title>activity-newAdd.jsp</title><!-- 添加活动 -->
	<!-- 静态引入 -->
	<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>

    <script type="text/javascript" src="/resources/ext/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="/resources/ext/jquery-validation/localization/messages_zh.js"></script>
    <!--引入wangEditor.css-->
    <link rel="stylesheet" type="text/css" href="/resources/ext/wangEditor-2.1.20/css/wangEditor.min.css">
    <script type="text/javascript" src="/resources/ext/wangEditor-2.1.20/js/wangEditor.min.js"></script>

    <link href="/resources/common/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
	<script src="/resources/common/js/moment-with-locales.min.js" type="text/javascript"></script>
	<script  src="/resources/common/js/bootstrap-datetimepicker.js" type="text/javascript"></script>

</head>
<body class="body">

<!--基础活动设置-->
<form action="/20203031" method="post" id="activityForm">
    <input type="hidden" name="token" value="${token}">
    <input type="hidden" name="puk" value="${data.puk}">
    <table>
		<tr class="trparam" >
			<td>
				<table>
					<tr>
						<td><input type="button" class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()"></td>
						<td align="right"></td>
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
                        <tr>
                            <td colspan="3">
                                <table>
                                    <tr>
                                        <td class="tdparam">编号：</td>
                                        <td class="tdblank300">
                                        <div></div>
                                        <input type="text" class="form-control" name="activityNumber" id="activityNumber" value="${data.activityNumber}"/>
                                        </td>
                                        <td class="tdparam">活动模式：</td>
                                        <td class="tdblank200">
                                            <input type="hidden"  id="activityTypeTypeHiddenInput">
                                            <select class="form-control tdblank120" id="activityTypeType"  name="activityTypeType" >
                                                <option value="">请选择</option>
                                                <option value="1000">日常签到</option>
                                                <option value="2000">团体活动</option>
                                                <option value="3000">优惠卷码</option>
                                            </select>
                                        </td>
                                        <td class="tdparam">活动分类：</td>
                                        <td>
                                            <input type="hidden" name="activityTypeName" id="activityTypeName">
                                            <input type="hidden"  id="activityTypeIdHiddenInput">
                                            <select class="form-control tdblank120" id="activityTypeId" name="activityTypeId" >
                                                <c:forEach var="item" items="${activityTypeList}">
                                                <option value="${item.puk}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr >
                            <td class="tdparam">活动名称：</td>
                            <td><input type="text" class="form-control" name="activityName" value="${data.activityName}"/></td>
                            <td class="tdblank120">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <table>
                                    <tr>
                                        <td class="tdparam">开始时间：</td>
                                        <td class="tdblank300">
                                            <div style="position:relative;"><input type='text' class="form-control " name="activityStartTime" id="activityStartTime" value="${data.activityStartTime}"/></div>
                                        </td>
                                        <td class="tdparam">结束时间：</td>
                                        <td class="tdblank300">
                                            <div style="position:relative;"><input type='text' class="form-control " name="activityEndTime" id="activityEndTime" value="${data.activityEndTime}"/></div>
                                        </td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr >
                            <td class="tdparam">活动海报：</td>
                            <td>
                                <input type="hidden" name="lookPic"  id="lookPic"/>
                                <u:fileupload height="200" width="200" id="upload1" type="1" token="${token}" target="lookPic" value="${data.lookPic}"/>
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr >
                            <td class="tdparam">活动规则：</td>
                            <td><textarea class="form-control" rows="8" name="activityRule" id="activityRuleTextarea">${data.activityRule}</textarea></td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <table>
                        <tr>
                            <td></td>
                            <td align="right"><input class="btn btn-primary btnright my-btn" value="下一步" type="submit" /></td>
                        </tr>
                    </table>
                </td>
            </tr>
    </table>
</form>

<script type="text/javascript">
    <c:if test="${JumpType == 1}">
        parent.changeNavbar('添加活动','首页》运营与统计》活动管理》添加活动》添加活动');
        $('#activityForm').attr('action','/20203031?JumpType=1');
    </c:if>
    <c:if test="${JumpType == 2}">
        parent.changeNavbar('修改活动','首页》运营与统计》活动管理》修改活动》修改活动');
        $('#activityForm').attr('action','/20203031?JumpType=2');
    </c:if>


    $(function(){
        $('#activityTypeType').val('${data.activityTypeType}');
        if (${JumpType == 2}){
            $('#activityTypeType').attr('disabled','disabled');
            $('#activityTypeTypeHiddenInput').attr('name','activityTypeType');
            $('#activityTypeTypeHiddenInput').val('${data.activityTypeType}');
            $('#activityTypeId').attr('disabled','disabled');
            $('#activityTypeIdHiddenInput').attr('name','activityTypeId');
            $('#activityTypeIdHiddenInput').val('${data.activityTypeId}');
        }
    });

    $('#activityStartTime').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: moment.locale('zh-cn')
    });
    $('#activityEndTime').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: moment.locale('zh-cn')
    });

    //文本编辑器
    var editor = new wangEditor('activityRuleTextarea');
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



    //=====================公共变量===================
	//活动验证
    $("#activityForm").validate({
        //调试模式,验证成功了也不会发生跳转
//        debug: true,
        rules: {
            activityNumber: "required",
            activityTypeId: "required",
            activityTypeType: "required",
            activityName: "required",
            activityStartTime: "required",
            activityEndTime: "required"
        },
        messages: {
            activityNumber: "不能为空,请重新输入!",
            activityTypeId: "不能为空,请重新输入!",
            activityTypeType: "不能为空,请重新输入!",
            activityName: "不能为空,请重新输入!",
            activityStartTime: "不能为空,请重新输入!",
            activityEndTime: "不能为空,请重新输入!"
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
        	$("#activityTypeName").val($("#activityTypeId").find("option:selected").text());
            form.submit();
        }
    });

</script>

</body>
</html>
