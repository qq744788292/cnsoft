package org.cnsoft.framework.config.custom;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.cnsoft.framework.cache.client.ClientBean;
import org.cnsoft.framework.cache.client.RequestHeaderBean;
import org.cnsoft.framework.cache.request.ClientRequestHelper;
import org.cnsoft.framework.common.buzzinezz.ISHeaderSupport;
import org.cnsoft.framework.utils.EmptyHelper;
import org.springframework.stereotype.Service;

/**
 * 自定义请求头处理
 * 
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
@Service("MyHttpRequestHeaderService")
public class MyHttpHeaderConfiger extends RequestHeaderBean implements ISHeaderSupport {

	public final String[] headers = { //
			"token", // 登录识别信息
			"clientId", // 浏览器客户端唯一标识
			"jobId", // 请求唯一标识
			"ver", // 版本号
			"pid", // 项目ID
			"sid" // 租户ID
	};

	/**
	 * 通用请求参数整理
	 * 
	 * @param request
	 * @throws Exception
	 */
	@Override
	public void loadHeaderParam(HttpServletRequest request) {
		ClientBean client = new ClientBean();
		for (String name : headers) {
			String value = request.getHeader(name);
			if (EmptyHelper.isEmpty(value)) {
				value = request.getParameter("value");// 参数列表
			}
			try {
				BeanUtils.setProperty(client, name, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ClientRequestHelper.setClientBusinessSupport(client);
	}

	@Override
	public RequestHeaderBean getHeaderParams() {
		return ClientRequestHelper.getClientBusinessSupport();
	}

}
