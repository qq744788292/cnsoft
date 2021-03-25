package com.zmsoft.tag.page;

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
 * 
 */
public class SecurityCodeTag extends AMyTagSupport {
	private static final long serialVersionUID = -5037377433501297479L;

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			if (EmptyHelper.isNotEmpty(token)) {
				StringBuilder sb = new StringBuilder();

				sb.append("<table align=\"center\" border=\"0\" width=\"100%\" >");
				sb.append("<tr>");
				if (EmptyHelper.isEmpty(id)) {
					id = " ";
				} else {
					id = " id=\"" + id + "\" ";
				}
				sb.append("<td><input type=\"text\" class=\"form-control\" name=\"" + name + "\" " + id + "placeholder=\"安全码\" /></td>");
				sb.append("<td width=\"" + size + "%\" >&nbsp;</td>");
				sb.append("<td><img src=\"/99993030?token=" + token + "\" width=\"65\" height=\"22\" onclick=\"this.src='/99993030?token=" + token + "&d='+Math.random();\"/></td>");
				sb.append("</tr>");
				sb.append("</table>");

				out.println(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("<span>SecurityCodeTag标签渲染出错</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	private String name;
	private int size = 5;
	private String token;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
