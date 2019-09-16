<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>group-list</title><!-- 用户组列表 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
    <c:import url="/WEB-INF/views/config/jquery-validation.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">
<form method="post" action="/90203020?token=${token}" id="addForm">
    <table>
        <input type="hidden" name="token" value="${token}"/>
        <tr>
            <td>
                <table>
                    <tr class="trparam">
                        <td class="tdparam">用户组ID:</td>
                        <td>
                            <input id="puk" type="text" class="form-control" name="puk"/>
                        </td>
                        <td class="tdblank120">&nbsp;</td>
                        <td class="tdparam">用户组名称:</td>
                        <td>
                            <input id="groupName" type="text" class="form-control" name="groupName"/>
                        </td>
                        <td class="tdblank120">&nbsp;</td>
                        <td class="tdbtn">
                            <input type="submit" class="btn btn-primary btnright" value="添加用户组" />
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<br>
<table>
    <form method="post" action="/90203010?token=${token}" id="searchForm">
        <input type="hidden" name="pageCurrent" id="pageCurrent" value="1" />
        <input type="hidden" name="pageLimit" id="pageLimit" value="12" />
        <tr class="trparam">
            <td>
                <table>
                    <tr>
                        <td></td>
                        <td class="tdparam">用户组名称:</td>
                        <td style="width: 150px">
                            <input type="text" class="form-control" name="groupName" placeholder="请输入用户组名称"
                                   value="${searchCondition.groupName}" />
                        </td>
                        <td class="tdbtn">
                            <input type="submit" class="btn btn-primary btnright" value="搜索" />
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
                    <th>用户组名称</th>
                    <th>备注</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${page.pageListData}">
                    <tr>
                        <td>${item.groupName}</td>
                        <td>${item.meno}</td>
                        <td class="tdblank300">
                            <input type="button" class="btn btn-group"
                                   onclick="parent.showPageForm('90303010?puk=${item.puk}')" value="关联用户">
                            <input type="button" class="btn btn-warning"
                                   onclick="parent.showPageForm('90204010?groupId=${item.puk}&groupName=${item.groupName}')"
                                   value="权限" />
                            <input type="button" class="btn btn-info"
                                   onclick="parent.showPageForm('90203031?puk=${item.puk}')" value="编辑" />
                            <input type="button" class="btn btn-danger"
                                   onclick="parent.showPageForm('90203040?puk=${item.puk}&updateTime=${item.updateTime}')"
                                   value="删除" />
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${page}" url="90301010"/></td>
    </tr>


</table>
<script type="text/javascript">
    parent.changeNavbar('权限管理', '首页》权限管理》用户组定义》用户组列表');

    if (!${message.code==0}) {
        parent.showMessage("${message.code}", "${message.msg}");
    }

    $("#addForm").validate({
        //调试模式,验证成功了也不会发生跳转
        //debug: true,
        rules: {
            groupName: {
                required: true,
                remote: {
                    type: "POST",
                    url: "/90203050",
                    data: {
                        groupName: function () {
                            return $("#groupName").val();
                        },
                        token: "${token}"
                    }
                }
            }
        },
        messages: {
            groupName: {
                required: "不能为空,请重新输入!",
                remote: "已存在,请重新输入!"
            }
        },
        errorClass: "text-danger",
        highlight: function (element, errorClass) {
            //给输入框添加红色外框
            $(element).closest("div.form-group").addClass("has-error");
        },
        unhighlight: function (element, errorClass) {
            $(element).closest("div.form-group").removeClass("has-error");
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
</script>

</body>
</html>
