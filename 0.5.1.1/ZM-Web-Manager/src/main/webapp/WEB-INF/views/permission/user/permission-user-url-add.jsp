<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/fileuploadtag.tld" prefix="u"%>
<html>
<head>
    <title>user-add</title><!-- 添加用户 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">

<form method="post" action="/90301050?token=${token}" id="addForm">
    <input type="hidden" name="pageCurrent"  value="1">
    <input type="hidden" name="pageLimit" value="10">
    <input type="hidden" name="model" value="${model}">
    <input type="hidden" name="token" value="${token}">
    <table style="border-collapse:separate; border-spacing:0px 30px;">
        <tr class="trparam" >
            <td>
                <table>
                    <tr>
                        <td><input type="button" class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()"></td>
                        <td align="right"><input type="submit" class="btn btn-primary btnright" value="保存" ></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr class="pageht">
            <td>&nbsp;</td>
        </tr>
        <tr >
            <td align="center" >
           <!--  <div  style="background-color: green;float: left; text-align:center;">
            	<div style="float: left;"><font>姓名:</font> </div>
            	<div style="float: right;"><input type="text" class="form-control" name="userName" style="width:200px"/></div>
            </div> -->
                  <table  >
                    <tr>
                        <td class="tdparam">姓名:</td>
                        <td><input type="text" class="form-control" name="userName" style="width:200px" placeholder="请输入姓名"/></td>
                        <td class="tdblank120">&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">登陆账户:</td>
                        <td><input type="text" class="form-control" name="userAccount" style="width:200px" placeholder="请输入账户"/></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">密码:</td>
                        <td>
                        <input id="pwd" type="password" class="form-control" name="userPassword" style="width:200px" placeholder="请输入密码"/>
                        <input id="pwdag" type="password" class="form-control" name="userPassword" style="width:200px" placeholder="请再次输入密码"/>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">手机号码:</td>
                        <td >
                        <input type="text" class="form-control" name="userPhone" style="width:200px" placeholder="请输入手机号"/>
                        <!--     <select name="userSex" class="form-control" style="width: 200px" >
                                <option value="">请选择</option>
                                <option value="1" >男</option>
                                <option value="2">女</option>
                                <option value="3">保密</option>
                            </select> -->
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                <!--     <tr>
                        <td class="tdparam">角色名称:</td>
                        <td>
                            <select class="form-control" id="roleId" name="roleId" style="width: 200px">
                            </select>
                        </td>
                        <td>&nbsp;</td>
                    </tr> -->
                    <tr>
                        <td class="tdparam">用户性别:</td>
                        <td>
							<input type="radio" name="userSex" value="1">男&nbsp;
							&nbsp;<input type="radio" name="userSex" value="2">女
						</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">电子邮箱:</td>
                        <td><input type="text" class="form-control" name="userMail" style="width:200px" placeholder="请输入电子邮箱"/></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">用户头像:</td>
                        
                        <td width="30%"	>
                        <input type="hidden" name="picUrl"  id="picUrl"/>
                        <u:fileupload height="200" width="200" id="upload1" type="1" token="${token}" target="picUrl" value="${data.picUrl}"/>
                        </td>
                    </tr>
                </table>  
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    parent.changeNavbar('添加系统用户','首页》用户与权限》系统用户管理》添加系统用户');
	$(function(){
		$("#pwdag").focusout(function(){
			var pwd1=$("#pwd").val();
			var pwd2=$("#pwdag").val();
			if(pwd1!=pwd2){
			alert("两次输入的密码不一致");
			return;
			}
		})
	})


/*     //初始化角色的方法
    $(function(){

		$("#roleId").empty();//清空下拉框
		$.ajax({
			type:"post",
			url:"/90102011",
			data:{
			    token:"${token}"
            },
			async: false,
			dataType:"json",
			success:function(result){
			  if(result.code == 0){
				  $("#roleId").append("<option value=''>请选择</option>");
				  for (var i = 0; i < result.data.length; i++) {
 				  	$("#roleId").append("<option value="+ result.data[i].puk + ">" + result.data[i].roleName + "</option>");
 			  	  }
			  }else{
				  console.log("错误");
			  }
			},
			error:function(error){
			    console.log(error)
			}
		});
	}); */

</script>
</body>
</html>
