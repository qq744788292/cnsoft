<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p"%>
<%@ taglib uri="/WEB-INF/tag/pageform.tld" prefix="pf" %>
<%@ page trimDirectiveWhitespaces="true"%>

<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
<!-- 静态引入 -->
<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8" />
</head>
<body class="body">
  <p:message data="${message}" />
<script type="text/javascript">
window.setTimeout(function() {
	showPageForm('${target}');
}, 3000);
</script>
</body>
</html>
