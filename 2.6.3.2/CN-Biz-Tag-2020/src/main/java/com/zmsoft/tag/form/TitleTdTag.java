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
public class TitleTdTag extends AMyTagSupport {
	private static final long serialVersionUID = -214971391090462545L;

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

			// <td style="text-align: right;width: 120px;">企业ID</td>
			sb.append("<td");
			if (EmptyHelper.isNotEmpty(style))
				sb.append(" style=\"" + style + "\">");
			else
				sb.append(" style=\"text-align: right;width: " + width + "px;\">");
			sb.append(tip);
			sb.append("</td>");
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

	private String tip = EMPTY;
	private String width = "140";
	private String style;

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		if (EmptyHelper.isNotEmpty(width))
			this.width = width.replace("px", "");
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}
