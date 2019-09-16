<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>classify-list.jsp</title><!-- 分类列表 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">
<table>

    <form method="post" action="/70201110?token=${token}" id="searchForm">
    <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
    <input type="hidden" name="pageLimit" id="pageLimit" value="12">
    <tr class="trparam" >
        <td>
            <table>
                <tr>
                    <td>
                    	<input type="button" style="width: 150px" onclick="parent.showPageForm('/70201120')" class="btn btn-default btnleft" value="添加">
                    </td>
                    <td class="tdparam">分类名称:</td>
                    <td style="width: 150px">
                        <input type="text" class="form-control" name="classifyName" placeholder="请输入" value="${param.classifyName}">
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
                    <th>分类ID</th>
                    <th>业务分类</th>
                    <th>分类名字</th>
                    <th>权重</th>
                    <th>分类URL</th>
                    <th class="tdblank200">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${page.pageListData}">
                    <tr>
                        <td>${item.puk}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.sysType == 1000}">影视</c:when>
                                <c:when test="${item.sysType == 2000}">游戏</c:when>
                            </c:choose>
                        </td>
                        <td>${item.classifyName}</td>
                        <td>${item.weight}</td>
                        <td>${item.classifyUrl}</td>
                        <td>
		                	<input type="button" class="btn btn-info" onclick="parent.showPageForm('/70201130?puk=${item.puk}')" value="编辑">
		                	<input type="button" class="btn btn-danger" onclick="parent.showDeleteWarning('/70201140?puk=${item.puk}&updateTime=${item.updateTime}','${item.classifyName}')" value="删除">
			            </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${page}" url="/70201110" /></td>
    </tr>
</table>

<script type="text/javascript">
    parent.changeNavbar('分类列表','首页 > 资源管理 > 文章管理 > 分类管理 > 分类一览');

</script>

<c:if test="${not empty message}">
<script type="text/javascript">
    parent.showMessage(${message.code}, '${message.msg}');
</script>
</c:if>
</body>
</html>
