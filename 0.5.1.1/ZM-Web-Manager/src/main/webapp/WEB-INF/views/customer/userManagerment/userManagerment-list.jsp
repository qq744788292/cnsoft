<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>userManagerment-list</title><!-- 用户信息 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body>
    <table>
        <form method="post" action="/20101010?token=${token}" >
            <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
            <input type="hidden" name="pageLimit" id="pageLimit" value="12">
        <tr class="trparam">
            <td>
                <table>
                    <tr>
                        <td style="width:120px;">
                            <input type="button" class="btn btn-default btnleft" value="添加" onclick="parent.showPageForm('/20101020')">
                        </td>
                        <td >&nbsp;</td>
                        <td class="tdparam tdblank60">状态</td>
                        <td class="tdblank80">
                            <select class="form-control tdblank80" name="state" id="state" >
                                <option value="">全部</option>
                                <option value="0" <c:if test="${searchCondition.state == '0'}">selected="selected"</c:if>>正常</option>
                                <option value="1" <c:if test="${searchCondition.state == '1'}">selected="selected"</c:if>>封号</option>
                            </select>
                        </td>
                        <td style="width:5px;">&nbsp;</td>
                        <td class="tdparam tdblank120">实名状态</td>
                        <td class="tdblank80">
                            <select class="form-control tdblank100" name="applyStateReal" id="applyStateReal" >
                                <option value="">全部</option>
                                <option value="1" <c:if test="${searchCondition.applyStateReal == '1'}">selected="selected"</c:if>>已申请</option>
                                <option value="2" <c:if test="${searchCondition.applyStateReal == '2'}">selected="selected"</c:if>>通过</option>
                                <option value="3" <c:if test="${searchCondition.applyStateReal == '3'}">selected="selected"</c:if>>未通过</option>
                                <option value="9" <c:if test="${searchCondition.applyStateReal == '9'}">selected="selected"</c:if>>未申请</option>
                            </select>
                        </td>
                        <td style="width:5px;">&nbsp;</td>
                        <td class="tdparam tdblank120">注册方式</td>
                        <td class="tdblank80">
                            <select class="form-control tdblank100" name="registerType" id="registerType" >
                                <option value="">全部</option>
                                <option value="1" <c:if test="${searchCondition.registerType == '1'}">selected="selected"</c:if>>手机号</option>
                                <option value="2" <c:if test="${searchCondition.registerType == '2'}">selected="selected"</c:if>>微信</option>
                                <option value="3" <c:if test="${searchCondition.registerType == '3'}">selected="selected"</c:if>>QQ</option>
                                <option value="9" <c:if test="${searchCondition.registerType == '9'}">selected="selected"</c:if>>后台</option>
                            </select>
                        </td>
                        <td style="width:5px;">&nbsp;</td>
                        <td class="tdparam tdblank60">性别</td>
                        <td class="tdblank120">
                            <select class="form-control tdblank80" name="sex" id="sex" >
                                <option value="">全部</option>
                                <option value="0" <c:if test="${searchCondition.sex == '0'}">selected="selected"</c:if>>保密</option>
                                <option value="1" <c:if test="${searchCondition.sex == '1'}">selected="selected"</c:if>>男</option>
                                <option value="2" <c:if test="${searchCondition.sex == '2'}">selected="selected"</c:if>>女</option>
                            </select>
                        </td>
                        <td style="width:5px;">&nbsp;</td>
                        <td class="tdparam tdblank80">年龄段</td>
                        <td class="tdblank80">
                            <select class="form-control tdblank80" name="ageGroup" id="ageGroup" >
                                <option value="">全部</option>
                                <option value="40" <c:if test="${searchCondition.ageGroup == '40'}">selected="selected"</c:if>>40后</option>
                                <option value="50" <c:if test="${searchCondition.ageGroup == '50'}">selected="selected"</c:if>>50后</option>
                                <option value="60" <c:if test="${searchCondition.ageGroup == '60'}">selected="selected"</c:if>>60后</option>
                                <option value="70" <c:if test="${searchCondition.ageGroup == '70'}">selected="selected"</c:if>>70后</option>
                                <option value="999" <c:if test="${searchCondition.ageGroup == '999'}">selected="selected"</c:if>>其他</option>
                            </select>
                        </td>
                        <td style="width:5px;">&nbsp;</td>
                        <td class="tdparam tdblank60">学历</td>
                        <td class="tdblank120">
                            <select class="form-control tdblank120" name="education" id="education" >
                                <option value="">全部</option>
                                <option value="1" <c:if test="${searchCondition.education == '1'}">selected="selected"</c:if>>初中及以下</option>
                                <option value="2" <c:if test="${searchCondition.education == '2'}">selected="selected"</c:if>>高中</option>
                                <option value="3" <c:if test="${searchCondition.education == '3'}">selected="selected"</c:if>>中专及以上</option>
                            </select>
                        </td>
                        <td style="width:5px;">&nbsp;</td>
                        <td class="tdparam tdblank60">昵称</td>
                        <td class="tdblank120"><input type="text" class="form-control" name="nickName" value="${searchCondition.nickName}"></td>
                        <td style="width:5px;">&nbsp;</td>
                        <td class="tdparam tdblank80">手机号</td>
                        <td class="tdblank120"><input type="text" class="form-control" name="userPhone" value="${searchCondition.userPhone}"></td>
                        <td style="width:10px;">&nbsp;</td>
                        <td style="width:100px;"><input type="submit" class="btn btn-primary btnright"value="搜索"></td>
                    </tr>
                </table>
            </td>
        </tr>
        </form>
        <tr>
            <td>
                <table class="table table-striped table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>用户ID</th>
                        <th>注册方式</th>
                        <th>注册日期</th>
                        <th>手机号</th>
                        <th>昵称</th>
                        <th>性别</th>
                        <th>年龄段</th>
                        <th>学历</th>
                        <th>实名状态</th>
                        <th>账号状态</th>
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
                                <c:if test="${item.registerType == '1'}">手机号</c:if>
                                <c:if test="${item.registerType == '2'}">微信</c:if>
                                <c:if test="${item.registerType == '3'}">QQ</c:if>
                                <c:if test="${item.registerType == '9'}">后台</c:if>
                            </td>
                            <td>${item.createTime}</td>
                            <td>${item.userPhone}</td>
                            <td>${item.nickName}</td>
                            <td>
                                <c:if test="${item.sex == '0'}">保密</c:if>
                                <c:if test="${item.sex == '1'}">男</c:if>
                                <c:if test="${item.sex == '2'}">女</c:if>
                            </td>
                            <td>
                                <c:if test="${item.ageGroup == '40'}">40后</c:if>
                                <c:if test="${item.ageGroup == '50'}">50后</c:if>
                                <c:if test="${item.ageGroup == '60'}">60后</c:if>
                                <c:if test="${item.ageGroup == '70'}">70后</c:if>
                                <c:if test="${item.ageGroup == '999'}">其他</c:if>
                            </td>
                            <td>
                                <c:if test="${item.education == '1'}">初中及以下</c:if>
                                <c:if test="${item.education == '2'}">高中</c:if>
                                <c:if test="${item.education == '3'}">中专及以上</c:if>
                            </td>
                            <td>
                                <c:if test="${item.applyStateReal == '1'}">已申请</c:if>
                                <c:if test="${item.applyStateReal == '2'}">通过</c:if>
                                <c:if test="${item.applyStateReal == '3'}">未通过</c:if>
                                <c:if test="${item.applyStateReal == '9'}">未申请</c:if>
                                <c:if test="${empty item.applyStateReal}">未申请</c:if>
                            </td>
                            <td>
                                <select name="state" style="height: 25px;" onchange="updateState(this.value,'${item.puk}');">
                                    <option value="0" <c:if test="${item.state == '0'}">selected="selected"</c:if>>正常</option>
                                    <option value="1" <c:if test="${item.state == '1'}">selected="selected"</c:if>>封号</option>
                                </select>
                            </td>
                            <td>
                                <input type="button" class="btn btn-success" onclick="parent.showPageForm('20101031?puk=${item.puk}&token=${token}')" value="查看">
                                <input type="button" class="btn btn-info" onclick="parent.showPageForm('20101030?puk=${item.puk}')" value="编辑">
                                <input type="button" class="btn btn-danger" onclick="parent.showPageForm('20101040?puk=${item.puk}&updateTime=${item.updateTime}&pageCurrent=1&pageLimit=${page.pageLimit}')" value="删除">
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <p:page page="${page}" url="60101010" />
            </td>
        </tr>
    </table>
<script type="text/javascript">
    parent.changeNavbar('用户管理', '首页》用户管理》用户信息》用户信息列表');

    if (!${message.code==0}) {
        parent.showMessage("${message.code}", "${message.msg}");
    }

    //修改状态
    function updateState(value,puk){
        $.ajax({
            type: "POST",
            url:"20101032?token=${token}",
            data:"puk="+puk+"&state="+value,
            success:function(message){
                parent.showMessage(message.code, message.msg);
                parent.showPageForm('20101010?token=${token}');
            }
        });
    }

</script>
</body>
</html>
