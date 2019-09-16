<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>activity-modify-coupon-exist.jsp</title><!-- 已存在的券码 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>

</head>
<body class="body" style="height: 200px">
<table>
    <tr>
        <td>
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>券码</th>
                    <th>状态</th>
                    <th>活动编号</th>
                    <th>活动名称</th>
                    <th>奖项</th>
                    <th>奖品名</th>
                    <%--<th>中奖人</th>--%>
                    <%--<th>中奖人手机号</th>--%>
                    <%--<th>中奖人地址</th>--%>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="tbody">
                <c:if test="${empty page.pageListData}">
                    <tr>
                        <td colspan="11"  style="text-align: center">没有数据</td>
                    </tr>
                </c:if>
                <c:forEach var="item" items="${page.pageListData}">
                    <tr>
                        <td>${item.ticketNumber}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.writeoffStatus == '0'}">未核销</c:when>
                                <c:when test="${item.writeoffStatus == '1'}">已核销</c:when>
                                <c:when test="${item.writeoffStatus == '2'}">已重置</c:when>
                                <c:otherwise>未知</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${item.activityNo}</td>
                        <td>${item.activityName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.rewardLevel == '0'}">特等奖</c:when>
                                <c:when test="${item.rewardLevel == '1'}">一等奖</c:when>
                                <c:when test="${item.rewardLevel == '2'}">二等奖</c:when>
                                <c:when test="${item.rewardLevel == '3'}">三等奖</c:when>
                                <c:when test="${item.rewardLevel == '4'}">四等奖</c:when>
                                <c:when test="${item.rewardLevel == '5'}">优秀奖</c:when>
                                <c:when test="${item.rewardLevel == '6'}">安慰奖</c:when>
                                <c:when test="${item.rewardLevel == '7'}">金奖</c:when>
                                <c:when test="${item.rewardLevel == '8'}">银奖</c:when>
                                <c:otherwise>未知</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${item.rewardName}</td>
                            <%--<td>${item.writeoffName}</td>--%>
                            <%--<td>${item.writeoffPhone}</td>--%>
                            <%--<td>${item.writeoffAddress}</td>--%>
                        <td >
                                <%--<input type="button" class="btn btn-info" onclick="parent.showPageForm('/80102021?puk=${item.puk}')" value="核销">--%>
                                <%--<input type="button" class="btn btn-info" onclick="parent.showPageForm('/80102040?puk=${item.puk}')" value="重置">--%>
                                <%--<input type="button" class="btn btn-info" onclick="parent.showPageForm('/80102021?puk=${item.puk}')" value="查看">--%>
                                <input type="button" class="btn btn-danger"  onclick="window.location.href='/80201023?token=${token}&pageLimit=100&puk=${item.puk}&updateTime=${item.updateTime}&activityNo=${item.activityNo}'" value="删除">
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <td><p:page page="${page}" url="2020303120" /></td>
</table>

<script type="text/javascript">
</script>
</body>
</html>
