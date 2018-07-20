package org.zmsoft.jfp.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.utils.PKHelper;

/**
 * 分页标签菜单栏
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public class ArticleEditorTag extends TagSupport implements IFrameworkConstants {
	private static final long serialVersionUID = -5372434896340133375L;

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			if (id != null) {
				StringBuilder sb = new StringBuilder();
				// 制作显示控件
				sb.append("<textarea id=\"" + id + "\" name=\"" + name + "\" style=\"" + style + "\">");
				sb.append(value);
				sb.append("</textarea>");
				// 加载组件依赖
				sb.append("<script type=\"text/javascript\" src=\"/resources/wangEditor-2.1.20/js/wangEditor.min.js\"></script>");
				// 制作js代码控件
				sb.append("<script type=\"text/javascript\">");
				sb.append("function loadEditor(){");
				sb.append("var editor = new wangEditor('" + id + "');");
				// 上传图片服务地址
				sb.append("editor.config.uploadImgUrl = '99999012';");
				sb.append("editor.config.uploadImgFileName = 'upFile';");
				// // 配置自定义参数（举例）
				sb.append("editor.config.uploadParams = {");
				sb.append("token: '"+token+"',");
				sb.append("jobId: '"+PKHelper.creatPUKey()+"'");
				sb.append("};");
				sb.append("editor.config.uploadHeaders = {");
				sb.append("'Accept-Type' : 'multipart/form-data'");
				sb.append("};");
				// // 隐藏掉插入网络图片功能。该配置，只有在你正确配置了图片上传功能之后才可用。
				sb.append("editor.config.hideLinkImg = true;");
				sb.append("editor.config.pasteFilter = false;");
				sb.append("editor.create();");
				sb.append("}");
				// //加载文本编辑器
				sb.append("loadEditor();");
				sb.append("</script>");

				out.println(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	/////////////////////////////////////////////////控件属性//////////////////////////////////////////////

	private String id;
	private String name;
	private String token;
	private String value;
	private String style;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}
