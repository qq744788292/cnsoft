<%
/*
 * 文件上传
 * @author ZmSoft
 * @version 0.1.0
 * @since 0.1.0 2018/3/1
 */
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pageform.tld" prefix="pf" %>
<html>
<html>
<head>
<meta charset="utf-8">
    
	<title>fileupload.jsp</title>
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/> 
</head>
<body>
<form id="myFileForm" class="layui-form" action="/99999011?token=${token}&id=${id}&width=${width}&height=${height}" enctype="multipart/form-data" method="post">
<table width="100%" height="100%" border="0">
	<tr>
		<td align="center">
	        <img src="${result.data}" width="${width}px" height="${height}px" alt="请选择">
		</td>
	</tr>
	<tr>
		<td align="center">
			<button type="button" onclick="upfile.click()" >上传文件</button>
			<input type="file" id="upfile" name="upfile" onchange="javascript:myFileForm.submit()" style="visibility: hidden; position: absolute;">
		</td>
	</tr>
</table>
</form>
</form>
<script type="text/javascript">
try{
	$("#${id}", window.parent.document).val('${result.data}');
}catch(e){
	
}
</script>

</body>
</html>
