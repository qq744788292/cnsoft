<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
  <head>
  <meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/validationEngine.jquery.css">
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/template.css"> 	  
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine-zh_CN.js"></script>
<script  src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script type="text/javascript">
	$(function (){
		$('#f15').focus();
		jQuery("#form01").validationEngine('attach',
			{promptPosition : "centerRight", autoPositionUpdate : true});		
	});
	/*function oldvalue(val){
// 		alert(val);
		var i=val;
		return i;
	}*/
	function checkf15(val){
		var val1=val;
		var val2=document.getElementById("f15").value;
		if(val1>val2){
// 			alert("not");
			document.getElementById("erroinfo").style.display="block";
			document.getElementById("f15").focus();
			document.getElementById("btn").disabled = true;
			return ;
// 			document.getElementById("btn").style.display="none";
// 			return false;
		}else{
// 			alert("y");
			document.getElementById("erroinfo").style.display="none";
// 			document.getElementById("btn").style.display="block";
			document.getElementById("btn").disabled = false;
// 			return true;
		}	
	}
		function checkf16(val){
		var val1=val;
		var val2=document.getElementById("f16").value;
		if(val1>val2){
			document.getElementById("erroinfo1").style.display="block";
			document.getElementById("f16").focus();
			
			return false;
		}else{
			document.getElementById("erroinfo1").style.display="none";
			return true;
		}	
	}
	function pre(){
	
	}

	function save(){

            document.form01.action = "/WMBM09/A.go";
			form01.submit();
	}

	function fanhui() {
		    document.form01.action = "/WMBM09/F.go";
	
			form01.submit();			
	}
	
    function tdChanged(value){
    	var strs=value.split("|");
    	document.getElementById("k01").value = strs[0];
    	document.getElementById("f15").value = strs[1];
    	document.getElementById("f16").value = strs[2]; 
    	document.getElementById("f17").value = strs[3];
    	document.getElementById("f19").value = strs[4]; 
    }
    function checkForm(){
	
	  
	  var pa = document.getElementById('k02').value;
	  var par = document.getElementById('k02a').value;
	  if(pa==''||par==''){
  		document.getElementById('k02_tips').innerHTML='??????????????????';
  		return false;
	  }
	 if(pa!=par){
	  	document.getElementById('k02a_tips').innerHTML='?????????????????????';	  
  		return false;

	 }
	
	  return true;
	  
} 
	</script>
  </head>
  <body>
  <form id="form01" name="form01" method="post" action="/WMBM09/A.go" onsubmit="return checkForm()">		
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">?????????????????????</h3></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">${message}</td>
		    <td align="right">
			<input type="button" class="btn btn-info" value="??????" onclick="fanhui()" />
			<input type="submit" class="btn btn-info" id="btn" value="????????" />
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">	
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;??????????????????</th>
					<td width="5"></td>
					<td>${parambean.fb1}</td>
					<td width="5"></td>
					<td width="50%"></td>
				</tr>
				<input  type="hidden"  name="k01" id="k01" value="${parambean.puk}"/>
				<input  type="hidden"  name="puk" id="puk" value="${puk}" />
				<input  type="hidden"  name="f19" id="f19" value="${parambean.f19}"/>

				
				 <tr height="40">
		          <td width="20%" align="right" style="color: #00F">?????????????????????</td>
		          <td width="5"></td>          
		          <td><input  type="text"  placeholder="" id="f15" name="f15" 
					 onchange="checkf15(${parambean.f15})" class="validate[required]"
					value="${parambean.f15}">&nbsp;&nbsp;%</td>
		          <td width="5"></td>
		          <td width="50%"><div style="display: none;" id="erroinfo">????????????????????????????????????</div></td>
		        </tr>
		                
		        
		        
		        <tr height="40">
		          <td width="20%" align="right" style="color:#F00;">???????????????(??????)???</td>
		          <td width="5"></td>
		          <td><input  type="text"  placeholder="" id="f16" name="f16" value="${parambean.f16}"
					onchange="checkf16(${parambean.f16})" class="validate[required,custom[number]]"></td>
		          <td width="5"></td>
		          <td width="40%">
		          <div style="float:left; width:240px;">??????????????????????????????????????? 9??????17??????</div><div id="erroinfo1" style="display: none; float:left; width:190px;">????????????????????????????????????</div>
		          
