package org.zmsoft.framework.common;

import org.zmsoft.framework.beans.UserBean;

/**
 * 当前登录用户
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public interface ISLoginer {
	/**
	 * 当前登录用户
	 */
	UserBean currentUser();

}