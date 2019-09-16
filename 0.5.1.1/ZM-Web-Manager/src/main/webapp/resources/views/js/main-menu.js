var selectPuk='';
var selectSubPuk='';
//一级菜单修改背景颜色
function changeBackground(puk){
	//设定新的
	if(puk != ''){	
		try{
		document.all("item_"+puk).style.background= "#2f88c6";
		//打开菜单	
		document.all("itemMenu_"+puk).style.display= "";
		document.all("itemMenu_"+puk).style.transition= "all .5s ease 0s"; 
		}catch(e){}
	}
	//处理旧的
	if(selectPuk != ''){
		try{
			//还原背景色		
			document.all("item_"+selectPuk).style.background= "#00428e"; 
			//关闭打开菜单		
			document.all("itemMenu_"+selectPuk).style.display= "none"; 
		}catch(e){}
	}
	selectPuk = puk;
}
//二级菜单修改背景颜色
function changeBackgroundSub(subPuk){
	//设定新的
	if(subPuk != ''){	
		try{
			document.all("itemSub_"+subPuk).style.background= "#2f88c6";
		}catch(e){}
	}
	//处理旧的
	if(selectSubPuk != ''){
		try{
			//还原背景色		
			document.all("itemSub_"+selectSubPuk).style.background= "#1b446a";
		}catch(e){}
	}
	selectSubPuk = subPuk;
}

//提示信息
function showMessage(type, message) {
	if(type=='' || message=="")
		return;
    if (type == 1) {//成功
        document.all("messagebox").className = "alert alert-success";
        document.all("messagelbl").innerText = " 【成功】  " + message;
        document.all("messagebox").style.display = "";
    } else if (type == 2) {//提示
        document.all("messagebox").className = "alert alert-info";
        document.all("messagelbl").innerText = " 【提示】  " + message;
        document.all("messagebox").style.display = "";
    } else if (type == 3) {//警告
        document.all("messagebox").className = "alert alert-warning";
        document.all("messagelbl").innerText = " 【警告】  " + message;
        document.all("messagebox").style.display = "";
    } else if (type == 4) {//注意、错误
        document.all("messagebox").className = "alert alert-danger";
        document.all("messagelbl").innerText = " 【注意】  " + message;
        document.all("messagebox").style.display = "";
    }
    window.setTimeout(function () {
        document.all("messagebox").style.display = "none";
    }, 3000);
}

//页面刷新
function changeForm(urlpath, title, navbar) {
    showPageForm(urlpath);
    changeNavbar(title, navbar);
}

function changeNavbar(title, navbar) {
    //更新功能显示名字
    document.all("title").innerText = title;
    //更新导航条
    document.all("navbar").innerText = navbar;
}

//业务页面刷新
function showPageForm(urlpath) {
    blankForm.action = urlpath;
    blankForm.submit();
}

//高度修正
function resizeIFrame() {
	var screen_width = document.documentElement.clientWidth;
	var screen_height = document.documentElement.clientHeight;

	var cur_width = 1200;
	var cur_height = 600;
	var cur_width_fix = 100;
	var cur_height_fix = 50;

	// 计算高度
	{
		var mb = myBrowser();
		console.log(mb);
		// 处理浏览器差异的13个像素
		if ("IE" == mb) {
			cur_height = screen_height - cur_height_fix-27;
		} else if ("FF" == mb) {
			cur_height = screen_height - cur_height_fix;
		} else if ("Chrome" == mb) {
			cur_height = screen_height - cur_height_fix;
		} else if ("Opera" == mb) {
			cur_height = screen_height - cur_height_fix;
		} else if ("Safari" == mb) {
			cur_height = screen_height - cur_height_fix;
		}
	}

	document.all("myIframeTD").style.height = cur_height + "px";
	
    document.getElementById("messagebox").style.width = (screen_width * 0.4) + "px";
}

//动态修正高度
resizeIFrame();

function showDeleteWarning(url,msg){
    $('#deleteWarningMessage').empty().text("【"+msg+"】");
    $("#deleteWarningModalDeleteButton").attr("onclick", "doDeleteRequest('"+url+"')");
    $("#deleteWarningModal").modal('show');
}
function doDeleteRequest(url){
    $("#deleteWarningModal").modal("hide");
    showPageForm(url);
}

//只区分浏览器，不考虑版本
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
	else if (userAgent.indexOf("compatible") > -1
			&& userAgent.indexOf("MSIE") > -1 && !isOpera) {
		return "IE";
	}
	// 判断是否IE浏览器
}

