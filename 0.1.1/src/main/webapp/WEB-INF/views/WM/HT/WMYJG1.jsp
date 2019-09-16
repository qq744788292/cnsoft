<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/lib/pagtag.tld" prefix="pagtag" %>
  <%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
 <head>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script  src="/resources/js/My97DatePicker/WdatePicker.js"></script>

 </head>
 <body>
 
 
 <sf:form method="get"  modelAttribute="param" action="F.go">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr>
         <td align="left" valign="top"><div class="tab-main dark-gray">
                   <h3 class="tab-main-title"><span style="font-size:14px; font-weight:bold;">佣金明细</span></h3>
                 </div></td>
       </tr>
       <tr>
         <td align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr>
             <td align="left" valign="top"><table width="800" border="0" cellpadding="0" cellspacing="0">
               <tr>
                 <td>&nbsp;</td>
                 <td>&nbsp;</td>
                 <td>&nbsp;</td>
                 <td>&nbsp;</td>
               </tr>
               <tr>
                 
                 <td width="192">&nbsp;</td>
                 <td width="202">&nbsp;</td>
                 <td width="113">&nbsp;</td>
                 <td width="328">&nbsp;</td>
               </tr>
               <tr>
                <td width="192" height="40" align="right"><strong>订单号： </strong></td>
                 <td align="left"><input type="text" name="puk" value="${param.puk }"></td>
                 <td width="192" height="40" align="right"><strong>用户名： </strong></td>
                 <td align="left"><input type="text" name="f01" value="${param.f01 }"></td>
                
               </tr>
               <tr>
                 <td width="192" height="40" align="right"><strong>会员名称：</strong>
                   <table  width="100%" border="0" cellpadding="0" cellspacing="0">
                     <tr>
                       <td></td>
                    </table></td>
                 <td align="left"><input type="text" name="f02" value="${param.f02 }"></td>   
                  <td align="right"><strong>结算状态：</strong></td>
                 <td align="left"><sf:select path="fb1">
                   <sf:option value="">请选择</sf:option>
                   <sf:option value="0">未结算</sf:option>
                   <sf:option value="1">已结算</sf:option>
                 </sf:select></td>
                              
               </tr>
               
                <tr>
               <td align="right"><strong>交易种类：</strong></td>
                 <td align="left"><sf:select path="bbb">
                   <sf:option value="">请选择</sf:option>
                   <sf:option value="0">充值</sf:option>
                   <sf:option value="1">提现</sf:option>
                     <sf:option value="2">转账</sf:option>
                 </sf:select></td>
                
               </tr>
               
               <tr>
                 <td width="192" height="40" align="right"><strong>起始时间：</strong>： </td>
                 <td align="left"><input type="text"  class="form-control input-sm" id="f04" name="f04" value="${param.f04 }" size="" style="width: 200px;" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" onClick="WdatePicker()" ></td>
                 <td align="right"><strong>结束时间： </strong></td>
                 <td align="left"><input type="text"  class="form-control input-sm" id="f06" name="f06" value="${param.f04 }" size="" style="width: 200px;" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" onClick="WdatePicker()" ></td>
               </tr>
               <tr>
                 <td height="40" colspan="4" align="right" ><div style="padding:0 90px 0 0; "><input type="submit" value="查询"></div></td>
                </tr>

      
       
             </table></td>
           </tr>
           <tr>
             <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                 <td align="left" style="padding:20px;"><pagtag:pagtag pageVo="${pageVO}" url="F.go" /></td>
                </tr>
             </table></td>
           </tr>
         </table></td>
       </tr>
 
 
 
 
 
 
 
 
<tr>
    <td align="left" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="table table-hover">
                            <tr>
                          
                              <th align="center" valign="middle">订单号</th>
                              <th align="center" valign="middle">会员名称</th>
                              <th align="center" valign="middle">用户名</th>
							 
                              <th align="center" valign="middle">交易金额</th>
                              <th align="center" valign="middle">佣金金额</th>
                               <th align="center" valign="middle">结算状态</th>
                                <th align="center" valign="middle">交易种类</th>
                               <th align="center" valign="middle">日期</th>
                               
        					
                            </tr>
		
	                    <c:forEach var="zftd" items="${pageVO.pageData}">
                            <tr >
                         
                             <td align="center" valign="middle">${zftd.puk}</td>
                              <td align="center" valign="middle">${zftd.eb2}</td>
                              <td align="center" valign="middle">${zftd.eb1}</td>
							  <td align="center" valign="middle">${zftd.f01}</td>
							  
							   <td align="center" valign="middle">${zftd.f08}</td>
							  <td align="center" valign="middle">
							  <c:if test="${zftd.f02=='0'}">
							          未结算
							  </c:if>
							   <c:if test="${zftd.f02=='1'}">
							        已结算
							   </c:if>
							 </td>
							 <td align="center" valign="middle">
							  <c:if test="${zftd.bbb=='0'}">
							         充值
							  </c:if>
							   <c:if test="${zftd.bbb=='1'}">
							        提现
							   </c:if>
							    <c:if test="${zftd.bbb=='2'}">
							        转账
							   </c:if>
							 </td>

							   <td align="center" valign="middle">${zftd.eb4}</td>
							  							
							 
							  </tr>
                           </c:forEach>
     </table></td>
</tr> 




</table>
</sf:form>
 </body>
</html>