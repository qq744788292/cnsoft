<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>job-task-list</title><!-- 定时任务配置列表 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">

<table >
    <form method="post" action="/80401010?token=${token}" id="searchForm">
        <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
        <input type="hidden" name="pageLimit" id="pageLimit" value="10">
        <tr class="trparam" >
        <td>
            <table  >
                <tr>
                    <td>
                    	<input type="button" style="width: 150px" onclick="parent.showPageForm('/80401020')" class="btn btn-default btnleft" value="添加定时任务">
                    </td>
                    <td class="tdparam">任务编号:</td>
                    <td style="width: 150px">
                        <input type="text" class="form-control" name="configType" placeholder="请输入任务编号" value="${param.jobNo}">
                    </td>
                    <td class="tdparam">任务名称:</td>
                    <td style="width: 150px">
                        <input type="text" class="form-control" name="configKey" placeholder="请输入任务名称" value="${param.jobName}">
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
                    <th>任务编号</th>
                    <th>任务名称</th>
                    <th>分组名称</th>
                    <th>任务状态</th>
                    <th>任务类别</th>
                    <th>cron表达式</th>
                    <th>并发支持</th>
                    <th class="tdblank200">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${list}">
                    <tr>
                        <td>${item.jobNo}</td>
                        <td>${item.jobName}</td>
                        <td>${item.jobGroup}</td>
                        <td >
		                	<c:choose>
		                		<c:when test="${item.jobStatus == 1 }">
		                			运行
		                		</c:when>
		                		<c:when test="${item.jobStatus == 2 }">
		                			停止
		                		</c:when>
		                		<c:otherwise>
		                			失效
		                		</c:otherwise>
		                	</c:choose>
		                </td>
                        <td>
		                	<c:choose>
		                		<c:when test="${item.jobType == 1000 }">
		                			本地接口
		                		</c:when>
		                		<c:otherwise>
		                			远程接口
		                		</c:otherwise>
		                	</c:choose>
		                </td>
                        <td>${item.cronExpression}</td>
                        <td>
		                	<c:choose>
		                		<c:when test="${item.isConcurrent == 1 }">
		                			不能并发
		                		</c:when>
		                		<c:otherwise>
		                			可以并发
		                		</c:otherwise>
		                	</c:choose>
		                </td>
                        <td>
							<c:choose>
		                		<c:when test="${item.jobStatus==1 }">
		                			<input type="button" class="btn btn-warning" onclick="parent.showPageForm('/80401060?jobStatus=2&puk=${item.puk}')" value="停止">
		                		</c:when>
		                		<c:otherwise>
		                			<input type="button" class="btn btn-success" onclick="parent.showPageForm('/80401060?jobStatus=1&puk=${item.puk}')" value="启动">
		                		</c:otherwise>
		                	</c:choose>
		                	<input type="button" class="btn btn-info" onclick="parent.showPageForm('/80401030?puk=${item.puk}')" value="编辑">
		                	<input type="button" class="btn btn-danger" onclick="parent.showDeleteWarning('/80401040?puk=${item.puk}','${item.jobName}')" value="删除">
			            </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
</table>

<script type="text/javascript">
    parent.changeNavbar('任务日志','首页 > 常用工具 > 爬虫管理 > 任务日志 > 详细列表');
</script>

<c:if test="${not empty message}">
<script type="text/javascript">
    parent.showMessage(4, '${message}');
</script>
</c:if>
</body>
</html>
