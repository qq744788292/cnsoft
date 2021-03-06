//请先手动引入微信框架
//<script src="/resources/active/jdqsH5/js/jweixin-1.2.0.js"></script>

///设置微信分享
///aid 文章ID
///2017.11.28
function loadWeixinShare(aid){
	//数字签名
	var _appID_ = ""; 
	var _jsapi_ticket_ = "";
	var _signature_ = "";
	var _nonceStr_ = "";
	var _timestamp_ = "";
	var _timestamp_ = "";
	//文章描述
	var _title_ = "";
	var _desc_ = "";
	var _link_ = "";
	var _imgUrl_ = "";
	//1.请求数字签名
	$.ajax({
		type:"post",
		url:"/weixin/loadShareToken",
		data:{
		    token:"${token}",
			id: aid
        },
		async: false,
		dataType:"json",
		success:function(result){
			if(result.code == 0){
				//设定参数
				_appID_ = result.appID;
				_jsapi_ticket_ = result.jsapi_ticket;
				_signature_ = result.signature;
				_nonceStr_ = result.nonceStr;
				_timestamp_ = result.timestamp;
				
				_title_ = result.title;
				_desc_ = result.desc;
				_link_ = result.link;
				_imgUrl_ = result.imgUrl;
				//初始化
				wx.config({
				    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    appId: _appID_, // 必填，公众号的唯一标识
				    timestamp: _timestamp_, // 必填，生成签名的时间戳
				    nonceStr: _nonceStr_, // 必填，生成签名的随机串
				    signature: _signature_,// 必填，签名，见附录1
				    jsApiList: ['checkJsApi','onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone']// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
				
				//微信分享
				wx.ready(function(){
				    // 定义通用发送数据
				    var _data_ = {
				    	title: _title,// 分享标题
				  	    desc: _desc_,// 分享描述
				  	    link: _link_, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
				  	    imgUrl: _imgUrl_,// 分享图标
				    };
				    
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
			}else{
				console.log("错误");
			}
		},
		error:function(error){
		    console.log(error)
		}
	});
}
/////////////////////////用户分享/////////////////////////////////////