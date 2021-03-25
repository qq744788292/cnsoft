//提示信息
function showMessage(type, message) {
	top.window.showMessage(type, message);
}

function showDeleteWarning(url,msg){
	top.window.showDeleteWarning(url,msg);
}

//页面刷新
function changeForm(urlpath, title, navbar) {
	top.window.changeForm(urlpath, title, navbar);
}

function changeNavbar(title, navbar) {
	top.window.changeNavbar(title, navbar);
}

//业务页面刷新
function showPageForm(urlpath) {
	top.window.showPageForm(urlpath);
}

//弹窗关闭
function closePageFormView(){
	parent.closePageFormView();
}