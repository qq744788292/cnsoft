<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>ticket-list.jsp</title><!-- 优惠券码列表 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
    <script type="text/javascript" src="/resources/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="/resources/jquery-validation/localization/messages_zh.js"></script>
</head>
<body class="body">

<table >
    <form method="post" action="/4040?token=${token}" id="searchForm">
    <input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
    <input type="hidden" name="pageLimit" id="pageLimit" value="15">
    <input type="hidden" name="type" id="type" value="${type}">
        <tr class="trparam" >
            <td>
                <table>
                    <tr>
                        <td>
                            <input type="button" onclick="parent.showPageForm('/80102021')" class="btn btn-default btnleft" value="生成券码">
                        </td>
                        <td class="tdparam">活动名称:</td>
                        <td class="tdblank180">
                            <select name="activityName" class="form-control" id="activityNameSelect"></select>
                        </td>
                        <td class="tdparam">时间:</td>
                        <td class="tdblank200"> <input type="text" name="startDate" class="form-control"  value="${searchCondition.startDate}" > </td>
                        <td class="tdblank5">&nbsp;</td>
                        <td class="tdblank20" align="center"><img src="/resources/img/line.png"></td>
                        <td class="tdblank5">&nbsp;</td>
                        <td class="tdblank200"><input type="text" name="endDate" class="form-control" value="${searchCondition.endDate}"> </td>
                        <td class="tdparam">状态:</td>
                        <td class="tdblank5">&nbsp;</td>
                        <td class="tdblank180">
                            <select class="form-control" name="writeoffStatus" id="">
                            <option value="">全部</option>
                            <option value="0" <c:if test="${searchCondition.writeoffStatus == '0'}">selected='selected'</c:if>>未核销</option>
                            <option value="1" <c:if test="${searchCondition.writeoffStatus == '1'}">selected='selected'</c:if>>已核销</option>
                            <option value="2" <c:if test="${searchCondition.writeoffStatus == '2'}">selected='selected'</c:if>>已重置</option>
                        </select>
                        </td>
                        <td class="tdparam">券码:</td>
                        <td class="tdblank180">
                            <input type="text"  class="form-control" name="ticketNumber" placeholder="请输入券码号码" value="${searchCondition.ticketNumber}">
                        </td>
                        <td class="tdbtn">
                            <input type="submit" class="btn btn-primary btnright" value="查询搜索">
                        </td>
                    </tr>
                </table>
                <table>
                    <tr height="50px">
                        <td align="center"><input type="button" style="width:100px;" class="btn btn-primary" onclick="mySubmit('1');" value="今天"></td>
                        <td align="center"><input type="button" style="width:100px;" class="btn btn-primary" onclick="mySubmit('2');" value="昨天"></td>
                        <td align="center"><input type="button" style="width:100px;" class="btn btn-primary" onclick="mySubmit('3');" value="前天"></td>
                        <td align="center"><input type="button" style="width:100px;" class="btn btn-primary" onclick="mySubmit('0');" value="最近7天"></td>
                        <td align="center"><input type="button" style="width:100px;" class="btn btn-primary" onclick="mySubmit('4');" value="最近30天"></td>
                        <td align="center"><input type="button" style="width:100px;" class="btn btn-primary" onclick="mySubmit('5');" value="本周"></td>
                        <td align="center"><input type="button" style="width:100px;" class="btn btn-primary" onclick="mySubmit('6');" value="上周"></td>
                        <td align="center"><input type="button" style="width:100px;" class="btn btn-primary" onclick="mySubmit('7');" value="本月"></td>
                        <td align="center"><input type="button" style="width:100px;" class="btn btn-primary" onclick="mySubmit('8');" value="上月"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </form>
    <tr>
        <td class="tdbtn" align="right">
            <input type="button" onclick="multiReset()" class="btn btn-primary btnright" value="批量重置">
        </td>
    </tr>
    <%-- 表格数据显示区--%>
    <tr>
        <td>
            <table class="table table-striped table-hover table-bordered">
            	<thead>
                <tr>
                    <th class="tdblank30" style="position: relative;"><input type="checkbox" id="selectAll" class="form-control"  style="bottom: -7px;position: absolute;width: 25px;"/></th>
                    <th>券码</th>
                    <th>状态</th>
                    <th>活动编号</th>
                    <th>活动名称</th>
                    <th>奖项</th>
                    <th>奖品名</th>
                    <%--<th>中奖人</th>--%>
                    <%--<th>中奖人手机号</th>--%>
                    <%--<th>中奖人地址</th>--%>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="tbody">
                <c:if test="${empty page.pageListData}">
                    <tr>
                        <td colspan="11"  style="text-align: center">没有数据</td>
                    </tr>
                </c:if>
                <c:forEach var="item" items="${page.pageListData}">
                    <tr>
                        <td>
                            <input type="checkbox" class="form-control"  name="puk" value="${item.puk}">
                        </td>
                        <td>${item.ticketNumber}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.writeoffStatus == '0'}">未核销</c:when>
                                <c:when test="${item.writeoffStatus == '1'}">已核销</c:when>
                                <c:when test="${item.writeoffStatus == '2'}">已重置</c:when>
                                <c:otherwise>未知</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${item.activityNo}</td>
                        <td>${item.activityName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.rewardLevel == '0'}">特等奖</c:when>
                                <c:when test="${item.rewardLevel == '1'}">一等奖</c:when>
                                <c:when test="${item.rewardLevel == '2'}">二等奖</c:when>
                                <c:when test="${item.rewardLevel == '3'}">三等奖</c:when>
                                <c:when test="${item.rewardLevel == '4'}">四等奖</c:when>
                                <c:when test="${item.rewardLevel == '5'}">优秀奖</c:when>
                                <c:when test="${item.rewardLevel == '6'}">安慰奖</c:when>
                                <c:when test="${item.rewardLevel == '7'}">金奖</c:when>
                                <c:when test="${item.rewardLevel == '8'}">银奖</c:when>
                                <c:otherwise>未知</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${item.rewardName}</td>
                        <%--<td>${item.writeoffName}</td>--%>
                        <%--<td>${item.writeoffPhone}</td>--%>
                        <%--<td>${item.writeoffAddress}</td>--%>
                        <td class="tdblank200">
                            <c:choose>
                                <c:when test="${item.writeoffStatus == '0'}">
                                    <input type="button" class="btn btn-info" onclick="showModel('${item.ticketNumber}')" value="核销">
                                    <input type="button" class="btn btn-info" onclick="parent.showPageForm('/80102040?puk=${item.puk}&writeoffStatus=2')" value="重置">
                                </c:when>
                                <c:when test="${item.writeoffStatus == '1'}">
                                    <input type="button" class="btn btn-info" onclick="parent.showPageForm('/80102040?puk=${item.puk}&writeoffStatus=2')" value="重置">
                                </c:when>
                                <c:when test="${item.writeoffStatus == '2'}">
                                    <input type="button" class="btn btn-info" onclick="showModel('${item.ticketNumber}')" value="核销">
                                </c:when>
                                <c:otherwise>未知</c:otherwise>
                            </c:choose>
							<%--<input type="button" class="btn btn-info" onclick="parent.showPageForm('/80102021?puk=${item.puk}')" value="核销">--%>
							<%--<input type="button" class="btn btn-info" onclick="parent.showPageForm('/80102040?puk=${item.puk}')" value="重置">--%>
							<%--<input type="button" class="btn btn-info" onclick="parent.showPageForm('/80102021?puk=${item.puk}')" value="查看">--%>
							<%--<input type="button" class="btn btn-danger"  onclick="parent.showPageForm('/80102030?puk=${item.puk}&updateTime=${item.updateTime}')" value="删除">--%>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${page}" url="4040" /></td>
    </tr>
