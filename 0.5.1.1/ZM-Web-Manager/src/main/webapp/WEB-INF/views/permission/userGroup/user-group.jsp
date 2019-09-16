<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>user-group</title><!-- 用户组关联用户列表 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body>
<table>
    <form method="post" action="/90303020?token=${token}" >
        <input type="hidden" name="groupId" value="${data.puk}" />
        <input type="hidden" name="groupName" value="${data.groupName}" />
        <input type="hidden" name="token" value="${token}" />
        <input type="hidden" id="userId" name="userId" value="" />
        <input type="hidden" id="userName" name="userName" value="" />
        <tr class="trparam">
            <td>
                <table>
                    <tr class="trparam">
                        <td class="tdblank30">&nbsp;</td>
                        <td class="tdparam">用户组名称:${data.groupName}</td>
                        <td></td>
                        <td class="tdbtn">
                            <input type="submit" class="btn btn-danger btnright" value="保存" id="submit" />
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <table class="table table-striped table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>
                            <input type="checkbox" id="selectAll" style="height: 20px;"/>
                        </th>
                        <th>用户ID</th>
                        <th>用户帐号</th>
                        <th>用户名称</th>
                        <th>电话</th>
                    </tr>
                    </thead>
                    <tbody id="tbody">
                    <c:forEach items="${userGroup}" var="item">
                        <tr>
                            <c:if test="${empty item.groupId}">
                                <td><input type="checkbox" style="height: 20px;" /></td>
                            </c:if>
                            <c:if test="${not empty item.groupId}">
                                <td ><input type="checkbox" style="height: 20px;"  checked="checked"/>
                                </td>
                            </c:if>
                            <td>${item.puk}</td>
                            <td>${item.userAccount}</td>
                            <td>${item.userName}</td>
                            <td>${item.userPhone}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </td>
        </tr>
    </form>
</table>
<script type="text/javascript">
    parent.changeNavbar('权限管理','首页》权限管理》用户组定义》用户组关联角色');

    $(function() {
        var select1 = $("#tbody :checkbox").length;
        $("#tbody :checkbox").on("change", function () {
            var select2 = $("#tbody :checked").length;
            if (select2 == select1) {
                $("#selectAll").prop("checked", true);
            } else {
                $("#selectAll").prop("checked", false);
            }
        });
    });

    //全选或取消全选
    $("#selectAll").on("change", function () {
        $('#tbody :checkbox').prop("checked",this.checked);
    });

    var userId=new Array();
    var userName=new Array();
    $("#submit").click(function () {

        $("#tbody :checkbox").each(function (index,item) {
            if (this.checked) {
                userId[index]=$(this).parent().parent().find("td:eq(1)").text();
                userName[index]=$(this).parent().parent().find("td:eq(3)").text();
            }
        });
        $("#userId").val(userId);
        $("#userName").val(userName);
    });

</script>
</body>
</html>
