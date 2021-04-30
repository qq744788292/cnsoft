package org.cnsoft.tag.form;

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
public class TdInputTag extends AMyTagSupport {
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
			// 表格
			sb.append("<td");
			if (EmptyHelper.isNotEmpty(tdclazz)) {
				sb.append(" class=\"" + tdclazz + "\">");
			} else {
				sb.append(">");
			}
			// 输入框
			sb.append("<input id=\"" + name + "\"");
			if (EmptyHelper.isNotEmpty(style))
				sb.append(" style=\"" + style + "\"");
			if (EmptyHelper.isNotEmpty(tip))
				sb.append(" placeholder=\"" + tip + "\"");
			sb.append(" type=\"text\"");
			sb.append(" class=\"form-control\"");
			if (EmptyHelper.isNotEmpty(dis))
				sb.append(" disabled");
			if (EmptyHelper.isNotEmpty(read))
				sb.append(" readonly");
			sb.append(" name=\"" + name + "\"");
			sb.append(" value=\"" + value + "\" />");
			// 结尾
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
	private String name = EMPTY;
	private String value = EMPTY;
	private String style;
	private String tip = EMPTY;
	private String dis = EMPTY;
	private String read = EMPTY;
	
	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getTdclazz() {
		return tdclazz;
	}

	public void setTdclazz(String tdclazz) {
		this.tdclazz = tdclazz;
	}

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

	public String getDis() {
		return dis;
	}

	public void setDis(String dis) {
		this.dis = dis;
	}

}
