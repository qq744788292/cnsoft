package org.cnsoft.tag.page;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.cnsoft.framework.utils.EmptyHelper;
import org.cnsoft.tag.AMyTagSupport;

/**
 * DIV分割栏
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class DivLineTag extends AMyTagSupport {
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
			sb.append("<div");
			if (EmptyHelper.isNotEmpty(clazz))
				sb.append(" class=\"" + clazz + "\"");

			if (EmptyHelper.isNotEmpty(height))
				sb.append(" style=\"height: " + height + "px\"");
			sb.append("></div>");
			out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("<span>DivLineTag标签渲染出错</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	/////////////////////////////////////////////////////////////////////////////
	private String height;

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	private String clazz;

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

}
