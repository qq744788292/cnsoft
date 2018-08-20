package org.zmsoft.jfp.framework.common;

import org.zmsoft.jfp.framework.beans.user.UserBean;

/**
 * 当前登录用户
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public interface ILoginer {
	/**
	 * 当前登录用户
	 */
	UserBean currentUser();

}