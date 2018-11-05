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
		var openId = $.cookie('wx_openId');//取出值
		//判断用户是否已经授权
		if (openId == null || openId == "") {
			//第一步：用户同意授权，获取code
			//自动授权
			location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${redirect}&response_type=code&scope=snsapi_base&state=${state}#wechat_redirect";
			//手动授权
			//location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${redirect}&response_type=code&scope=snsapi_userinfo&state=${state}#wechat_redirect";
		}
		//已经授权的场合自动登录
		else {			
			//自动登录
			location.href = "${login_href}?openId=" + openId;
		}
	</script>
</body>
</html>
