package org.zmsoft.framework.support.helper;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
import org.zmsoft.framework.role.ISHDPRoleSupport;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyFrameWorkSupport;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 用户职能身份权限管理
 * 
 * @author spookfcy
 *
 */
public class MyManagerHDPRoleHelper extends MyFrameWorkSupport {

	public static boolean prepareUserDataRole(String hdpServiceName, MyDataBaseObjectSupport3 dataBean) {
		if (EmptyHelper.isNotEmpty(dataBean)) {
			ISHDPRoleSupport _ISHDPRoleSupport_ = MyBeanFactoryHelper.getBean(hdpServiceName);
			if (EmptyHelper.isNotEmpty(_ISHDPRoleSupport_)) {
				return _ISHDPRoleSupport_.prepareUserDataRole(dataBean);
			}
		}

		return false;
	}
	
	public static boolean loadUserDataRole(String hdpServiceName, UserBean currentUser) {
		if (EmptyHelper.isNotEmpty(currentUser)) {
			ISHDPRoleSupport _ISHDPRoleSupport_ = MyBeanFactoryHelper.getBean(hdpServiceName);
			if (EmptyHelper.isNotEmpty(_ISHDPRoleSupport_)) {
				return _ISHDPRoleSupport_.loadUserDataRole(currentUser);
			}
		}

		return false;
	}
}
