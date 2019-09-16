<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/fileuploadtag.tld" prefix="u"%>
<html>
<head>
    <title>wx-material-url-modify</title><!-- 编辑素材地址 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body onload="body_onload()">

<form method="post" action="/70212050?token=${token}" id="modifyForm">
    <input type="hidden" name="mode" value="${mode}">
    <input type="hidden" name="puk" value="${data.puk}">
	<input type="hidden" name="lookPicUrl" id="lookPicUrl" value="${dbo.lookPicUrl}">
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
				            <td class="tdparam">标题:</td>
				            <td><input type="text" class="form-control" name="title" value="${data.title}"/></td>
				            <td class="tdblank120"><font style="font-size:26px;color:red;">*</font></td>
				        </tr>
				        <tr>
				            <td class="tdparam">发布时间:</td>
				            <td><input type="text" class="form-control" name="onlineTime" value="${data.onlineTime}"/></td>
				            <td class="tdblank120"><font style="font-size:26px;color:red;">*</font></td>
				        </tr>
				        <tr>
				            <td class="tdparam">作者:</td>
				            <td><input type="text" class="form-control" name="author" value="${data.author}"/></td>
				            <td><font style="font-size:26px;color:red;">*</font></td>
				        </tr>
				        <tr>
				            <td class="tdparam">缩略图URL:</td>
				            <td>
				            <u:fileupload height="268" width="202" id="upload1" type="1" token="${token}" target="lookPicUrl" value="${dbo.lookPicUrl}"/>
							</td>
				            <td><font style="font-size:26px;color:red;">*</font></td>
				        </tr>
				        <tr>
				            <td class="tdparam">地址内容:</td>
				            <td><input type="text" class="form-control" name="materialUrl" value="${data.materialUrl}"/></td>
				        </tr>
				        <tr>
				            <td class="tdparam">归属菜单ID:</td>
				            <td><input type="text" class="form-control" name="menuId" value="${data.menuId}"/></td>
				        </tr>
				        
				      </table>
				    </td>
				  </tr>
    </table>
    
</form>

<script type="text/javascript">
    parent.changeNavbar('编辑任务','首页>资源管理>抓取管理>编辑任务');
    //初始化单选框
	function body_onload(){
	}
</script>
</body>
</html>
