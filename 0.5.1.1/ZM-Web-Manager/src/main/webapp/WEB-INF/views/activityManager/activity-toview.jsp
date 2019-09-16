<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/fileuploadtag.tld" prefix="u"%>
<html>
<head>
    <title>activity-check.jsp</title><!-- 添加分类 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
    <script type="text/javascript" src="/resources/ext/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="/resources/ext/jquery-validation/localization/messages_zh.js"></script>
</head>
<body class="body">

<form method="post" action="" id="addForm">
    <input type="hidden" name="pageCurrent"  value="1">
    <input type="hidden" name="pageLimit" value="15">
    <input type="hidden" name="puk" value="">
    <table>
        <tr class="trparam" >
            <td>
                <table>
                    <tr>
                        <td><input type="button" class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()"></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr class="pageht">
            <td>&nbsp;</td>
        </tr>
        <tr >
            <td>
                <table class="table-striped table-hover" >
                    <tr>
                        <td class="tdparam">活动编号:</td>
                        <td><input type="text" class="form-control" name="number" value="${data.activityNumber}" disabled/></td>
                        <td class="tdblank120">&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">活动名称:</td>
                        <td><input type="text" class="form-control" name="number" value="${data.activityName}" disabled/></td>
                        <td class="tdblank120">&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">所属类型:</td>
                        <td>
                            <select name="type" class="form-control" style="width: 200px" id="typeSelect" disabled>
                            	<c:choose>
                                    <c:when test="${data.activityTypeType == '1000'}">
                                    	<option value="1000">日常签到</option>
                                    </c:when>
                                    <c:when test="${data.activityTypeType == '2000'}">
										<option value="2000">团体活动</option>
									</c:when>
                                    <c:otherwise>
										<option value="3000">优惠券码</option>
									</c:otherwise>
                            	</c:choose>
                            </select>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">活动分类:</td>
                        <td><input type="text" class="form-control" name="" value="${data.activityTypeName}" disabled/></td>
                        <td>&nbsp;</td>
                    </tr>
                    
                    <tr>
                    	<td class="tdparam">奖品内容:</td>
                   		<td>
                   			<c:forEach var="item" items="${list}">
                        		<input type="text" class="form-control" name="sendTime" value="${item.prize}" style="margin: 10px 0;" disabled/>
                        	</c:forEach>
                        </td>
                    	<td>&nbsp;</td>
                    </tr>
                    
                    <tr>
                        <td class="tdparam">活动状态:</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${data.activityState == '1'}">
                        			<input type="text" class="form-control" name="" value="未开始" disabled/>
                        		</c:when>
                        		<c:when test="${data.activityState == '2'}">
                        			<input type="text" class="form-control" name="" value="正进行" disabled/>
                        		</c:when>
                        		<c:when test="${data.activityState == '3'}">
                        			<input type="text" class="form-control" name="" value="已过期" disabled/>
                        		</c:when>
                        	</c:choose>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">是否发布:</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${data.isSened == '1'}">
                        			<input type="text" class="form-control" name="" value="是" disabled/>
                        		</c:when>
                        		<c:when test="${data.isSened == '2'}">
                        			<input type="text" class="form-control" name="" value="否" disabled/>
                        		</c:when>
                        	</c:choose>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">发布时间:</td>
                        <td><input type="text" class="form-control" name="" value="${data.sendTime}" disabled/></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">开始时间:</td>
                        <td><input type="text" class="form-control" name="activityStartTime" value="${data.activityStartTime}" disabled/></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">结束时间:</td>
                        <td><input type="text" class="form-control" name="activityEndTime" value="${data.activityEndTime}" disabled/></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="tdparam">海报:</td>
                        <td>
	                        <div style="position: relative;">
	                        	<u:fileupload height="200" width="200" id="upload1" type="1" token="${token}" target="lookPic" value="${data.activityPicture}"/>
	                        </div>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    parent.changeNavbar('添加活动分类','首页》运营与统计》活动分类》添加活动分类');
    var data = ${data};
    console.log(data);
</script>
</body>
</html>
