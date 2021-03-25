//请先手动引入微信框架
//<script src="/resources/active/jdqsH5/js/jweixin-1.2.0.js"></script>

///设置微信分享
///aid 文章ID
///2017.11.28
function loadWeixinShareDefault(mid){
	return loadWeixinShare("1234567890", 'MaterialShareService', '1', mid);
}
function loadWeixinShare(wxSharetoken, wxShareProcessName, wxShareType, wxShareMaterialId){
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
		url:"/api/1.0/wxshare/loadShareInfo",
		data:{
			jobId : getTimeMs(),
			url: window.location.href,
		    token:wxSharetoken,
			spName: wxShareProcessName,
			type: wxShareType,
			id: wxShareMaterialId
        },
		async: false,
		dataType:"json",
		success:function(result){
			console.log("result.data==="+result.data);
			if(result.code == 0){
				//设定参数
				_appID_ = result.data.appID;
				_jsapi_ticket_ = result.data.jsapi_ticket;
				_signature_ = result.data.signature;
				_nonceStr_ = result.data.nonceStr;
				_timestamp_ = result.data.timestamp;
				
				_title_ = result.data.title;
				_desc_ = result.data.desc;
				_link_ = result.data.link;//1.4版本变量名称修正
				_imgUrl_ = result.data.imgUrl;
				//初始化
				wx.config({
				    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    appId: _appID_, // 必填，公众号的唯一标识
				    timestamp: _timestamp_, // 必填，生成签名的时间戳
				    nonceStr: _nonceStr_, // 必填，生成签名的随机串
				    signature: _signature_,// 必填，签名，见附录1
				    //1.4版本接口名称修正
				    jsApiList: ['checkJsApi','updateAppMessageShareData','updateTimelineShareData','onMenuShareWeibo']// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
				
				//微信分享
				wx.ready(function(){
				    // 定义通用发送数据
				    var _data_ = {
				    	title: _title_,// 分享标题
				  	    desc: _desc_,// 分享描述
				  	    link: _link_, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
				  	    imgUrl: _imgUrl_,// 分享图标
				    };
					console.log("_data_==="+_data_);

				    wx.onMenuShareTimeline({
				        title: _data_.title, 	// 分享标题
				  	    desc: _data_.desc,		// 分享描述
				        link: _data_.link, 		// 分享链接
				        imgUrl: _data_.imgUrl, 	// 分享图标
				        success: function (res) {
					    	  alert('ok');
					      }
				    });
				
				    wx.updateAppMessageShareData(_data_);  //分享给朋友及分享到QQ
				    wx.updateTimelineShareData(_data_); //分享到朋友圈及分享到QQ空间
				    wx.onMenuShareWeibo(_data_); //分享到微博
				});
			}else{
		    	  alert('接口错误');
			}
		},
		error:function(error){
			 alert(error);
		}
	});
}
/////////////////////////用户分享/////////////////////////////////////