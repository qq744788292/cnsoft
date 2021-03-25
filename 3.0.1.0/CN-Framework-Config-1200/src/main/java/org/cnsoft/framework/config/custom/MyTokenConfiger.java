package org.cnsoft.framework.config.custom;

import javax.servlet.http.HttpServletRequest;

import org.cnsoft.framework.common.buzzinezz.ISTokenSupport;
import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.springframework.stereotype.Service;

/**
 * 自定义请求标识验证处理
 * 
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <MyTokenBusinessSupport#jobIdRole>
 *
 */
@Service("MyCustomTokenCheckService")
public class MyTokenConfiger implements ISTokenSupport, ICFrameworkConstants {

	public MyTokenConfiger() {
		// 自动接口拦截器(0关闭1开启)
		// TokenBusinessSupport.jobIdRole = ONE;
	}

	@Override
	public boolean checkToken(HttpServletRequest request) throws Exception {
		return false;
	}

}
