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

<form action="/dba/1.0/tool/transferok" method="POST">
<pf:token />
	<table class="table table-striped table-hover" style="width:96%;margin: 2%" >
		<tr>
			<td>请输入安全验证码
			&nbsp;&nbsp;<input type="password" name="pass" maxlength="12" value="123456" /> 
			&nbsp;&nbsp;<span style="color: red">${msg}</span>
			</td>
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<td colspan="2">源数据库</td>
						<p:tdline width="10" />
						<td colspan="2">目标数据库</td>
					</tr>
					<tr>
						<pf:title tip="源数据库驱动" />
						<pf:tdinput name="sServer.driverClass" tip="请输入源数据库驱动程序" value="com.mysql.jdbc.Driver" />
						<p:tdline width="10" />
						<pf:title tip="目标数据库驱动" />
						<pf:tdinput name="tServer.driverClass" tip="请输入目标数据库驱动程序" value="com.mysql.jdbc.Driver" />
					</tr>
					<tr>
						<pf:title tip="源数据库链接地址" />
						<pf:tdinput name="sServer.url" tip="请输入源数据库链接地址" value="com.mysql.jdbc.Driver" />
						<p:tdline width="10" />
						<pf:title tip="目标数据库链接地址" />
						<pf:tdinput name="tServer.url" tip="请输入目标数据库链接地址" value="com.mysql.jdbc.Driver" />
					</tr>
					<tr>
						<pf:title tip="源数据库用户名称" />
						<pf:tdinput name="sServer.username" tip="请输入源数据库用户名称" value="com.mysql.jdbc.Driver" />
						<p:tdline width="10" />
						<pf:title tip="目标数据库用户名称" />
						<pf:tdinput name="tServer.username" tip="请输入目标数据库用户名称" value="com.mysql.jdbc.Driver" />
					</tr>
					<tr>
						<pf:title tip="源数据库用户密码" />
						<pf:tdinput name="sServer.password" tip="请输入源数据库用户密码" value="com.mysql.jdbc.Driver" />
						<p:tdline width="10" />
						<pf:title tip="目标数据库用户密码" style="text-align: right;width: 160px;" />
						<pf:tdinput name="tServer.password" tip="请输入目标数据库用户密码" value="com.mysql.jdbc.Driver" />
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td><input type="submit" class="btn btn-primary btnright" value="保存" /></td>
		</tr>
	</table>
</form>
</body>
</html>