</table>

<!-- 核销信息填写 -->
<div class="modal fade" id="messageModel" tabindex="-1" role="dialog" aria-labelledby="messageModel">
    <div class="modal-dialog" role="document">
        <div class="modal-content message_align">
            <form method="post" action="/80102050?token=${token}" id="messageModelForm">
                <input type="hidden" name="ticketNumber" id="ticketNumber">
                <input type="hidden" name="fb2" id="userId"><!--userId-->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">核销信息</h4>
                </div>
                <div class="modal-body" >
                    <table class="table-striped table-hover" >
                        <tr>
                            <td class="tdparam">中奖人名字:</td>
                            <td><input type="text" class="form-control" name="writeoffName" id="writeoffNameInput" /></td>
                            <td class="tdblank120">&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="tdparam">中奖人手机号:</td>
                            <td><input type="text" class="form-control" name="writeoffPhone" id="writeoffPhoneInput" /></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="tdparam">中奖人地址:</td>
                            <td>
                                <textarea class="form-control" name="writeoffAddress"></textarea>
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="3" style="text-align: center;color: red">
                                <span id="messageSpan" ></span>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="messageModel" id="messageFormConfirmButton" onclick="submitMessageModelForm()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    parent.changeNavbar('券码列表','首页》运营与统计》券码》券码列表');

    $(function(){
        $.ajax({
            type:"POST",
            url:"/80102091?token=${token}",
            success:function(result){
                $("#activityNameSelect").append("<option value=''>全部</option>");
                for (var i = 0; i < result.data.length; i++) {
                    $("#activityNameSelect").append("<option value="+ result.data[i].activityName +">" + result.data[i].activityName + "</option>");
                }
            }
        });
    });

    if(!${resultMessage.code==0}){
        parent.showMessage("${resultMessage.code}","${resultMessage.message}");
    }

    $("#selectAll").on("change",function(){
        $(':checkbox').prop("checked",this.checked);
    });

    function mySubmit(type){
        $("#type").val(type);
        searchForm.submit();
    }


    function multiReset(){
//        alert("multiReset");
        var puks=[];
        $('#tbody input:checkbox').each(function() {
            if ($(this).prop('checked') ==true) {
//                alert($(this).val());
                puks.push($(this).val());
            }
        });
        console.log(puks);
        if(puks == null ||puks ==""){
            parent.showMessage("3","请选择至少一条数据");
        }else{
            $.ajax({
                type:"POST",
                url:"/80102041",
                data:{
                    token:'${token}',
                    puks :puks
                },
                traditional: true,//这个设置为true，data:{"steps":["qwe","asd","zxc"]}会转换成steps=qwe&steps=asd&...
                success:function(result){
                    if(result.code == 1){
                        parent.showMessage(result.code,result.message);
                        window.location.reload();
                    }
                }
            });
        }

    }

    function submitMessageModelForm(){
        var  writeoffName =$('#writeoffNameInput').val();
        var  writeoffPhone =$('#writeoffPhoneInput').val();
        if(writeoffName ==""){
            $('#messageSpan').text("请输入姓名!");
        }else if(writeoffPhone ==""){
            $('#messageSpan').text("请输入手机号码!");
        }else{
            $.ajax({
                type:"POST",
                url:"/80102092?token=${token}&realName="+writeoffName+"&phone="+writeoffPhone,
                success:function(result){
                    console.log(result);
                    if('0' != result.code){
                        //显示结果
                        $('#messageSpan').text(result.message);
                    }else{
                        $('#userId').val(result.data.puk);
                        $('#messageModelForm').submit();
                    };
                }
            });
        };
    }

    function showModel(ticketNumber){
        $("#ticketNumber").val(ticketNumber);
        $("#messageModel").modal({
            backdrop:"static"
        });
        $('#messageModel').modal('show');
    }

    $("#messageModelForm").validate({
        //调试模式,验证成功了也不会发生跳转
        //debug: true,
        rules: {
            writeoffName : "required",
            writeoffPhone : "required",
//            writeoffAddress : "required"

        },
        messages: {
            writeoffName:"不能为空,请重新输入!",
            writeoffPhone:"不能为空,请重新输入!",
//            writeoffAddress:"不能为空,请重新输入!"
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
