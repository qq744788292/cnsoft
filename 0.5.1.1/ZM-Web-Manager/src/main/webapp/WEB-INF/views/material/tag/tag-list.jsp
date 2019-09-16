<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>tag-list.jsp</title><!-- 标签列表 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">
<table>

    <form method="post" action="/70201010?token=${token}" id="searchForm">
    <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
    <input type="hidden" name="pageLimit" id="pageLimit" value="12">
    <tr class="trparam" >
        <td>
            <table>
                <tr>
                    <td>
                    	<input type="button" style="width: 150px" onclick="parent.showPageForm('/70201020')" class="btn btn-default btnleft" value="添加">
                    </td>
                    <td class="tdparam">标签名称:</td>
                    <td style="width: 150px">
                        <input type="text" class="form-control" name="tagName" placeholder="请输入" value="${param.tagName}">
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
                    <th>标签ID</th>
                    <th>分类ID</th>
                    <th>标签名字</th>
                    <th>权重</th>
                    <th>标签URL</th>
                    <th class="tdblank200">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${page.pageListData}">
                    <tr>
                        <td>${item.puk}</td>
                        <td>${item.classifyId}</td>
                        <td>${item.tagName}</td>
                        <td>${item.weight}</td>
                        <td>${item.tagUrl}</td>
                        <td>
		                	<input type="button" class="btn btn-info" onclick="parent.showPageForm('/70201030?puk=${item.puk}')" value="编辑">
		                	<input type="button" class="btn btn-danger" onclick="parent.showDeleteWarning('/70201040?puk=${item.puk}&updateTime=${item.updateTime}','${item.tagName}')" value="删除">
			            </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${page}" url="/70201010" /></td>
    </tr>
</table>

<script type="text/javascript">
    parent.changeNavbar('标签列表','首页 > 资源管理 > 文章管理 > 标签管理 > 标签一览');

</script>

<c:if test="${not empty message}">
<script type="text/javascript">
    parent.showMessage(${message.code}, '${message.msg}');
</script>
</c:if>
</body>
</html>
