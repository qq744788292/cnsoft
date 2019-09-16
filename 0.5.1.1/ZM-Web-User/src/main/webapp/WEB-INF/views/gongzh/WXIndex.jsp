<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="keywords" content="浙江省对外贸易公共服务平台">
    <meta name="description" content="宜田科技">
    <meta name="version" content="1.1.1-RELEASE build 2018.05.21">
	<title>浙江省对外贸易公共服务平台</title>

</head>

<body>
<c:if test="${not empty menu}">
<!-- 
	//         http://jr2.wx.zjmade.cn/index.html?token=A13135520006B1C0C7F1815891451226C869A21615801122A74_2_E_7_5_8_C_&flag=0&menuId=&menuName=#/submitPerson
 -->
	<c:choose> 
	  <c:when test="${menu.menuType=='1000'}">   
    <script type="text/javascript">
	var url = "http://jr.wx.zjmade.cn/index.html?token=${token}&flag=${flag}&menuId=${menu.puk}&menuName=${menu.menuName}&menuUrl=/helloworld"
	</script> 
	  </c:when> 
	  <c:when test="${menu.menuType=='2000'}">   
    <script type="text/javascript">
	var url = "${menu.menuUrl}"
	</script> 
	  </c:when>  
	  <c:when test="${menu.menuType=='3000'}">   
    <script type="text/javascript">
	var url = "http://jr.wx.zjmade.cn/index.html?token=${token}&flag=${flag}&menuId=${menu.puk}&menuName=${menu.menuName}"
	</script> 
	  </c:when> 
	  <c:otherwise>   
    <script type="text/javascript">
	var url = "http://jr.wx.zjmade.cn/index.html?token=${token}&flag=${flag}&menuId=${menu.puk}&menuName=${menu.menuName}&menuUrl=${menu.menuUrl}"
	</script> 
	  </c:otherwise> 
	</c:choose> 

	<script type="text/javascript">
	location.href= url;
	</script>

</c:if>

<c:if test="${ empty menu}">
配置错误!!!
</c:if>


</body>
</html>