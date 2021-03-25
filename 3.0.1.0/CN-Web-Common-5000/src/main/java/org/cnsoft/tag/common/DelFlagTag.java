package org.cnsoft.tag.common;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.cnsoft.tag.AMyTagSupport;

public class DelFlagTag extends AMyTagSupport {
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
			if (ONE.equals(data))
				out.println("已删除");
			else
				out.println("有效");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.EVAL_BODY_INCLUDE;
	}
	///////////////////////////////////////////////// 控件属性//////////////////////////////////////////////

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
