<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>system-Config-url-add</title><!-- 添加系统配置 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">

<form method="post" action="/90101050?token=${token}" id="addForm" >
    <input type="hidden" name="pageCurrent"  value="1">
    <input type="hidden" name="pageLimit" value="10">
	<input type="hidden" name="mode" value="${mode}">
    <table >
        <tr class="trparam">
            <td>
                <table>
                    <tr>
                        <td><input type="button"  class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()"></td>
                        <td align="right"><input type="submit"  class="btn btn-primary btnright" value="保存" ></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr class="pageht">
            <td style="width:120px;">&nbsp;</td>
        </tr>
        <tr >
            <td>
                <table class="table-striped table-hover">
                    <tr >
                        <td class="tdparam">系统配置分类:</td>
                        <td><input type="text" class="form-control" name="configType"/></td>
                        <td class="tdblank120">&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">系统配置名称:</td>
                        <td><input type="text" class="form-control" name="configKey"/></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">配置内容:</td>
                        <td><input type="text" class="form-control" name="configValue"/></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr >
                        <td class="tdparam">备注:</td>
                        <td><textarea class="form-control" name="meno" cols="30" rows="5"></textarea></td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    parent.changeNavbar('添加系统配置','首页》系统管理》系统配置管理》添加系统配置');


</script>
</body>
</html>
