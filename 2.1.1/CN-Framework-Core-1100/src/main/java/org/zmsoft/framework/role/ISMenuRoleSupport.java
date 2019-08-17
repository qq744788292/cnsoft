package org.zmsoft.framework.role;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;

/**
 * 用户功能权限模型
 * @author spookfcy
 *
 */
public interface ISMenuRoleSupport {
	
	/**
	 * 整理用户菜单权限
	 * @param bean
	 */
	public boolean prepareUserMenuRole(MyDataBaseObjectSupport3 dataBean) ;
	
	/**
	 * 加载当前用户的菜单权限<br>
	 * Redis缓存模型
	 * @param currentUser
	 */
	public boolean loadUserMenuRole(UserBean currentUser) ;
}
