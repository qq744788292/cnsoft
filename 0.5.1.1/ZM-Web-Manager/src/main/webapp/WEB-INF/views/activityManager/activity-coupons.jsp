<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<%@ taglib uri="/WEB-INF/tag/fileuploadtag.tld" prefix="u"%>

<html>
<head>
    <title>activity-add.jsp</title><!-- 添加活动 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>

    <script type="text/javascript" src="/resources/ext/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="/resources/ext/jquery-validation/localization/messages_zh.js"></script>
	<link href="/resources/common/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
	<script src="/resources/common/js/moment-with-locales.min.js" type="text/javascript"></script>
	<script  src="/resources/common/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
		<style>

			.flag-style{
				background: #f5f5f5;
				margin-left: -15px;
				height: 40px;
				line-height: 40px;
				text-align: center;
			}
			.row-comm{
				margin-top: 10px;
			}
			.my-btn{
				float: right;
				margin-top: 15px;
			}
			.cums tr td{
				width: 60px;
				border: 1px solid #aaa;
				text-align: center;
			}
			.margin-container{
				margin: 0 40px;
			}
		</style>
</head>
<body class="body">
	<table>
		<tr class="trparam" >
			<td><input type="button" class="btn btn-default btnleft" value="返回" onclick="JavaScript:history.back()"></td>
			<td align="right"></td>
		</tr>
	</table>
		<div class="container-fluid margin-container">
			<!--基础活动设置-->
			<form action="/2020303110?token=${token}" method="post" id="addCouponForm" >
                <input type="hidden" value="${JumpType}" name="JumpType">

                <input type="hidden" name="pv1" id="pv1">
            <%--<input type="hidden" value="${data.puk}" name="puk">--%>
                <!--最外层包裹div，用于显示隐藏控制-->
				<div class="online-activity">
					<!--活动基础设置最外层包裹div，控制显示隐藏-->
					<div class="activity-set">
						<div class="row row-comm">
							<div class="col-xs-12">
								<div class="col-xs-2 flag-style">
									<span>编号</span>
								</div>
								<div class="col-xs-10">
									<input type="text"  class="form-control" value="${data.puk}" disabled="disabled"  />
                                    <input type="hidden"  class="form-control" value="${data.puk}"  name="activityNo"/>
                                </div>
							</div>
						</div>

						<!-- item-start：卷码类型 -->
						<div class="row row-comm">
							<div class="col-xs-12">
								<div class="col-xs-2 flag-style">
									<span>卷码类型 </span>
								</div>
								<div class="col-xs-10">
                                    <input type="text"  class="form-control" value="${data.activityTypeName}" disabled="disabled"  />
                                    <input type="hidden"  class="form-control" value="${data.activityTypeId}"  name="ticketType"/>
                                </div>
							</div>
						</div>
						<!-- item-end：卷码类型 -->
						<!-- item-start：活动名称 -->
						<div class="row row-comm">
							<div class="col-xs-12">
								<div class="col-xs-2 flag-style">
									<span>活动名称</span>
								</div>
								<div class="col-xs-10">
									<input type="text" class="form-control" disabled="disabled" value="${data.activityName}" />
									<input type="hidden" class="form-control"  value="${data.activityName}" name="activityName"/>
								</div>
							</div>
						</div>
						<!-- item-end：活动名称 -->
						
						<!-- item-start：活动分类 -->
						<div class="row row-comm">
							<div class="col-xs-12">
								<div class="col-xs-2 flag-style">
									<span>活动分类</span>
								</div>
								<div class="col-xs-10">
                                    <input type="hidden" class="form-control"  value="3000" name="activityType"/>
                                    <select class="form-control" id="activity-up-type"  disabled="disabled" >
										<option value="3000"  >优惠券码</option>
		                            </select>
								</div>
							</div>
						</div>
                        <%--已有券码列表--%>
                        <c:if test="${JumpType == 2}">
                            <div class="row row-comm">
                                <div class="col-xs-12">
                                    <div class="col-xs-2 flag-style">
                                        <span>已有券码</span>
                                    </div>
                                    <div class="col-xs-10" style="height: 300px">
                                        <iframe src="/2020303120?token=${token}&activityNo=${data.puk}&pageLimit=100" height="100%" width="100%"  frameborder="0" scrolling="auto"  name="BizFrame1" ></iframe>
                                    </div>
                                </div>
                            </div>
                        </c:if>
						<!-- item-end：活动分类 -->
                        <div class="row row-comm">
                            <table class="table table-bordered table-striped" style="margin-bottom: 0px;" id="dataTable">
                                <tr>
                                    <td>奖项</td>
                                    <td colspan="2">奖品</td>
                                    <td>数量</td>
                                    <td align="center"><button type="button" class="btn btn-default" onclick="addRow();">添加</button></td>
                                </tr>
                                <tr>
                                    <td>
                                        <select class="form-control">
                                            <option value="">请选择</option>
                                            <option value="0" >特等奖</option>
                                            <option value="1">一等奖</option>
                                            <option value="2">二等奖</option>
                                            <option value="3">三等奖</option>
                                            <option value="4">四等奖</option>
                                            <option value="5">优秀奖</option>
                                            <option value="6">安慰奖</option>
                                            <option value="7">金奖</option>
                                            <option value="8">银奖</option>
                                        </select>
                                    </td>
                                    <td style="width:300px">
                                        <input type="text" class="form-control"  style="width:300px"/>
                                    </td>
                                    <td style="width:300px">
                                        <select class="form-control">
                                            <option value=""></option>
                                            <option value="30;一个月全频道会员">一个月全频道会员</option>
                                        </select>
                                    </td>
                                    <td style="width:160px"><input type="text" class="form-control" /></td>
                                    <td style="width:160px"><button type="button" class="btn btn-default" onclick="removeRow(this);">删除</button></td>
                                </tr>
                            </table>
                        </div>
                        <input class="btn btn-primary btnright my-btn" value="下一步" id="next-page" type="button" onclick="save()" style="margin-left: 10px"/>
						<%--<input class="btn btn-primary btnright my-btn" value="下一步"  onclick="nextPage('${token}')" type="button" />--%>
					</div>
				</div>
			</form>
		</div>

