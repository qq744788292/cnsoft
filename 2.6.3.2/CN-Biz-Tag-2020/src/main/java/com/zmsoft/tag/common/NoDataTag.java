package com.zmsoft.tag.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.zmsoft.framework.beans.db.MyDataBaseObjectBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.utils.EmptyHelper;

import com.zmsoft.tag.AMyTagSupport;

public class NoDataTag extends AMyTagSupport {

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

			if (EmptyHelper.isNotEmpty(page)) {
				items = page.getPageListData();
			}
			
			if (EmptyHelper.isEmpty(items)) {
				emptyFlag = true;
			} else if ((items instanceof List) && ((List<?>) items).isEmpty()) {
				emptyFlag = true;
			}

			StringBuilder sb = new StringBuilder();
			if (emptyFlag) {
				sb.append("<tr>");
				sb.append("<td colspan=\"").append(size).append("\" ").append("class=\"tablenodata\" ").append("style=\"text-align: center;padding: 0.75rem;\">");
				sb.append("没有数据！");
				sb.append("</td>");
				sb.append("</tr>");
				out.println(sb.toString());
			}else{
				sb.append("<!--数据展示-->");
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
	// 分页实体
	private PageModel<MyDataBaseObjectBean> page;

	public PageModel<MyDataBaseObjectBean> getPage() {
		return page;
	}

	public void setPage(PageModel<MyDataBaseObjectBean> page) {
		this.page = page;
	}

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
