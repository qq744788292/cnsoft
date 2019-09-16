<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>user-list</title><!-- 系统用户列表 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">

<table style="border-collapse:separate; border-spacing:0px 20px;">
    <form method="post" action="/90301010?token=${token}" id="searchForm">
    <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
    <input type="hidden" name="pageLimit" id="pageLimit" value="10">
        <tr class="trparam" >
            <td>
                <table>
                    <tr>
                        <td>
                            <input type="button" onclick="parent.showPageForm('90301020')" class="btn btn-default btnleft" value="新建">
                        </td>
                         <td class="tdparam">人员姓名:</td>
                        <td style="width: 150px">
                            <input type="text"  class="form-control" name="userName" placeholder="请输入帐号" value="${searchCondition.userName}">
                        </td>
                        <td class="tdparam">手机号码:</td>
                        <td style="width: 150px">
                            <input type="text"  class="form-control" name="userPhone" placeholder="请输入帐号" value="${searchCondition.userPhone}">
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
    <tr style="margin-top:50px;" >
        <td>
            <table class="table table-striped table-hover table-bordered">
            	<thead>
                <tr>
                    <th style="text-align:center;">姓名</th>
                    <th style="text-align:center;">帐号</th>
                    <th style="text-align:center;">手机号码</th>
                    <th style="text-align:center;">性别</th>
                    <th style="text-align:center;">账户状态</th>
                    <th style="text-align:center;">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${list.pageListData}">
                    <tr>
                        <td>${item.userName}</td>
                        <td>${item.userAccount}</td>
                        <td>${item.userPhone}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.userSex==1}">男</c:when>
                                <c:when test="${item.userSex==2}">女</c:when>
                                <c:otherwise>未知</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                        	<c:choose>
                        		<c:when test="${item.allowLogin==0 }">正常</c:when>
                        		<c:when test="${item.allowLogin==1 }">失效</c:when>
                        	</c:choose>
                        </td>
                        <td class="tdblank140" style="white-space: nowrap;">
                       		<c:if test="${item.puk !=1}">
                        	<input type="button" class="btn btn-info" onclick="parent.showPageForm('90301080?puk=${item.puk}')" value="启用/停用">
                        	</c:if>
                        	<input type="button" class="btn btn-info" onclick="parent.showPageForm('90301030?puk=${item.puk}')" value="关联角色">
                        	<input type="button" class="btn btn-info" onclick="parent.showPageForm('90301030?puk=${item.puk}')" value="关联用户组">
							<input type="button" class="btn btn-info" onclick="parent.showPageForm('90301030?puk=${item.puk}')" value="编辑">
                            <c:if test="${item.puk !=1}">
							<input type="button" class="btn btn-danger"  onclick="parent.showPageForm('90301040?puk=${item.puk}&updateTime=${item.updateTime}')" value="删除">
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${list}" url="90301010" /></td>
    </tr>
</table>
<script type="text/javascript">
    parent.changeNavbar('系统用户管理','首页》用户与权限》系统用户管理》系统用户列表');

    if(!${message.code==0}){
        parent.showMessage("${message.code}","${message.msg}");
    }

</script>

</body>
</html>
