package org.cnsoft.tag.common;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.cnsoft.framework.beans.common.RESTResultBean;
import org.cnsoft.tag.AMyTagSupport;

/**
 * 分页标签菜单栏
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public class MessageTag extends AMyTagSupport {
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
			if (out != null && data != null) {
				StringBuilder sb = new StringBuilder();
				// 制作js代码控件
				sb.append("<script type=\"text/javascript\">");
				// showMessage('${message.code}', '${message.msg}');
				sb.append("showMessage('" + data.getCode() + "','" + data.getMsg().replaceAll("'", " ") + "');");
				sb.append("</script>");
				out.println(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	///////////////////////////////////////////////// 控件属性//////////////////////////////////////////////

	private RESTResultBean<String> data;

	public void setData(RESTResultBean<String> data) {
		this.data = data;
	}
}
