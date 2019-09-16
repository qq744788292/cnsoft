<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<!-- <title>pay-creat-fail.jsp</title> -->
		<title>家游学院</title>
		<link rel="stylesheet" href="/resources/css/pay/payWay.css" />
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

	</head>
	
	<body>
	<script type="text/javascript">
	var ref = document.referrer;
	alert("${message}");
	if(ref==''){
		location.href="http://u.2jiayou.com/weixin/menu/1";
	}else{
		location.href=history.back();
	}
	</script>
	</body>
</html>