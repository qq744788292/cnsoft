package com.zmsoft.tag.page;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import com.zmsoft.tag.AMyTagSupport;

/**
 * 分页标签菜单栏
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class MustCurrent extends AMyTagSupport {
	private static final long serialVersionUID = -1687817864059391002L;

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
			// <i class="icon ZM-Page-icon-font" style="color:red;margin-left: 10px">&#xe645;</i>
			sb.append("<i class=\"icon ZM-Page-icon-font\" style=\"color:red;margin-left: 10px\">&#xe645;</i>");

			out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("<span>PageCurrent标签渲染出错</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	/////////////////////////////////////////////////////////////////////////////
	private String value = ONE;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
