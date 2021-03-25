package org.cnsoft.tag.page;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.cnsoft.framework.db.MyDataBaseObjectBean;
import org.cnsoft.framework.db.page.PageModel;
import org.cnsoft.framework.utils.EmptyHelper;
import org.cnsoft.tag.AMyTagSupport;

/**
 * 分页标签菜单栏
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public class TdTag extends AMyTagSupport {
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

			String orderby = null;
			if (page != null) {
				orderby = page.currentOrderby();
			}
			String ico = "";
			// 1.没有排序
			// 2.升序
			// 3.降序
			if (EmptyHelper.isEmpty(orderby)) {
				// orderby = "ORDER BY " + order + " ASC";
			} else {
				if (orderby.indexOf(order) >= 0) {
					if (orderby.indexOf("DESC") > 0) {
						order = "ORDER BY " + order + " ASC";
						ico = "&nbsp;<span class=\"glyphicon glyphicon-menu-up\"></span>";
					} else {
						order = "ORDER BY " + order + " DESC";
						ico = "&nbsp;<span class=\"glyphicon glyphicon-menu-down\"></span>";
					}
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("<td style=\"cursor: pointer; " + style + "\" onclick=\"" + target + "('" + order + "')\">");
			sb.append(value);// 添加展示内容
			sb.append(ico);
			sb.append("</td>");
			out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("<span>TdTag标签渲染出错</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	/////////////////////////////////////////////////////////////////////////////
	// 页面的超链接
	private String target;
	private String value;
	private String style;
	private String order;
	// 分页实体
	private PageModel<MyDataBaseObjectBean> page;

	public PageModel<MyDataBaseObjectBean> getPage() {
		return page;
	}

	public void setPage(PageModel<MyDataBaseObjectBean> page) {
		this.page = page;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
