<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta charset="UTF-8">
		<!--<link rel="shortcut icon" href="http://116.62.22.225/static/ico.ico">-->
		<meta name="App-Config" content="fullscreen=yes,useHistoryState=yes,transition=yes">
		<meta content="telephone=no,email=no" name="format-detection">
		<meta content="yes" name="apple-touch-fullscreen">
		<meta content="yes" name="apple-mobile-web-app-capable">
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<meta name="keywords" content="家游学院">
		<meta name="description" content="家游学院">
		<title>支付方式</title>
		
		<script type="text/javascript" src="/resources/js/flexible.js" ></script><!--flexible适配-->
		<script type="text/javascript" src="/resources/js/jquery-1.11.1.min.js" ></script><!--引入jquery-->
		<script type="text/javascript" src="/resources/js/jweixin-1.2.0.js"></script>
		<script type="text/javascript" src="/resources/js/json2.js"></script>
		<link rel="stylesheet" href="/resources/css/pay/reset.css" /><!--样式重置-->
		<link rel="stylesheet" href="/resources/css/pay/common.css" /><!--公共样式-->
		<link rel="stylesheet" href="/resources/css/pay/payWay.css" />
	</head>
	<body>
		<div class="pay-container">
			<div class="up">
				<div class="title margin-comm-left">订单详情：</div>
				<div class="small-bottom-line"></div>
				<div class="pay-content margin-comm-left margin-comm-right">
					<img src="${material.lookPicUrl}" />
					<p class="fontSize18">${material.title}</p>
				</div>
				<div class="small-bottom-line"></div>
				<div class="margin-comm-right">
					<div class="right fontSize18">
						共<span class="fontSize18"><c:if test="${empty material.eb4}">1</c:if><c:if test="${not empty material.eb4}">${material.eb4}</c:if></span>集  
						合计：<span class="fontSize18"><c:if test="${empty material.eb3}">0</c:if><c:if test="${not empty material.eb3}">${material.eb3}</c:if></span>元
					</div>
				</div>
			</div>			
			<div>
				<div class="bottom-line"></div>
				<div class="money margin-comm-left orange-color">
					<span class="fontSize18">微信账户支付</span>
					<span class="span-two"><img src="/resources/img/pay/gou-selected.png" class="gou"/></span>
				</div>
		</div>
		<%-- ${userId}<br>${openId} --%>
		<div class="sure-btn" onclick="userPay();">确认支付</div>
	<script type="text/javascript">
		/////////////////////////////////////用户下单/////////////////////////////////////
		///
		///微信统一下单
		///
		function userPay(){
			var _rechargeMoney_ = '${material.eb3}';
			var _body_ = '购买家游学院产品【${material.title}】';
			
			//用户下单，保存订单
			$.ajax({
				type: "POST",
				url: "/99996012",
				data: {
					token: '${token}',
					openid: '${openId}',
					//以下是平台支付参数
					payType : '0412',
					rechargeMoney: _rechargeMoney_,//需要支付金额 
					nonceStr: '${nonceStr}',
					//以下是订单参数
					materialId: '${material.puk}',
					materialClassify: '${material.classify}',
					userId: '${userId}',
					userIp: '${ip}',
					body: _body_,
					//请求编号
					jobId: '${timestamp}',
					//以下是微信支付参数
					pv1: '${timestamp}',
					pv2: 'MD5'
				},
				dataType: "json",
				success: function(rest) {
					//console.log(rest);
					//alert(JSON.stringify(rest)); 
					if('0' == rest.code){
						///微信账户支付
						num = 120;
						//console.log("wxh5pay=="+data);
						//alert(JSON.stringify(rest.data));  
					    WeixinJSBridge.invoke(
							'getBrandWCPayRequest',
							{  
								"appId" 	: rest.data.appId,      	//公众号名称，由商户传入  
								"timeStamp"	: rest.data.timeStamp,	//时间戳，自 1970 年以来的秒数  
								"nonceStr" 	: rest.data.nonceStr,	//随机串  
								"package" 	: rest.data.pakkage,     //商品包信息     "prepay_id=" + 
								"signType" 	: rest.data.signType,  	//微信签名方式:  
								"paySign" 	: rest.data.paySign    	//微信签名  
							},
							function(res){   
								//alert(JSON.stringify(res)); 
								if(res.err_msg == "get_brand_wcpay_request:ok"){ 
			 	                    alert("支付成功!"); 
			 	           			location.href="http://u.2jiayou.com/weixin/menu/1";
			 	                }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
			 	                    alert("取消支付!");
			 	                }else{  
			 	                   alert("支付失败!");
			 	                } 
						});
					}else{
						alert("下单失败！"+rest.message);
						num = 0;
						return;
					} 
				},
				error: function() {
					return null;
				}
		  	});
		}
	</script>		
	</body>
</html>