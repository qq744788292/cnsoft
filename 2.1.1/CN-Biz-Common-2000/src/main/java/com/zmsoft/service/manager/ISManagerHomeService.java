package com.zmsoft.service.manager;

import java.util.Map;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.IFrameworkConstants;

/**
 * 用户首页
 * 
 * @author spookfcy
 *
 */
public interface ISManagerHomeService extends IFrameworkConstants {

	/**
	 * 获得当前用户信息
	 * 
	 * @return
	 */
	public RESTResultBean<UserBean> doUserInfo();

	public RESTResultBean<Map<String, String>> doUserMenu();

	public RESTResultBean<Map<String, String>> doUserRole();

}
