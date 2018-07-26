package org.zmsoft.jfp.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

/**
 * 分页标签菜单栏
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public class NavbarTag extends TagSupport implements IFrameworkConstants {
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
				if (EmptyHelper.isEmpty(mode)) {//列表页面
					sb.append("parent.changeNavbar('" + title + "','" + navbar + "');");
				}else if (ONE.equals(mode)) {//添加模式
					sb.append("parent.changeNavbar('添加" + title + "','" + navbar + "');");
				}else if (TWO.equals(mode)) {//编辑模式
					sb.append("parent.changeNavbar('编辑" + title + "','" + navbar + "');");
				}else{
					
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
