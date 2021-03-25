package org.zmsoft.config.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.common.buzzinezz.ISTokenSupport;
import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * 自定义请求标识验证处理
 * 
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <TokenBusinessSupport#jobIdRole>
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
		// TODO Auto-generated method stub
		return false;
	}

}
