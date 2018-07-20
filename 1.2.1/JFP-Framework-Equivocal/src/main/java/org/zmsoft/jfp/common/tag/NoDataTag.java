package org.zmsoft.jfp.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.constants.ITagConstants;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

public class NoDataTag extends TagSupport implements IFrameworkConstants, ITagConstants {

	private static final long serialVersionUID = -2513942037163321743L;

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = null;
		out = this.pageContext.getOut();
		try {
			boolean emptyFlag = false;
			if (EmptyHelper.isEmpty(items)) {
				emptyFlag = true;
			} else if ((items instanceof List) && ((List<?>) items).isEmpty()) {
				emptyFlag = true;
			}

			if (emptyFlag) {
				StringBuilder sb = new StringBuilder();
				sb.append("<tr>");
				sb.append("<td colspan=\"").append(size).append("\" style=\"background: gainsboro;text-align: center\">");
				sb.append("没有数据！");
				sb.append("</td>");
				sb.append("</tr>");
				out.println(sb.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Tag.EVAL_BODY_INCLUDE;
	}

	////////////////////////////////////////////////////////////////////////////
	private Object items;
	private int size;

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
