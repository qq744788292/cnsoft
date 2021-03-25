package org.zmsoft.framework.support.helper;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
import org.zmsoft.framework.role.ISMenuRoleSupport;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyFrameWorkSupport;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 用户功能权限管理
 * 
 * @author ZMSoft
 *
 */
public class MyManagerMenuRoleHelper extends MyFrameWorkSupport {

	public static boolean prepareUserMenuRole(String menuServiceName, MyDataBaseObjectSupport3 dataBean) {
		if (EmptyHelper.isNotEmpty(dataBean)) {
			ISMenuRoleSupport _ISMenuRoleSupport_ = MyBeanFactoryHelper.getBean(menuServiceName);
			if (EmptyHelper.isNotEmpty(_ISMenuRoleSupport_)) {
				return _ISMenuRoleSupport_.prepareUserMenuRole(dataBean);
			}
		}

		return false;
	}

	public static boolean loadUserMenuRole(String menuServiceName, UserBean currentUser) {
		if (EmptyHelper.isNotEmpty(currentUser)) {
			ISMenuRoleSupport _ISMenuRoleSupport_ = MyBeanFactoryHelper.getBean(menuServiceName);
			if (EmptyHelper.isNotEmpty(_ISMenuRoleSupport_)) {
				return _ISMenuRoleSupport_.loadUserMenuRole(currentUser);
			}
		}

		return false;
	}
}
