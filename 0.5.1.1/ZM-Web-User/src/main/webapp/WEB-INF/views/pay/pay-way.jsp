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
				<div class="small-bottom-line"></div>
				<div class="money margin-comm-left">
					<span class="fontSize18">微信扫码支付</span>
					<span class="span-two dn"><img src="/resources/img/pay/gou-selected.png" class="gou"/></span>
				</div>
				<div class="small-bottom-line"></div>
				<div class="money margin-comm-left">
					<span class="fontSize18">微信找人代付</span>
					<span class="span-two dn"><img src="/resources/img/pay/gou-selected.png" class="gou"/></span>
				</div>
				<!-- <div class="small-bottom-line"></div>
				<div class="money margin-comm-left">
					<span class="fontSize18">支付宝支付</span>
					<span class="span-two dn"><img src="/resources/img/pay/gou-selected.png" class="gou"/></span>
				</div> -->
			</div>
		</div>
		<div class="sure-btn" onclick="userPay();">确认支付</div>
		<div class="pay-qrcode" id="payqrcode" onclick="payQrcodeClose()">
			<img class="pay_img" src="" id="wxQrcodePay"/>
		</div>
	<script type="text/javascript">
		var payType = "1";
		$(".money").on('click',function(){
			//alert($(this).children("span:first-child").text());
			if($(this).children("span:first-child").text() == "微信账户支付"){
				payType = "1";
			}
			else if($(this).children("span:first-child").text() == "微信扫码支付"){
				payType = "2";
			}
			else if($(this).children("span:first-child").text() == "微信找人代付"){
				payType = "3";
			}
			else if($(this).children("span:first-child").text() == "支付宝支付"){
				payType = "4";
			}
			$(this).addClass("orange-color");
			$(this).siblings(".money").removeClass("orange-color");
			$(this).children(".span-two").removeClass("dn");
			$(this).siblings(".money").children(".span-two").addClass("dn");
		});
		
		function userPay(){			
			if(payType == '1')
				weixinCreatOrder('0412',wxh5pay);//H5网页下单
			else if(payType == '2')
				weixinCreatOrder('0402',wxqrcodepay);//二维码下单
			else if(payType == '3')
				weixinCreatOrder('0402',wxsharepay);//分享下单	
		}		
	</script>
	<script type="text/javascript">
		function payQrcodeClose(){
			document.all("payqrcode").style.display = "none";
			num = 0;	
		};
		var token = "${token}";
		var openId = "${openId}";
		///////////////////////微信参数/////////////////////////////////
		var ip = "${ip}";
		var _appID_ = "${appID}";
		var _signature_ = "${signature}";
		var _jsapi_ticket_ = "${jsapi_ticket}";
		var _url_ = "${url}";
		var _nonceStr_ = "${nonceStr}";
		var _timestamp_ = ${timestamp};
		///////////////////////////////////////////////////////////////////
		//初始化
		wx.config({
		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: _appID_, // 必填，公众号的唯一标识
		    timestamp: _timestamp_, // 必填，生成签名的时间戳
		    nonceStr: _nonceStr_, // 必填，生成签名的随机串
		    signature: _signature_,// 必填，签名，见附录1
		    jsApiList: ['checkJsApi','onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone']// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});

		/////////////////////////用户分享/////////////////////////////////////
		var _link_ = "https://u.2jiayou.com/ordershare/${userId}/${order.materialId}";//地址/购买人/素材/订单号
		var _desc_ = "(${material.title})我觉得很好，请帮我购买";
		wx.ready(function(){
		    // 定义通用发送数据
		    var _data_ = {
		    	title: '老年云课堂-家游学院',// 分享标题
		  	    desc: _desc_,// 分享描述
		  	    link: _link_, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		  	    imgUrl: '${material.lookPicUrl}',// 分享图标
		    }
		    
		    wx.onMenuShareTimeline({
		        title: _data_.desc, 	// 分享标题
		  	    desc: _data_.desc,		// 分享描述
		        link: _data_.link, 		// 分享链接
		        imgUrl: _data_.imgUrl, 	// 分享图标
		        success: function (res) {
			    	  shareOK();
			      }
		    });
		
		    wx.onMenuShareAppMessage(_data_);  //发送给好友
		    wx.onMenuShareQQ(_data_); //发送给QQ
		    wx.onMenuShareWeibo(_data_); //分享到微博
		    wx.onMenuShareQZone(_data_); //分享到空间
		});
		
		//config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
		wx.error(function (res) {
			//alert(res.errMsg);
		});

		/////////////////////////////////////用户下单/////////////////////////////////////
		///
		///微信扫码支付
		///
		var wxsharepay = function(data){
			//_link_ = _link_+data.orderNumber;
			//alert(_link_);
			alert("请点击右上角微信分享给好友，完成支付");
		}
		///
		///微信扫码支付
		///
		var wxqrcodepay = function(data){
			orderNumber = data.orderNumber;
			//console.log(data);

	        var screen_width = document.documentElement.clientWidth;
	        var screen_heigh = document.documentElement.clientHeight;//window.screen.height ;
	        document.all("payqrcode").style.display="block";
			//document.all("payqrcode").style.top=(screen_heigh/2 -100)+"px";
			//document.all("payqrcode").style.left = (screen_width/2 -100)+"px";
			
			var src = '/99999031/220/220?content=' + data.codeUrl;
			$("#wxQrcodePay").attr('src',src); 
			
			setTimeout('loadOrderPayResult()', 1000);//查询支付结果
		}
		///
		///微信账户支付
		///
		//{nonce_str=SRJkNFitBLcEywoy, appid=wx038792acead673b4, sign=8174FDA5898FF4965EA62EAFCE2FA77D,
		//	trade_type=JSAPI, return_msg=OK,
		//	result_code=SUCCESS, mch_id=1495451332, 
		//	return_code=SUCCESS, prepay_id=wx2018012316460070bab5565c0403181543}
		//}
		var wxh5pay = function(data){
			//console.log("wxh5pay=="+data);
		    //alert(data.appId);
		    //alert(data.timeStamp);
		    //alert(data.nonceStr);
		    //alert(data.pakkage);
		    //alert(data.signType);
		    //alert(data.paySign);
			//alert(JSON.stringify(data));  
		    WeixinJSBridge.invoke(
				'getBrandWCPayRequest',
				{  
					"appId" 	: data.appId,      	//公众号名称，由商户传入  
					"timeStamp"	: data.timeStamp,	//时间戳，自 1970 年以来的秒数  
					"nonceStr" 	: data.nonceStr,	//随机串  
					"package" 	: data.pakkage,     //商品包信息     "prepay_id=" + 
					"signType" 	: data.signType,  	//微信签名方式:  
					"paySign" 	: data.paySign    	//微信签名  
				},
				function(res){   
					//alert(JSON.stringify(result)); 
					if(res.err_msg == "get_brand_wcpay_request:ok"){ 
 	                    alert("支付成功!"); 
 	                   	userOrderHadPay();
 	                }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
 	                    alert("取消支付!");
 	                }else{  
 	                   alert("支付失败!");
 	                } 
			});
		}
		///
		///微信统一下单
		///
		function weixinCreatOrder(payType, methodName){
			//console.log(methodName);
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
					payType : payType,
					rechargeMoney: _rechargeMoney_,//需要支付金额 
					nonceStr: _nonceStr_,
					//以下是订单参数
					materialId: '${material.puk}',
					materialClassify: '${material.classify}',
					userId: '${userId}',
					userIp: '${ip}',
					body: _body_,
					//请求编号
					jobId: _timestamp_,
					//以下是微信支付参数
					pv1: _timestamp_,
					pv2: 'MD5'
				},
				dataType: "json",
				success: function(rest) {
					console.log(rest);
					if('0' == rest.code){
						methodName(rest.data);
						num = 120;
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
		
		////////////////////////////////////////支付查询/////////////////////////////////////////////////
		var num = 0;
		var orderNumber = "";
		//AJAX轮询支付结果
		function loadOrderPayResult(){
			num = num - 1;
			if(num > 1){
				if(num % 10 == 0){
				  	$.ajax({
				  		type:"POST",
				  		url:"/99996020?token=${token}&orderNumber="+orderNumber,
				  		success:function(rest){
				  			console.log(rest);
				  			if('0' == rest.data){
								//显示结果
								userOrderHadPay();
							}
				  		}
				  	});
				}
				setTimeout('loadOrderPayResult()', 1000);
			}else{
		        document.all("payqrcode").style.display="none";
				num = 0;
			}
  			console.log(num);
		}
		////////////////////////////////////////成功i功能支付/////////////////////////////////////////////////
		var jumpTxt = 'http://user.2jiayou.com/newMultimedia.html?token=${token}';
		var mUrl = '&classify=${order.materialClassify}&materialId=${order.materialId}&classifyId=&centreClassifyId=';

		//用户支付返回
		function userOrderHadPay(){
			num = 0;
	        document.all("payqrcode").style.display="none";
			alert("支付成功！");
			location.href = jumpTxt + mUrl ;
		}
	</script>		
	</body>
</html>
