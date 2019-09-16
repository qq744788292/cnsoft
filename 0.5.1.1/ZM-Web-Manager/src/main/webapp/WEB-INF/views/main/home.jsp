<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <meta charset="utf-8">

    <title>欢迎访问运维管理中心</title>
    
    <!-- 静态引入 -->
	<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/> 
	
  </head>

<body>
	
	<form action="/ManagerHome" id="userForm" name="userForm" method="POST">
		<input type="hidden" name="token" id="token" value="${token}">
	</form> 
	
	<script type="text/javascript">
		userForm.submit();
	</script>
  </body>
</html>
