package org.zmsoft.service.player;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * 用户首页
 */
public interface ISPlayerHomeService extends ICFrameworkConstants {

	/**
	 * 获得当前用户登陆信息
	 * 
	 * @return
	 */
	public RESTResultBean<UserBean> doUserInfo();

}
