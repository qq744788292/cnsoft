<%
	/**
	 * 用户授权
	 * @author ZmSoft
	 * @version 0.1.0
	 * @since 0.1.0 2018/4/2
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="version" content="1.1.1-RELEASE build 2018.05.21">
<title>Loading</title>

</head>

<body>
	<script type="text/javascript">
		var url = "/h5/#/mainPage?loginType=9&userSource=10&token=${token}";//最终访问地址
	</script>
	<c:if test="${ empty menuId}">
		<script type="text/javascript">
			url = "/h5/index.html?loginType=9&userSource=10&token=${token}";//最终访问地址
		</script>
	</c:if>

	<c:if test="${not empty menuId}">
		<c:choose>
			<c:when test="${menuId=='1'}">
				<script type="text/javascript">
					//首页
					url = "/h5/#/mainPage?loginType=9&userSource=10&token=${token}";
				</script>
			</c:when>
			<c:when test="${menuId=='2'}">
				<script type="text/javascript">
					//楼盘
					url = "/h5/#/mainPage/building?loginType=9&userSource=10&token=${token}";
				</script>
			</c:when>
			<c:when test="${menuId=='3'}">
				<script type="text/javascript">
					//资讯
				 	url = "/h5/#/articleList?loginType=9&userSource=10&token=${token}";
				</script>
			</c:when>
			<c:otherwise>
				<script type="text/javascript">
					url = "";//默认地址
				</script>
			</c:otherwise>
		</c:choose>
	</c:if>

	<script type="text/javascript">
		//加载显示页面
		if (url != "")
			location.href = url;
	</script>
</body>
</html>