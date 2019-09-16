<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>user-modify</title><!-- 修改用户 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">

<form method="post" action="/90301050?token=${token}" id="modifyForm">
    <input type="hidden" name="pageCurrent"  value="1">
    <input type="hidden" name="pageLimit" value="10">
    <input type="hidden" name="model" value="${model}">
    <input type="hidden" name="token" value="${token}">
    <input type="hidden" name="puk" value="${data.puk}">
    <table >
        <tr class="trparam" >
            <td>
                <table>
                    <tr>
                        <td><input type="button"  class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()"></td>
                        <td align="right"><input type="submit" class="btn btn-primary btnleft" value="保存" ></td>
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
                        <td class="tdparam">帐号:</td>
                        <td><input  type="text" class="form-control" name="userAccount" value="${data.userAccount}" /></td>
                        <td class="tdblank120">&nbsp;</td>
                    </tr>
                    <tr  >
                        <td class="tdparam">密码:</td>
                        <td><input  type="password" class="form-control" name="userPassword" value="${data.userPassword}"/></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr  >
                        <td class="tdparam">姓名:</td>
                        <td><input  type="text" class="form-control" name="userName" value="${data.userName}" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr  >
                        <td class="tdparam">性别:</td>
                        <td>
                        <input type="radio" name="userSex" value="1">男&nbsp;
							&nbsp;<input type="radio" name="userSex" value="2">女
                            <!-- <select name="userSex" class="form-control" id="userSex" >
                                <option value="1" >男</option>
                                <option value="2">女</option>
                                <option value="3">保密</option>
                            </select> -->
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                 <!--    <tr  >
                        <td class="tdparam">角色名称:</td>
                        <td>
                            <select  class="form-control" id="roleId" name="roleId" >
            
                            </select>
                        </td>
                        <td>&nbsp;</td>
                    </tr> -->
                    <tr  >
                        <td class="tdparam">邮箱:</td>
                        <td><input  type="text" class="form-control" name="userMail" value="${data.userMail}"/></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr  >
                        <td class="tdparam">手机号码:</td>
                        <td><input  type="text" class="form-control" name="userPhone" value="${data.userPhone}" /></td>
                        <td>&nbsp;</td>
                    </tr>
                <%--     <tr  >
                        <td class="tdparam">备注:</td>
                        <td><textarea class="form-control" name="meno"  cols="30" rows="5">${data.meno}</textarea></td>
                        <td>&nbsp;</td>
                    </tr> --%>
                </table>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    parent.changeNavbar('修改系统用户','首页》用户与权限》系统用户管理》修改系统用户');

</script>
</body>
</html>
