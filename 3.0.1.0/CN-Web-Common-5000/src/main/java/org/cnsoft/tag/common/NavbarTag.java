package org.cnsoft.tag.common;

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
 *
 */
public class NavbarTag extends AMyTagSupport {
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
			if (out != null) {
				StringBuilder sb = new StringBuilder();
				// 制作js代码控件
				sb.append("<script type=\"text/javascript\">");
				// 更新导航条
				if (EmptyHelper.isNotEmpty(auto)) {
					if (EmptyHelper.isEmpty(mode)) {// 列表页面
						sb.append("changeNavbar('" + title + "列表','" + navbar + "');");
					} else if (ONE.equals(mode)) {// 添加模式
						sb.append("changeNavbar('添加" + title + "','" + navbar + "');");
					} else if (TWO.equals(mode)) {// 编辑模式
						sb.append("changeNavbar('编辑" + title + "','" + navbar + "');");
					} else {// 默认模式
						sb.append("changeNavbar('" + title + "','" + navbar + "');");
					}
				} else {// 默认模式
					sb.append("changeNavbar('" + title + "','" + navbar + "');");
				}
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

	private String title;
	private String navbar;
	private String mode;
	private String auto;

	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNavbar() {
		return navbar;
	}

	public void setNavbar(String navbar) {
		this.navbar = navbar;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
