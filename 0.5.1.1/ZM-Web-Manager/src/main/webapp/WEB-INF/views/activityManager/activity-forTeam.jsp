<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<%@ taglib uri="/WEB-INF/tag/fileuploadtag.tld" prefix="u"%>

<html>
<head>
    <title>activity-add-offline.jsp</title><!-- 添加团体活动/线下活动 -->
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
			<!--线下活动-->
			<form action="/80201122?token=${token}" method="post" id="offlineActivityForm">
                <input type="hidden" value="${data.puk}" name="puk">
                <input type="hidden" value="${JumpType}" name="JumpType">
                <div class="activity-down">
					<div class="activity-set">
						<div class="row row-comm">
							<div class="col-xs-6">
								<div class="col-xs-4 flag-style">
									<span>是否收费</span>
								</div>
								<div class="col-xs-8">
									<select class="form-control" name="isFree" id="activityPriceSelect">
										<option value="1">是</option>
		                            	<option value="2" selected>否</option>
		                            </select>
								</div>
							</div>
							<div class="col-xs-6" style="display: none;" id="activityPriceDiv">
								<div class="col-xs-4 flag-style">
									<span>价格</span>
								</div>
								<div class="col-xs-8" >
									<input type="text" class="form-control" name="activityPrice" value="${data.activityPrice}"/>
								</div>
							</div>
						</div>
						
						<div class="row row-comm">
							<div class="col-xs-6">
								<div class="col-xs-4 flag-style">
									<span>是否限定人数</span>
								</div>
								<div class="col-xs-8">
									<select class="form-control" name="isAstrict" id="mostPeopleSelect">
										<option value="1">是</option>
		                            	<option value="2" selected >否</option>
		                            </select>
								</div>
							</div>
							<div class="col-xs-6" style="display: none;" id="mostPeopleDiv">
								<div class="col-xs-4 flag-style">
									<span>人数</span>
								</div>
								<div class="col-xs-8">
									<input type="text" class="form-control" name="mostPeople" value="${data.mostPeople}"/>
								</div>
							</div>
						</div>
						
						<div class="row row-comm">
							<div class="col-xs-12">
								<div class="col-xs-2 flag-style">
									<span>活动地点</span>
								</div>
								<div class="col-xs-10">
									<input type="text" class="form-control" name="activityPlace" value="${data.activityPlace}"/>
								</div>
							</div>
						</div>
						
						<div class="row row-comm">
							<div class="col-xs-12">
								<div class="col-xs-2 flag-style">
									<span>主办方/主讲人</span>
								</div>
								<div class="col-xs-10">
									<input type="text" class="form-control" name="compere" value="${data.compere}"/>
								</div>
							</div>
						</div>
						
						<input class="btn btn-primary btnright my-btn" value="发布" type="button" onclick="save('${token}')"/>
						<input class="btn btn-primary btnright my-btn" value="下一步" type="button" onclick="nextPage('${token}')"/>
					</div>
				</div>
			</form>
		</div>

<script type="text/javascript">
    <c:if test="${JumpType == 1}">
        parent.changeNavbar('添加团体活动','首页》运营与统计》活动管理》添加活动》添加团体活动');
    </c:if>
    <c:if test="${JumpType == 2}">
        parent.changeNavbar('修改团体活动','首页》运营与统计》活动管理》修改活动》修改团体活动');
    </c:if>

    if(${data.isAstrict ==1}){
        $('#mostPeopleDiv').show();
        $('#mostPeopleSelect').val('${data.isAstrict}');
    }
    if(${data.isFree ==1}){
        $('#activityPriceDiv').show();
        $('#activityPriceSelect').val('${data.isFree}');
    }

    $("#mostPeopleSelect").change(function(){
        if($(this).val() == 1){
            $('#mostPeopleDiv').show();
        }else if($(this).val() == 2){
            $('#mostPeopleDiv').hide();
            $('#mostPeopleDiv :input').val("0");
        };
    });
    $("#activityPriceSelect").change(function(){
        if($(this).val() == 1){
            $('#activityPriceDiv').show();
        }else if($(this).val() == 2){
            $('#activityPriceDiv').hide();
            $('#activityPriceDiv :input').val("0");
        };
    });

  //点击保存
    function save(token){
    	//到分享页
    	offlineActivityForm.action = '/202030621?token='+token;
    	//调用验证
        if (! $("#offlineActivityForm").valid()) {
            return;
        }
            offlineActivityForm.submit();
    }
      //点击下一页
      function nextPage(token){
          //调用验证
          if (! $("#offlineActivityForm").valid()) {
              return;
          }
          offlineActivityForm.submit();
      }
      
    $("#offlineActivityForm").validate({
        //调试模式,验证成功了也不会发生跳转
//        debug: true,
        rules: {
            activityPlace: "required",
            compere: "required",
            activityPrice:{
                required:true,
                min:1
            },
            mostPeople: {
                required:true,
                min:1
            },
        },
        messages: {
            activityPlace: "不能为空,请重新输入!",
            compere: "不能为空,请重新输入!",
            activityPrice: {
                required:"不能为空,请重新输入!",
                min:"不能少于1!"
            },
            mostPeople: {
                required:"不能为空,请重新输入!",
                min:"不能少于1!"
            },
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
//            form.submit();
        }
    });
</script>

</body>
</html>
