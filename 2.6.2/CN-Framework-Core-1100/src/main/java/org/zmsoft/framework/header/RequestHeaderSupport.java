package org.zmsoft.framework.header;

import javax.servlet.http.HttpServletRequest;

import org.zmsoft.framework.beans.common.HeaderBean;
import org.zmsoft.framework.constants.ICHeaderConstants;

/**
 * 当前请求参数梳理
 * @author fcy
 *
 */
public class RequestHeaderSupport implements ICHeaderConstants {

	public static HeaderBean loadHeaderParam(HttpServletRequest request) {
		HeaderBean header = new HeaderBean();
		// 获得参数里面的Token
		header.setToken(request.getHeader(HEADER_PARAM_TOKEN));
		header.setJobId(request.getHeader(HEADER_PARAM_JOBID));
		header.setVersion(request.getHeader(HEADER_PARAM_VERSION));
		header.setHdp(request.getHeader(HEADER_PARAM_HDP));
		return header;
	}
}
