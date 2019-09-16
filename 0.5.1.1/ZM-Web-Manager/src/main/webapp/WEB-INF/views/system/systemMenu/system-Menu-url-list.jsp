<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>


<html>
<head>
    <title>system-Menu-url-list</title><!-- 系统菜单列表 -->
	<!-- 静态引入 -->
	<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
    <link href="/resources/common/css/menuicon.css" rel="stylesheet" media="screen">
</head>
<body class="body">
<table >
    <form method="post" action="/90102010?token=${token}" id="searchForm">
        <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
        <input type="hidden" name="pageLimit" id="pageLimit" value="10">
        <tr class="trparam">
        <td>
            <table  >
                <tr>
                    <td >
                        <input type="button" style="width: 150px" onclick="parent.showPageForm('/90102020')" class="btn btn-default btnleft" value="添加系统菜单">
                    </td>
                    <td class="tdparam">父菜单ID:</td>
                    <td style="width: 150px" >
                        <input type="text"  class="form-control" name="fatherMenuId" placeholder="请输入父菜单ID" value="${searchCondition.fatherMenuId}">
                    </td>
                    <td class="tdparam">系统菜单名称:</td>
                    <td style="width: 150px" >
                        <input type="text"  class="form-control" name="menuName" placeholder="请输入系统菜单名称" value="${searchCondition.menuName}">
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
                <tr >
                    <th >系统菜单ID</th>
                    <th >父菜单流水ID</th>
                    <th >菜单名称</th>
                    <th >菜单版本号</th>
                    <th >排列顺序</th>
                    <th >菜单图标</th>
                    <th >菜单URL</th>
                    <th >菜单等级</th>
                    <th >备注</th>
                    <th >操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${list.pageListData}">
                    <tr >
                        <td >${item.puk}</td>
                        <td >${item.fatherMenuId}</td>
                        <td >${item.menuName}</td>
                        <td >${item.menuVer}</td>
                        <td >${item.sortNum}</td>
                        <td ><i class="icon ZM-Main-icon-font">${item.menuPicUrl}</i></td>
                        <td >${item.menuUrl}</td>
                        <td >${item.menuLevel}</td>
                        <td >${item.meno}</td>
                        <td >
                            <div class="">
                            	<%--编辑功能屏蔽 只允许新增和删除 --%>
                                <input type="button" class="btn btn-info" onclick="parent.showPageForm('/90102030?puk=${item.puk}')" value="编辑">
                                <input type="button" class="btn btn-danger"  onclick="parent.showPageForm('/90102040?puk=${item.puk}&updateTime=${item.updateTime}')" value="删除">
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
        <td><p:page page="${list}" url="90102010" /></td>
    </tr>

</table>
<script type="text/javascript">
    parent.changeNavbar('系统菜单管理','首页》系统管理》系统菜单管理》系统菜单列表');

    if(!${message.code==0}){
        parent.showMessage("${message.code}","${message.msg}");
    }
</script>

</body>
</html>
