package com.zmsoft.service.manager;

import java.util.List;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.IFrameworkConstants;

import com.zmsoft.persistent.common.ManagerMenu.ManagerMenuDBO;

/**
 * 用户首页
 * 
 * @author spookfcy
 *
 */
public interface ISManagerHomeService extends IFrameworkConstants {

	/**
	 * 获得当前用户信息
	 * 
	 * @return
	 */
	public RESTResultBean<UserBean> doUserInfo() throws Exception;

	public RESTResultBean<List<ManagerMenuDBO>> doUserMenu() throws Exception;

	public RESTResultBean<List<ManagerMenuDBO>> doUserRole() throws Exception;

}
