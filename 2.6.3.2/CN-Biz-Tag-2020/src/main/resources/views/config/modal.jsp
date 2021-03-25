<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p"%>
<%@ taglib uri="/WEB-INF/tag/pageform.tld" prefix="pf" %>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- 这里是模态框（Modal）提示描述 -->
<div id="messagebox" style="position:absolute; width:600px; margin-left:-300px;left:50%; top:25px;display: none;">
	<div id="messagetyle" class="alert alert-success">
	  <strong id="messagetitle">成功!</strong><label id="messagelbl"></label>
	</div>
</div>

<!-- 这里是简单对话框框 -->
<div class="modal fade" id="deleteWarningModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">警告</h4>
            </div>
            <div class="modal-body">
                <h3 style="text-align: center; color: red" id="defaultMessage"></h3>
                <p  style="text-align: center; color: red;font-size: large" id="deleteWarningMessage"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light tdblank80" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary tdblank80"  id="deleteWarningModalDeleteButton">确认</button>
            </div>
        </div>
    </div>
</div>