<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>

<table><tr>
<td class="tdblank220"><select class="form-control tdblank220" id="_provinces_" onchange="myChangeProvinces(this.value)">
    <option value="">--请选择--</option>
</select></td>
<td class="tdblank220"><select class="form-control tdblank220" id="_cities_" onchange="myChangeCities(this.value)">
    <option value="">--请选择--</option>
</select></td>
<td class="tdblank220"><select class="form-control tdblank220" id="_areas_">
        <option value="">--请选择--</option>
</select></td>
<td>&nbsp;</td>
</tr></table>
<script type="text/javascript">
//获得省市区数据
var myCityArray=new Array();
</script>
<script type="text/javascript" src="/resources/js/provinces-cities-areas.js"></script>
<script type="text/javascript">
//加载省
//myCityArray[0] = new Array("CHINA_PROVINCES","110000","北京市","1"");
function showMyProvinces() {
	//清空原有数据
	$("#_provinces_").empty();
    $("#_provinces_").append("<option value>请选择</option>");

	for(var i=0;i<myCityArray.length;i++){
		if(myCityArray[i] !=null && myCityArray[i][0] == "CHINA_PROVINCES"){
			 $("#_provinces_").append("<option value="+ myCityArray[i][1] + ">" + myCityArray[i][2] + "</option>");
		}
	}
}
//选择省
function myChangeProvinces(pid) {
	//清空原有数据
	$("#_cities_").empty();
    $("#_cities_").append("<option value>请选择</option>");
	$("#_areas_").empty();
    $("#_areas_").append("<option value>请选择</option>");

	for(var i=0;i<myCityArray.length;i++){
		if(myCityArray[i] !=null && myCityArray[i][0] == ("PROVINCES_"+pid)){
			$("#_cities_").append("<option value="+ myCityArray[i][1] + ">" + myCityArray[i][2] + "</option>");
		}
	}
    $("#_provinces_").val(pid);
}
//选择市
function myChangeCities(cid) {
	//清空原有数据
	$("#_areas_").empty();
    $("#_areas_").append("<option value>请选择</option>");

	for(var i=0;i<myCityArray.length;i++){
		if(myCityArray[i] !=null && myCityArray[i][0] == ("CITIES_"+cid)){
			$("#_areas_").append("<option value="+ myCityArray[i][1] + ">" + myCityArray[i][2] + "</option>");
		}
	}
	//清空原有数据
    $("#_cities_").val(cid);
}
//选择区
function myChangeAreas(cid) {
	//清空原有数据
    $("#_areas_").val(cid);
}
//初始化
function showMyArea(pid,cid,aid){
	myChangeProvinces(pid);
	myChangeCities(cid);
	myChangeAreas(aid);
}
//获取数据
//基于页面hidden
function getMyArea(pid,cid,aid){
    $("#"+pid).val($("#_provinces_").val());
    $("#"+cid).val($("#_cities_").val());
    $("#"+aid).val($("#_areas_").val());
}
showMyProvinces();
</script>