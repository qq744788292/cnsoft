// 提示信息
function showMessage(code, message) {
	if (code == '1') {// 警告
		showMessageByType('1', '4', message);
	} else {
		var arr = message.split(":");
		showMessageByType('0', arr[0], arr[1]);
	}
}
function showMessageByType(code, type, message) {
	if (type == '' || message == "")
		return;
	else if (type == '1') {// 成功
		document.all("messagetyle").className = "alert alert-success";
		document.all(" messagetitle").innerText = "【成功】  ";
		document.all("messagelbl").innerText = message;
		document.all("messagebox").style.display = "";
	} else if (type == '2') {// 提示
		document.all("messagetyle").className = "alert alert-info";
		document.all("messagetitle").innerText = "【提示】  ";
		document.all("messagelbl").innerText = message;
		document.all("messagebox").style.display = "";
	} else if (type == '3') {// 警告
		document.all("messagetyle").className = "alert alert-warning";
		document.all("messagetitle").innerText = "【警告】  ";
		document.all("messagelbl").innerText = message;
		document.all("messagebox").style.display = "";
	} else if (type == '4') {// 注意、错误
		document.all("messagetyle").className = "alert alert-danger";
		document.all("messagetitle").innerText = "【注意】  ";
		document.all("messagelbl").innerText = message;
		document.all("messagebox").style.display = "";
	} else {

	}
	window.setTimeout(function() {
		document.all("messagebox").style.display = "none";
	}, 3000);
}

// 页面刷新
function changeForm(urlpath, title, navbar) {
	showPageForm(urlpath);
	changeNavbar(title, navbar);
}

function changeNavbar(title, navbar) {
	// 更新功能显示名字
	document.all("title").innerText = title;
	// 更新导航条
	document.all("navbar").innerText = navbar;
}

function showConfirmInfo(url, msg) {
	$('#myModalLabel').empty().text("确认");
	$('#defaultMessage').empty().text("即将进行以下重要操作，请确认！");
	$('#deleteWarningMessage').empty().text("【" + msg + "】");
	$("#deleteWarningModalDeleteButton").attr("onclick", "doDeleteRequest('" + url + "')");
	$("#deleteWarningModal").modal('show');
}
function showDeleteWarning(url, msg) {
	$('#myModalLabel').empty().text("警告");
	$('#defaultMessage').empty().text("删除之后数据可能无法恢复，确认删除吗?");
	$('#deleteWarningMessage').empty().text("【" + msg + "】");
	$("#deleteWarningModalDeleteButton").attr("onclick", "doDeleteRequest('" + url + "')");
	$("#deleteWarningModal").modal('show');
}

function doDeleteRequest(url) {
	$("#deleteWarningModal").modal("hide");
	showPageForm(url);
}

// 只区分浏览器，不考虑版本
function myBrowser() {
	var userAgent = navigator.userAgent; // 取得浏览器的userAgent字符串
	var isOpera = userAgent.indexOf("Opera") > -1;
	if (isOpera) {
		return "Opera"
	}
	// 判断是否Opera浏览器
	else if (userAgent.indexOf("Firefox") > -1) {
		return "FF";
	} // 判断是否Firefox浏览器
	else if (userAgent.indexOf("Chrome") > -1) {
		return "Chrome";
	} else if (userAgent.indexOf("Safari") > -1) {
		return "Safari";
	} // 判断是否Safari浏览器
	else if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
		return "IE";
	}
	// 判断是否IE浏览器
}
