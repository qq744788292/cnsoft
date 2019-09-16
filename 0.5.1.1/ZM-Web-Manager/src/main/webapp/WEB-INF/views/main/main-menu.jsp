<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
    <meta charset="utf-8">
    <title>欢迎访问运维管理中心</title>

    <!-- 静态资源文件引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
    <link href="/resources/common/css/menuicon.css" rel="stylesheet" media="screen">

    <style>
        /*简单粗暴重置默认样式===============================*/
        .menu {
            background: #2f88c6; 
            background: #0284cf; 
            background: #43b3e5; 
            background: #4aa3df;
        }
        
        .level1Menu {
            color: #fff;
            background: #00428e; /*一级菜单打开*/
            padding-left: 10px;
            border-bottom: 0px solid #000;
            font-size: 18px;
            position: relative;
            cursor : pointer;
            transition: all .5s ease 0s;
            vertical-align: middle;
       	}
		.level1Menu:hover, .level1Menu:focus, .level1Menu:active {
		  color: #ffffff;
		  background: #266dd9 !important;
		  outline: none;
		}
		.level2Menu {
            color: #fff;
            background: #1b446a; /*二级菜单打开*/
            padding-left: 45px;
            border-bottom: 0px solid #000;
            font-size: 16px;
            position: relative;
            cursor : pointer;
            transition: all .5s ease 0s;
            vertical-align: middle;
       	}
		.level2Menu:hover, .level2Menu:focus, .level2Menu:active {
		  color: #ffffff;
		  background: #0284cf !important;
		  outline: none;
		}
    </style>

</head>

<body class="body">
<form action="/" id="blankForm" name="blankForm" target="BizFrame" method="POST">
    <input type="hidden" name="token" id="token" value="${token}">
</form>
<form action="/ManagerLogout" id="userForm" name="userForm" method="POST">
	<input type="hidden" name="token" id="token" value="${token}">
