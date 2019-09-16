<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>check-data.jsp</title>
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
		<link href="/resources/common/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
		<script src="/resources/common/js/moment-with-locales.min.js" type="text/javascript"></script>
		<script  src="/resources/common/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
		<style>
			.height-40{
				height: 40px;
			}
			.span-left{
				float: left;
				margin-left: 20px;
				text-align: center;
				line-height: 35px;
			}
			.width-60{
				width: 160px;
				float: left;
				margin-left: 20px;
			}
		</style>
</head>
<body class="body">

<table >
    <form method="post" action="/90101010?token=${token}" id="searchForm">
    <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
    <input type="hidden" name="pageLimit" id="pageLimit" value="10">
        <tr class="trparam" >
            <td>
                <table>
                    <tr>
                        <td>
                            <select class="form-control">
                            	<option value="">省</option>
                            </select>
                        </td>
                        <td>
                        	<select class="form-control">
                            	<option value="">市</option>
                            </select>
                        </td>
                        <td>
                        	<select class="form-control">
                            	<option value="">区</option>
                            </select>
                        </td>
                        <td>
                        	<select class="form-control">
                            	<option value="">活动分类</option>
                            </select>
                        </td>
                        <td>
                        	<select class="form-control">
                            	<option value="">状态</option>
                            </select>
                        </td>
                        <td>
                        	<span class="span-left">报名时间</span>
                        	<div style="position: relative;">
	                        	<input type="text" class="form-control width-60" id="startTime"/>
	                        	<input type="text" class="form-control width-60" id="endTime"/>
                        	</div>
                        </td>
                        <td style="width: 150px">
                            <input type="text"  class="form-control" name="userName" placeholder="输入手机号或姓名" value="">
                        </td>
                        <td class="tdbtn">
                            <input type="submit" class="btn btn-primary btnright" value="搜索">
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
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>省</th>
                    <th>市</th>
                    <th>区</th>
                    <th>详细地址</th>
                    <th>报名时间</th>
                    <th>备注</th>
                </tr>
                </thead>
                <tbody>
                	<c:forEach var="item" items="${page.pageListData}">
                		<tr>
		                    <td>${item.userId}</td>
		                    <td>${item.userName}</td>
		                    <td>${item.userPhone}</td>
		                    <td>${item.userProvince}</td>
		                    <td>${item.userCity}</td>
		                    <td>${item.userArea}</td>
		                    <td>${item.userAdress}</td>
		                    <td>${item.enrollTime}</td>
		                    <td>${item.meno}</td>
		                </tr>
                	</c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${page}" url="80201026?puk=${data.puk}" /></td>
    </tr>
</table>
<script type="text/javascript">
    parent.changeNavbar('活动数据一览','首页》运营》活动管理》活动数据一览');

    /* if(!${resultMessage.code==0}){
        parent.showMessage("${resultMessage.code}","${resultMessage.message}");
    } */
	
    $('#startTime').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: moment.locale('zh-cn')
    });
    $('#endTime').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: moment.locale('zh-cn')
    });
    
   /*  var token = '${token}';
    console.log(token);
    
    $("#addActivityBtn").on("click",function(){
    	$.ajax({
    		type: "POST",
    		url: "/80201011",
    		data: {
    			token: token
    		},
    		success: function(data){
    			
    		},
    		error: function(error){}
    	});
    }); */
</script>

</body>
</html>
