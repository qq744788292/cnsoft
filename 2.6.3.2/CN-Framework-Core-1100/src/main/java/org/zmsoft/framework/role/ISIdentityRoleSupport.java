package org.zmsoft.framework.role;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;

/**
 * 用户职能身份权限模型
 * 
 * @author ZMSoft
 *
 */
public interface ISIdentityRoleSupport {

	/**
	 * 整理用户数据权限
	 * 
	 * @param bean
	 */
	public boolean prepareUserIdentityRole(MyDataBaseObjectSupport3 dataBean);

	/**
	 * 加载当前用户的数据权限<br>
	 * Redis缓存模型
	 * 
	 * @param currentUser
	 */
	public boolean loadUserIdentityRole(UserBean currentUser);
}
