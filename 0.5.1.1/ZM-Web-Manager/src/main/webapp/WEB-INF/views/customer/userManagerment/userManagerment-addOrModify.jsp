<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>userManagerment-addOrModify</title><!-- 用户 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
    <c:import url="/WEB-INF/views/config/jquery-validation.jsp" charEncoding="UTF-8"/>
</head>
<body>
<form action="/20101050?token=${token}" method="post" id="addOrModify">
    <input type="hidden" name="puk" id="puk" value="${data.puk}">
    <input type="hidden" name="mode" value="${mode}" id="mode">
    <input type="hidden" name="token" value="${token}">
    <table>
        <tr>
            <td>
                <table>
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
                        <td class="tdparam">手机号：</td>
                        <td><input type="text" name="userPhone" id="userPhone" class="form-control" value="${data.userPhone}"></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">昵称：</td>
                        <td><input type="text" name="nickName" id="nickName" class="form-control" value="${data.nickName}"></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">性别：</td>
                        <td>
                            <c:if test="${empty data.sex}">
                                <input type="radio" name="sex"  value="0" checked="checked">保密 &nbsp;&nbsp;
                            </c:if>
                            <c:if test="${not empty data.sex}">
                                <input type="radio" name="sex"  value="0" <c:if test="${data.sex== '0'}">checked="checked"</c:if>>保密
                                &nbsp;&nbsp;
                            </c:if>
                            <input type="radio" name="sex" value="1" <c:if test="${data.sex== '1'}">checked="checked"</c:if>>男
                            &nbsp;&nbsp;
                            <input type="radio" name="sex"  value="2" <c:if test="${data.sex== '2'}">checked="checked"</c:if>>女
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">学历：</td>
                        <td>
                            <input type="radio" name="education"  value="1" <c:if test="${data.education== '1'}">checked="checked"</c:if>>初中及以下
                            &nbsp;&nbsp;
                            <input type="radio" name="education" value="2" <c:if test="${data.education== '2'}">checked="checked"</c:if>>高中
                            &nbsp;&nbsp;
                            <input type="radio" name="education"  value="3" <c:if test="${data.education== '3'}">checked="checked"</c:if>>中专及以上
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">年龄段：</td>
                        <td>
                            <input type="radio" name="ageGroup"  value="40" <c:if test="${data.ageGroup== '40'}">checked="checked"</c:if>>40后
                            &nbsp;&nbsp;
                            <input type="radio" name="ageGroup"  value="50" <c:if test="${data.ageGroup== '50'}">checked="checked"</c:if>>50后
                            &nbsp;&nbsp;
                            <input type="radio" name="ageGroup"  value="60" <c:if test="${data.ageGroup== '60'}">checked="checked"</c:if>>60后
                            &nbsp;&nbsp;
                            <input type="radio" name="ageGroup"  value="70" <c:if test="${data.ageGroup== '70'}">checked="checked"</c:if>>70后
                            &nbsp;&nbsp;
                            <input type="radio" name="ageGroup"  value="999" <c:if test="${data.ageGroup== '999'}">checked="checked"</c:if>>其他
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">生日：</td>
                        <td><input type="text" name="birthday" id="birthday" class="form-control" value="${data.birthday}"></td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    <c:if test="${mode==1}">
         parent.changeNavbar('用户管理', '首页》用户管理》用户信息》用户新增');
    </c:if>
    <c:if test="${mode==2}">
            parent.changeNavbar('用户管理', '首页》用户管理》用户信息》用户修改');
    </c:if>

    if (!${message.code==0}) {
        parent.showMessage("${message.code}", "${message.msg}");
    }

    $("#addOrModify").validate({
        //调试模式,验证成功了也不会发生跳转
        //debug: true,
        rules:{
            userPhone:"required",
            nickName:{
                required: true,
                remote:{
                    type: "POST",
                    url: "/20101060",
                    data:{
                        nickName:function () {return $("#nickName").val();},
                        mode:function () {return $("#mode").val();},
                        count:function () {return $("#puk").val();},
                        token:"${token}"
                    }
                }
            },
        },
        messages:{
            userPhone: "不能为空,请重新输入!",
            nickName:{
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
