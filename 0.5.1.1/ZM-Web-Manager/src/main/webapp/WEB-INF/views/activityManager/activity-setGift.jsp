<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<%@ taglib uri="/WEB-INF/tag/fileuploadtag.tld" prefix="u"%>

<html>
<head>
    <title>activity-addGift.jsp</title><!-- 添加签到礼包 -->
		<!-- 静态引入 -->
		<c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>

    <script type="text/javascript" src="/resources/ext/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="/resources/ext/jquery-validation/localization/messages_zh.js"></script>
	<link href="/resources/common/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
	<script src="/resources/common/js/moment-with-locales.min.js" type="text/javascript"></script>
	<script  src="/resources/common/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
		<style>
			.select-title{
				background: orange;
			}
			.dn{
				display: none;
			}
			.flag-style{
				background: #f5f5f5;
				margin-left: -15px;
				height: 40px;
				line-height: 40px;
				text-align: center;
			}
			.pas-flag{
				background: #f5f5f5;
				height: 40px;
				line-height: 40px;
				text-align: center;
			}
			.row-comm{
				margin-top: 10px;
			}
			.margin-left-10{
				margin-left: -10px;
			}

			.my-btn{
				float: right;
				margin-top: 15px;
			}
			.cums{
				height: 160px;
			}
			.cums tr td{
				width: 60px;
				border: 1px solid #aaa;
				text-align: center;
			}
			.prize{
				height: 40px;
				line-height: 40px;
			}
			.prize-set{
				margin: 10px 0 60px 0;
			}
			.margin-bottom-10{
				margin-bottom: 10px;
			}
			.bg-selected{
				background: orange;
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
			<form action="/202030612?token=${token}" method="post" id="onlineActivityForm" >
                <input type="hidden" value="${data.puk}" name="puk">
                <input type="hidden" value="${JumpType}" name="JumpType">
				<!--最外层包裹div，用于显示隐藏控制-->
				<div class="online-activity">
					<!--奖品与分享设置最外层包裹div-->
					<div class="prizeAndShare-set">
						<div class="row row-comm">
							<div class="col-xs-2 pas-flag">
								<span>第几次可拿礼包</span>
							</div>
							<div class="col-xs-10">
								<table class="cums">
									<tr>
										<td>1次</td>
										<td>2次</td>
										<td>3次</td>
										<td>4次</td>
										<td>5次</td>
										<td>6次</td>
										<td>7次</td>
										<td>8次</td>
										<td>9次</td>
										<td>10次</td>
									</tr>
									<tr>
										<td>11次</td>
										<td>12次</td>
										<td>13次</td>
										<td>14次</td>
										<td>15次</td>
										<td>16次</td>
										<td>17次</td>
										<td>18次</td>
										<td>19次</td>
										<td>20次</td>
									</tr>
									<tr>
										<td>21次</td>
										<td>22次</td>
										<td>23次</td>
										<td>24次</td>
										<td>25次</td>
										<td>26次</td>
										<td>27次</td>
										<td>28次</td>
										<td>29次</td>
										<td>30次</td>
									</tr>
								</table>
							</div>
						</div>
						
						<!--奖品设置，通过js控制-->
						<div class="row prize-set">
						
						</div>
						
						<input class="btn btn-primary btnright my-btn" value="发布" type="button" onclick="save('${token}')"/>
						<input class="btn btn-primary btnright my-btn" value="下一步" type="button" onclick="nextPage('${token}')"/>
					</div>
					<!--奖品与分享设置最外层包裹div-->
				</div>
			</form>
			<!--基础活动设置-->
		</div>

<script type="text/javascript">
    <c:if test="${JumpType == 1}">
        parent.changeNavbar('添加签到奖品','首页》运营与统计》活动管理》添加活动》添加签到奖品');
    </c:if>
    <c:if test="${JumpType == 2}">
        parent.changeNavbar('修改签到奖品','首页》运营与统计》活动管理》修改活动》修改签到奖品');
    </c:if>


    //    console.log(activityTypes);
if(${JumpType == 2}){
    <%--console.log('${activityPrizeList}');--%>
    var activityPrizeList = JSON.parse('${activityPrizeList}');
    $.each(activityPrizeList,function(){
        var num = this.number;
//        $('.cums tr td:contains('+num+'次)[innerHTML="1次"]').addClass("bg-selected no-more-check");
        $('.cums tr td:contains('+num+'次)').addClass("bg-selected no-more-check");

        $(".prize-set").append('<div class=current'+num+'>'+'<div class="col-xs-2 prize">第<span>'+num+'</span>次奖品设置</div>'
            +'<input type="hidden"  name="prizeNumber" value="'+num+'"/>'
            +'<div class="col-xs-2 flag-style">礼包类型</div>'
            +'<div class="col-xs-3">'
            +'<div class="btn-group margin-left-10">'
            //					+'<button type="button" class="btn btn-default height-40" id=select'+currentNum+'>全部</button>'
            //					+'<button type="button" class="btn btn-default dropdown-toggle height-40" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
            //					+'<span class="caret"></span>'
            //					+'</button>'
            //					+'<ul class="dropdown-menu" id=ul-select'+currentNum+'>'
            //					+'<li><a href="#">会员时长</a></li><li><a href="#">课程</a></li>'
            //					+'<li><a href="#">积分</a></li><li><a href="#">实体礼品</a></li><li><a href="#">优惠卷</a></li></ul>'
            +'<select name="prizeType" class="form-control" >'
            +'<option value="1000">会员时长</option>'
            +'</select>'
            +'</div>'
            +'</div>'
            +'<div class="col-xs-2 flag-style">赠送时长</div>'
            +'<div class="col-xs-3 margin-bottom-10"><input type="text" class="form-control" name="prize1000Time" value="'+this.prize+'"/></div>'
            +'</div>');

    });
}


    $('#activityStartTime').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: moment.locale('zh-cn')
    });
    $('#activityEndTime').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: moment.locale('zh-cn')
    });
	
    $('#activityDownStartTime').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: moment.locale('zh-cn')
    });
    $('#activityEndStartTime').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: moment.locale('zh-cn')
    });

	//活动设置和奖品与分享设置的页面js显示隐藏控制
	$("#activitySet").on("click",function(){
		$("#prizeAndShareSet").removeClass("select-title");
		$("#activitySet").addClass("select-title");
		$(".activity-set").removeClass("dn");
		$(".prizeAndShare-set").addClass("dn");
		$(".activity-title").removeClass("dn")
	});
	
	function prizeAndShare(){
		$("#prizeAndShareSet").on("click",function(){
			$("#prizeAndShareSet").addClass("select-title");
			$("#activitySet").removeClass("select-title");
			$(".activity-set").addClass("dn");
			$(".prizeAndShare-set").removeClass("dn");
			$(".activity-title").addClass("dn");
		});
	}
	prizeAndShare();
	
	//保存进入下一页
