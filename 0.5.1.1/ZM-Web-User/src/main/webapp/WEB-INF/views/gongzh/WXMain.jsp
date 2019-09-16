<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>用户授权</title>
    
	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<script src="/resources/js/jquery.cookie.js"></script>
	
</head>

<body>
	<script type="text/javascript">
		var openId = $.cookie('wx_openId');//取出值
		//判断用户是否已经授权
		if (openId == null || openId == "") {
			//第一步：用户同意授权，获取code
			//手动授权
		//	location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${redirect}&response_type=code&scope=snsapi_base&state=${state}#wechat_redirect";
			location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${redirect}&response_type=code&scope=snsapi_userinfo&state=${state}#wechat_redirect";
		}
		//已经授权的场合自动登录
		else {			
			//自动登录
			location.href = "${href}?openId=" + openId;
		}
	</script>
</body>
</html>
