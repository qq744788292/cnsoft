<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<%@ taglib uri="/WEB-INF/tag/fileuploadtag.tld" prefix="u"%>

<html>
<head>
    <title>activity-share.jsp</title><!-- 添加活动 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>

    <script type="text/javascript" src="/resources/ext/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="/resources/ext/jquery-validation/localization/messages_zh.js"></script>
	<link href="/resources/common/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
	<script src="/resources/common/js/moment-with-locales.min.js" type="text/javascript"></script>
	<script  src="/resources/common/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
		<style>

			.flag-style{
				background: #f5f5f5;
				margin-left: -15px;
				height: 40px;
				line-height: 40px;
				text-align: center;
			}
			.row-comm{
				margin-top: 10px;
			}

			.cums tr td{
				width: 60px;
				border: 1px solid #aaa;
				text-align: center;
			}

			.margin-container{
				margin: 0 40px;
			}
		</style>
</head>
<body class="body">
<form action="/202030613?token=${token}" method="post" id="shareForm" >
    <input type="hidden" value="${data.puk}" name="puk">
    <input type="hidden" value="${JumpType}" name="JumpType">
	<table>
		<tr class="trparam" >
			<td><input type="button" class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()"></td>
			<td align="right"><input type="submit" class="btn btn-primary btnright btnleft" value="确定"/></td>
		</tr>
	</table>
    <div class="container-fluid margin-container">
            <!--最外层包裹div，用于显示隐藏控制-->
				<div class="online-activity">
					<!--奖品与分享设置最外层包裹div-->
					<div class="prizeAndShare-set">
						
						<div class="row row-comm">
							<div class="col-xs-12">
								<div class="col-xs-2 flag-style">
									<span>分享标题</span>
								</div>
								<div class="col-xs-10">
									<input type="text" class="form-control" name="shareTitle" value="${data.shareTitle}"/>
								</div>
							</div>
						</div>
						
						<div class="row row-comm">
							<div class="col-xs-12">
								<div class="col-xs-2 flag-style">
									<span>分享正文</span>
								</div>
								<div class="col-xs-10">
									<textarea class="form-control" rows="5" name="shareText" >${data.shareText}</textarea>
								</div>
							</div>
						</div>
						
						<div class="row row-comm">
							<div class="col-xs-12">
								<div class="col-xs-2 flag-style">
									<span>分享图标</span>
								</div>
								<div class="col-xs-1">
									<div class="col-xs-3">
									</div>
									<input type="hidden" name="sharePic"  id="sharePic"/>
	                                <u:fileupload height="200" width="200" id="upload1" type="1" token="${token}" target="sharePic" value="${data.sharePic}"/>
	                            </div>
							</div>
						</div>
						
						<div class="row row-comm">
							<div class="col-xs-12">
								<div class="col-xs-2 flag-style">
									<span>是否发布</span>
								</div>
								<div class="col-xs-10">
									<select class="form-control" name="isSened" id="isSened">
                                        <option value="">请选择</option>
                                        <option value="1" selected="selected">是</option>
		                            	<option value="2">否</option>
		                            </select>
								</div>
							</div>
						</div>
						
					</div>
					<!--奖品与分享设置最外层包裹div-->
				</div>

			<!--基础活动设置-->

		</div>
</form>
<script type="text/javascript">
    <c:if test="${JumpType == 1}">
        parent.changeNavbar('添加分享活动','首页》运营与统计》活动管理》添加活动》添加分享活动');
    </c:if>
    <c:if test="${JumpType == 2}">
        parent.changeNavbar('修改分享活动','首页》运营与统计》活动管理》修改活动》修改分享活动');
    </c:if>

    $(function(){
        $('#isSened').val('${data.isSened}');
    });
    $("#shareForm").validate({
        //调试模式,验证成功了也不会发生跳转
//        debug: true,
        rules: {
            shareTitle: "required",
            shareText: "required",
        },
        messages: {
            shareTitle: "不能为空,请重新输入!",
            shareText: "不能为空,请重新输入!",
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

</body>
</html>
