package org.zmsoft.service.manager;

import java.util.List;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.persistent.common.ManagerMenu.ManagerMenuDBO;

/**
 * 用户首页
 * 
 * @author ZMSoft
 *
 */
public interface ISManagerHomeService extends ICFrameworkConstants {

	/**
	 * 获得当前用户信息
	 * 
	 * @return
	 */
	public RESTResultBean<UserBean> doUserInfo() throws Exception;

	public RESTResultBean<List<ManagerMenuDBO>> doUserMenu(ManagerMenuDBO param) throws Exception;

	public RESTResultBean<List<ManagerMenuDBO>> doUserRole(ManagerMenuDBO param) throws Exception;

}
