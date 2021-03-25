//通信定义代码需要写入页面，并先加载，//controller层url
//var ws = new WebSocket("ws://127.0.0.1:8888/webchat/123123/456456"); 
//$(function(){
//    $("#contentInp").keyup(function(evt){
//        if(evt.which == 13){ //enter键发送消息
//            var htm = $("#contentInp").val();
 //           
 //       }
  //  });
//})

ws.onopen = function(){
    appendHtm("连接成功！");
}

// 从服务端接收到消息，将消息回显到聊天记录区
ws.onmessage = function(evt){
    appendHtm(evt.data);//todo
}

ws.onerror = function(){
    appendHtm("连接失败！");
}

ws.onclose = function(){
    appendHtm("连接关闭！");
}

// 注销登录
function doClose(){
    ws.close();
}

// 发送消息
function doSend(token,cmd,msg){
	var d=new Date();
	var msgData = new Object();
	//请求时间
	msgData.jobid = d.getTime();
	//登录token
    msgData.token = token;
	//接收者ID（默认全体）
	msgData.toId;
	//通信数据签名
	msgData.sign;
	//通信数据指令ID
	msgData.command = cmd;
	//通信数据内容
	msgData.msg = msg;
	//数据发送
    ws.send(JSON.stringify(msgData));
}

/**************************************时间格式化处理************************************/
function dateFtt(fmt, date) { //author: meizz   
    var o = {
        "M+": date.getMonth() + 1, //月份   
        "d+": date.getDate(), //日   
        "h+": date.getHours(), //小时   
        "m+": date.getMinutes(), //分   
        "s+": date.getSeconds(), //秒   
        "q+": Math.floor((date.getMonth()+3)/3), //季度   
        "S": date.getMilliseconds() //毫秒   
    };
    if(/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}