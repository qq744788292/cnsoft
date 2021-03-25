//获取url相应参数值
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}

//获取当前时间的毫秒
function getTimeMs() {
	var t = new Date();
	return t.getTime();
}


//json对象转url地址传参
function jsonTourlparams(object){
	var str = '';
	for(var key in object){
		str = str + key + '=' + object[key] + '&';
	}
	return str.substring(0,str.length-1);
}

//把毫秒的日期时间，转成形如2019-02-28
function formattingDate(date) {
	var t = new Date(date)
	var y=t.getFullYear()
	var m=t.getMonth()+1
	m = m < 10 ? '0'+m : m
	var d=t.getDate()
	d = d < 10 ? '0'+d : d
	var str=y+'-'+m+'-'+d
	return str
}


//把毫秒的日期时间，转成形如2019-02-28  减去或者加几年
function formattingDate2(date,type) {
	var t = new Date(date)
	var y=t.getFullYear()+type
	var m=t.getMonth()+1
	m = m < 10 ? '0'+m : m
	var d=t.getDate()
	d = d < 10 ? '0'+d : d
	var str=y+'-'+m+'-'+d
	return str
}


//按个数对数组进行截取，返回新数组
function chooseArrNumber(arr,num){
	var array = [];
	var yushu = arr.length % num;
	if(yushu === 0){
		var n = parseInt(arr.length / num);
	}else{
		var n = parseInt(arr.length / num) + 1;
	}
	for(var i = 0 ; i<n ;i++){
		array[i] = arr.slice(i*num,(i+1)*num);
	}
	return array;
}

//毫秒时间判断今天明天
function msjudgeDay(time){
	var t = new Date();
	t.setHours(0);
	t.setMinutes(0);
	t.setSeconds(0);
	t.setMilliseconds(0);
	tnd = t.getTime()+1000 * 60 * 60 * 24;
	tnnd = t.getTime()+1000 * 60 * 60 * 48;
	if(time >= tnnd){
		return '后天';
	}else if(time >= tnd){
		return '明天';
	}else if(time < tnd){
		return '今天';
	}else{
		
	}
}

//毫秒时间获取小时和分
function getTimeHM(t){
	var date = new Date(t);
	var h=date.getHours()>12?date.getHours()-12:date.getHours();
		h=h<10?"0"+h:""+h;
	var m=date.getMinutes();
		m=m<10?"0"+m:""+m;
		
	return h+':'+m;
}



//毫秒转天时小时毫秒
function getTimeDHMS(t){
	function checkTime(i) { //将0-9的数字前面加上0，例1变为01 
		if(i < 10) {
			i = "0" + i;
		}
		return i;
	}    	
	var days = parseInt(t / 1000 / 60 / 60 / 24 , 10); //计算剩余的天数 
	var hours = parseInt(t / 1000 / 60 / 60 % 24 , 10); //计算剩余的小时 
	var minutes = parseInt(t / 1000 / 60 % 60, 10);//计算剩余的分钟 
	var seconds = parseInt(t / 1000 % 60, 10);//计算剩余的秒数 
	days = checkTime(days); 
	hours = checkTime(hours); 
	minutes = checkTime(minutes); 
	seconds = checkTime(seconds);
	
	return days+"天" + hours+"小时" + minutes+"分"+seconds+"秒";		
}


//是否是手机号 是 返回 true
function judgePhone(str) {
	var reg = /^0?(13|14|15|18)[0-9]{9}$/
	return reg.test(str)
}

//去除字符串开始和结尾空格的方法；
function clearTrim(str){
	var reg=/(^\s+)|(\s+$)/;
	str=str.replace(reg,"");
	return str;
}

//是否是QQ号 是 返回 true
function judgeQQ(str) {	
	var reg = /^[1-9]([0-9]{5,11})$/
	return reg.test(str)
}

//是否数字正则
function judgeNumber(str) {	
	var reg = /^[0-9]$/
	return reg.test(str)
}

//是否大写字母
function judgeWordBig(str) {	
	var reg = /^[A-Z]$/
	return reg.test(str)
}

//是否正整数
function judgeZhengNumber(str) {	
	var reg = /^[1-9]\d*$/
	return reg.test(str)
}

//是否邮箱
function judgeEmail(str) {	
	var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
	return reg.test(str)
}

//是否中文名字
function judgeChinese(str) {	
	var reg = /^[\u4e00-\u9fa5]{2,11}$/;
	return reg.test(str)
}

//判断是否为字母和数字账号
function judgeAccount(str) {
	var reg = /^[a-zA-Z0-9]*$/;
	return reg.test(str)
}

//json对象第一级属性名，转数组
function jsonToArray(json){
	var array = [];
	for(key in json){
		array.push(key);
	}
	return array;
}