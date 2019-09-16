<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>家游学院</title>
	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<script src="/resources/js/jquery.cookie.js"></script>

</head>

<body>
<!-- 用户分享 -->
<c:if test="${ menuId eq '0'}">
	<c:if test="${ empty userId}">
		<script type="text/javascript">
		//分享
		location.href="/viewmaterial/${puk}";
		</script>
	</c:if>
	<c:if test="${not empty userId}">
		<form action="/9999601111" method="POST" id="buyForm">
			<input type="hidden" name="openId" id="openId" value="${openId}">
			<input type="hidden" name="materialId" id="materialId" value="${puk}">
			<input type="hidden" name="userId" id="userId" value="${userId}">
		</form>
		<script type="text/javascript">
			//代付
			buyForm.submit();
		</script>
	</c:if>
</c:if>

<!-- 系统首页 -->
<c:if test="${ menuId eq '1'}">
	<script type="text/javascript">
	location.href="";
	</script>
</c:if>

<!-- 活动页面 -->
<c:if test="${menuId eq '2'}">
	<script type="text/javascript">
	location.href="";
	</script>
</c:if>

<!-- 活动页面 -->
<c:if test="${menuId eq '3'}">
	<script type="text/javascript">
	location.href="";
	</script>
</c:if>




</body>
</html>
