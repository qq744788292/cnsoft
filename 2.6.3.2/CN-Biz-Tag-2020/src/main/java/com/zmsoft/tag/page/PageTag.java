package com.zmsoft.tag.page;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.zmsoft.framework.beans.db.MyDataBaseObjectBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.beans.page.PageModelHelper;
import org.zmsoft.framework.utils.StringUtil;

import com.zmsoft.tag.AMyTagSupport;

/**
 * 分页标签菜单栏
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public class PageTag extends AMyTagSupport {

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		return doStartTagByTable();
	}

	
	/**
	 * 利用表格属性显示分页工具栏
	 * 
	 * @return
	 * @throws JspException
	 */
	public int doStartTagByTable() throws JspException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			// 开始处理分页组件
			if (page != null) {
				int currentPage = page.getPageCurrent(); // 当前页
				if (currentPage == 0) {
					currentPage = 1;
				}
				int totalcount = page.getResultCount(); // 总条数
				int totalPage = page.getPageCount(); // 总页数

				StringBuilder paramStringBuilder = PageModelHelper.preparePageModelFormParamBean(((HttpServletRequest) pageContext.getRequest()), page, url);

				paramStringBuilder.append("&pageCurrent=");

				String paramStr = paramStringBuilder.toString();
				StringBuilder sb = new StringBuilder();
				sb.append("\r\n");
				sb.append("<div class=\"page_div\">");
				sb.append("<table class=\"page_table\">");
				sb.append("<tr class=\"pageht\">");
				///////////////////////////// 左侧统计///////////////////////////////////////////
				sb.append("<td align=\"left\">");
				sb.append("<table>");
				sb.append("<tr class=\"pageht\">");
				if (show.charAt(0) == '0')
					sb.append("<td style=\"width:" + (("" + totalcount).length() * 14 + 66) + "px;\">总" + totalcount + "条记录</td>");
				if (show.charAt(1) == '0')
					sb.append("<td style=\"width:" + (("" + totalPage).length() * 14 + 38) + "px;\">共" + totalPage + "页</td>");
				if (show.charAt(2) == '0')
					sb.append("<td style=\"width:" + (("" + page.getPageLimit()).length() * 12 + 52) + "px\">每页" + page.getPageLimit() + "条</td>");
				sb.append("<td></td></tr>");
				sb.append("</table>");
				sb.append("</td>");
				//////////////////////////////////// 右侧分页//////////////////////////////////////
				sb.append("<td align=\"right\">");
				sb.append("<table>");
				sb.append("<tr class=\"pageht\"><td></td>");

				// 页面总数在1
				if (totalPage == 1) {
					sb.append("<td class=\"page_item page_item_select\">1</td>");
				}
				// 页面总数在5以内
				else if (totalPage > 1 && totalPage <= 5) {
					for (int i = 1; i <= totalPage; i++) {
						if (i == currentPage) {
							sb.append("<td class=\"page_item page_item_select\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\">").append(i).append("</td>");
						} else {
							sb.append("<td class=\"page_item\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\">").append(i).append("</td>");
						}
					}
				}
				// 页面超过5页，让其保持剧中 类似于 // 23 4 56
				else if (totalPage > 5) {
					if (currentPage >= 3) {
						// 总页数
						sb.append("<td class=\"page_item pagesize2\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append("1").append("')\" >").append("首页").append("</td>");
						sb.append("<td class=\"page_item pagesize2\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage - 1).append("')\" >").append("上一页").append("</td>");
						int gap = totalPage - currentPage; // 当前页与尾页的距离
						if (gap >= 2) { // 23 4 56
							for (int i = currentPage - 2; i < currentPage; i++) {
								sb.append("<td class=\"page_item\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\">").append(i).append("</td>");
							}
							sb.append("<td class=\"page_item page_item_select\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage).append("')\">").append(currentPage).append("</td>");
							for (int i = currentPage + 1; i <= currentPage + 2; i++) {
								sb.append("<td class=\"page_item\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\">").append(i).append("</td>");
							}
						} else if (gap == 0) { // 3456 7
							for (int i = currentPage - 4; i < currentPage; i++) {
								sb.append("<td class=\"page_item\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\">").append(i).append("</td>");
							}
							sb.append("<td class=\"page_item page_item_select\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage).append("')\">").append(currentPage).append("</td>");
						} else if (gap == 1) { // 345 6 7
							for (int i = currentPage - 3; i < currentPage; i++) {
								sb.append("<td class=\"page_item\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\">").append(i).append("</td>");
							}

							sb.append("<td class=\"page_item page_item_select\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage).append("')\">").append(currentPage).append("</td>");
							sb.append("<td class=\"page_item\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage + 1).append("')\">").append(currentPage + 1).append("</td>");
						}

						if (currentPage != totalPage) {
							sb.append("<td class=\"page_item pagesize3\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage + 1).append("')\" >").append("下一页").append("</td>");
						}
						sb.append("<td class=\"page_item pagesize2\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(totalPage).append("')\" >").append("尾页").append("</td>");
					}
					// 总页数>5 但当前页在第1或者2页
					else if (currentPage < 3) {
						for (int i = 1; i <= 5; i++) {
							if (i == currentPage) {
								sb.append("<td class=\"page_item page_item_select\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\">").append(i).append("</td>");
							} else {
								sb.append("<td class=\"page_item\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\">").append(i).append("</td>");
							}
						}
						if (currentPage != totalPage) {
							sb.append("<td class=\"page_item pagesize3\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage + 1).append("')\" >").append("下一页").append("</td>");
						}
						sb.append("<td class=\"page_item pagesize2\" onclick=\"parent.showPageForm('").append(url).append(paramStr).append(totalPage).append("')\" >").append("尾页").append("</td>");
					}
				}

				// 跳转指定页面
				if (page.currentResultCountFlag() && totalPage > 5 && show.charAt(3) == '0') {
					sb.append("<td style=\"width:10px;\"></td>");

					sb.append("<td style=\"width: 42px;\"><input class=\"form-control\" id=\"_jumpPageInput_\" type=\"text\"></td>");
					sb.append("<td style=\"width: 10px;\"></td>");
					sb.append("<td style=\"width: 34px;\"><input class=\"btn btn-default\" type=\"button\" onclick=\"_doJumpPage_()\" value=\"跳转\"></td>");
					sb.append("<td style=\"width: 10px;\"></td>");
					sb.append("\r\n");
					sb.append("<script type=\"text/javascript\">");
					sb.append("function _doJumpPage_(){");
					sb.append("var jumpNum=$('#_jumpPageInput_').val();");
					sb.append("var url='").append(url).append(paramStr).append("'+jumpNum;");
					sb.append("if(jumpNum == ''){return;}");
					sb.append("if(jumpNum > ").append(totalPage).append("){return;}");
					sb.append("parent.showPageForm(url);");
					sb.append("} ");
					sb.append("</script>");
				}

				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</div>");
				out.println(sb.toString());

			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("<span>PageTag.doStartTagByTable标签渲染出错</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	private static final long serialVersionUID = -436689146502854892L;

	// 展示开关
	private String show = "0000";

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = StringUtil.completion(4, "0", show);
	}

	// 分页实体
	private PageModel<MyDataBaseObjectBean> page;

	public PageModel<MyDataBaseObjectBean> getPage() {
		return page;
	}

	public void setPage(PageModel<MyDataBaseObjectBean> page) {
		this.page = page;
	}

	// 页面的超链接
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