//	$("#next-page").on("click",function(){
//		$("#prizeAndShareSet").addClass("select-title");
//		$("#activitySet").removeClass("select-title");
//		$(".activity-set").addClass("dn");
//		$(".prizeAndShare-set").removeClass("dn");
//		$(".activity-title").addClass("dn");
//	});
	
	//table td点击添加奖品设置
	$(".cums tr td").on("click",function(){
		var num = $(this).text();
		var currentNum = num.replace("次","");
		if($(this).hasClass("bg-selected")){
			$(".current"+currentNum).remove();
			$(".current"+currentNum).addClass("dn");
			$(this).removeClass("bg-selected");
			$(this).removeClass("no-more-check");
		}else{
			$(".prize-set").append('<div class=current'+currentNum+'>'+'<div class="col-xs-2 prize">第<span>'+num+'</span>奖品设置</div>'
					+'<input type="hidden"  name="prizeNumber" value="'+currentNum+'"/>'
					+'<div class="col-xs-2 flag-style">礼包类型</div>'
					+'<div class="col-xs-3">'
					+'<div class="btn-group margin-left-10">'
//					+'<button type="button" class="btn btn-default height-40" id=select'+currentNum+'>全部</button>'
//					+'<button type="button" class="btn btn-default dropdown-toggle height-40" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
//					+'<span class="caret"></span>'
//					+'</button>'
//					+'<ul class="dropdown-menu" id=ul-select'+currentNum+'>'
//					+'<li><a href="#">会员时长</a></li><li><a href="#">课程</a></li>'
//					+'<li><a href="#">积分</a></li><li><a href="#">实体礼品</a></li><li><a href="#">优惠卷</a></li></ul>'
                    +'<select name="prizeType" class="form-control" >'
					+'<option value="1000">会员时长</option>'
					+'</select>'
					+'</div>'
					+'</div>'
					+'<div class="col-xs-2 flag-style">赠送时长</div>'
					+'<div class="col-xs-3 margin-bottom-10"><input type="text" class="form-control" name="prize1000Time"/></div>'
					+'</div>');
			$(this).addClass("no-more-check");
            $(this).addClass("bg-selected");

        }
		    $("#ul-select"+currentNum+" li").on("click",function(){
			$("#select"+currentNum).text($(this).text());
		});
	});
    //点击保存
  function save(token){
  	//到分享页
  	onlineActivityForm.action = '/202030611?token='+token;
  	onlineActivityForm.submit();
  }
    //点击下一页
    function nextPage(token){
      	onlineActivityForm.submit();
    }
</script>

</body>
</html>
