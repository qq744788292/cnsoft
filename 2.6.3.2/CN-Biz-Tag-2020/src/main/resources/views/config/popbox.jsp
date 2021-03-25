<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p"%>
<%@ taglib uri="/WEB-INF/tag/pageform.tld" prefix="pf" %>
<%@ page trimDirectiveWhitespaces="true"%>

<div class="popbox" id="_popbox_" onclick="javascript:_display_popbox_(2)" style="display: none;">
  <div class="popbox_container" id="_popbox_container_">
    <table style="height: 100%">
      <tr>
        <td valign="middle" class="popboxleftbtn" onclick="javascript:_display_popbox_(2)">
        	<i class="icon ZM-Page-icon-font" style="font-size: 12px; color: #1b1e21;">&#xe61a;</i>
        </td>
        <td style="width: 5px;" ></td>
        <td id="myIframeTD" style="height: 100%;" valign="top" align="center">
        	<iframe src="/static/blank.html" allowtransparency="yes" id="_myBizFrame_" name="_myBizFrame_"></iframe>
        </td>
      </tr>
      <tr style="height: 2px;"></tr>
    </table>
  </div>
</div>
  <form method="post" action="" id="_popboxForm_" target="_myBizFrame_">
	<pf:token />
    <p:pageCurrent />
    <p:pageLimit />
  </form>
  
<script type="text/javascript" src="/custom/framework/js/popbox.js?20190927"></script>