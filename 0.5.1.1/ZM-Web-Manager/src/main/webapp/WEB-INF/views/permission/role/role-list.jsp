<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>role-list</title><!-- 角色列表 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
    <c:import url="/WEB-INF/views/config/jquery-validation.jsp" charEncoding="UTF-8"/>
</head>
<body class="body">
<form method="post" action="/90201020?token=${token}" id="addForm" >
    <input type="hidden" name="authId" id="authId" />
    <input type="hidden" name="authName" id="authName" />
    <table>
        <tr>
            <td>
                <table>
                    <tr class="trparam">
                        <td class="tdparam">角色ID:</td>
                        <td>
                            <input  id="puk" type="text" class="form-control" name="puk" />
                        </td>
                        <td class="tdblank120">&nbsp;</td>
                        <td class="tdparam">角色名称:</td>
                        <td>
                            <input  id="roleName" type="text" class="form-control" name="roleName" />
                        </td>
                        <td class="tdblank120">&nbsp;</td>
                        <td class="tdbtn">
                            <input type="submit" class="btn btn-primary btnright" value="添加角色" id="submitButton" />
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td >
                <table>
                    <tr class="trparam">
                        <td class="tdparam">权限标识:</td>
                        <td>
                            <span>
                                <label>
                                    <input type="checkbox" id="select" value="0"/>全选
                                </label>
                            </span>
                            <span id="checkboxList">
                                <label>
                                    <input type="checkbox"  value="1"/>添加
                                </label>
                                <label>
                                    <input type="checkbox"  value="2"/>删除
                                </label>
                                <label>
                                    <input type="checkbox"  value="3" />修改
                                </label>
                                <label>
                                    <input type="checkbox"  value="4"/>查询
                                </label>
                                <label>
                                    <input type="checkbox"  value="5"/>导入
                                </label>
                                 <label>
                                    <input type="checkbox"  value="6"/>导出
                                </label>
                            </span>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<br>
<table>
    <form method="post" action="/90201010?token=${token}" id="searchForm">
        <input type="hidden" name="pageCurrent" id="pageCurrent" value="1" />
        <input type="hidden" name="pageLimit" id="pageLimit" value="12" />
        <tr class="trparam">
            <td>
                <table>
                    <tr>
                        <td></td>
                        <td class="tdparam">角色名称:</td>
                        <td style="width: 200px">
                            <input type="text" class="form-control" name="roleName" placeholder="请输入角色名称"
                                   value="${searchCondition.roleName}" />
                        </td>
                        <td class="tdbtn">
                            <input type="submit" class="btn btn-primary btnright" value="搜索" />
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
                <tr>
                    <th>角色ID</th>
                    <th>角色名称</th>
                    <th>权限设置</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${page.pageListData}">
                    <tr>
                        <td>${item.puk}</td>
                        <td>${item.roleName}</td>
                        <td>
                            <span id="checkboxList1">
                                <c:if test="${not empty item.fb1}"><input type="checkbox" disabled="disabled"  checked="checked" />
                                <span style="font-weight: bolder;color: #1b1b1b">添加</span></c:if>
                                <input type="checkbox" disabled="disabled" <c:if test="${empty item.fb1}"> />
                                <span style="color: #9c9c9c">添加</span></c:if>

                                <c:if test="${not empty item.fb2}"><input type="checkbox" disabled="disabled"  checked="checked" />
                                <span style="font-weight: bolder;color: #1b1b1b">删除</span></c:if>
                                <input type="checkbox" disabled="disabled" <c:if test="${empty item.fb2}"> />
                                <span style="color: #9c9c9c">删除</span></c:if>

                                <c:if test="${not empty item.fb3}"><input type="checkbox" disabled="disabled"  checked="checked" />
                                <span style="font-weight: bolder;color: #1b1b1b">修改</span></c:if>
                                <input type="checkbox" disabled="disabled" <c:if test="${empty item.fb3}"> />
                                <span style="color: #9c9c9c">修改</span></c:if>

                                <c:if test="${not empty item.fb4}"><input type="checkbox" disabled="disabled"  checked="checked" />
                                <span style="font-weight: bolder;color: #1b1b1b">查询</span></c:if>
                                <input type="checkbox" disabled="disabled" <c:if test="${empty item.fb4}"> />
                                <span style="color: #9c9c9c">查询</span></c:if>

                                <c:if test="${not empty item.fb5}"><input type="checkbox" disabled="disabled"  checked="checked" />
                                <span style="font-weight: bolder;color: #1b1b1b">导入</span></c:if>
                                <input type="checkbox" disabled="disabled" <c:if test="${empty item.fb5}"> />
                                <span style="color: #9c9c9c">导入</span></c:if>

                                <c:if test="${not empty item.eb1}"><input type="checkbox" disabled="disabled" checked="checked" />
                                <span style="font-weight: bolder;color: #1b1b1b">导出</span></c:if>
                                <c:if test="${empty item.eb1}"><input type="checkbox" disabled="disabled"  />
                                <span style="color: #9c9c9c">导出</span></c:if>
                            </span>
                        </td>
                        <td style="width: 330px;">
                            <input type="button" class="btn btn-default"
                                   onclick="parent.showPageForm('90302010?puk=${item.puk}')" value="关联用户" />
                            <input type="button" class="btn btn-success"
                                   onclick="parent.showPageForm('90202010?puk=${item.puk}')" value="修改权限" />
                            <input type="button" class="btn btn-info"
                                   onclick="parent.showPageForm('90201031?puk=${item.puk}')" value="编辑" />
                            <input type="button" class="btn btn-danger"
                                   onclick="parent.showPageForm('90201040?puk=${item.puk}&updateTime=${item.updateTime}')"
                                   value="删除" />
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <%--分页信息--%>
    <tr>
        <td><p:page page="${page}" url="90201010"/></td>
    </tr>

