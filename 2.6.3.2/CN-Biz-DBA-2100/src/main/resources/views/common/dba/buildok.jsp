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
	<table class="table table-striped table-hover" style="width:96%;margin: 2%" >
		<tr>
			<td align="right"><input type="button" class="btn btn-primary btnright" value="返回" onclick="javascript:history.back();" /></td>
		</tr>
		<tr>
			<td><div style="border:1px solid #333;">
				<table>
					<tr>
						<td colspan="3">目标数据库</td>
					</tr>
					<tr>
						<pf:title tip="目标数据库驱动" style="width:18%;text-align: right;padding-right: 10px;"/>
						<p:tdline width="10" />
						<pf:tdinput name="tServer.driverClass" tip="请输入目标数据库驱动程序" value="${tserver.driverClass}" dis="true" />
					</tr>
					<tr>
						<pf:title tip="目标数据库链接地址"/>
						<p:tdline width="10" />
						<pf:tdinput name="tServer.url" tip="请输入目标数据库链接地址" value="${tserver.url}" dis="true" />
					</tr>
					<tr>
						<pf:title tip="目标数据库用户名称"/>
						<p:tdline width="10" />
						<pf:tdinput name="tServer.username" tip="请输入目标数据库用户名称" value="${tserver.username}" dis="true" />
					</tr>
				</table></div>
			</td>
		</tr>
		<tr>
			<td><div id="msgBox" style="border:1px solid #333;"></div></td>
		</tr>
	</table>

<form action="/dba/1.0/tool/buildok" method="POST" id="dbaForm">
<pf:token />
</form>
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
