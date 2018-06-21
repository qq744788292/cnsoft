package org.zmsoft.jfp.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

/**
 * 分页标签菜单栏
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public class FileUploadTag extends TagSupport implements IFrameworkConstants {
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
			StringBuilder sb = new StringBuilder();
			// 请求链接
			sb.append("<iframe scrolling=\"no\"  src=\"/99999000?");
			sb.append("token=" + token);
			sb.append("&id=" + target);
			sb.append("&value=" + value);
			sb.append("&width=" + width);
			sb.append("&height=" + height);
			sb.append("\"");

			// 添加样式
			sb.append(" style=\"border: 0; width:" + width + "px; height:" + height + "px;\">");

			// 结束标识
			sb.append("</iframe>");

			out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	private String token;
	private String target;
	private int height = 150;
	private int width = 150;
	private String value;
	private String type = "1";

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
