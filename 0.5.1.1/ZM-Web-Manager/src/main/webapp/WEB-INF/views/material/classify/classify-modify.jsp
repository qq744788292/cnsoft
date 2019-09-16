<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tag/fileuploadtag.tld" prefix="u" %>
<html>
<head>
    <title>classify-modify.jsp</title><!-- 编辑分类 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
    <c:import url="/WEB-INF/views/config/jquery-validation.jsp" charEncoding="UTF-8"/>
</head>
<body>

<form method="post" action="/70201150?token=${token}" id="addOrModifyForm">
    <input type="hidden" name="mode" value="${mode}">
    <input type="hidden" name="puk" value="${data.puk}" id="puk">
    <table>
        <tr class="trparam">
            <td>
                <table>
                    <tr>
                        <td><input type="button" class="btn btn-default btnleft" value="返回"
                                   onclick="JavaScript:history.back()"></td>
                        <td align="right"><input type="submit" class="btn btn-primary btnright" value="保存"></td>
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
                        <td class="tdparam">业务分类:</td>
                        <td>
                            <select name="sysType" id="sysTypeSelect" class="form-control">
                                <option value="">--请选择--</option>
                                <option value="1000">影视</option>
                                <option value="2000">游戏</option>
                            </select>
                        </td>
                        <td class="tdblank120"><span style="span-size:26px;color:red;">*</span></td>
                    </tr>
                    <tr>
                        <td class="tdparam">分类名称:</td>
                        <td><input type="text" class="form-control" name="classifyName" id="classifyName" value="${data.classifyName}"/>
                        </td>
                        <td class="tdblank120"><span style="span-size:26px;color:red;">*</span></td>
                    </tr>
                    <tr>
                        <td class="tdparam">权重:</td>
                        <td><input type="text" class="form-control" name="weight" value="${data.weight}"/></td>
                        <td><span style="span-size:26px;color:red;">*</span></td>
                    </tr>
                    <tr>
                        <td class="tdparam">分类URL:</td>
                        <td><input type="text" class="form-control" name="classifyUrl" value="${data.classifyUrl}"/>
                        </td>
                        <td><span style="span-size:26px;color:red;">*</span></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>

</form>

<script type="text/javascript">
    <c:if test="${mode == 1}">
    parent.changeNavbar('添加分类', '首页 > 资源管理 > 文章管理 > 分类管理 > 添加分类');
    </c:if>
    <c:if test="${mode == 2}">
    parent.changeNavbar('编辑分类', '首页 > 资源管理 > 文章管理 > 分类管理 > 编辑分类');
    $("#sysTypeSelect").val('${data.sysType}');
    </c:if>


    $("#addOrModifyForm").validate({
        //调试模式,验证成功了也不会发生跳转
        //debug: true,
        rules: {
            sysType : "required",
            classifyName : {
                required:true,
                remote:{
                    type:"POST",
                    url:"/70201190", //请求地址
                    data:{
                        classifyName:function(){ return $("#classifyName").val(); },
                        currentPuk:function(){ return $("#puk").val(); },
                        mode:"${mode}",
                        token: "${token}"
                    }
                }
            },
            weight : "required",
            classifyUrl : "required",
        },
        messages: {
            sysType:"不能为空,请重新输入!",
            classifyName:{
                required:"不能为空,请重新输入!",
                remote:"已存在,请重新输入!"
            },
            weight:"不能为空,请重新输入!",
            classifyUrl:"不能为空,请重新输入!",
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
