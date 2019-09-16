<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p" %>
<html>
<head>
    <title>role-permission</title><!-- 角色权限列表 -->
    <!-- 静态引入 -->
    <c:import url="/WEB-INF/views/config/include.jsp" charEncoding="UTF-8"/>
</head>
<body>
<table>
    <form method="post" action="/90202020?token=${token}" >
        <input type="hidden" name="token" value="${token}" />
        <input type="hidden" name="authId" id="authId" />
        <input type="hidden" name="authName" id="authName" />
        <input type="hidden" name="roleName" value="${role.roleName}" />
        <input type="hidden" name="roleId" value="${role.puk}" />
        <tr class="trparam">
            <td>
                <table>
                    <tr class="trparam">
                        <td class="tdblank30">&nbsp;</td>
                        <td class="tdparam">角色名称:${role.roleName}</td>
                        <td></td>
                        <td class="tdbtn">
                            <input type="submit" class="btn btn-danger btnright" value="保存" id="submit" />
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <table>
                    <thead><tr></tr><tr></tr><tr></tr></thead>
                    <tbody>
                    <tr style="text-align: center">
                        <td></td>
                        <td>
                            <span>
                                <label style="margin-right: 20px;">
                                    <input type="checkbox" id="select" value="0"/>全选
                                </label>
                            </span>
                            <span id="checkboxList">
                                    <label style="margin-right: 20px;"><input type="checkbox"  value="1"/>添加</label>
                                    <label style="margin-right: 20px;"><input type="checkbox"  value="2"/>删除</label>
                                    <label style="margin-right: 20px;"><input type="checkbox" value="3" />修改</label>
                                    <label style="margin-right: 20px;"><input type="checkbox" value="4"/>查询</label>
                                    <label style="margin-right: 20px;"><input type="checkbox"  value="5" />导入</label>
                                    <label style="margin-right: 20px;"><input type="checkbox"  value="6" />导出</label>
                            </span>
                        </td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </td>
        </tr>
    </form>
</table>
<script type="text/javascript">
    parent.changeNavbar('权限管理', '首页》权限管理》角色定义》角色操作权限');

    var data=${data};

    var authId=new Array();
    var authName=new Array();
    $("#submit").click(function () {
        $("#checkboxList :checkbox").each(function (index,item) {
            if(this.checked){
                authId[index]=$(this).val();
                authName[index]=$(this).parent().text();
            }
        });
        $("#authId").val(authId);
        $("#authName").val(authName);
    });


    //设置默认选中
    $.each(${data},function (index,item) {
        var index=(item.authId)-1;
        $("#checkboxList :checkbox").eq(index).prop("checked",true);
    });

    //全选或取消全选
    $("#select").on("change", function () {
        $('#checkboxList :checkbox').prop("checked",this.checked);
    });

    $(function(){
        var checkboxListLength=$("#checkboxList :checkbox").length;
        if(data.length==checkboxListLength){
            $('#select').prop("checked",true);//全选按钮
        }
        $("#checkboxList :checkbox").on("change",function(){
            var checkboxCheckedLength= $("#checkboxList :checked").length;
            if(checkboxListLength==checkboxCheckedLength){
                $('#select').prop("checked",true);//全选按钮
            }else{
                $('#select').prop("checked",false);
            }
        });
    });

</script>
</body>
</html>
