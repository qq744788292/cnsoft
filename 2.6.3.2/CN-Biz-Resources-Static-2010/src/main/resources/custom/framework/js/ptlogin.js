//业务页面刷新
function onloaded() {
	// 设定高度
	$('#bottom_tr').height(50)
	$('#login_tr').height($(document).height() - 150)
	// 间隔5s检查是否登录
	setInterval(checklogin(), 5000);
}

//检查是否登录
function checklogin() {
	var checkloginurl = $('#checkloginurl').val();
	$.ajax({
		type : "post",
		url : checkloginurl,
		data : {
			jobId : getTimeMs()
		},
		dataType : "json",
		success : function(result) {
			console.log(result);
			if (result.code == '0') {//登录的场合
				$('#token').val(result.token);
				partner_home_form.submit();
			}else if (result.code == '1') {//继续等待
				setInterval(checklogin(), 5000);
			}else{//二维码过期
				//显示提示信息
				$('#form_message').removeClass('dn');
				$('#form_message').html(result.msg);
			}
		},
		error : function(result) {
			console.log(result);
		}
	});
}
