package org.zmsoft.jfp.framework.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.beans.page.PageModelHelper;
import org.zmsoft.jfp.framework.common.MyDataBaseExtSupport;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.utils.StringUtil;

/**
 * 分页标签菜单栏
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public class PageTag extends TagSupport implements IFrameworkConstants {

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		// 0:div,1:table
		if (type == 0)
			return doStartTagByDiv();
		else
			return doStartTagByTable();
	}

	/**
	 * 利用图层属性显示分页工具栏
	 * 
	 * @return
	 * @throws JspException
	 */
	public int doStartTagByDiv() throws JspException {
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
				///////////////////////////// 开头///////////////////////////////////////////
				sb.append("<div id=\"page\">");
				sb.append("<div class=\"layui-box layui-laypage layui-laypage-default\" id=\"layui-laypage-1\">");
				///////////////////////////// 分页///////////////////////////////////////////
				// ----------------------------左侧统计----------------------------
				// 总记录数
				sb.append("<span>共" + totalcount + "条记录</span>");
				// 每页记录数目
				sb.append("<span>每页" + page.getPageLimit() + "条</span>");
				// 总页数
				sb.append("<span>共" + totalPage + "页</span>");
				// ----------------------------右侧分页----------------------------

				// 页面总数在1
				if (totalPage == 1) {
					// 首页
					sb.append("<a class=\"layui-laypage-prev layui-disabled\" href=\"javascript:;\">").append("首页").append("</a>");
					// 上一页
					sb.append("<a class=\"layui-laypage-prev layui-disabled\" href=\"javascript:;\">").append("上一页").append("</a>");

					sb.append("<span class=\"layui-laypage-curr\" ><em class=\"layui-laypage-em\"></em><em>1</em></span>");

					// 下一页
					sb.append("<a class=\"layui-laypage-next layui-disabled\" href=\"javascript:;\">").append("下一页").append("</a>");
					// 尾页
					sb.append("<a class=\"layui-laypage-next layui-disabled\" href=\"javascript:;\">").append("尾页").append("</a>");

				}
				// 页面总数在5以内
				else if (totalPage > 1 && totalPage <= 5) {
					// 首页
					sb.append("<a class=\"layui-laypage-prev\" href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(1).append("');\">").append("首页").append("</a>");
					// 上一页
					if (currentPage == 1)
						sb.append("<a class=\"layui-laypage-prev layui-disabled\" href=\"javascript:;\">").append("上一页").append("</a>");
					else
						sb.append("<a class=\"layui-laypage-prev\" href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(currentPage - 1).append("');\">").append("上一页").append("</a>");
					for (int i = 1; i <= totalPage; i++) {
						if (i == currentPage) {
							sb.append("<span class=\"layui-laypage-curr\" ><em class=\"layui-laypage-em\"></em><em>").append(i).append("</em></span>");
						} else {
							sb.append("<a href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(i).append("');\">").append(i).append("</a>");
						}
					}
					// 下一页
					if (currentPage != totalPage)
						sb.append("<a class=\"layui-laypage-next\" href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(currentPage + 1).append("');\">").append("下一页").append("</a>");
					else
						sb.append("<a class=\"layui-laypage-next layui-disabled\" href=\"javascript:;\">").append("下一页").append("</a>");
					// 尾页
					sb.append("<a class=\"layui-laypage-next\" href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(totalPage).append("');\">").append("尾页").append("</a>");
				}
				// 页面超过5页，让其保持剧中 类似于 // 23 4 56
				else if (totalPage > 5) {
					if (currentPage >= 3) {
						// 首页
						sb.append("<a class=\"layui-laypage-prev\" href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(1).append("');\">").append("首页").append("</a>");
						// 上一页
						if (currentPage == 1)
							sb.append("<a class=\"layui-laypage-prev layui-disabled\" href=\"javascript:;\">").append("上一页").append("</a>");
						else
							sb.append("<a class=\"layui-laypage-prev\" href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(currentPage - 1).append("');\">").append("上一页").append("</a>");

						int gap = totalPage - currentPage; // 当前页与尾页的距离
						if (gap >= 2) { // 23 4 56
							for (int i = currentPage - 2; i < currentPage; i++) {
								sb.append("<a href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(i).append("');\">").append(i).append("</a>");
							}
							sb.append("<span class=\"layui-laypage-curr\" ><em class=\"layui-laypage-em\"></em><em>").append(currentPage).append("</em></span>");
							for (int i = currentPage + 1; i <= currentPage + 2; i++) {
								sb.append("<a href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(i).append("');\">").append(i).append("</a>");
							}
						} else if (gap == 0) { // 3456 7
							for (int i = currentPage - 4; i < currentPage; i++) {
								sb.append("<a href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(i).append("');\">").append(i).append("</a>");
							}
							sb.append("<span class=\"layui-laypage-curr\" ><em class=\"layui-laypage-em\"></em><em>").append(currentPage).append("</em></span>");
						} else if (gap == 1) { // 345 6 7
							for (int i = currentPage - 3; i < currentPage; i++) {
								sb.append("<a href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(i).append("');\">").append(i).append("</a>");
							}

							sb.append("<span class=\"layui-laypage-curr\" ><em class=\"layui-laypage-em\"></em><em>").append(currentPage).append("</em></span>");
							sb.append("<a href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(currentPage + 1).append("');\">").append(currentPage + 1).append("</a>");
						}
						// 下一页
						if (currentPage != totalPage)
							sb.append("<a class=\"layui-laypage-next\" href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(currentPage + 1).append("');\">").append("下一页").append("</a>");
						else
							sb.append("<a class=\"layui-laypage-next layui-disabled\" href=\"javascript:;\">").append("下一页").append("</a>");
						// 尾页
						sb.append("<a class=\"layui-laypage-next\" href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(totalPage).append("');\">").append("尾页").append("</a>");
					}
					// 总页数>5 但当前页在第1或者2页
					else if (currentPage < 3) {
						// 首页
						sb.append("<a class=\"layui-laypage-prev\" href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(1).append("');\">").append("首页").append("</a>");
						// 上一页
						if (currentPage == 1)
							sb.append("<a class=\"layui-laypage-prev layui-disabled\" href=\"javascript:;\">").append("上一页").append("</a>");
						else
							sb.append("<a class=\"layui-laypage-prev\" href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(currentPage - 1).append("');\">").append("上一页").append("</a>");

						for (int i = 1; i <= 5; i++) {
							if (i == currentPage) {
								sb.append("<span class=\"layui-laypage-curr\" ><em class=\"layui-laypage-em\"></em><em>").append(i).append("</em></span>");
							} else {
								sb.append("<a href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(i).append("');\">").append(i).append("</a>");
							}
						}
						// 下一页
						if (currentPage != totalPage)
							sb.append("<a class=\"layui-laypage-next\" href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(currentPage + 1).append("');\">").append("下一页").append("</a>");
						else
							sb.append("<a class=\"layui-laypage-next layui-disabled\" href=\"javascript:;\">").append("下一页").append("</a>");
						// 尾页
						sb.append("<a class=\"layui-laypage-next\" href=\"javascript:parent.showPageForm('").append(url).append(paramStr).append(totalPage).append("');\">").append("尾页").append("</a>");
					}
				}

				///////////////////////////// 结尾///////////////////////////////////////////
				sb.append("</div>");
				sb.append("</div>");
				out.println(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("<span>PageTag.doStartTagByDiv标签渲染出错</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
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
				sb.append("<table>");
				sb.append("<tr class=\"pageht\">");
				///////////////////////////// 左侧统计///////////////////////////////////////////
				sb.append("<td align=\"left\">");
				sb.append("<table>");
				sb.append("<tr class=\"pageht\"><td style=\"width:26px;\"></td>");
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

				// sb.append("<td>首页</td>");
				// sb.append("<td>上一页</td>");
				// sb.append("<td
				// onclick=\"parent.showPageForm('").append(url).append(paramStr).append("')\"
				// style=\"color:#fff; text-align:center; cursor:
				// pointer;background: #333333;\">1</td>");
				// sb.append("<td onclick=\"parent.showPageForm('www')\"
				// style=\"text-align:center; cursor: pointer;\">2</td>");
				// sb.append("<td>3</td>");
				// sb.append("<td>4</td>");
				// sb.append("<td>5</td>");
				// sb.append("<td>下一页</td>");
				// sb.append("<td>尾页</td>");

				// 页面总数在1
				if (totalPage == 1) {
					sb.append("<td style=\"color:#fff; text-align:center; cursor: pointer;background: #333333;width:26px;\">1</td>");
				}
				// 页面总数在5以内
				else if (totalPage > 1 && totalPage <= 5) {
					for (int i = 1; i <= totalPage; i++) {
						if (i == currentPage) {
							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"color:#fff; text-align:center; cursor: pointer;background: #333333;width:26px;\">").append(i).append("</td>");
						} else {
							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"text-align:center; cursor: pointer;width:26px;\">").append(i).append("</td>");
						}
					}
				}
				// 页面超过5页，让其保持剧中 类似于 // 23 4 56
				else if (totalPage > 5) {
					if (currentPage >= 3) {
						// 总页数
						sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append("1").append("')\" style=\"text-align:center; cursor: pointer;width:34px;\">").append("首页").append("</td>");
						sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage - 1).append("')\" style=\"text-align:center; cursor: pointer;width:50px;\">").append("上一页").append("</td>");
						int gap = totalPage - currentPage; // 当前页与尾页的距离
						if (gap >= 2) { // 23 4 56
							for (int i = currentPage - 2; i < currentPage; i++) {
								sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"text-align:center; cursor: pointer;width:26px;\">").append(i).append("</td>");
							}
							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage).append("')\" style=\"color:#fff; text-align:center; cursor: pointer;background: #333333;width:26px;\">").append(currentPage).append("</td>");
							for (int i = currentPage + 1; i <= currentPage + 2; i++) {
								sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"text-align:center; cursor: pointer;width:26px;\">").append(i).append("</td>");
							}
						} else if (gap == 0) { // 3456 7
							for (int i = currentPage - 4; i < currentPage; i++) {
								sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"text-align:center; cursor: pointer;width:26px;\">").append(i).append("</td>");
							}
							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage).append("')\" style=\"color:#fff; text-align:center; cursor: pointer;background: #333333;width:26px;\">").append(currentPage).append("</td>");
						} else if (gap == 1) { // 345 6 7
							for (int i = currentPage - 3; i < currentPage; i++) {
								sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"text-align:center; cursor: pointer;width:26px;\">").append(i).append("</td>");
							}

							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage).append("')\" style=\"color:#fff; text-align:center; cursor: pointer;background: #333333;width:26px;\">").append(currentPage).append("</td>");
							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage + 1).append("')\" style=\"text-align:center; cursor: pointer;width:26px;\">").append(currentPage + 1).append("</td>");
						}

						if (currentPage != totalPage) {
							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage + 1).append("')\" style=\"text-align:center; cursor: pointer;width:50px;\">").append("下一页").append("</td>");
						}
						sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(totalPage).append("')\" style=\"text-align:center; cursor: pointer;width:34px\">").append("尾页").append("</td>");
					}
					// 总页数>5 但当前页在第1或者2页
					else if (currentPage < 3) {
						for (int i = 1; i <= 5; i++) {
							if (i == currentPage) {
								sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"color:#fff; text-align:center; cursor: pointer;background: #333333;width:26px;\">").append(i).append("</td>");
							} else {
								sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"text-align:center; cursor: pointer;width:26px;\">").append(i).append("</td>");
							}
						}
						if (currentPage != totalPage) {
							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage + 1).append("')\" style=\"text-align:center; cursor: pointer;width:50px;\">").append("下一页").append("</td>");
						}
						sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(totalPage).append("')\" style=\"text-align:center; cursor: pointer;width:34px;\">").append("尾页").append("</td>");
					}
				}

				// 跳转指定页面
				sb.append("<td style=\"width:10px;\"></td>");
				if (totalPage > 5 && show.charAt(3) == '0') {
					sb.append("<td style=\"width: 42px;\"><input class=\"form-control\" id=\"_jumpPageInput_\" type=\"text\"></td>");
					sb.append("<td style=\"width:10px;\"></td>");
					sb.append("<td style=\"width: 34px;\"><input class=\"btn btn-default\" type=\"button\" onclick=\"_doJumpPage_()\" value=\"跳转\"></td>");
					sb.append("<td style=\"width:10px;\"></td>");
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
	private PageModel<MyDataBaseExtSupport> page;

	// 页面的超链接
	private String url;
	// 展示类别(0:div,1:table)
	private int type = 9;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public PageModel<MyDataBaseExtSupport> getPage() {
		return page;
	}

	public void setPage(PageModel<MyDataBaseExtSupport> page) {
		this.page = page;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
