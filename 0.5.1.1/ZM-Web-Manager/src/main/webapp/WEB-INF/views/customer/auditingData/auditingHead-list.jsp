<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>auditingHead-list</title><!-- 头像审核 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body>
<table>
    <tr class="trparam">
        <td>
            <form action="/20101110?token=${token}" method="post">
                <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
                <input type="hidden" name="pageLimit" id="pageLimit" value="12">
                <table>
                    <tr class="trparam">
                        <td></td>
                        <td class="tdparam">用户ID:</td>
                        <td style="width: 150px">
                            <input type="text" class="form-control" name="puk" placeholder="请输入用户ID"
                                   value="${searchCondition.puk}">
                        </td>
                        <td class="tdbtn"><input class="btn btn-primary btnright" type="submit" value="搜索"></td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>用户ID</th>
                    <th>头像</th>
                    <th>审核状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${empty page.pageListData}">
                    <tr>
                        <td colspan="11"  style="background: gainsboro;text-align: center">没有数据</td>
                    </tr>
                </c:if>
                <c:forEach var="item" items="${page.pageListData}">
                    <tr>
                        <td>${item.puk}</td>
                        <td>
                            <img src="${item.headPortraitUrl}" style="width: 64px;height: 64px;">
                        </td>
                        <td>
                            <select style="height: 25px;" id="applyStateHead" name="applyStateHead"  onchange="updateState(this.value,'${item.puk}');">
                                <option value="1" <c:if test="${item.applyStateHead == '1'}">selected="selected"</c:if>>待审核</option>
                                <option value="2" <c:if test="${item.applyStateHead == '2'}">selected="selected"</c:if>>通过</option>
                                <option value="3" <c:if test="${item.applyStateHead == '3'}">selected="selected"</c:if>>不通过</option>
                            </select>
                        </td>
                        <td>
                            <input type="button" class="btn btn-success" onclick="parent.showPageForm('20101131?puk=${item.puk}&token=${token}')" value="查看">
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <p:page page="${page}" url="20101110" />
        </td>
    </tr>
</table>
<script type="text/javascript">
    parent.changeNavbar('用户管理', '首页》用户管理》头像审核》头像审核列表');

    if (!${message.code==0}) {
        parent.showMessage("${message.code}", "${message.msg}");
    }

    //修改状态
    function updateState(value,puk){
        $.ajax({
            type: "POST",
            url:"20101130?token=${token}",
            data:"puk="+puk+"&applyStateHead="+value,
            success:function(message){
                parent.showMessage(message.code, message.msg);
                parent.showPageForm('20101110?token=${token}');
            }
        });
    }

</script>
</body>
</html>
