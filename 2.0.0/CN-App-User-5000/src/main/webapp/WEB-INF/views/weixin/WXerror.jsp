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
<title>用户授权</title>
	<script src="/resources/js/jquery-1.12.4.min.js"></script>
	<script src="/resources/js/jquery.cookie.js"></script>
</head>

<body>

<script type="text/javascript">
	var msg = "${msg}";
	if(msg!="")
		alert(msg);
</script>
	用户授权失败，请关闭重试。
</body>
</html>
