<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>auditingNick-see</title><!-- 查看昵称审核详情 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body>
<table >
    <tr class="trparam" >
        <td>
            <input type="button"  class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()">

        </td>
    </tr>
    <tr class="pageht">
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <table class="table-striped table-hover">
                <tr>
                    <td class="tdparam">昵称：</td>
                    <td>${data.nickName}</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<script type="text/javascript">
    parent.changeNavbar('用户管理', '首页》用户管理》头像审核》查看昵称审核详情');
</script>
</body>
</html>
