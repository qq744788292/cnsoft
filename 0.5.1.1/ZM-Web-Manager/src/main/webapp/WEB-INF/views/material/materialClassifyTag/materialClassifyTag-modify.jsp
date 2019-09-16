<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>materialClassifyTag-modify.jsp</title><!-- 编辑标签 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
		<c:import url="/WEB-INF/views/config/jquery-validation.jsp" charEncoding="UTF-8"/>
</head>
<body >

<form method="post" action="/70203050?token=${token}" id="addOrModifyForm">
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
                    <tr>
                        <td class="tdparam">素材ID:</td>
                        <td><input type="text" class="form-control" name="materialId" value="${data.materialId}" id="tagName"/></td>
                        <td class="tdblank120"><span style="span-size:26px;color:red;">*</span></td>
                    </tr>
                    <tr>
                        <td class="tdparam">分类ID:</td>
                        <td><input type="text" class="form-control" name="classifyId" value="${data.classifyId}"/></td>
                        <td><span style="span-size:26px;color:red;">*</span></td>
                    </tr>
                    <tr>
                        <td class="tdparam">标签ID:</td>
                        <td><input type="text" class="form-control" name="tagId" value="${data.tagId}"/></td>
                        <td><span style="span-size:26px;color:red;">*</span></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    
</form>

<script type="text/javascript">
    <c:if test="${mode == 1}">
    parent.changeNavbar('添加标签','首页 > 资源管理 > 文章管理 > 标签管理 > 添加标签');
    </c:if>
    <c:if test="${mode == 2}">
    parent.changeNavbar('编辑标签','首页 > 资源管理 > 文章管理 > 标签管理 > 编辑标签');
    <%--$("#classifyIdSelect").val('${data.classifyId}');--%>
    </c:if>

    $("#addOrModifyForm").validate({
        //调试模式,验证成功了也不会发生跳转
        //debug: true,
        rules: {
            materialId : "required",
            classifyId : "required",
            tagId : "required",
        },
        messages: {
            materialId:"不能为空,请重新输入!",
            classifyId:"不能为空,请重新输入!",
            tagId:"不能为空,请重新输入!",
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
