<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>wx-material-url-list.jsp</title><!-- 定时任务配置列表 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">
<table>
<form method="post" action="/70212090?token=${token}" id="crawlerForm">
    <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
    <input type="hidden" name="pageLimit" id="pageLimit" value="12">
    <input type="hidden" name="serviceName" id="serviceName" value="ZJWMJRFWMaterialUrlCrawlerService">
    <input type="hidden" name="remoteURL" id="remoteURL" value="浙江外贸金融服务">
</form>
<form method="post" action="/70212010?token=${token}" id="searchForm">
    <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
    <input type="hidden" name="pageLimit" id="pageLimit" value="12">
    <tr class="trparam" >
        <td>
            <table>
                <tr>
                    <td>
                    	<input type="button" style="width: 150px" onclick="parent.showPageForm('/70212020')" class="btn btn-default btnleft" value="添加文章地址">
                    	<input type="button" style="width: 220px" onclick="javascript:crawlerForm.submit()" class="btn btn-primary btnleft" value="手动获取公众号文章">
                    </td>
                    <td class="tdparam">标题:</td>
                    <td style="width: 150px">
                        <input type="text" class="form-control" name="title" placeholder="请输入标题" value="${param.title}">
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
                    <th>标题</th>
                    <th>缩略图</th>
                    <th>菜单所属</th>
                    <th>二维码</th>
                    <th class="tdblank200">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${page.pageListData}">
                    <tr>
                        <td>${item.title}</td>
                        <td><img src="${item.lookPicUrl}" width="80" height="40"></td>
                        <td>${item.menuId}</td>
                        <td><input type="button" class="btn" title="${item.materialUrl}" onclick="showQrCode('${item.puk}')" value="查看"></td>
                        <td>
		                	<input type="button" class="btn btn-info" onclick="parent.showPageForm('/70212030?puk=${item.puk}')" value="编辑">
		                	<input type="button" class="btn btn-danger" onclick="parent.showDeleteWarning('/70212040?puk=${item.puk}&updateTime=${item.updateTime}','${item.title}')" value="删除">
			            </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${page}" url="/70212010" /></td>
    </tr>
</table>

<!-- Modal -->
<div class="modal fade" id="spreadCodeModal" tabindex="-1" role="dialog" aria-labelledby="spreadCodeModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="spreadCodeModalLabel">二维码</h4>
            </div>
            <div class="modal-body">
                <div class="pay-qrcode" id="spread_code" >
                    <div>
                        <span style="font-size:20px;font-width:bolder;padding-left: 50px">该资源的固定地址如下:</span>
                    </div>
                    <div>
                        <span style="padding-left: 60px" id="menuUrl"></span>
                    </div>
                    <div>
                        <img style="padding-left: 150px" src="/99999031/220/220?content=http://u.wx.zjmade.com" id="wxQrcode"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    parent.changeNavbar('文章地址','首页 > 资源管理 > 微信文章管理 > 文章地址管理 > 文章地址一览');
    
    //页面地址二维码
    function showQrCode(puk){
        var menuUrl='http://u.wx.zjmade.cn/WXMaterialView/'+puk;
        var imgUrl='/99999031/220/220?content='+menuUrl;
        $('#menuUrl').html(menuUrl);
        $('#wxQrcode').attr("src",imgUrl);
        $('#spreadCodeModal').modal('show');
	}
</script>

<c:if test="${not empty message}">
<script type="text/javascript">
    parent.showMessage(${message.code}, '${message.msg}');
</script>
</c:if>
</body>
</html>
