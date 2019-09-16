<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<%@ taglib uri="/WEB-INF/tag/fileuploadtag.tld" prefix="u"%>
<html>
<head>
    <title>popularizeManage-addOrModify</title><!-- 添加/修改推广员 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
    <c:import url="/WEB-INF/views/config/jquery-validation.jsp" charEncoding="UTF-8"/>
</head>
<body>
<form action="/40101050?token=${token}" method="post" id="addOrModify">
    <input type="hidden" name="mode" value="${mode}" id="mode">
    <input type="hidden" name="puk" value="${data.puk}" id="puk">
    <input type="hidden" name="token" value="${token}">
    <table>
        <tr class="trparam" >
            <td>
                <table>
                    <tr>
                        <td><input type="button" class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()"></td>
                        <td align="right"><input type="submit" class="btn btn-primary btnright"  value="保存" ></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr class="pageht">
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>
                <table class="table-striped table-hover" >
                    <tr>
                        <td class="tdparam">登陆账号:</td>
                        <td><input type="text" class="form-control" id="userName" name="userName" value="${data.userName}"/></td>
                        <td class="tdblank120">&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">登录密码</td>
                        <td><input type="password" class="form-control" name="password" value="${data.password}"/></td>
                        <td class="tdblank120">&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">姓名:</td>
                        <td><input type="text" class="form-control" name="name" value="${data.name}"/></td>
                        <td class="tdblank120">&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">身份证号码:</td>
                        <td><input type="text" class="form-control" name="identityCardNumber" value="${data.identityCardNumber}"/></td>
                        <td class="tdblank120">&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">手机号码:</td>
                        <td><input type="text" class="form-control" name="phone" value="${data.phone}"/></td>
                        <td class="tdblank120">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div style="text-align: center">
                                <span style="width: 200px;display:inline-block" >身份证照片(正面)</span>
                                <span style="width: 75px;display:inline-block"></span>
                                <span style="width: 200px;display:inline-block">身份证照片(反面)</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div style="text-align: center">
                                <input type="hidden" name="identityCardPictureFront" id="idCardFront">
                                <u:fileupload height="200" width="200" id="upload1" type="1" token="${token}" target="idCardFront" value="${data.identityCardPictureFront}"/>
                                <input type="hidden" name="identityCardPictureBack" id="idCardBack">
                                <u:fileupload height="200" width="200" id="upload2" type="1" token="${token}" target="idCardBack" value="${data.identityCardPictureBack}"/>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    <c:if test="${mode==1}">
         parent.changeNavbar('推广管理', '首页》推广管理》推广员信息》推广员新增');
    </c:if>
    <c:if test="${mode==2}">
         parent.changeNavbar('推广管理', '首页》推广管理》推广员信息》推广员修改');
    </c:if>

    $("#addOrModify").validate({
        //调试模式,验证成功了也不会发生跳转
        //debug: true,
        rules:{
            userName:{
                required: true,
                remote:{
                    type: "POST",
                    url: "/40101060",
                    data:{
                        userName:function () {return $("#userName").val();},
                        mode:function () {return $("#mode").val();},
                        count:function () {return $("#puk").val();},
                        token:"${token}"
                    }
                }
            },
            password: "required",
            name: "required",
            identityCardNumber: "required",
            phone: "required"
        },
        messages:{
            userName:{
                required:"不能为空,请重新输入!",
                remote:"已存在,请重新输入!"
            },
            password: "不能为空,请重新输入!",
            name: "不能为空,请重新输入!",
            identityCardNumber: "不能为空,请重新输入!",
            phone: "不能为空,请重新输入!"
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
