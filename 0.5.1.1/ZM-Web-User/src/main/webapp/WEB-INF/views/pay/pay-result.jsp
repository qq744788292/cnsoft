<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>pay-result.jsp</title>
		<link rel="stylesheet" href="/resources/css/sys/pay/reset.css" />
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	</head>
	<style>
		.dn{
			display:none;
		}
		.popupMoneySure{
			cursor:default;
		}		
		.popupMoneySure .content{
			width:394px;
			height:484px;
			border:3px solid #ab0f0d;
			background-color:#1e1b1a;
			z-index:101;
		}
		.popupMoneySure .content .top {
			position:relative;
			height:42px;
		}
		.popupMoneySure .content .top h3{
			font-weight:normal;
			text-indent:10px;
			font-size:18px;
			line-height:42px;
			color:#d3d3d3;			
		}
		.popupMoneySure .content .top .close{
			position:absolute;
			top:0;
			right:0;
			width:42px;
			height:42px;
			background:url(/resources/img/payclose.png) no-repeat center center;
			background-size:100% 100%;
			cursor:pointer;
		}
		.popupMoneySure .content .bottom h5.errorTitle{
			margin:80px auto;
			line-height:40px;
			font-size:22px;
			color:#d3d3d3;
			text-align:center;
		}
		.popupMoneySure .content .bottom p.error{
			line-height:40px;
			font-size:20px;
			color:#d3d3d3;
			text-align:center;
		}		
		/*确认充值弹窗*/			
	</style>
	<body>
	<div class="popupMoneySure">
		<div class="content">
			<div class="top">
				<h3>提示</h3>
				<div class="close"></div>
			</div>
			<div class="bottom">
				<h5 class="errorTitle">${title}</h1>
				<p class="error">${message}</p>
		</div>		
	</div>		
	
<c:if test="${message != null and message != ''}">
	<script type="text/javascript">
		function closeWebPage(){  
		 if (navigator.userAgent.indexOf("MSIE") > 0) {//close IE  
		  if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {  
		   window.opener = null;  
		   window.close();  
		  } else {  
		   window.open('', '_top');  
		   window.top.close();  
		  }  
		 }  
		 else if (navigator.userAgent.indexOf("Firefox") > 0) {//close firefox  
		  window.location.href = 'about:blank ';  
		 } else {//close chrome;It is effective when it is only one.  
		  window.opener = null;  
		  window.open('', '_self');  
		  window.close();  
		 }  
		}  
	
		alert('${message}');	
		closeWebPage();
	</script>
</c:if>	
	</body>
</html>
