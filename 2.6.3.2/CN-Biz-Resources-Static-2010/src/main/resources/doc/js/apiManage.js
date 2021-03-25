function onload(){
	// 渲染左侧列表
	$.ajax({
	  url: '/doc/1.0/base/list',
	  method: 'GET',
	  data: {},
	  dataType: 'json',
	  success: function (data) {
	    var html = template('apiList', { data: data.data })
	    $('.apiList').html(html)
	    // 接口列表点击
	    var apiList = $('.apiList li')
	    var apiArr = []
	    for(let i = 0; i < apiList.length; i++) {
	      $(apiList[i]).click(function () {
	        var apiUrl = $(apiList[i]).find('span').text()
	        console.log(apiUrl, 'apiUrl')
	        var index = apiArr.findIndex(item => item == apiUrl)
	        if(index == -1) {
	          apiArr[0] = apiUrl
	          //apiArr.push(apiUrl)
	          $(selact).removeClass('act')
	          selact = this
	          $(this).addClass('act')
	        } else {
	          apiArr.splice(index,1)
	          $(this).removeClass('act')
	        }
	        console.log(apiArr, 'apiArr')
	        getApiListDetail(apiArr)
	      })
	    }
	  },
	  error: function (err) {
	    console.log(err)
	  }
	})
}
onload();
var selact;

// 点击获取详情
function getApiListDetail (apiArr) {
  var result = []
  for(var i = 0; i< apiArr.length; i++) {
    $.ajax({
      url: '' + apiArr[i],
      method: 'GET',
      data: {},
      dataType: 'json',
      success: function (data) {
    	result[0] = data
        //result.push(data)
        var html = template('list', { data: result })
        $('.listBox').html(html)
      },
      error: function (err) {
        console.log(err)
      }
    })
  }
  document.body.scrollTop = 0;
}
//打开页面
function showBasePage(urlpath){
	var newToken = prompt('请输入管理员Token');//读取Url参数
	$("#token").val(newToken);
	urlpath = urlpath.replace("api", "page")+"/list";
	pageForm.action = urlpath;
	pageForm.submit();
}
