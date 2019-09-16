<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>money-apply-list.jsp</title><!-- 企业申请列表 -->
	<!-- 静态引入 -->
	<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">
<table>

<form method="post" action="/20101010?token=${token}" id="searchForm">
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
                    <th>联系人</th>
                    <th>联系方式</th>
                    <th>需求内容</th>
                    <th>申请时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${page.pageListData}">
                    <tr>
                        <td>${item.enterpriseName}</td>
                        <td>${item.contacts}</td>
                        <td>${item.contactInformation}</td>
                        <td style="width: 500px;">${item.description}</td>
                        <td>${item.createTime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${page}" url="/20101010" /></td>
    </tr>
</table>

<script type="text/javascript">
    parent.changeNavbar('文章地址','首页 > 资源管理 > 微信文章管理 > 文章地址管理 > 文章地址一览');
</script>

</body>
</html>
