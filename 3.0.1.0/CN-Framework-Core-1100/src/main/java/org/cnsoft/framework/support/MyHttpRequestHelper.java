package org.cnsoft.framework.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.cnsoft.framework.utils.EmptyHelper;


/**
 * 请求头参数获取
 *
 */
public class MyHttpRequestHelper implements ICFrameworkConstants{

	public static String loadJobId(HttpServletRequest request, HttpServletResponse response) {
		// 获得参数里面的Token
		String jobId = request.getHeader(CONSTANT_USER_JOBID);// 头部
		if (EmptyHelper.isEmpty(jobId)) {
			jobId = request.getParameter(CONSTANT_USER_JOBID);// 参数列表
		}
		return jobId;
	}


	public static String loadToken(HttpServletRequest request, HttpServletResponse response) {
		// 获得参数里面的Token
		String token = request.getHeader(CONSTANT_USER_TOKEN);// 头部
		if (EmptyHelper.isEmpty(token)) {
			token = request.getParameter(CONSTANT_USER_TOKEN);// 参数列表
		}
		return token;
	}
}
