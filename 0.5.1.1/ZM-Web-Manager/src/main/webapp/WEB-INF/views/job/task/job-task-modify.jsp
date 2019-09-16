<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>job-task-modify</title><!-- 编辑任务 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body onload="body_onload()">

<form method="post" action="/80401050?token=${token}" id="modifyForm">
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
				            <td class="tdparam">任务编号:</td>
				            <td><input type="text" class="form-control" name="jobNo" value="${data.jobNo}"/></td>
				            <td class="tdblank120"><font style="font-size:26px;color:red;">*</font></td>
				        </tr>
				        <tr>
				            <td class="tdparam">任务名称:</td>
				            <td><input type="text" class="form-control" name="jobName" value="${data.jobName}"/></td>
				            <td class="tdblank120"><font style="font-size:26px;color:red;">*</font></td>
				        </tr>
				        <tr>
				            <td class="tdparam">任务分组:</td>
				            <td><input type="text" class="form-control" name="jobGroup" value="${data.jobGroup}"/></td>
				            <td><font style="font-size:26px;color:red;">*</font></td>
				        </tr>
				        <tr>
				            <td class="tdparam">任务模式:</td>
				            <td>
					            <table style="height: 100%">
					            	<tr>
					            		<td style="width: 26px"><input type="radio" class="form-control" name="jobTypeSelect" value="1000" onclick="jobTypeChanged(this.value)"></td>
					            		<td style="width: 120px">&nbsp;本地模式</td>
					            		<td style="width: 26px"><input type="radio" class="form-control" name="jobTypeSelect" value="2000" onclick="jobTypeChanged(this.value)"></td>
					            		<td style="width: 120px">&nbsp;远程模式</td>
					            		<td></td>
					            	</tr>
					            </table>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdparam">cron表达式:</td>
				            <td><input type="text" class="form-control" name="cronExpression" value="${data.cronExpression}"/></td>
				            <td><font style="font-size:26px;color:red;">*</font></td>
				        </tr>
				        <tr>
				            <td class="tdparam">任务描述:</td>
				            <td><textarea name="description" class="form-control"  cols="30" rows="3">${data.description}</textarea></td>
				        </tr>
				        <tr>
				            <td class="tdparam">并发支持:</td>
				            <td>
					            <table>
					            	<tr>
					            		<td style="width: 26px"><input type="radio" class="form-control" name="isConcurrent" checked value="1"></td>
					            		<td style="width: 120px">&nbsp;不能并发</td>
					            		<td style="width: 26px"><input type="radio" class="form-control" name="isConcurrent" value="2"></td>
					            		<td style="width: 120px">&nbsp;允许并发</td>
					            		<td></td>
					            	</tr>
					            </table>
				            </td>
				        </tr>
				        
				        <!-- 本地模式 -->
				        <tr id="jobType_1000" style="display: none;">
				            <td colspan="2">
					            <table>
						            <tr>
							            <td class="tdparam">类ID:</td>
							            <td><input type="text" class="form-control" name="springBeanId" value="${data.springBeanId}"/></td>
							        </tr>
							        <tr>
							            <td class="tdparam">方法名:</td>
							            <td><input  type="text" class="form-control" name="beanMethodName" value="${data.beanMethodName}"/></td>
							        </tr>
					            </table>
							</td>
				        </tr>
				        <!-- 远程模式 -->
				        <tr id="jobType_2000" style="display: none;">
				            <td colspan="2">
					            <table>
						            <tr>
							            <td class="tdparam">接口请求模式:</td>
							            <td>
								            <table>
								            	<tr>
								            		<td style="width: 26px"><input type="radio" class="form-control" name="apiMode" checked value="GET"></td>
								            		<td style="width: 120px">&nbsp;GET</td>
								            		<td style="width: 26px"><input type="radio" class="form-control" name="apiMode" value="POST"></td>
								            		<td style="width: 120px">&nbsp;POST</td>
								            		<td></td>
								            	</tr>
								            </table>
										</td>
							        </tr>
						            <tr>
							            <td class="tdparam">接口名称:</td>
							            <td><input type="text" class="form-control" name="apiName" value="${data.apiName}"/></td>
							        </tr>
						            <tr>
							            <td class="tdparam">接口地址URL:</td>
							            <td><input type="text" class="form-control" name="apiUrl" value="${data.apiUrl}"/></td>
							        </tr>
							        <tr>
							            <td class="tdparam">接口参数:</td>
							            <td><input  type="text" class="form-control" name="apiParam" value="${data.apiParam}"/></td>
							        </tr>
					            </table>
							</td>
				        </tr>
				        
				      </table>
				    </td>
				  </tr>
    </table>
    
    <input type="hidden" name="jobStatus" value="${data.jobStatus}">
    <input type="hidden" name="jobType" value="${data.jobType}">
</form>

<script type="text/javascript">
    parent.changeNavbar('编辑任务','首页>资源管理>抓取管理>编辑任务');
    //初始化单选框
	function body_onload(){
    	//并发同步
		var val = "${data.isConcurrent}";
		if(val == "1"){
			$("input[name='isConcurrent']").get(0).checked = true;
		}else{
			$("input[name='isConcurrent']").get(1).checked = true;
		}
		
		//任务模式
		val = "${data.jobType}";
		if(val == "1000"){
			$("input[name='jobTypeSelect']").get(0).checked = true;
		}else{
			$("input[name='jobTypeSelect']").get(1).checked = true;
		}
		jobTypeChanged(val);
	}
    //模式切换
    function jobTypeChanged(type){
		$("input[name='jobType']").val(type);
    	if(type=='1000'){
    		$("#jobType_1000").css('display','');
    		$("#jobType_2000").css('display','none');
    	}else{
    		$("#jobType_1000").css('display','none');
    		$("#jobType_2000").css('display','');
    	}
    }
</script>
</body>
</html>
