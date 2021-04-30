package org.cnsoft.framework.saas.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.db.support.ADataBaseDefaultSupportBean;
import org.cnsoft.framework.utils.EmptyHelper;

public class MySAASBusinesslogicPlugin {
	
	public static final String SAAS_PLUGIN_PARAM_REQUEST = "SAASPluginParamRequest";
	public static final String SAAS_PLUGIN_PARAM_DB = "SAASPluginParamDB";

	//参数拦截
	//#MySafeStrategyConfigurer
	public static boolean requestCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ISSAASPluginParamRequest saasppr = MyBeanFactoryHelper.getBean(SAAS_PLUGIN_PARAM_REQUEST);
		if(EmptyHelper.isNotEmpty(saasppr)) {
			return saasppr.doCheck(request, response);
		}
		
		return true;
	}
	
	
	//参数拦截
	//#MySafeStrategyConfigurer
	public static boolean sqlHandle(ADataBaseDefaultSupportBean formParamBean) {
		ISSAASPluginParamDB saasppd = MyBeanFactoryHelper.getBean(SAAS_PLUGIN_PARAM_DB);
		if(EmptyHelper.isNotEmpty(saasppd)) {
			return saasppd.doCompletion(formParamBean);
		}
		
		return true;
	}
}
