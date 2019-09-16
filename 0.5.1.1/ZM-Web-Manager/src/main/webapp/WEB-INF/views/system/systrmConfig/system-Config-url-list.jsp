<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>system-Config-url-list</title><!-- 系统配置列表 -->
	<!-- 静态引入 -->
	<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">

<table >
    <form method="post" action="/90101020?token=${token}" id="searchForm">
        <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
        <input type="hidden" name="pageLimit" id="pageLimit" value="10">
        <tr class="trparam" >
        <td >
            <table  >
                <tr>
                    <!-- <td >
                        <input type="button" style="width: 150px" onclick="parent.showPageForm('90101020')" class="btn btn-default btnleft" value="添加系统配置">
                    </td> -->
                    <td class="tdparam" align="left">参数分类:</td>
                    <td style="width: 30%">
                        <input type="text"  class="form-control" name="configType"  value="${searchCondition.configType}">
                    </td>
                    <td>&nbsp;</td>
                    <td class="tdbtn"  align="right">
                        <input type="submit" class="btn btn-primary btnright"  value="保存">
                    </td>
                </tr>
                <tr>
                	<td class="tdparam" align="left">参数Key:</td>
                    <td >
                        <input type="text"  class="form-control" name="configKey"  value="${searchCondition.configKey}">
                    </td>
                    <td class="tdparam" >参数值:</td>
                    <td>
                        <input style="width:70%" type="text"  class="form-control" name="configValue"  value="${searchCondition.configValue}">
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    </form>
	<form method="post" action="/90103010?token=${token}" id="searchForm">
	 	<input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
	    <input type="hidden" name="pageLimit" id="pageLimit" value="10">
	    <tr>
	    	<td>
	    		<table class="table table-striped table-hover table-bordered">
	    			<tr>
	    				<td class="tdparam" align="left" style="text-align: right">参数分类:</td>
			            <td >
			                <select name="configType" id="selectType">
			                	<option>参数分类</option>
			                </select>
			            </td>
			            <td class="tdparam" style="text-align: right">参数Key:</td>
			            <td>
			                <input style="width:70%" type="text"  class="form-control" name="configKey"  value="${searchCondition.configKey}">
			            </td>
			            <td class="tdbtn" >
                        <input style="width:60%" type="submit" class="btn btn-primary btnright"  value="检索">
                    	</td>
	    			</tr>
	    		</table>
	    	</td>
	    </tr> 	
		
	</form>

    <%-- 表格数据显示区--%>
    <tr>
        <td>
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr >
                    <th >系统配置分类</th>
                    <th >系统配置名称</th>
                    <th >配置内容</th>
                    <th >备注</th>
                    <th >操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="list" items="${data.pageListData}">
                    <tr  >
                        <td >${list.configType}</td>
                        <td >${list.configKey}</td>
                        <td >${list.configValue}</td>
                        <td >${list.meno}</td>
                        <td >
                            <div class="">
                                <input type="button" class="btn btn-info" onclick="parent.showPageForm('/90101030?configType=${list.configType}&configKey=${list.configKey}')" value="编辑">
                                <input type="button" class="btn btn-danger"  onclick="parent.showPageForm('/90101040?configType=${list.configType}&configKey=${list.configKey}&updateTime=${list.updateTime}')" value="删除">
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${data}" url="90101010" /></td>
    </tr>
</table>
<script type="text/javascript">
    parent.changeNavbar('系统配置管理','首页》系统管理》系统配置管理》系统配置列表');

    if(!${message.code==0}){
        parent.showMessage("${message.code}","${message.msg}");
    }

    $(function(){

		$("#selectType").empty();//清空下拉框
		$.ajax({
			type:"post",
			url:"/90103011",
			data:{
			    token:"${token}"
            },
			async: false,
			dataType:"json",
			contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			success:function(data){
			  $.each(data,function(index,item){
				  $("#selectType").append("<option value="+ item.configType + ">" + item.configType + "</option>");
			  })
			},
			error:function(error){
			    console.log(error)
			}
		});
	});
</script>

</body>
</html>
