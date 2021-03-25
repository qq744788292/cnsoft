package com.zmsoft.tag.common;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import com.zmsoft.tag.AMyTagSupport;

public class ModeTag extends AMyTagSupport {
	private static final long serialVersionUID = -3685182704454090517L;

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = null;
		out = this.pageContext.getOut();
		try {
			out.println("<input type=\"hidden\" name=\"mode\" id=\"mode\" value=\"" + data + "\">");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.EVAL_BODY_INCLUDE;
	}

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
