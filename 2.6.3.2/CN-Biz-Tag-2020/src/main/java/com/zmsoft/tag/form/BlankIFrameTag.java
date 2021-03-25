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
public class BlankIFrameTag extends AMyTagSupport {
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

			// <iframe src="/static/blank.html" style="width: 0px; height:
			// 0px;" id="blankForm" name="blankForm"></iframe>
			sb.append("<iframe");
			if (EmptyHelper.isNotEmpty(src)) {
				sb.append(" src=\"" + src + "\"");
			}
			if (EmptyHelper.isNotEmpty(style))
				sb.append(" style=\"" + style + "\"");
			else
				sb.append(" style=\"width: 0px; height: 0px\"");
			sb.append(" name=\"" + name + "\"");
			sb.append(" id=\"" + name + "\" />");
			// 结尾
			sb.append("</iframe>");
			out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("<span>BlankIFrameTag标签渲染出错</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	private String name;
	private String style;
	private String src;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

}
