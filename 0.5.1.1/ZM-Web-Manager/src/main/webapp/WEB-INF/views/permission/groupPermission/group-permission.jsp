<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>group-permission</title><!-- 用户组权限列表 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
    <style type="text/css">
        div {
            margin: 25px 60px;
        }

        span {
            margin-left: 100px;
        }
    </style>
</head>
<body>
<form method="post" action="/90204020?token=${token}">
    <input type="hidden" name="token" value="${token}" />
    <input type="hidden" name="menuId" id="menuId"/>
    <input type="hidden" name="menuName" id="menuName"/>
    <input type="hidden" name="groupName" value="${data.groupName}"/>
    <input type="hidden" name="groupId" value="${data.puk}"/>
    <input type="hidden" name="menuLevel" id="menuLevel"/>
    <table>
        <tr class="trparam">
            <td>
                <table>
                    <tr class="trparam">
                        <td class="tdblank30">&nbsp;</td>
                        <td class="tdparam">用户组名称:${data.groupName}</td>
                        <td></td>
                        <td class="tdbtn">
                            <input type="submit" class="btn btn-danger btnright" value="保存" id="submitButton" />
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <c:forEach items="${groupPermission}" var="item">
                    <c:if test="${item.menuLevel==1}">
                        <div><input type="checkbox" value="${item.puk}" id="${item.menuLevel}"
                                          class="parent" />${item.menuName}</div>
                    </c:if>
                    <c:if test="${item.menuLevel==2}">
                        <span><input type="checkbox" value="${item.puk}" name="${item.fatherMenuId}" id="${item.menuLevel}"
                                     class="children" />${item.menuName}</span>
                    </c:if>
                </c:forEach>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    parent.changeNavbar('权限管理', '首页》权限管理》用户组定义》用户组关联权限');

    if (!${message.code==0}) {
        parent.showMessage("${message.code}", "${message.msg}");
    }

    //默认选定
    $.each(${groupPermission}, function (index, item) {
        if (item.groupId != undefined) {
            var puk = item.puk;
            $("input[value='" + puk + "']").prop("checked", true);
        }
    });

    //全选反选
    $(".parent").on("change", function () {
        var parentValue = $(this).val();
        if (this.checked) {
            $("input[name='" + parentValue + "']").prop("checked", true);
        } else {
            $("input[name='" + parentValue + "']").prop("checked", false);
        }
    });

    //按钮优化
    var size = 0;
    $(".children").on("change", function () {
        var childreName = $(this).attr("name");
        if (this.checked) {
            $("input[value='" + childreName + "']").prop("checked", true);
            size += 1;
        } else {
            size -= 1;
        }
        if (size == 0) {
            $("input[value='" + childreName + "']").prop("checked", false);
        }
    });

    $("#submitButton").click(function () {
        var menuId = new Array();
        var menuName = new Array();
        var menuLevel=new  Array();
        $("input[type='checkbox']").each(function (index, item) {
            if (this.checked) {
                menuId[index] = $(this).val();
                menuName[index] = $(this).parent().text();
                menuLevel[index]=$(this).attr("id");
            }
        });
        $("#menuId").val(menuId);
        $("#menuName").val(menuName);
        $("#menuLevel").val(menuLevel);
    });

</script>
</body>
</html>
