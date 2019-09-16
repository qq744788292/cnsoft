<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>job-task-list</title><!-- 定时任务日志列表 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">

<table >
    <form method="post" action="/80402020?token=${token}" id="searchForm">
        <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
        <input type="hidden" name="pageLimit" id="pageLimit" value="12">
        <tr class="trparam" >
        <td >
            <table  >
                <tr>
                    <td><input type="button" class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()"></td>
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
                    <th>任务ID</th>
                    <th>任务编号</th>
                    <th>任务名称</th>
                    <th>启动时间</th>
                    <th>运行时间（毫秒）</th>
                    <th>运行结果状态</th>
                    <th>运行结果内容</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${page.pageListData}">
                    <tr>
                        <td>${item.jobId}</td>
                        <td>${item.jobNo}</td>
                        <td>${item.jobName}</td>
                        <td>${item.jobStartTime}</td>
                        <td>${item.jobUseTime}</td>
                        <td>${item.jobRunStatus}</td>
                        <td>
                        	<a href="#" title="${item.jobRunResult}">鼠标悬停浏览结果</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${page}" url="/80402020" /></td>
    </tr>
</table>
<script type="text/javascript">
    parent.changeNavbar('任务日志','首页》常用工具》爬虫管理》任务日志》详细列表');
</script>

</body>
</html>
