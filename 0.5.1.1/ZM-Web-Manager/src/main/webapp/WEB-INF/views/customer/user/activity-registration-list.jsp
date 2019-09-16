<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>activity-registration-list.jsp</title><!-- 活动报名列表 -->
	<!-- 静态引入 -->
	<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">
<table>

<form method="post" action="/10101010?token=${token}" id="searchForm">
    <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
    <input type="hidden" name="pageLimit" id="pageLimit" value="12">
    <tr class="trparam" >
        <td>
            <table>
                <tr>
                    <td>
                    </td>
                    <td class="tdparam">企业名称:</td>
                    <td style="width: 150px">
                        <input type="text" class="form-control" name="companyName" placeholder="请输入企业名称" value="${param.companyName}">
                    </td>
                    <td class="tdparam">姓名:</td>
                    <td style="width: 150px">
                        <input type="text" class="form-control" name="realName" placeholder="请输入姓名" value="${param.realName}">
                    </td>
                    <td class="tdparam">手机号:</td>
                    <td style="width: 150px">
                        <input type="text" class="form-control" name="userPhone" placeholder="请输入手机号" value="${param.userPhone}">
                    </td>
                    <td class="tdbtn">
                        <input type="submit" class="btn btn-primary btnright"  value="搜索">
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</form>

    <%-- 表格数据显示区--%>
    <tr>
        <td>
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr >
                    <th>企业名称</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>报名时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${page.pageListData}">
                    <tr>
                        <td>${item.companyName}</td>
                        <td>${item.realName}</td>
                        <td>${item.userPhone}</td>
                        <td>${item.createTime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${page}" url="/10101010" /></td>
    </tr>
</table>

<script type="text/javascript">
    parent.changeNavbar('文章地址','首页 > 资源管理 > 微信文章管理 > 文章地址管理 > 文章地址一览');
</script>

</body>
</html>