<script type="text/javascript">
    <c:if test="${JumpType == 1}">
    parent.changeNavbar('添加卷码','首页》运营与统计》活动管理》添加活动》添加卷码');
    </c:if>
    <c:if test="${JumpType == 2}">
    parent.changeNavbar('修改卷码','首页》运营与统计》活动管理》修改活动》修改卷码');
    </c:if>
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //整理数据
    function save(){
        var content = getContent();
        if(content == ""){
            //校验不通过 ;
            return false;
        }
        $("#pv1").val(content);
        $('#addCouponForm').submit();
    }

    //获取填写的内容，并根据策略类型做参数校验
    function getContent(){
        var checekContent = [];
        $("#dataTable tr:gt(0)").each(function(i){
            var tds = $(this).children("td");
            var rewardLevel = $.trim($(tds[0]).children("select").val());
            var rewardName1 =  $.trim($(tds[1]).children("input").val());
            var rewardName2 = $.trim($(tds[2]).children("select").val());
            if(${JumpType==1}) {
                //根据类型判断必填参数是否已经填写
                if ((rewardName1 == "") && (rewardName2 == "")) {
                    //校验不通过
                    parent.showMessage(2, "请填写完整");
                    return "";
                }
            }
            var rewardName = rewardName1;
            if(rewardName1 == ""){
                rewardName = rewardName2;
            }else{
                rewardName = '0;' + rewardName1;
            }
            var rewardCount =  $.trim($(tds[3]).children("input").val());

            //console.log(rewardLevel);console.log(rewardName);console.log(rewardCount);

            //根据类型判断必填参数是否已经填写
            if((rewardLevel == "")||(rewardCount == "")){
                //校验不通过
                parent.showMessage(2,"请填写完整");
                return "";
            }
            //拼装内容
            insertContent(i,checekContent,rewardLevel,rewardName,rewardCount);
        });
        //console.log(JSON.stringify(checekContent));
        return JSON.stringify(checekContent);
    }

    //整理数据
    function insertContent(index,array,rewardLevel,rewardName,rewardCount){
        array[index]={"rewardLevel":""+rewardLevel+"","rewardName":""+rewardName+"","rewardCount":""+rewardCount+""}
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //删除行
    function removeRow(current){
        var length = $("#dataTable tr:gt(0)").length;
        //最少要保留一行
        if(length == 1){
            parent.showMessage(2,"请至少保留一行");
            return
        }
        var row = getRowObj(current);
        if(row != null){
            row.parentNode.removeChild(row);
        }
    }
    //得到行对象
    function getRowObj(obj){
        while(obj.tagName.toLowerCase() != "tr"){
            obj = obj.parentNode;
            if(obj.tagName.toLowerCase() == "table")return null;
        }
        return obj;
    }

    //添加行
    function addRow(){
        var rowStr = '<tr>'
            +'   <td>'
            +'        <select  class="form-control">'
            +'            <option value="">请选择</option>'
            +'            <option value="0" >特等奖</option>'
            +'            <option value="1">一等奖</option>'
            +'            <option value="2">二等奖</option>'
            +'            <option value="3">三等奖</option>'
            +'            <option value="4">四等奖</option>'
            +'            <option value="5">优秀奖</option>'
            +'            <option value="6">安慰奖</option>'
            +'            <option value="7">金奖</option>'
            +'            <option value="8">银奖</option>'
            +'        </select>'
            +'    </td>'
            +'   <td style="width:300px">'
            +'        <input type="text" class="form-control"/>'
            +'    </td>'
            +'    <td style="width:300px">'
            +'        <select class="form-control">'
            +'            <option value=""></option>'
            +'            <option value="30;一个月全频道会员">一个月全频道会员</option>'
            +'        </select>'
            +'    </td>'
            +'    <td style="width:160px"><input type="text" class="form-control" /></td>'
            +'	<td style="width:160px" align="center"><button type="button" class="btn btn-default" onclick="removeRow(this);">删除</button></td>'
            +'</tr>';
        $("#dataTable").append(rowStr);
    }

    //线上活动验证
    $("#addCouponForm").validate({
        //调试模式,验证成功了也不会发生跳转
//        debug: true,
        rules: {
            activityNumber: "required",
            activityTypeId: "required",
            activityName: "required",
            activityType: "required"
        },
        messages: {
            activityNumber: "不能为空,请重新输入!",
            activityTypeId: "不能为空,请重新输入!",
            activityName: "不能为空,请重新输入!",
            activityTitle: "不能为空,请重新输入!",
            activityStartTime: "不能为空,请重新输入!",
            activityEndTime: "不能为空,请重新输入!",
            activityType: "不能为空,请重新输入!"
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
