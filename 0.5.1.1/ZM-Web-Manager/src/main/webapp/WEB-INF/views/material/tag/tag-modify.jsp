<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>tag-modify.jsp</title><!-- 编辑标签 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
		<c:import url="/WEB-INF/views/config/jquery-validation.jsp" charEncoding="UTF-8"/>
</head>
<body >

<form method="post" action="/70201050?token=${token}" id="addOrModifyForm">
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
                        <td class="tdparam">分类ID:</td>
                        <td>
                            <select name="classifyId" class="form-control" id="classifyIdSelect">
                                <option value="">--请选择--</option>
                                <c:forEach var="item" items="${classifyList}">
                                    <option value="${item.puk}">${item.classifyName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="tdblank120"><span style="span-size:26px;color:red;">*</span></td>
                    </tr>
                    <tr>
                        <td class="tdparam">标签名称:</td>
                        <td><input type="text" class="form-control" name="tagName" value="${data.tagName}" id="tagName"/></td>
                        <td class="tdblank120"><span style="span-size:26px;color:red;">*</span></td>
                    </tr>
                    <tr>
                        <td class="tdparam">权重:</td>
                        <td><input type="text" class="form-control" name="weight" value="${data.weight}"/></td>
                        <td><span style="span-size:26px;color:red;">*</span></td>
                    </tr>
                    <tr>
                        <td class="tdparam">标签URL:</td>
                        <td><input type="text" class="form-control" name="tagUrl" value="${data.tagUrl}"/></td>
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
    $("#classifyIdSelect").val('${data.classifyId}');
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
                    url:"/70201090", //请求地址
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
</body>
</html>
