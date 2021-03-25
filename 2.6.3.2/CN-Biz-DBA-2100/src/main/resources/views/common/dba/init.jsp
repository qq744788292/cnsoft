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
<form action="" method="POST" id="dbaform">
<pf:token />
	<table class="table " style="width:96%;margin: 2%" >
		<tr>
			<td colspan="2">请选择</td>
		</tr>
		<p:trline/>
	</table>
	<table class="table table-striped table-hover" style="width:96%;margin: 2%" >
		<tr>
			<td style="text-align: center;"><input type="button" class="btn btn-primary btnright" value="数据库重构" onclick="doProcess('1')" /></td>
			<td>新建数据库或者删除原库，并完成数据初始化</td>
		</tr>
		<tr>
			<td style="text-align: center;"><input type="button" class="btn btn-primary btnright" value="数据库升级" onclick="doProcess('2')" /></td>
			<td>原有数据库升级到最新数据库，保留数据</td>
		</tr>
		<tr>
			<td style="text-align: center;"><input type="button" class="btn btn-primary btnright" value="数据库迁移" onclick="doProcess('3')" /></td>
			<td>数据库之间完整迁移</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
function doProcess(type) {
	if(type=='1')
		dbaform.action = "/dba/1.0/tool/build";
	if(type=='2')
		dbaform.action = "/dba/1.0/tool/update";
	if(type=='3')
		dbaform.action = "/dba/1.0/tool/transfer";
		
	dbaform.submit();
}
</script>
</body>
</html>
