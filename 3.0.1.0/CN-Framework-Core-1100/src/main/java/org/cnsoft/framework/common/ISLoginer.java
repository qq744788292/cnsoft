package org.cnsoft.framework.common;

import org.cnsoft.framework.beans.user.UserBean;
import org.cnsoft.framework.core.ISSystem;

/**
 * 当前登录用户
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public interface ISLoginer extends ISSystem {
	/**
	 * 当前登录用户
	 */
	UserBean currentUser();

}