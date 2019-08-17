package org.zmsoft.framework.support.helper;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
import org.zmsoft.framework.role.ISIdentityRoleSupport;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyFrameWorkSupport;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 用户职能身份权限管理
 * 
 * @author spookfcy
 *
 */
public class MyManagerIdentityRoleHelper extends MyFrameWorkSupport {

	public static boolean prepareUserIdentityRole(String identityServiceName, MyDataBaseObjectSupport3 dataBean) {
		if (EmptyHelper.isNotEmpty(dataBean)) {
			ISIdentityRoleSupport _ISIdentityRoleSupport_ = MyBeanFactoryHelper.getBean(identityServiceName);
			if (EmptyHelper.isNotEmpty(_ISIdentityRoleSupport_)) {
				return _ISIdentityRoleSupport_.prepareUserIdentityRole(dataBean);
			}
		}

		return false;
	}
	
	public static boolean loadUserIdentityRole(String identityServiceName, UserBean currentUser) {
		if (EmptyHelper.isNotEmpty(currentUser)) {
			ISIdentityRoleSupport _ISIdentityRoleSupport_ = MyBeanFactoryHelper.getBean(identityServiceName);
			if (EmptyHelper.isNotEmpty(_ISIdentityRoleSupport_)) {
				return _ISIdentityRoleSupport_.loadUserIdentityRole(currentUser);
			}
		}

		return false;
	}
}
