<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>material-list.jsp</title><!-- 素材列表 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">
<table>

    <form method="post" action="/70202010?token=${token}" id="searchForm">
    <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
    <input type="hidden" name="pageLimit" id="pageLimit" value="12">
    <tr class="trparam" >
        <td>
            <table>
                <tr>
                    <td>
                    	<input type="button" style="width: 150px" onclick="parent.showPageForm('/70202020')" class="btn btn-default btnleft" value="添加">
                    </td>
                    <td class="tdparam">标题:</td>
                    <td style="width: 150px">
                        <input type="text" class="form-control" name="title" placeholder="请输入" value="${param.title}">
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
                    <th>标题</th>
                    <th>发布时间</th>
                    <th>素材分类</th>
                    <th>素材类型</th>
                    <th>简介</th>
                    <th>内容</th>
                    <th>缩略图URL</th>
                    <th>发布者</th>
                    <th>素材URL</th>
                    <th>所属合集ID</th>
                    <th>推荐</th>
                    <th>浏览人数</th>
                    <th>申请审核结果状态</th>
                    <th>审核人ID</th>
                    <th>审核人姓名</th>
                    <th class="tdblank200">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${page.pageListData}">
                    <tr>
                        <td>${item.title}</td>
                        <td>${item.onlineTime}</td>
                        <td>${item.classify}</td>
                        <td>${item.type}</td>
                        <td>${item.synopsis}</td>
                        <td>${item.content}</td>
                        <td>${item.lookPicUrl}</td>
                        <td>${item.author}</td>
                        <td>${item.materialUrl}</td>
                        <td>${item.collectionId}</td>
                        <td>${item.top}</td>
                        <td>${item.numRead}</td>
                        <td>${item.applyFlag}</td>
                        <td>${item.applyUserId}</td>
                        <td>${item.applyUserName}</td>
                        <td>
		                	<input type="button" class="btn btn-info" onclick="parent.showPageForm('/70202030?puk=${item.puk}')" value="编辑">
		                	<input type="button" class="btn btn-danger" onclick="parent.showDeleteWarning('/70202040?puk=${item.puk}&updateTime=${item.updateTime}','${item.title}')" value="删除">
			            </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${page}" url="/70202010" /></td>
    </tr>
</table>

<script type="text/javascript">
    parent.changeNavbar('素材列表','首页 > 资源管理 > 文章管理 > 素材管理 > 素材一览');

</script>

<c:if test="${not empty message}">
<script type="text/javascript">
    parent.showMessage(${message.code}, '${message.msg}');
</script>
</c:if>
</body>
</html>