</table>
<script type="text/javascript">
    parent.changeNavbar('权限管理', '首页》权限管理》角色定义》角色列表');

    if(!${message.code==0}){
    parent.showMessage("${message.code}","${message.msg}");
    }

    //全选或取消全选
    $("#select").on("change", function () {
        $('#checkboxList :checkbox').prop("checked",this.checked);
    });

    $(function(){
        var checkboxListLength=$("#checkboxList :checkbox").length;
        $("#checkboxList :checkbox").on("change",function(){
            var checkboxCheckedLength= $("#checkboxList :checked").length;
            if(checkboxListLength==checkboxCheckedLength){
                $('#select').prop("checked",true);//全选按钮
            }else{
                $('#select').prop("checked",false);
            }
        });
    });

    $("#submitButton").click(function () {
        var authId=new Array();
        var authName=new Array();
        $("#checkboxList :checkbox").each(function (index,item) {
            if(this.checked){
                authId[index]=$(this).val();
                authName[index]=$(this).parent().text();
            }
        });
        $("#authId").val(authId);
        $("#authName").val(authName);
    });

   $("#addForm").validate({
       //调试模式,验证成功了也不会发生跳转
       //debug: true,
       rules :{
           roleName:{
               required:true,
               remote:{
                   type:"POST",
                   url:"/90201050",
                   data:{
                       roleName:function (){return $("#roleName").val();},
                       token: "${token}"
                   }
               }
           }
       },
       messages :{
           roleName:{
               required:"不能为空,请重新输入!",
               remote:"已存在,请重新输入!"
           }
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

//    //from提交前执行函数
//    function beforeSubmitDo(){
//        if($("#roleName").val()=="" ||$("#roleName").val()==null){
//            parent.showMessage("3","角色名不能为空");
//            $("#roleName").attr("style","border-color: red");
//            return false;
//        }
//    };

//    $("#submit").click(function () {
//        var authId=new Array();
//        var authName=new Array();
//        $("#checkboxList :checkbox").each(function (index,item) {
//            if(this.checked){
//                authId[index]=$(this).val();
//                authName[index]=$(this).parent().text();
//            }
//        });
//        $("#authId").val(authId);
//        $("#authName").val(authName);
//    });

</script>

</body>
</html>