<!-- 		          <div>??????????????????????????????????????? 9??????17??????</div><div style="display: none;" id="erroinfo1">????????????????????????????????????</div></td> -->
		        </tr>
		        <tr height="40">
		          <td width="20%" align="right" style="color:#F00;">???????????????(?????????)???</td>
		          <td width="5"></td>          
		          <td><input  type="text" id="eb3" name="eb3" value="${parambean.eb3}"
					class="validate[required,custom[integer]]">
		          </td>
		          <td width="5"></td>
		          <td>??????????????????</td>
		        </tr>
		        <tr height="40">
		          <td width="20%" align="right" style="color:#F00;">???????????????(?????????)???</td>
		          <td width="5"></td>          
		          <td><input  type="text" id="eb4" name="eb4" value="${parambean.eb4}"
					class="validate[required,custom[integer]]"></td>
		          <td width="5"></td>
		          <td>???????????????</td>
		        </tr>
				
				
				
				<tr height="40">
				<!-- ???????????????????????????????????? -->
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;??????????????????</th>
					<td width="5"></td>
					<td><input  type="text" id="f17" readonly  name="f17" value="${parambean.f17}"
					class="validate[required,custom[integer]]"></td>
					<td width="5"></td>
					<td width="40%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;???????????????</th>
					<td width="5"></td>
					<td><input  type="text"  placeholder="" id="f01" name="f01" value="${parambean1.f01}"
					class="validate[required]"></td>					
					<td width="5"></td>
					<td width="40%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;??????????????</th>
					<td width="5"></td>
					<td>
					<input  type="text"  placeholder="" id="f02" name="f02" value="${parambean1.f02}"
					class="validate[required]">
<!-- 					<select name="f02"> -->
<!-- 					<c:forEach var="cssb01dbo" items="${list}"> -->
<!-- 	                <option value="${parambean1.f02}" selected>${cssb01dbo.f02}</option> -->
<!-- 	                </c:forEach> -->
<!--                     </select> -->
					</td>
					<td width="5"></td>
					<td width="40%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;??????????????</th>
					<td width="5"></td>
					<td>
					<input  type="text"  placeholder="" id="fb3" name="fb3" value="${parambean1.fb3}"class="validate[required]">
					</td>
					<td width="5"></td>
					<td width="40%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;???????????????</th>
					<td width="5"></td>
					<td>
					<input  type="text"  placeholder="" id="f03" name="f03" value="${parambean1.f03}"
					class="validate[required]">
					</td>
					<td width="5"></td>
					<td width="40%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;????????????</th>
					<td width="5"></td>
					<td>
					<input  type="text"  placeholder="" id="k02" name="k02" value="${parambean1.k02}"
					class="validate[required],custom[integer]]"><span id="k02_tips" style="background:red"></span>
					</td>
					<td width="5"></td>
					<td width="40%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;??????????????????</th>
					<td width="5"></td>
					<td>
					<input  type="text"  placeholder="" id="k02a" name="k02a" value="${parambean1.k02}"
					class="validate[required],custom[integer]]"><span id="k02a_tips" style="background:red"></span>
					</td>
					<td width="5"></td>
					<td width="40%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;???????????</th>
					<td width="5"></td>
					<td>
					<input  type="text"  placeholder="" id="f04" name="f04" value="${parambean1.f04}"
					class="validate[required]">
					</td>
					<td width="5"></td>
					<td width="40%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;?????????</th>
					<td width="5"></td>
					<td>
					<input  type="text"  placeholder="" id="f05" name="f05" value="${parambean1.f05}"
					class="validate[required]">
					</td>
					<td width="5"></td>
					<td width="40%"></td>
				</tr>

			</table>
		</td>
	</tr>
  </table>
  </form>
</body>
</html>
