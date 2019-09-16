<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p"%>
<%@ taglib uri="/WEB-INF/tag/fileuploadtag.tld" prefix="u"%>
<%@ taglib uri="/WEB-INF/tag/securitycodetag.tld" prefix="s"%>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="keywords" content="运维系统">
    <meta name="description" content="佐梦科技">
    <meta name="version" content="1.1.1-RELEASE build 2018.05.21">

	<!-- 以上是后台引入文件配置 -->
<title>欢迎访问运维管理中心</title>
<script type="text/javascript">
top.location.href="/ManagerLogin";
</script>
</head>
<body>

	<H1>Welcome</H1>
	<br>
	<h3>欢迎访问运维管理中心</h3>	
	<br>
	<h3>${DDD}</h3>
	<a href="/ManagerLogin">登录系统</a>
	
</body>
</html>
