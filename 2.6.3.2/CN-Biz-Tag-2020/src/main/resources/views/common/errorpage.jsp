<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p"%>
<%@ taglib uri="/WEB-INF/tag/pageform.tld" prefix="pf" %>
<%@ page trimDirectiveWhitespaces="true"%>

<html>
<head>
<meta charset="utf-8">
<title>errorpage</title>
<!-- 静态引入 -->
<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8" />
</head>
<body class="body">
<br>
  <div class="card-header text-center" style="border : 0px">系统异常<br>${result.msg}</div>   
  <p:message data="${result}" />
</body>
</html>
