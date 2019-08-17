<%
	/**
	 * 用户授权
	 * @author ZmSoft
	 * @version 0.1.0
	 * @since 0.1.0 2018/4/2
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="version" content="1.1.1-RELEASE build 2018.05.21">
<title>Loading</title>

</head>

<body>
	<script type="text/javascript">
		var url = "https://uatuser.gtdreamlife.com/h5/#/mainPage?loginType=9&userSource=10&token=${token}";//最终访问地址
	</script>
	<c:if test="${ empty type}">
		<script type="text/javascript">
			url = "https://uatuser.gtdreamlife.com/h5/index.html?loginType=9&userSource=10&token=${token}";//最终访问地址
		</script>
	</c:if>

	<!-- type类型分类  1.楼盘详情,2.活动,3.文章 -->
	
	<c:if test="${not empty type}">
		<c:choose>
			<c:when test="${type=='1'}">
				<script type="text/javascript">
					//楼盘/mainPage/buildDetail?estateId=
					url = "https://uatuser.gtdreamlife.com/h5/#/mainPage/buildDetail?estateId=${menuId}&loginType=9&userSource=10&token=${token}";
				</script>
			</c:when>
			<c:when test="${type=='2'}">
				<script type="text/javascript">
					//活动
					url = "https://uatuser.gtdreamlife.com/h5/#/mainPage/building?loginType=9&userSource=10&token=${token}";
				</script>
			</c:when>
			<c:when test="${type=='3'}">
				<script type="text/javascript">
					//文章
				 	url = "https://uatuser.gtdreamlife.com/h5/#/articleList/articleinfo?id=${menuId}&loginType=9&userSource=10&token=${token}";
				</script>
			</c:when>
			<c:otherwise>
				<script type="text/javascript">
					url = "https://uatuser.gtdreamlife.com";//默认地址
				</script>
			</c:otherwise>
		</c:choose>
	</c:if>

	<script type="text/javascript">
		//加载显示页面
		//alert(url);
		location.href = url;
	</script>
</body>
</html>