<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>警告</title>
</head>
<body>
	<table align="center" width="100%" style="margin: 0; padding: 0;"
		border="0">
		<tr height="40"
			style="border: 1px solid #DBDBDB; background: #f8f8f8;">
			<td align="center"><h4 style="margin-left: 10px;">您的账户存在异常，请联系管理员解决！</h4></td>
		</tr>
		<%
			Exception e = (Exception) request.getAttribute("exception");
		%>
		<tr>
			<td align="center"><%=e.getMessage()%></td>
		</tr>
		<%
			for (StackTraceElement ste : e.getStackTrace()) {
				out.println("<tr>");
				out.println("<td>");
				out.println(ste);
				out.println("</td>");
				out.println("</tr>");
			}
		%>
	</table>
</body>
</html>
