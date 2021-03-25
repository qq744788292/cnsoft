<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p"%>
<%@ taglib uri="/WEB-INF/tag/pageform.tld" prefix="pf"%>
<html>
<head>
<meta name="description" content="ZM version.1.6.2 since.2020.4.2">
<title>数据库迁移工具</title>
<jsp:include page="/WEB-INF/views/config/include.jsp" />
<jsp:include page="/WEB-INF/views/config/jquery-validation.jsp" />
</head>
<body class="body">
<form action="/dba/1.0/tool/update" method="POST" id="dbaForm" name="dbaForm">
<pf:token />
</form>
	<table class="table table-striped table-hover" style="width:96%;margin: 2%" >
		<tr>
			<td align="right"><input type="button" class="btn btn-primary btnright" value="返回" onclick="javascript:history.back();" /></td>
		</tr>
		<tr>
			<td><div style="border:1px solid #333;">
				<table>
					<tr>
						<td colspan="2">源数据库</td>
						<td colspan="2">目标数据库</td>
					</tr>
					<tr>
						<pf:title tip="源数据库驱动" />
						<pf:tdinput name="sServer.driverClass" tip="请输入源数据库驱动程序" value="${sServer.driverClass}" dis="true"/>
						<p:tdline width="10" />
						<pf:title tip="目标数据库驱动" />
						<pf:tdinput name="tServer.driverClass" tip="请输入目标数据库驱动程序" value="${tServer.driverClass}" dis="true" />
					</tr>
					<tr>
						<pf:title tip="源数据库链接地址" />
						<pf:tdinput name="sServer.url" tip="请输入源数据库链接地址" value="${tServer.url}" dis="true" />
						<p:tdline width="10" />
						<pf:title tip="目标数据库链接地址" />
						<pf:tdinput name="tServer.url" tip="请输入目标数据库链接地址" value="${tServer.url}" dis="true" />
					</tr>
					<tr>
						<pf:title tip="源数据库用户名称" />
						<pf:tdinput name="sServer.username" tip="请输入源数据库用户名称" value="${tServer.username}" dis="true" />
						<p:tdline width="10" />
						<pf:title tip="目标数据库用户名称" />
						<pf:tdinput name="tServer.username" tip="请输入目标数据库用户名称" value="${tServer.username}" dis="true" />
					</tr>
					<tr>
						<pf:title tip="源数据库用户密码" />
						<pf:tdinput name="sServer.password" tip="请输入源数据库用户密码" value="${tServer.password}" dis="true"/>
						<p:tdline width="10" />
						<pf:title tip="目标数据库用户密码" />
						<pf:tdinput name="tServer.password" tip="请输入目标数据库用户密码" value="${tServer.password}" dis="true" />
					</tr>
				</table></div>
			</td>
		</tr>
		<tr>
			<td><div id="msgBox" style="border:1px solid #333;"></div></td>
		</tr>
	</table>

<script type="text/javascript">
function doUpdate() {
	if(runFlg > 1 ){
	    $.ajax({
	        type: 'POST',
	        url: '/dba/1.0/tool/msg',
	        dataType: 'json',
	        data: $('#dbaForm').serialize(),
	        success: function(rest) {
	            //alert(rest.data);
	            if(rest.code=='1')
	            	runFlg = 1;
	            else
	            	$("#msgBox").append(rest.msg+'<br>');
	          }
	    });
	}
}
var runFlg = 10;
setInterval('doUpdate()', 1500);
</script>
	
</body>
</html>
