<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>activity-manage-list.jsp</title><!-- 活动列表 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
		<style type="text/css">
			.contair-model{
				position: absolute;
				width: 100%;
				height: 100%;
				background: rgba(0,0,0,0.5);
				z-index: 1;
			}
			.model-warn{
				height: 300px;
				width: 450px;
				background: white;
				border-radius: 10px;
				position: absolute;
				top: 50%;
				left: 50%;
				margin-left: -225px;
				margin-top: -150px;
			}
			.model-title{
				height: 40px;
				line-height: 40px;
				padding-left: 10px;
				background: #F5F5F5;
				border-top-right-radius: 10px;
				border-top-left-radius: 10px;
			}
			.model-content{
				text-align: center;
				line-height: 200px;
				font-size: 16px;
			}
			.model-left{
				float: left;
				background: #337ab7;
				height: 60px;
				width: 225px;
				border-bottom-left-radius: 10px;
				text-align: center;
				line-height: 60px;
				font-size: 16px;
				color: white;
				cursor: pointer;
			}
			.model-left:hover{
				background: #2e6da4;
			}
			.model-right{
				float: right;
				background: #ec971f;
				height: 60px;
				width: 225px;
				border-bottom-right-radius: 10px;
				text-align: center;
				line-height: 60px;
				font-size: 16px;
				color: white;
				cursor: pointer;
			}
			.model-right:hover{
				background: #d58512;
			}
			.dn{
				display: none;
			}
		</style>
</head>
<body class="body">
<div class="contair-model dn">
	<div class="model-warn">
		<div class="model-title">
			<span>发布活动</span>
		</div>
		<div class="model-content">您确发布该活动吗？</div>
		<div class="btn-bottom">
			<div class="model-left">
				确定
			</div>
			<div class="model-right">
				取消
			</div>
		</div>
	</div>
