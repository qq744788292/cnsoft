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
				out.println(sb.toString());
				
				sb = new StringBuilder();
				// 加载文本编辑器
				sb.append("<script type=\"text/javascript\">");
				sb.append("loadEditor('");
				sb.append(id);
				sb.append("','");
				sb.append(token);
				sb.append("','");
				sb.append(PKHelper.creatPUKey());
				sb.append("');");
				sb.append("</script>");
				out.print(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	///////////////////////////////////////////////// 控件属性//////////////////////////////////////////////

	private String name;
	private String token;
	private String value;
	private String style;

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
