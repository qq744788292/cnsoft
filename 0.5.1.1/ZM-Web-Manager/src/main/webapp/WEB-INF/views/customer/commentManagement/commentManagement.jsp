<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<html>
  <head>
    <meta charset="utf-8">
    <title>commentManagement-list.jsp</title>
    <!-- 静态引入 -->
	<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
  </head>
  <body class="body">
	<table >
		<form method="post" action="/201030?token=${token}" id="searchForm">
			<input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
			<input type="hidden" name="pageLimit" id="pageLimit" value="10">
			<tr class="trparam">
				<td>
					<table>
						<tr>
							<td></td>
							<td class="tdparam">用户ID:</td>
							<td style="width: 150px">
								<input type="text" class="form-control" name="puk" placeholder="请输入用户ID"
									   value="${dbo.puk}">
							</td>
							<td class="tdbtn"><input class="btn btn-primary btnright" type="submit" value="搜索"></td>
						</tr>
					</table>
				</td>
			</tr>
		</form>
		<tr>
			<td>
				<table class="table table-striped table-hover table-bordered">
					<thead>
					<tr>
						<th>用户ID</th>
						<th>昵称</th>
						<th>内容</th>
						<th>评论时间</th>
						<th>审核状态</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${page.pageListData}">
					<tr>
						 <td>${item.userId}</td>
						 <td>${item.criticNickName}</td>
						 <td>
						 	<a href="#" class="tooltip-test" data-toggle="tooltip" data-placement="top" title="${item.content}">浏览</a>
							 <%-- <input type="button" class="btn btn-success" onclick="parent.showPageForm('60401020?puk=${item.puk}&token=${token}')" value="查看"> --%>
						 </td>
						 <td>${item.createTime}</td>
						 <td>
							<select id="yesOrNo" name="yesOrNo"  onchange="updateState(this.value,'${item.puk}','${item.updateTime}');">
								<option value="1" <c:if test="${item.yesOrNo == '1'}">selected="selected"</c:if>>待审核</option>
								<option value="2" <c:if test="${item.yesOrNo == '2'}">selected="selected"</c:if>>通过</option>
								<option value="2" <c:if test="${item.yesOrNo == '3'}">selected="selected"</c:if>>不通过</option>
							</select>
						 </td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
			</td>
		</tr>						
		<tr>
			<td>
				<p:page page="${page}" url="201030" />
			</td>
		</tr>
	</table>
	<script type="text/javascript">parent.changeNavbar('评论审核','首页》客服管理》评论审核');</script>
  </body>
  <script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
  </script>
   <script type="text/javascript">
		function showPageFormView(urlpath){
			document.myFormView.action=urlpath;
			myFormView.submit();
		}
		
		//修改状态
		function updateState(value,puk,updateTime){
			$.ajax({
				type: "POST",
				url:"60401030?token=${token}",
				data:"puk="+puk+"&yesOrNo="+value+"&updateTime="+updateTime,
				success:function(data){
					parent.showPageForm('60401010?token=${token}&pageCurrent=1&pageLimit=10')
				}
			});
		}
	</script>
</html>
