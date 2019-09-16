<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <base href="<%=basePath%>">    
    <title>search</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <!-- Bootstrap -->
    <link href="/resources/css/wm/zk/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/global.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/sticky-footer-navbar.css" rel="stylesheet">	
    
	<!-- Bootstrap js -->
	<script src="/resources/js/wm/zk/jquery.js"></script>
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
    <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
	<script  src="/resources/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	function search(){
		form1.submit();
	}
	</script>
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">条件查询</h4></td>
	</tr>
	<tr class="message-button">
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;"></td>
		    <td align="right">
		    <form action="" method="post" id="form2" name="form2">
					<input type="button" value="清空"  class="btn btn-default btn-sm" onclick="parent.toTargetForm('/WMJGY1L/H.go','${bbb}')">
					<input type="button" value="查询"  class="btn btn-default btn-sm" onclick="search()">
           </form>
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
		<form action="${bbb}" method="post" id="form1" name="form1">	
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
				<%-- <tr height="40">
					<td width="20%" align="right">通道名称</td>
					<td width="5"></td>
					<td>
					<select id="f04" name="f04" style="width: 200px;">
	        			<option></option>
	        			<c:forEach var="item" items="${listXttd}">
	        			<option value="${item.puk}">${item.f03}</option>	        			
	        			</c:forEach>	        			
	      			</select>
					</td>
					<td width="5">&nbsp;</td>
					<td></td>
				</tr> --%>
				
				<c:choose>
		        	<c:when test="${k01!=1}">
				<tr height="40">
					<td width="20%" align="right">登录会员id</td>
					<td width="5"></td>
					<td><input type="text"  class="form-control input-sm" id="f01" name="f01" value="" style="width: 200px;" ></td>
					<td width="5">&nbsp;</td>
					<td></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">会员名字：</td>
					<td width="5"></td>
					<td><input type="text"  class="form-control input-sm" id="f02" name="f02" value=""  style="width: 200px;" ></td>
					<td width="5">&nbsp;</td>
					<td></td>
				</tr>
                	</c:when>
                </c:choose>				
				<tr height="40">
					<td width="20%" align="right">查询日期：</td>
					<td width="5"></td>
					<td><input type="text"  class="form-control input-sm" id="f04" name="f04" value="" size="" style="width: 200px;" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" onClick="WdatePicker()" > ～ <input type="text"  class="form-control input-sm" id="f06" name="f06" value="" size="" style="width: 200px;" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" onClick="WdatePicker()" ></td>
					<td width="5">&nbsp;</td>
					<td></td>
				</tr>
			</table>
			</form>
		</td>
	</tr>
  </table>
</body>
</html>
