package org.zmsoft.config.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.beans.common.HeaderBean;
import org.zmsoft.framework.cache.request.RequestHelper;
import org.zmsoft.framework.common.buzzinezz.ISHeaderSupport;
import org.zmsoft.framework.constants.ICHeaderConstants;

/**
 * 自定义请求头处理
 * 
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
@Service("MyHttpRequestHeaderService")
public class MyHttpHeaderConfiger implements ISHeaderSupport, ICHeaderConstants {
	public MyHttpHeaderConfiger() {
	}

	@Override
	public void loadHeaderParam(HttpServletRequest request) {

		HeaderBean header = new HeaderBean();
		// 获得参数里面的Token
		header.setToken(request.getHeader(HEADER_PARAM_TOKEN));
		header.setJobId(request.getHeader(HEADER_PARAM_JOBID));
		header.setVersion(request.getHeader(HEADER_PARAM_VERSION));
		header.setHdp(request.getHeader(HEADER_PARAM_HDP));

		RequestHelper.setAttributes(header);

		// // 获得参数里面的Token
		// {
		// String token = request.getHeader("token");// 头部
		// if (EmptyHelper.isEmpty(token)) {
		// token = request.getParameter("token");// 参数列表
		// }
		//
		// addAttribute("token", token);
		// }
		// // 获得参数里面的randomKey
		// {
		// String randomKey = request.getHeader("randomKey");// 头部
		// if (EmptyHelper.isEmpty(randomKey)) {
		// randomKey = request.getParameter("randomKey");// 参数列表
		// }
		//
		// addAttribute("randomKey", randomKey);
		// }
		// // 获得参数里面的parkId
		// {
		// String parkId = request.getHeader("parkId");// 头部
		// if (EmptyHelper.isEmpty(parkId)) {
		// parkId = request.getParameter("parkId");// 参数列表
		// }
		//
		// addAttribute("parkId", parkId);
		// }

	}

}
