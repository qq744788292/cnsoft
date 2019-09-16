<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>	
    <base href="<%=basePath%>">
    
    <title>画面菜单创建与编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="/resources/css/pt/bootstrap.min.css" rel="stylesheet" media="screen">
<!-- 	<link href="/resources/css/pt/global.css" rel="stylesheet" media="screen"> -->
	<script type="text/javascript" src="/resources/js/pt/jquery.js"></script>
	<script src="/resources/js/pt/bootstrap.min.js"></script>
	<script type="text/javascript">

		function save(){
// 			isNull();
			var  rs=document.formsb01.puk.value;
			 	if(rs!=""){
					document.formsb01.action="/PTYDM/U.go";
					formsb01.submit();
				}else{
					document.formsb01.action="/PTYDM/I.go";
 					formsb01.submit(); 				
				}
 		}
		function clearForm(){	
		
			var formObj=document.getElementById('formsb01');
			if(formObj==undefined){
				return;
			}	
			
			for(var i=0; i<formObj.elements.length; i++){
				if(formObj.elements[i].type == "text"){
					formObj.elements[i].value = ""; 
				}else if(formObj.elements[i].type == "password"){
					formObj.elements[i].value = "";  
				}else if(formObj.elements[i].type == "radio"){
					formObj.elements[i].checked = false;
				}else if(formObj.elements[i].type == "checkbox"){
					formObj.elements[i].checked = false;
				}else if(formObj.elements[i].type == "file"){
					var file = formObj.elements[i]; 
					if (file.outerHTML){
						file.outerHTML = file.outerHTML;
					}else{
						 file.value = ""; 
					}
				}else if(formObj.elements[i].type == "textarea"){
					formObj.elements[i].value = "";
				}else if(formObj.elements[i].type == "select"){
					formObj.elements[i].options[0].selected = true;
				}else if(formObj.elements[i].type == "hidden"){
					formObj.elements[i].value = "";
				}
			}
		}	
		
	</script>
  </head> 
  <body>
  <table width="100%" border="0">
  	<form  action="" method="post" id="formsb01" name="formsb01">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">画面菜单创建与编辑</h4></td>
    </tr>
    <tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">提示信息</td>
		    <td align="right">
					  <button type="submit" class="btn btn-default btn-sm"><a href="http://localhost:8888/PTYDM/F.go">返回</a></button>
					  <button type="reset" class="btn btn-default btn-sm">重置</button>
					  <button type="submit" class="btn btn-primary btn-sm" onclick="save()">保存</button>
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td><table align="center" width="100%" style="margin:0; padding:0;" border="0">
        <tr height="40">
          <td width="20%" align="right"></td>
          <td width="5"></td>
          <td><input type="hidden" class="form-control input-sm" id="puk" name="puk" value="${parambean1.puk}"></td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">画面名称：</td>
          <td width="5"></td>
          <td><input type="text" class="form-control input-sm" id="f01" name="f01" maxlength="20" value="${parambean1.f01}"></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr height="40">
			<td width="20%" align="right">显示为菜单：</td>
			<td width="5"></td>
				<td><input type="checkbox" class="form-control input-sm" id="f01" name="f01" maxlength="20" value="${parambean1.f01}"></td>
			<td width="5"></td>
			<td width="30%">备注</td>
		</tr>
        <tr>
          <tr height="40">
          <td width="20%" align="right">父菜单ID：</td>
          <td width="5"></td>
          <td>
		  <select class="form-control" id="k03" name="k03">
			<c:forEach var="cssb03dbo" items="${list}" end="0">
				<option value="${cssb03dbo.k03}">${cssb03dbo.k03}</option>
				
			</c:forEach>
		  </select>
		  </td>
		  <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">菜单URL：</td>
          <td width="5"></td>
          <td><input type="text" class="form-control input-sm" id="f03" name="f03" maxlength="200" value="${parambean1.f03}"></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">排列顺序：</td>
          <td width="5"></td>
          <td><input type="text" class="form-control input-sm" id="f04" name="f04" maxlength="4" value="${parambean1.f04}"></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr>
          <tr height="40">
          <td width="20%" align="right">画面种类：</td>
          <td width="5"></td>
          <td>
		  <select class="form-control">
			<c:forEach var="parambean1" items="${list}">
        	 <option value="${parambean1.f06}">1</option>
        	 <option value="${parambean1.f06}">2</option>
        	 </c:forEach>	
		  </select>
		  </td>
		  <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        </tr><tr height="40">
        <td width="20%" align="right"></td>
        <td width="5"></td>
        	<td><textarea  rows="8" cols="60" id="f04" name="f04"  value="">${parambean1.f04}</textarea></td>
		<td width="5"></td>
		<td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right"></td>
          <td width="5"></td>
          <td><input type="hidden" class="form-control input-sm" id="uu1" name="uu1" value="${parambean1.uu1}"></td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
      </table></td>
    </tr>
    </form>
  </table>
  </body>
</html>