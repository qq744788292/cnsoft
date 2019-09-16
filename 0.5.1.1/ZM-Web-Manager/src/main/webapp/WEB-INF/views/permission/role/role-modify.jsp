<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>role-modify</title><!-- 修改角色 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
    <c:import url="/WEB-INF/views/config/jquery-validation.jsp" charEncoding="UTF-8"/>
</head>
<body>

<form method="post" action="/90201030?token=${token}" id="modifyForm">
    <input type="hidden" name="pageCurrent"  value="1" />
    <input type="hidden" name="pageLimit" value="12" />
    <input type="hidden" name="puk" value="${data.puk}" />

    <table >
        <tr class="trparam">
            <td>
                <table>
                    <tr>
                        <td><input type="button"  class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()" /></td>
                        <td align="right"><input type="submit"  class="btn btn-primary btnright" value="保存" /></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr class="pageht">
            <td >&nbsp;</td>
        </tr>
        <tr >
            <td>
                <table class="table-striped table-hover">
                    <tr >
                        <td class="tdparam">角色名称:</td>
                        <td><input id="roleName"  type="text" class="form-control " name="roleName" value="${data.roleName}" /></td>
                        <td class="tdblank120">&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">备注:</td>
                        <td><textarea class="form-control"  name="meno"  cols="30" rows="5">${data.meno}</textarea></td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    parent.changeNavbar('权限管理','首页》权限管理》角色定义》修改角色');

    $("#modifyForm").validate({
        //调试模式,验证成功了也不会发生跳转
        //debug: true,
        rules:{
            roleName:{
                required:true,
                remote:{
                    type:"POST",
                    url:"/90201050",
                    data:{
                        roleName:function () {return $("#roleName").val();},
                        num:function () {return  $("input[name='puk']").val();},
                        token: "${token}"
                    }
                }
            }
        },
        messages:{
            roleName:{
                required:"不能为空,请重新输入!",
                remote:"已存在,请重新输入!"
            }
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
