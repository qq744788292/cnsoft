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
    <title>用户组定义</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/resources/css/wm/zk/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/global.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/sticky-footer-navbar.css" rel="stylesheet">	
    
	<!-- Bootstrap js -->
	<script src="/resources/js/wm/zk/jquery.js"></script>
	<script type="text/javascript" src="/resources/js/wm/jquery-1.8.3.js"></script>
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
    <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#k01").blur(function() {
				if ("" == $(this).val()) {
					alert("您还没有输入该用户组编号");
					$(this).focus();
				} else {
					$("#k02").blur(function() {
						if ("" == $(this).val()) {
							alert("请输入用户组名称");
							$(this).focus();
						} else {
							$("#k03").blur(function() {
								alert("");
								if ("" == $("#k03 option:selected").text()) {
									alert("请选择上级用户组编号");
									$(this).focus();
								} 
// 								else {
// 									$("#f02").blur(function() {
// 										if ("" == $(this).text()) {
// 											alert("请选择系统分类");
// 											$(this).focus();
// 										}
// 									});
// 								}
							});
						}
					});
				}
			});
		});
		function clearForm() {
			var formObj = document.getElementById("form1");
				if (formObj == undefined) {
				return;
			}
			for ( var i = 0; i < formObj.elements.length; i++) {
				if (formObj.elements[i].type == "text") {
					formObj.elements[i].value = "";
				} else if (formObj.elements[i].type == "password") {
					formObj.elements[i].value = "";
				} else if (formObj.elements[i].type == "radio") {
					formObj.elements[i].checked = false;
				} else if (formObj.elements[i].type == "checkbox") {
					formObj.elements[i].checked = false;
				} else if (formObj.elements[i].type == "file") {
					var file = formObj.elements[i];
					if (file.outerHTML) {
						file.outerHTML = file.outerHTML;
					} else {
						file.value = "";
					}
				} else if (formObj.elements[i].type == "textarea") {
					formObj.elements[i].value = "";
				} else if (formObj.elements[i].type == "select") {
					formObj.elements[i].options[0].selected = true;
				} else if (formObj.elements[i].type == "hidden") {
					formObj.elements[i].value = "";
				}
			}
		}
		function back() {
			document.form1.action = "/WMKZ06/F.go";
			form1.submit();
		}
		function save(){
	  		var rs = document.form1.puk.value;
			if (rs != "") {
				document.form1.action = "/WMKZ06/U.go";
				form1.submit();
			} else {
				document.form1.action = "/WMKZ06/C.go";
				form1.submit();
			}	
		}
		function insert(){
// 			alert(document.form1);
			document.form1.action = "/WMKZ06/C.go";
			form1.submit();
			
		}
		function insert1(){//update('${insert.puk}','${parambean.puk}')
			document.form3.action = "/WMKZ06/C.go";
			form3.submit();
// 			update();
// 			update('${insert.puk}','${parambean.puk}');
		}
		function update(k03,puk){		 
// 			 insert();
// 			 var k03=document.form3.puk;
// 			 alert(k03);
			 document.form2.action = "/WMKZ06/U1.go?k03="+k03+"&puk="+puk;
			 form2.submit();
		}	
	</script>
  </head>
  <body>
  <table width="100%" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">用户组定义</h4></td>
    </tr>
    <tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:14px;">
		   </td>
		    <td align="right">
<!-- 					  <button  class="btn btn-default btn-sm" onclick="back()">返回</button> -->
					  <button  class="btn btn-default btn-sm" onclick="clearForm()">重置</button>
					  <button  class="btn btn-primary btn-sm" onclick="save()">保存</button>	
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td>     
      <form action="" method="post" id="form1" name="form1">
      <table align="center" width="100%" style="margin:0; padding:0;" border="0">  
         <tr height="40">
          <td width="20%" align="right"></td>
          <td width="5"></td>
          <td>
          	<input type="hidden" class="form-control input-sm" 
          	id="puk" name="puk" value="${parambean1.puk}" >
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">用户组编号：</td>
          <td width="5"></td>
          <td>
          <input type="text"   class="form-control input-sm" id="k01" name="k01" value="${parambean1.k01}">
<!--           <input type="text" class="form-control input-sm" id="exampleInputEmail1"> -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">用户组名称：</td>
          <td width="5"></td>
          <td>
             <input type="text"  class="form-control input-sm" id="f01" name="f01" value="${parambean1.f01}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        
         <tr height="40">
          <td width="20%" align="right">上级用户组编号：</td>
          <td width="5"></td>
          <td>
          <select id="k03" name="k03">
          	<option></option>
          	<c:forEach var="f" items="${pageVO.pageData}">
          	<option value="${f.puk}">    
          	 ${f.f01}		
          	</option>
          	</c:forEach>
          </select>          
          <select name="f02" id="f02">
<!--           	<option>用户组系统分类</option> -->
			<c:forEach var="fl" items="${list1}">
				<option value="${fl.puk}">${fl.f01}</option>
			</c:forEach>
          </select>
<!--              <input type="text"   class="form-control input-sm" id="k03" name="k03" value="${parambean1.k03}"  >          -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">备注说明：</td>
          <td width="5"></td>
          <td>
          	<textarea rows="" cols="" id="bbb" name="bbb" value="">${parambean1.bbb}</textarea>
<!--              <input type="text"  class="form-control input-sm" id="f01" name="f01" value="${parambean1.f01}"  >          -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
<!--          <tr height="40"> -->
<!--           <td width="20%" align="right">上级用户组名称：</td> -->
<!--           <td width="5"></td> -->
<!--           <td> -->
<!--              <input type="text"   class="form-control input-sm" id="k02" name="k02" value="${parambean1.k02}"  >          -->
<!--           </td> -->
<!--           <td width="5"></td> -->
<!--           <td width="30%"></td> -->
<!--         </tr> -->
<!--         <tr height="40"> -->
<!--           <td width="20%" align="right">用户组系统分类：</td> -->
<!--           <td width="5"></td> -->
<!--           <td> -->
<!--              <input type="text"  class="form-control input-sm" id="f02" name="f02" value="${parambean1.f02}"  >          -->
<!--           </td> -->
<!--           <td width="5"></td> -->
<!--           <td width="30%"></td> -->
<!--         </tr>                 -->
         <tr height="40">
<!--          更新时间 -->
          <td width="20%" align="right"></td>
          <td width="5"></td>
          <td>
             <input type="hidden" class="form-control input-sm" id="uu1" name="uu1" value="${parambean1.uu1}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
      </table>
       <input type="hidden" id="eb5" name="eb5" value="${eb5}"  >   
<!--          <input type="hidden" id="eb5" name="eb5" value="${parambean.puk}"  >    -->
      </form>
      </td>
    </tr>
  </table>
</body>
</html>
