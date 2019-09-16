<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>system-Menu-url-add</title><!-- 添加菜单 -->
		<!-- 静态引入 -->
	<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
	<script type="text/javascript" src="/resources/ext/jquery-validation/jquery.validate.js"></script>
	<script type="text/javascript" src="/resources/ext/jquery-validation/localization/messages_zh.js"></script>
</head>
<body class="body">

<form method="post" action="/90102050?token=${token}" id="addForm">
    <input type="hidden" name="pageCurrent"  value="1">
    <input type="hidden" name="pageLimit" value="10">
    <input type="hidden" name="token" value="${token}">
    <input type="hidden" name="model" value="${model}">
    <table >
        <tr class="trparam">
            <td>
                <table>
                    <tr>
                        <td><input type="button" class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()"></td>
                        <td align="right"><input type="submit"  class="btn btn-primary btnright"  value="保存" ></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr class="pageht">
            <td style="width:120px;">&nbsp;</td>
        </tr>
        <tr >
            <td>
                <table class="table-striped table-hover">
                    <tr  >
                        <td class="tdparam">菜单ID:</td>
                        <td ><input  type="text" class="form-control" name="puk"  /></td>
                        <td  class="tdblank120">&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">上级菜单ID:</td>
                        <td ><input  type="text" class="form-control" name="fatherMenuId"  /></td>
                        <td  >&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">菜单级别:</td>
                        <td ><input type="radio" name="menuLevel" value="1">一级菜单&nbsp;
							&nbsp;<input type="radio" name="menuLevel" value="2">二级菜单&nbsp;
							&nbsp;<input type="radio" name="menuLevel" value="3">三级菜单
						</td>
                        <td  >&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">菜单名称:</td>
                        <td ><input  type="text" class="form-control" name="menuName" /></td>
                        <td  >&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">菜单类别:</td>
                        <td >
							<input type="radio" name="menuType" value="1">本地栏目&nbsp;
							&nbsp;<input type="radio" name="menuType" value="2">远程页面&nbsp;
							&nbsp;<input type="radio" name="menuType" value="3">内部页面
						</td>
                        <td  >&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">菜单内容:</td>
                        <td ><input  type="text" class="form-control" name="menuUrl"  /></td>
                        <td  >&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">菜单排序:</td>
                        <td ><input  type="text" class="form-control" name="sortNum" /></td>
                        <td  >&nbsp;</td>
                    </tr>
                  <!--   <tr >
                        <td class="tdparam">菜单等级:</td>
                        <td ><input  type="text" class="form-control" name="menuLevel"  /></td>
                        <td  >&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">备注:</td>
                        <td ><textarea  class="form-control" name="meno"  cols="30" rows="5"></textarea></td>
                        <td  >&nbsp;</td>
                    </tr> -->
                </table>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    parent.changeNavbar('添加菜单','首页》系统管理》菜单管理》添加菜单');
	$(function(){
		$("#addForm").validate({
	        //调试模式,验证成功了也不会发生跳转
	        //debug: true,
	        rules: {
	            puk : "required",
	            menuName : "required",
	            menuLevel : "required"
	            
	        },
	        messages: {
	        	puk:"请输入菜单ID",
	        	menuName:"请输入菜单名称",    
	        	menuLevel:"请输入菜单等级"       
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
	})



</script>
</body>
</html>
