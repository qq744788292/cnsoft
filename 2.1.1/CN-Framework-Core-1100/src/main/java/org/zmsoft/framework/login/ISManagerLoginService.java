package org.zmsoft.framework.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.framework.beans.LoginerBean;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.IFrameworkConstants;

/**
 * 管理员登录接口
 * 
 * @author spookfcy
 * @see <application-bean.yml>
 */
public interface ISManagerLoginService extends IFrameworkConstants {

	public RESTResultBean<UserBean> doLogin(HttpServletRequest request, HttpServletResponse response, LoginerBean loginer) throws Exception;

}
