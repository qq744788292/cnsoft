<%
/*
 * 图片上传
 * @author ZmSoft
 * @version 0.1.0
 * @since 0.1.0 2018/3/1
 */
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<html>
<head>
<script type="text/javascript">
	parent.setPicUrl${id}('${result.data}');
</script>
</head>
<body>
${result.data}
</body>
</html>
