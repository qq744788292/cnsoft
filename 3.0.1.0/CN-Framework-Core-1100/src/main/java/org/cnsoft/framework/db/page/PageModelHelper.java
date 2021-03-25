package org.cnsoft.framework.db.page;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 分页数据参数转换
 * 
 * @author CNSoft
 * @since 2.0.0 2018/10/10
 * @version 2.0.0 2018/10/10
 */
public class PageModelHelper {

	public static <T> StringBuilder preparePageModelFormParamBean(HttpServletRequest request, PageModel<T> pageModel, String requestURI) {
		StringBuilder stb = new StringBuilder();
		try {
			// 梳理请求参数
			Map<String,String> param = BeanUtils.describe(pageModel.currentFormParamBean());

			// 补全判断
			if (requestURI.indexOf('?') > 0) {
				stb.append("&");
			} else {
				stb.append("?");
			}
			// 补全Order by
			stb.append("orderby").append("=").append(pageModel.currentOrderby());

			// 拼接
			stb.append("&");

			// 补全分页条数
			stb.append("pageLimit").append("=").append(pageModel.getPageLimit());

			// 不全请求参数
			for (Entry<String, String> entry : param.entrySet()) {
				if ("orderby".equals(entry.getKey()) || "pageCurrent".equals(entry.getKey()) || "pageLimit".equals(entry.getKey()))
					continue;
				// 整理参数
				stb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			stb = new StringBuilder("?message=参数设定异常");
		}
		return stb;
	}

}
