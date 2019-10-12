package org.zmsoft.framework.support;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.cache.session.SessionHelper;

/**
 * 开放接口控制层超类<br>
 * 需要用户登录
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public class MyOpenApiSupport extends MyTokenCommonSupport {

	/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 当前登录用户
	 */
	public UserBean currentUser() {
		UserBean loginer = SessionHelper.currentUser();
		if (loginer == null) {
			throw new RuntimeException("当前用户没有登录");
		}
		return loginer;
	}
}
