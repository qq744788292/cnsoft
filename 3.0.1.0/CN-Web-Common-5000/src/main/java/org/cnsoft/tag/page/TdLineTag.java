package org.cnsoft.tag.page;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.cnsoft.framework.utils.EmptyHelper;
import org.cnsoft.tag.AMyTagSupport;

/**
 * 分页标签菜单栏
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class TdLineTag extends AMyTagSupport {
	private static final long serialVersionUID = -7022081226075145681L;

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			StringBuilder sb = new StringBuilder();
			sb.append("<td");
			if (EmptyHelper.isNotEmpty(style)) {
				sb.append(" style=\"" + style + "\">");
			} else if (EmptyHelper.isNotEmpty(width)) {
				sb.append(" style=\"width: " + width + "px;\">");
			} else if (EmptyHelper.isNotEmpty(tdclazz)) {
				sb.append(" class=\"" + tdclazz + "\">");
			} else {
				sb.append(" class=\"pagewh\">");
			}
			sb.append("&nbsp;");
			sb.append("</td>");
			out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("<span>TdLineTag标签渲染出错</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	private String tdclazz;
	private String style;
	private String width = "10";

	public String getTdclazz() {
		return tdclazz;
	}

	public void setTdclazz(String tdclazz) {
		this.tdclazz = tdclazz;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		if (EmptyHelper.isNotEmpty(width))
			this.width = width.replace("px", "");
	}

}
