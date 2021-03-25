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
<form action="/dba/1.0/tool/buildok" method="POST">
<pf:token />
	<table class="table table-striped table-hover" style="width:96%;margin: 2%" >
		<tr>
			<td>请输入安全验证码
			&nbsp;&nbsp;<input type="password" name="pass" maxlength="12" value="123456" /> 
			&nbsp;&nbsp;<span style="color: red">${msg}</span>
			</td>
		</tr>
		<p:trline/>
		<tr>
			<td>
				<table>
					<tr>
						<td colspan="3">目标数据库</td>
					</tr>
					<tr>
						<pf:title tip="目标数据库驱动"  style="width:18%;text-align: right;padding-right: 10px;"/>
						<p:tdline width="10" />
						<pf:tdinput name="tServer.driverClass" tip="请输入目标数据库驱动程序" value="com.mysql.jdbc.Driver" />
					</tr>
					<tr>
						<pf:title tip="目标数据库链接地址" />
						<p:tdline width="10" />
						<pf:tdinput name="tServer.url" tip="请输入目标数据库链接地址" value="jdbc:mysql://47.96.56.82:3306/zm_dev?useUnicode=yes&characterEncoding=utf8&&autoReconnect=true" />
					</tr>
					<tr>
						<pf:title tip="目标数据库用户名称" />
						<p:tdline width="10" />
						<pf:tdinput name="tServer.username" tip="请输入目标数据库用户名称" value="zhsys" />
					</tr>
					<tr>
						<pf:title tip="目标数据库用户密码"/>
						<p:tdline width="10" />
						<td><input type="password" class="form-control" name="tServer.password" maxlength="120" value="zaq12wsx" /></td>
					</tr>
					<tr>
						<pf:title tip="操作类别"/>
						<p:tdline width="10" />
						<td>
						<select name="type" class="form-control" style="width:18%;">
						  <option value ="creat">新建</option>
						  <option value ="init">初始化</option>
						  <option value="build">重构</option>
						</select>
						</td>
					</tr>
					<tr>
						<pf:title tip="原表模式"/>
						<p:tdline width="10" />
						<td>
						<select  name="drop" class="form-control" style="width:18%;">
						  <option value ="2">删除原表</option>
						  <option value ="1">保留原表</option>
						</select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td><input type="submit" class="btn btn-primary btnright" value="开始" /></td>
		</tr>
	</table>
</form>
</body>
</html>
