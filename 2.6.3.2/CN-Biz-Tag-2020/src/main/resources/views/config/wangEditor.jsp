<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/pagetag.tld" prefix="p"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- 加载组件依赖引入 -->
<link rel="stylesheet" type="text/css" href="/resources/ext/wangEditor-2.1.20/css/wangEditor.min.css">
<script type="text/javascript" src="/resources/ext/wangEditor-2.1.20/js/wangEditor.min.js"></script>
<!-- 富文本编辑器引入 -->
<script type="text/javascript">
	function loadEditor(_id_, _token_, _jobId_) {
		var editor = new wangEditor(_id_);
		// 颜色
		editor.config.colors = {
			'#000000' : '黑色',
			'#696969 ' : '标准灰',
			'#ff8c00' : 'logo橙'
		};
		// 字体
		editor.config.familys = [ '宋体', '黑体', '楷体', '微软雅黑', 'Arial', 'Verdana', 'Georgia' ];
		// 上传图片服务地址
		editor.config.uploadImgUrl = '/99999012';
		editor.config.uploadImgFileName = 'upFile';
		// 配置自定义参数（举例）
		editor.config.uploadParams = {
			token : _token_,
			jobId : _jobId_
		};
		editor.config.uploadHeaders = {
			'Accept-Type' : 'multipart/form-data'
		};
		// 隐藏掉插入网络图片功能。该配置，只有在你正确配置了图片上传功能之后才可用。
		editor.config.hideLinkImg = true;
		editor.config.pasteFilter = true;
		//editor.config.pasteText = true;
		editor.create();
	}
</script>
