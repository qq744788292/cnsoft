<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>wx-menu-list</title><!-- 定时任务配置列表 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">

<table >
<form method="post" action="/70211010?token=${token}" id="searchForm">
    <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
    <input type="hidden" name="pageLimit" id="pageLimit" value="12">
    <tr class="trparam" >
        <td>
            <table>
                <tr>
                    <td>
                    	<input type="button" style="width: 150px" onclick="parent.showPageForm('/70211020')" class="btn btn-default btnleft" value="添加微信菜单">
                    </td>
                    <td class="tdparam">菜单名称:</td>
                    <td style="width: 150px">
                        <input type="text" class="form-control" name="menuName" placeholder="请输入菜单名称" value="${param.menuName}">
                    </td>
                    <td class="tdbtn">
                        <input type="submit" class="btn btn-primary btnright"  value="搜索">
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
                    <th>菜单ID</th>
                    <th>上级菜单名称</th>
                    <th>菜单名称</th>
                    <th>菜单等级</th>
                    <th>菜单类别</th>
                    <th>菜单类别</th>
                    
                    <th>菜单内容</th>
                    <th>排列顺序</th>
                    <th class="tdblank200">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${page.pageListData}">
                    <tr>
                        <td>${item.puk}</td>
                        <td>${item.fatherMenuId}</td>
                        <td>${item.menuName}</td>
                        <td>${item.menuLevel}</td>
                        <td>
		                	<c:choose>
		                		<c:when test="${item.menuLevel == 1 }">
		                			一级菜单
		                		</c:when>
		                		<c:when test="${item.menuLevel == 2 }">
		                			二级菜单
		                		</c:when>
		                		<c:when test="${item.menuLevel == 3 }">
		                			三级菜单
		                		</c:when>
		                		<c:otherwise>
		                			不明
		                		</c:otherwise>
		                	</c:choose>
		                </td>
                        <td>
		                	<c:choose>
		                		<c:when test="${item.menuType == 1000 }">
		                			本地栏目
		                		</c:when>
		                		<c:when test="${item.menuType == 2000 }">
		                			远程页面
		                		</c:when>
		                		<c:when test="${item.menuType == 3000 }">
		                			内部页面
		                		</c:when>
		                		<c:otherwise>
		                			不明
		                		</c:otherwise>
		                	</c:choose>
		                </td>
		                
                        <td>
	                        <c:if test="${not empty item.menuUrl}">
	                        <a href="${item.menuUrl}" title="${item.menuUrl}" target="_blank">内容</a>
							</c:if>	
                        </td>
                        <td>${item.sortNum}</td>
                        <td>
		                	<input type="button" class="btn btn-info" onclick="parent.showPageForm('/70211030?puk=${item.puk}')" value="编辑">
		                	<input type="button" class="btn btn-danger" onclick="parent.showDeleteWarning('/70211040?puk=${item.puk}&updateTime=${item.updateTime}','${item.menuName}')" value="删除">
			            </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${page}" url="/70211010" /></td>
    </tr>
</table>

<script type="text/javascript">
    parent.changeNavbar('微信菜单','首页 > 资源管理 > 微信文章管理 > 菜单栏目管理 > 菜单一览');
</script>

<c:if test="${not empty result}">
<script type="text/javascript">
    parent.showMessage('${result.code}', '${result.msg}');
</script>
</c:if>
</body>
</html>
