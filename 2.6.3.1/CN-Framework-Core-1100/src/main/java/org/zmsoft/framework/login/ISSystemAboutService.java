package org.zmsoft.framework.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.framework.beans.VersionBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * 系统相关接口
 */
public interface ISSystemAboutService extends ICFrameworkConstants {

	/**
	 * 服务条款
	 */
	public RESTResultBean<String> doClause(HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * 注册协议
	 */
	public RESTResultBean<String> doTreaty(HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * 关于我们
	 */
	public RESTResultBean<String> doAbout(HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * 版本检测
	 */
	public RESTResultBean<VersionBean> doNew(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
