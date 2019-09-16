﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/pagtag.tld" prefix="pagetag" %>

<!DOCTYPE html>
<html lang="en">
  <head>  
    <title>列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <!-- Bootstrap -->
    <link href="/resources/css/wm/zk/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/global.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/sticky-footer-navbar.css" rel="stylesheet">	
    
	<!-- Bootstrap js -->
	<script src="/resources/js/wm/zk/jquery.js"></script>
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
    <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
		<style type="text/css"> 
          th{
          	 text-align: center;
          }       
    </style> 
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">佣金管理</h4></td>
	</tr>
	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" border="0">
				<tr>
					<td width="2%"></td>
					<td align="left">
					<pagetag:pagtag  pageVo="${pageVO}"  url="/WMJG07/F.go" style="4"/>
					</td>
					<td align="right">${message}</td>
					<td width="30%" align="right">
						<input type="button" class="btn btn-primary btn-sm" value="检索"
					onclick="parent.toTargetForm('/WMJGY1L/H.go','/WMJG07/F.go')" >
					</td>
					<td width="2%"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table class="table table-hover">
                <tr>
                  	<th>流水号</th>
                  	<th>授权域名</th>
                  	<th>会员名称</th>
                  	<th>交易种类</th>
                  	<th>交易操作金额(元)</th>
                  	<th>佣金数额(元)</th>
                  	<th>结算状态</th>
                  	<th>操作时间</th>
                </tr>
		                <c:forEach var="wmbm02dbo" items="${pageVO.pageData}">	
		                <tr align="center">
		                  	  <td>${wmbm02dbo.puk}</td>
		                  	   <td>${wmbm02dbo.k02}</td>
			                  <td>${wmbm02dbo.f04}</td>
			                  <td>
			                  <c:choose>
		                  		<c:when test="${wmbm02dbo.k01==0}">
		                  			充值       			
		                  		</c:when>
		                  		<c:when test="${wmbm02dbo.k01==1}">
		                  			 提现      			
		                  		</c:when>
		                  		<c:when test="${wmbm02dbo.k02==1}">
		                  			 转账     			
		                  		</c:when>
		                  	 	</c:choose>	
			                  </td>
			                  <td>${wmbm02dbo.f01}</td>
			                  <td>${wmbm02dbo.f08}</td>
			                  <td>
			                    <c:choose>
		                  		<c:when test="${wmbm02dbo.f02==0}">
		                  			未结算       			
		                  		</c:when>
		                  		<c:when test="${wmbm02dbo.f02==1}">
		                  			 已结算      			
		                  		</c:when>
		                  	 	</c:choose>				                  
			                  </td>
			      
			                  <td>${wmbm02dbo.cc1}</td>
		                </tr>
	                </c:forEach>
            </table>
		</td>
	</tr>
  </table>
</body>
</html>