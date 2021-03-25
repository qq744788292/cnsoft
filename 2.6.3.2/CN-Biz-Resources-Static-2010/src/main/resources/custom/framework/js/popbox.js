// 窗口展示
function _display_popbox_(type) {
	if (type == 1) {// 打开
		$("#_popbox_").css('display', '');
		$("#_popbox_container_").addClass("popbox_move");
	} else if (type == 2) {// 关闭
		$("#_popbox_").css('display', 'none');
		$("#_popbox_container_").removeClass("popbox_move")
		document.all("_myBizFrame_").src = "/static/blank.html";
	}
}

// 预览
function showPageFormView(urlpath) {
	_display_popbox_(1);
	_popboxForm_.action = urlpath;
	_popboxForm_.submit();
}

// 关闭
function closePageFormView() {
	_display_popbox_(2);
}