<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>wx-menu-modify</title><!-- 编辑微信菜单 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body onload="body_onload()">

<form method="post" action="/70211050?token=${token}" id="modifyForm">
    <input type="hidden" name="mode" value="${mode}">
    <input type="hidden" name="puk" value="${data.puk}">
    <table>
		<tr class="trparam" >
		            <td>
		                <table >
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
		     	<tr>
			     	<td>
			     	<table class="table-striped table-hover">
				        <tr>
				            <td class="tdparam">菜单ID:</td>
				            <td><input type="text" class="form-control" name="puk" value="${data.puk}"/></td>
				            <td class="tdblank120"><font style="font-size:26px;color:red;">*</font></td>
				        </tr>
				        <tr>
				            <td class="tdparam">上级菜单ID:</td>
				            <td><input type="text" class="form-control" name="fatherMenuId" value="${data.fatherMenuId}"/></td>
				            <td class="tdblank120"><font style="font-size:26px;color:red;">*</font></td>
				        </tr>
				        <tr>
				            <td class="tdparam">菜单级别:</td>
				            <td>
					            <table style="height: 100%">
					            	<tr>
					            		<td style="width: 26px"><input type="radio" class="form-control" name="menuLevelSelect" value="1" onclick="menuLevelChanged(this.value)"></td>
					            		<td style="width: 120px">&nbsp;一级菜单</td>
					            		<td style="width: 26px"><input type="radio" class="form-control" name="menuLevelSelect" value="2" onclick="menuLevelChanged(this.value)"></td>
					            		<td style="width: 120px">&nbsp;二级菜单</td>
					            		<td style="width: 26px"><input type="radio" class="form-control" name="menuLevelSelect" value="3" onclick="menuLevelChanged(this.value)"></td>
					            		<td style="width: 120px">&nbsp;三级菜单</td>
					            		<td></td>
					            	</tr>
					            </table>
				            </td>
				            <td class="tdblank120"><font style="font-size:26px;color:red;">*</font></td>
				        </tr>
				        <tr>
				            <td class="tdparam">菜单名称:</td>
				            <td><input type="text" class="form-control" name="menuName" value="${data.menuName}"/></td>
				            <td class="tdblank120"><font style="font-size:26px;color:red;">*</font></td>
				        </tr>
				        <tr>
				            <td class="tdparam">菜单类别:</td>
				            <td>
					            <table style="height: 100%">
					            	<tr>
					            		<td style="width: 26px"><input type="radio" class="form-control" name="menuTypeSelect" value="1000" onclick="menuTypeChanged(this.value)"></td>
					            		<td style="width: 120px">&nbsp;本地栏目</td>
					            		<td style="width: 26px"><input type="radio" class="form-control" name="menuTypeSelect" value="2000" onclick="menuTypeChanged(this.value)"></td>
					            		<td style="width: 120px">&nbsp;远程页面</td>
					            		<td style="width: 26px"><input type="radio" class="form-control" name="menuTypeSelect" value="3000" onclick="menuTypeChanged(this.value)"></td>
					            		<td style="width: 120px">&nbsp;内部页面</td>
					            		<td></td>
					            	</tr>
					            </table>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdparam">菜单内容:</td>
				            <td><input type="text" class="form-control" name="menuUrl" value="${data.menuUrl}"/></td>
				            <td><font style="font-size:26px;color:red;">*</font></td>
				        </tr>
				        <tr>
				            <td class="tdparam">排列顺序:</td>
				            <td><input type="text" class="form-control tdparam" name="sortNum" value="${data.sortNum}"/></td>
				        </tr>				        
				      </table>
				    </td>
				  </tr>
    </table>
    
    <input type="hidden" name="menuLevel" value="${data.menuLevel}">
    <input type="hidden" name="menuType" value="${data.menuType}">
</form>

<script type="text/javascript">
    parent.changeNavbar('编辑任务','首页>资源管理>抓取管理>编辑任务');
    //初始化单选框
	function body_onload(){
    	//菜单级别
		var val = "${data.menuLevel}";
		if(val == "1"){
			$("input[name='menuLevelSelect']").get(0).checked = true;
		}else if(val == "2"){
			$("input[name='menuLevelSelect']").get(1).checked = true;
		}else{
			$("input[name='menuLevelSelect']").get(2).checked = true;
		}
		
		//菜单类别
		var val = "${data.menuType}";
		if(val == "1000"){
			$("input[name='menuTypeSelect']").get(0).checked = true;
		}else if(val == "2000"){
			$("input[name='menuTypeSelect']").get(1).checked = true;
		}else{
			$("input[name='menuTypeSelect']").get(2).checked = true;
		}
	}
</script>
</body>
</html>