</form> 
<table style="height: 100%;">
    <tr><!--  style="background: #1a6cb9;" -->
        <!-- 这里是左上用户信息 -->
        <td style="width: 200px; cursor: pointer; color:#fff; background: #000;vertical-align: middle;">
            <table>
                <tr>
                    <td style="width: 12px"></td>
                    <td style="font-size:18px; vertical-align: middle; color:#fff;" onclick="javascript:userForm.submit();">
                    	<i class="icon ZM-Main-icon-font">&#xe616;</i>&nbsp;&nbsp;${loginer.userNameCN}
                    </td>
                    <td style="width: 1px;background-color: white;"></td>
                </tr>
            </table>
		</td>
        <!-- 这里是右侧顶部导航 -->
        <td style="height:50px; background: #000;vertical-align: middle;">
            <table>
                <tr>
                    <td>
                        <table style="width: 100%;">
                            <tr>
                                <!-- <td style="width: 20px"><img src="/resources/common/img/split.png" style="width: 1px; height: 48px"></td> -->
                                <!-- 左侧 -->
                                <td id="title" align="center" style="color:#fff; font-size:18px; width: 160px; vertical-align: middle; font-weight: bold;"></td>
                                <!-- 导航 -->
                                <td id="navbar" align="left" style="color:#fff; font-size:14px; vertical-align: middle;">欢迎使用.</td>
                                <td style="width: 200px;"></td>
                                <td align="center" style="width:260px;">
	                                <div class="input-group" style="width:240px;">
					                    <input type="text" class="form-control">
					                    <span class="input-group-btn">
					                        <button class="btn btn-default" type="button">检索</button>
					                    </span>
					                </div>
						        </td>
                                <td align="right" style="cursor: pointer; width:80px;">
			                        <table>
			                            <tr>
                                			<td align="left"  style="width: 1px;background-color: white;"></td>
						                    <td align="center" style="font-size:16px; vertical-align: middle; color:#fff;" onclick="javascript:userForm.submit();">
						                        <i class="icon ZM-Main-icon-font" style="font-size:16px;">&#xe612;</i></span>&nbsp;&nbsp;退出
						                    </td>
			                            </tr>
			                        </table>
			                    </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
	</tr>
    <!-- 左侧菜单  valign="top"  style="height: 500px; "-->
    <tr>
        <td style="background: #1a6cb9;vertical-align: top;">
        <!-- 菜单展示开始 -->
	        <table style="width: 100%; background: #000;" border = 1>
	<!-- 开始一级菜单 -->
	<c:forEach var="item" items="${MENU}">
	        <tr style="display: ;"><!-- 一级菜单区域ID -->
		<c:choose>
           	<c:when test="${item.menuUrl != ''}">
	        	<td id="item_${item.puk}" class="level1Menu" onclick="changeBackground('${item.puk}');showPageForm('${item.menuUrl}')">
                    <i class="icon ZM-Main-icon-font">${item.menuPicUrl}</i>
                    <span style="font-size: 10px;">&nbsp;</span></span>${item.menuName}
                    <c:if test="${(item.nextSubs)!= null && fn:length(item.nextSubs) > 0}">
	                    <div style="float: right; margin-right: 0px;">
	                    	<i class="icon ZM-Main-icon-font">&#xe6f6;</i>
	                    	<span style="font-size: 10px;vertical-align: middle;">&nbsp;</span>
						</div>
					</c:if>
	        	</td>
            </c:when>
            <c:otherwise>
	        	<td id="item_${item.puk}" class="level1Menu" onclick="changeBackground('${item.puk}');">
                    <i class="icon ZM-Main-icon-font">${item.menuPicUrl}</i>
                    <span style="font-size: 10px;">&nbsp;</span></span>${item.menuName}
                    <c:if test="${(item.nextSubs)!= null && fn:length(item.nextSubs) > 0}">
	                    <div style="float: right; margin-right: 0px;">
	                    	<i class="icon ZM-Main-icon-font">&#xe6f6;</i>
	                    	<span style="font-size: 10px;vertical-align: middle;">&nbsp;</span>
						</div>
					</c:if>
	        	</td>
			</c:otherwise>
		</c:choose>
	        </tr>
	<!-- 结束一级菜单 -->
	<!-- 开始二级菜单 -->
	<c:if test="${(item.nextSubs)!= null && fn:length(item.nextSubs) > 0}">
	<!-- 结果集大于0，才显示 -->
	        <tr style="display: none;" id="itemMenu_${item.puk}"><!-- 二级菜单区域ID -->
	        	<td>
	        		<table>
	<c:forEach var="itemSub" items="${item.nextSubs}">
						<tr>
		<c:choose>
       		<c:when test="${itemSub.menuUrl != ''}">
				        	<td id="itemSub_${itemSub.puk}" class="level2Menu" onclick="changeBackgroundSub('${itemSub.puk}');showPageForm('${itemSub.menuUrl}')">
			               		${itemSub.menuName}
				        	</td>
            </c:when>
            <c:otherwise>
				        	<td id="itemSub_${itemSub.puk}" class="level2Menu" onclick="changeBackgroundSub('${itemSub.puk}');">
			               		${itemSub.menuName}
				        	</td>
            </c:otherwise>
        </c:choose>
        				</tr>
	</c:forEach>
					</table>
				</td>
	        </tr>
	</c:if>
	<!-- 结束二级菜单 -->
</c:forEach>
	        </table>
	    <!-- 菜单展示结束 -->    
        </td>
        <td id="myIframeTD" style="height: 20px" valign="top">
            <iframe src="/resources/blank.html" width="100%" height="100%" frameborder="no" marginwidth="0" marginheight="0" allowtransparency="yes" id="BizFrame" name="BizFrame"></iframe>
        </td>
    </tr>
</table>

<!-- 这里是提示描述 -->
<div style="position:absolute; width:600px; margin-left:-300px;left:50%; top:25px;">
    <div id="messagebox" role="alert" style="width:600px;display:none;">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <label id="messagelbl"></label>
    </div>
</div>

<!-- 这里是删除提示框 -->
<div class="modal fade" id="deleteWarningModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">警告</h4>
            </div>
            <div class="modal-body">
                <h3 style="text-align: center; color: red">删除之后数据可能无法恢复，确认删除吗?</h3>
                <p  style="text-align: center; color: red;font-size: large" id="deleteWarningMessage"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default tdblank80" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary tdblank80"  id="deleteWarningModalDeleteButton">确认</button>
            </div>
        </div>
    </div>
</div>

<script src="/resources/common/js/easing.js"></script>

<script src="/resources/views/js/main-menu.js"></script>

</body>
</html>
