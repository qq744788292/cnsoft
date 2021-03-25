package com.zmsoft.tag.form;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.zmsoft.framework.utils.EmptyHelper;

import com.zmsoft.tag.AMyTagSupport;

/**
 * 分页标签菜单栏
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class InputTag extends AMyTagSupport {
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

			// <input id="roleName" type="text" class="form-control" name="roleName" value="${data.roleName}" />
			sb.append("<input id=\"" + name + "\"");
			if (EmptyHelper.isNotEmpty(style))
				sb.append(" style=\"" + style + "\"");
			if (EmptyHelper.isNotEmpty(tip))
				sb.append(" placeholder=\"" + tip + "\"");
			sb.append(" type=\"text\"");
			sb.append(" class=\"form-control\"");
			sb.append(" name=\"" + name + "\"");
			sb.append(" value=\"" + value + "\" />");
			out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("<span>InputTag标签渲染出错</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	private String name = EMPTY;
	private String value = EMPTY;
	private String style;
	private String tip = EMPTY;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}


}
