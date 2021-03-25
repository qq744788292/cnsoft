package org.cnsoft.tag.page;

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
public class PageLimit extends AMyTagSupport {
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
			// <input type="hidden" name="pageLimit" id="pageLimit" value="10">
			sb.append("<input type=\"hidden\" name=\"pageLimit\" id=\"pageLimit\" value=\"" + getValue() + "\">");

			out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("<span>PageLimit标签渲染出错</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	/////////////////////////////////////////////////////////////////////////////

	private String value = EMPTY;

	public String getValue() {
		if (EmptyHelper.isEmpty(value)) {// 没有提供初始化的场合
			// SystemConfigCacheService_ =
			// MyBeanFactoryHelper.getBean("SystemConfigCacheService");
			// SystemConfigDBO config = SystemConfigCacheService_.loadCacheData(PAGE_LIMIT);
			// if (EmptyHelper.isEmpty(value)) {// 没有提供初始化的场合
			return TWELVE;
			// } else {
			// return config.getValue();
			// }
		}

		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
