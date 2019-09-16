<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>system-Menu-url-modify</title><!-- 修改菜单 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body  class="body">

<form method="post" action="/90102050?token=${token}" id="modifyForm">
    <input type="hidden" name="pageCurrent"  value="1">
    <input type="hidden" name="pageLimit" value="10">
    <input type="hidden" name="token" value="${token}">
    <%--<input type="hidden" name="puk" value="${data.puk}"">--%>
    <table >
        <tr class="trparam" >
            <td>
                <table>
                    <tr>
                        <td><input type="button" class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()"></td>
                        <td align="right"><input type="submit"  class="btn btn-primary btnright" value="保存" ></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr class="pageht">
            <td >&nbsp;</td>
        </tr>
        <tr >
            <td>
                <table class="table-striped table-hover">
                    <tr  >
                        <td class="tdparam">菜单ID:</td>
                        <td ><input  type="text" class="form-control" name="puk" value="${data.puk}" /></td>
                        <td  class="tdblank120">&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">父菜单ID:</td>
                        <td ><input  type="text" class="form-control" name="fatherMenuId"  value="${data.fatherMenuId}"/></td>
                        <td  >&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">菜单名称:</td>
                        <td ><input  type="text" class="form-control" name="menuName" value="${data.menuName}"/></td>
                        <td  >&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">菜单版本号:</td>
                        <td ><input  type="text" class="form-control" name="menuVer" value="${data.menuVer}"/></td>
                        <td  >&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">排列顺序:</td>
                        <td ><input  type="text" class="form-control" name="sortNum"  value="${data.sortNum}"/></td>
                        <td  >&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">菜单图标:</td>
                        <td ><input  type="text" class="form-control" name="menuPicUrl" value="${data.menuPicUrl}" /></td>
                        <td  >&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">菜单URL:</td>
                        <td ><input  type="text" class="form-control" name="menuUrl" value="${data.menuUrl}"/></td>
                        <td  >&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">菜单等级:</td>
                        <td ><input  type="text" class="form-control" name="menuLevel" value="${data.menuLevel}" /></td>
                        <td  >&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">备注:</td>
                        <td ><textarea  class="form-control" name="meno"  cols="30" rows="5" >${data.meno}</textarea></td>
                        <td  >&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    parent.changeNavbar('修改菜单','首页》系统管理》菜单管理》修改菜单');
    

</script>
</body>
</html>
