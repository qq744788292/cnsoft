package com.zmsoft.service.player;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.IFrameworkConstants;

/**
 * 用户首页
 * 
 * @author spookfcy
 *
 */
public interface ISPlayerHomeService extends IFrameworkConstants {

	/**
	 * 获得当前用户登陆信息
	 * 
	 * @return
	 */
	public RESTResultBean<UserBean> doUserInfo();

}
