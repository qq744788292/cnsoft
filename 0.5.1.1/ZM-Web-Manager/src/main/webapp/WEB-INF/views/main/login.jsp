<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <meta charset="utf-8">

    <title>欢迎访问运维管理中心</title>
    
    <!-- 静态引入 -->
	<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/> 
    <link href="/resources/views/css/login.css" rel="stylesheet">
	
  </head>

  <body class="body">
  
					<c:if test="${message != ''}">
					<div class="form-group">
						${message}
					</div>
					</c:if>
	<!-- 这里是登录框 -->
	<div class="form-login">
		<form action="/ManagerLogin" method="POST" class="fh5co-form animate-box" data-animate-effect="fadeIn">
			<input type="hidden" name="token" id="token" value="${token}">
			<h2>请输入...</h2>
			<div class="form-group">
				<label for="username" class="sr-only">Username</label>
				<input type="text" class="form-control" name="account"  placeholder="用户名" value="${loginer.account}" autofocus>
			</div>
			<div class="form-group">
				<label for="password" class="sr-only">Password</label>
				<input type="password" class="form-control" name="passWord" placeholder="密码">
			</div>
			<div class="form-group">
				<table>
					<tr>
						<td>
							<select class="form-control" style="width:110px;" id="hdp" name="hdp" >
								<option value="MANAGER">管理后台</option>
								<option value="OPERATION">运维后台</option>
								<option value="CUSTOMER">客服后台</option>
							</select>
						</td>
						<td width="5%" >&nbsp;</td>
						<td><input type="text" class="form-control" name="securityCode" placeholder="安全码" ></td>
						<td width="5%" >&nbsp;</td>
						<td><img src="99993030?token=" width="68" height="32" onclick="javascript:this.src='99993030?token=&t=' + Math.random()"/></td>
					</tr>
				</table>

			</div>
			<div class="form-group">
				<input type="submit" value="进入" class="btn btn-primary">
			</div>
		</form>
	</div>
	<div style="height:90%; width:100%;">
		<table>
			<tr>
				<td>
					<table>
						<tr style="height:80px;background:#000;">
							<td style="width:200px;color:#fff;"></td>
							<td style="color:#fff; font-size:30px;">欢迎访问运维管理中心</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr style="height: 550px; background: url('/resources/views/img/bg1.jpg') center top no-repeat; opacity: 1;" id="login_tr">
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>
					<table style="width:100%;height:40px;"><!--  border-top:1px solid #DBDBDB; -->
						<tr>
							<td align="center">
								Copyright©2017
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
<script type="text/javascript">


</script>
  </body>
</html>