</div>
<table >
    <form method="post" action="/202030?token=${token}" id="searchForm">
    <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
    <input type="hidden" name="pageLimit" id="pageLimit" value="10">
        <tr class="trparam" >
            <td>
                <table>
                    <tr>
                        <td><input type="button" class="btn btn-default btnleft" value="新增" id="addActivityBtn" onclick="parent.showPageForm('/20203060?JumpType=1')"/></td>
                        <td></td>
                        <td class="tdparam1">活动编号：</td>
                        <td class="tdblank200"><input type="text"  class="form-control" name="activityNumber" placeholder="请输入活动编号" value="${searchCondition.activityNumber}"></td>
                        <td class="tdparam1">活动名称:</td>
                        <td class="tdblank200">
                            <input type="text"  class="form-control" name="activityName" placeholder="请输入活动名称" value="${searchCondition.activityName}">
                        </td>
                        <td class="tdparam1">模式：</td>
                        <td class="tdblank120">
                        	<select name="activityTypeType" class="form-control" id="activityTypeTypeSelect">
                                <option value="">全部</option>
                                <option value="1000">日常签到</option>
								<option value="2000">团体活动</option>
								<option value="3000">优惠卷码</option>
                            </select>
                        </td>
                        <td class="tdparam1">类型：</td>
                        <td class="">
                        	<select name="activityTypeId" class="form-control" id="activityTypeIdSelect">
                                <option value="">全部</option>
                                <c:forEach var="item" items="${activityTypeName}">
                                    <option value="${item.puk}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="tdparam1">状态：</td>
                        <td class="tdblank100">
                        	<select name="activityState" class="form-control" id="activityStateSelect">
                            	<option value="">全部</option>
                            	<option value="1">未开始</option>
                            	<option value="2">正进行</option>
                            	<option value="3">已过期</option>
                            </select>
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
                    <th>活动名称</th>
                    <th>活动时间</th>
                    <th>模式</th>
                    <th>分类</th>
                    <th>发布状态</th>
                    <th>发布时间</th>
                    <th>推广码</th>
                    <th>统计</th>
                	<th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${list.pageListData}">
                        <tr>
                            <td>${item.activityNumber}</td>
                            <td>${item.activityName}</td>
                            <td>${item.activityStartTime}-${item.activityEndTime}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${item.activityTypeType == '1000'}">日常签到</c:when>
                                    <c:when test="${item.activityTypeType == '2000'}">团体活动</c:when>
                                    <c:when test="${item.activityTypeType == '3000'}">优惠卷码</c:when>
                                    <c:otherwise>未知</c:otherwise>
                                </c:choose>
                            </td>
                            <td>${item.activityTypeName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${item.isSened == '1'}">
                                        <input type="button" class="btn btn-info" value="上线" onclick="parent.showPageForm('/20203010?puk=${item.puk}&isSened=1')">
                                    </c:when>
                                    <c:otherwise>
                                        <input type="button" class="btn btn-info" value="下线" onclick="parent.showPageForm('/20203010?puk=${item.puk}&isSened=2')">
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${item.sendTime}</td>
                        	<td><input type="button" class="btn" onclick="showmodal_code('${item.puk}')" value="查看"></td>
                            <td>
                                <c:if test="${item.activityTypeType == '2000'}">
                                	<input type="button" class="btn btn-info" onclick="parent.showPageForm('/20203040?puk=${item.puk}')" value="查看数据">
                                </c:if>
                            </td>
                            <td>
                                <input type="button" class="btn btn-info" onclick="parent.showPageForm('/20203020?puk=${item.puk}')" value="查看">
                                <input type="button" class="btn btn-info" onclick="parent.showPageForm('/20203060?JumpType=2&puk=${item.puk}')" value="编辑">
                                <input type="button" class="btn btn-danger"  onclick="parent.showPageForm('/20203050?activityTypeType=${item.activityTypeType}&puk=${item.puk}&updateTime=${item.updateTime}')" value="删除">
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${list}" url="202030" /></td>
    </tr>
</table>

<!-- Modal -->
<div class="modal fade" id="spreadCodeModal" tabindex="-1" role="dialog" aria-labelledby="spreadCodeModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="spreadCodeModalLabel">二维码</h4>
            </div>
            <div class="modal-body">
                <div class="pay-qrcode" id="spread_code1" >
                    <div >
                        <span style="font-size:20px;font-width:bolder;padding-left: 50px">该推广员的固定地址如下:</span>
                    </div>
                    <div >
                        <span style="padding-left: 60px" id="popularizeAddress"></span>
                    </div>
                    <div>
                        <img style="padding-left: 150px" class="pay_img" src="/99999031/220/220?content='http://u.2jiayou.com/popularize/1/1'" id="wxQrcodePay"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-primary">确定</button>--%>
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    //parent.changeNavbar('活动列表','首页》运营与统计》活动管理》活动列表');
    //推广码
    function showmodal_code(puk){
        var url='http://u.2jiayou.com/active/SYSTEM/'+puk;
        var url1='/99999031/220/220?content='+url+'';
        $('#popularizeAddress').html(url);
        $('#wxQrcodePay').attr("src",url1);
        $('#spreadCodeModal').modal('show');
    }
    
    if(!${message.code==0}){
        parent.showMessage("${message.code}","${message.msg}");
    }
    
    //发布活动
    function sendActivity(puk){
    	$(".contair-model").removeClass("dn");
    }
    	
	//取消发布
	$(".model-right").on("click",function(){
		$(".contair-model").addClass("dn");
	});
	
	//确定发布
	$(".model-left").on("click",function(){
		$(".contair-model").addClass("dn");
	});

   $(function(){
       //回显搜索条件
      $("#activityNumberSelect") .val("${searchCondition.activityNumber}");
      $("#activityTypeTypeSelect") .val("${searchCondition.activityTypeType}");
      $("#activityTypeIdSelect") .val("${searchCondition.activityTypeId}");
      $("#activityStateSelect") .val("${searchCondition.activityState}");
   });
	
   //活动
   <%--console.log("活动详情");--%>
   <%--console.log(${page.pageListData});--%>
</script>

</body>
</html>
