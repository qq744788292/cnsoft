<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>popularizeStatistic-list</title><!-- 推广员统计列表 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body>
<form action="/40201010?token=${token}" method="post">
    <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
    <input type="hidden" name="pageLimit" id="pageLimit" value="12">
    <input type="hidden" name="token" value="${token}">
    <table>
        <tr class="trparam" >
            <td>
                <table>
                    <tr>
                        <td></td>
                        <td class="tdparam">身份证号码:</td>
                        <td style="width: 150px">
                            <input type="text"  class="form-control" name="identityCardNumber" placeholder="请输入身份证号码" value="${searchCondition.identityCardNumber}" />
                        </td>
                        <td class="tdparam">手机号码:</td>
                        <td style="width: 150px">
                            <input type="text"  class="form-control" name="phone" placeholder="请输入手机号码" value="${searchCondition.phone}" />
                        </td>
                        <td class="tdbtn">
                            <input type="submit" class="btn btn-primary btnright" value="搜索">
                        </td>
                    </tr>
                </table>
            </td>
        </tr>

        <%-- 表格数据显示区--%>
        <tr>
            <td>
                <table class="table table-striped table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>身份证号码</th>
                        <th>手机号码</th>
                        <th>推广人数</th>
                        <th>7天内活跃人数</th>
                        <th>30天内活跃人数</th>
                        <th>充值人数</th>
                        <th>推广链接</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${empty page.pageListData}">
                        <tr>
                            <td colspan="8"  style="background: gainsboro;text-align: center">没有数据</td>
                        </tr>
                    </c:if>
                    <c:forEach var="item" items="${page.pageListData}">
                        <tr>
                            <td>${item.name}</td>
                            <td>${item.identityCardNumber}</td>
                            <td>${item.phone}</td>
                            <td>${item.fb1}</td>
                            <td></td><td></td>
                            <td>${item.fb2}</td>
                            <td><input type="button" class="btn" onclick="spread_code('${item.puk}')" value="查看"></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </td>
        </tr>
        <%--分页信息--%>
        <tr>
            <td><p:page page="${page}" url="40101010"/></td>
        </tr>
    </table>
</form>

<!-- Modal模态框 -->
<div class="modal fade" id="spreadCodeModal" tabindex="-1" role="dialog" aria-labelledby="spreadCodeModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="spreadCodeModalLabel">二维码</h4>
            </div>
            <div class="modal-body">
                <div class="pay-qrcode" id="spread_code" >
                    <div >
                        <span style="font-size:20px;font-width:bolder;padding-left: 50px">该推广员的固定地址如下:</span>
                    </div>
                    <div >
                        <span style="padding-left: 60px" id="popularizeAddress"></span>
                    </div>
                    <div>
                        <img style="padding-left: 150px" class="pay_img" src="/99999031/220/220?content='http://u.2jiayou.com/popularize/1/1'" id="wxQrcodePay"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-primary">确定</button>--%>
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    parent.changeNavbar('推广管理', '首页》推广管理》推广员信息》推广统计列表');

    if (!${message.code==0}) {
        parent.showMessage("${message.code}", "${message.msg}");
    }

    function spread_code(puk){
        var url='http://u.2jiayou.com/popularize/'+puk;
        var url1='/99999031/220/220?content='+url+'';
        $('#popularizeAddress').html(url);
        $('#wxQrcodePay').attr("src",url1);
        $('#spreadCodeModal').modal('show');
    }
</script>
</body>
</html>
