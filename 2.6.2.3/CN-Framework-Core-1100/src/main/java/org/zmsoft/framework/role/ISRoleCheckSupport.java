package org.zmsoft.framework.role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户权限模型
 * 
 * @author ZMSoft
 *
 */
public interface ISRoleCheckSupport {

	/**
	 * 用户权限检查
	 * 
	 * @param bean
	 */
	public boolean doCheckRole(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
