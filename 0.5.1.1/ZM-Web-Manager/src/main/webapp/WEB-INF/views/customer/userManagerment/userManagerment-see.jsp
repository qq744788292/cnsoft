<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>userManagerment-see</title><!-- 单个用户信息查看 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body>
<table >
    <tr class="trparam" >
        <td>
            <table>
                <tr>
                    <td><input type="button" class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()"></td>
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
                <tr height="60px">
                    <td class="tdparam">注册方式：</td>
                    <td>
                        <c:if test="${data.registerType== '1'}">手机号</c:if>
                        <c:if test="${data.registerType== '2'}">微信</c:if>
                        <c:if test="${data.registerType== '3'}">QQ</c:if>
                        <c:if test="${data.registerType== '9'}">后台</c:if>
                    </td>
                    <td class="tdblank120">&nbsp;</td>
                </tr>
                <tr height="60px">
                    <td class="tdparam">手机号：</td>
                    <td>${data.userPhone}</td>
                    <td>&nbsp;</td></tr>
                <tr height="60px">
                    <td class="tdparam">昵称：</td>
                    <td>${data.nickName}</td>
                    <td>&nbsp;</td>
                </tr>
                <tr height="60px">
                    <td class="tdparam">性别：</td>
                    <td>
                        <c:if test="${data.sex== '0'}">保密</c:if>
                        <c:if test="${data.sex== '1'}">男</c:if>
                        <c:if test="${data.sex== '2'}">女</c:if>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr height="60px">
                    <td class="tdparam">学历：</td>
                    <td>
                        <c:if test="${data.education== '1'}">初中及以下</c:if>
                        <c:if test="${data.education== '2'}">高中</c:if>
                        <c:if test="${data.education== '3'}">中专及以上</c:if>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr height="60px">
                    <td class="tdparam">年龄段：</td>
                    <td>
                        <c:if test="${data.ageGroup== '40'}">40后</c:if>
                        <c:if test="${data.ageGroup== '50'}">50后</c:if>
                        <c:if test="${data.ageGroup== '60'}">60后</c:if>
                        <c:if test="${data.ageGroup== '70'}">70后</c:if>
                        <c:if test="${data.ageGroup== '999'}">其他</c:if>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr height="60px">
                    <td class="tdparam">生日：</td>
                    <td>${data.birthday}
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr height="60px">
                    <td class="tdparam">真实姓名：</td>
                    <td>${data.realName}</td>
                    <td>&nbsp;</td>
                </tr>
                <tr height="60px">
                    <td class="tdparam">身份证号：</td>
                    <td>${data.idCard}</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<script type="text/javascript">
    parent.changeNavbar('用户管理', '首页》用户管理》用户信息》查看用户');

    if (!${message.code==0}) {
        parent.showMessage("${message.code}", "${message.msg}");
    }

</script>
</body>
</html>
