package com.lcgj.sjzt.rool.manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.cache.client.ClientHelper;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.client.ClientBusinessSupport;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.HttpRequestHelper;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZTManagerHdpService extends MyTokenCommonSupport implements ISCompanyRoolCheck {

	@Override
	public boolean checkCompanyHdp(RequestContext context, HttpServletRequest request, String appId) throws ZuulException {
		try {
			// 获取接口路径
			String currentRoleURL = request.getRequestURI().toString();
			UserBean currentUser = SessionHelper.currentUser();
			// 获得所有权限
			String funcs = (String) myCacheService.getObject(API_ROLE_KEY + ZERO);
			if ((ONE.equals(currentUser.getIsAdmin()) == false) && EmptyHelper.isNotEmpty(funcs)) {
				// 如果当前路径需要权限管理
				if (funcs.indexOf(currentRoleURL) > -1) {
					String key = currentUser.getId() + currentRoleURL;

					// 获取用户请求权限
					String func = (String) myCacheService.getObject(API_ROLE_KEY + key);
					if (EmptyHelper.isEmpty(func)) {
						doLogData(request, appId, "false");
						return false;
					}
				}
			}
			doLogData(request, appId, "true");
		} catch (Exception e) {
		}
		return true;

	}

	/**
	 * 制作请求日志
	 * 
	 * @param elkLogCache
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	public boolean doLogData(HttpServletRequest request, String appId, String execute) throws Exception {

		if (logger.isDebugEnabled()) {
			System.out.println("=====请求路径=====>>>>>" + request.getRequestURL().toString());
			System.out.println("=====请求参数=====>>>>>" + JSON.toJSONString(request.getParameterMap()));
		}

		// 记录用户信息
		ClientBusinessSupport clent = new ClientBusinessSupport();
		// 用户IP地址
		clent.setClientIp(HttpRequestHelper.getClientRemoteIPAddr(request));
		// 访问域名路径
		clent.setSourceAddress(HttpRequestHelper.getServerAddr(request));
		// 完整路径
		clent.setReferralLink(request.getRequestURL().toString());
		ClientHelper.setClientBusinessSupport(clent);
//		// 登录日志
//		LogDataSupport LogDataService_ = SpringContextHolder.getBean("LogDataSupport");
//		LogDataBean logData = new LogDataBean();
//		logData.setApi(request.getRequestURL().toString());
//		logData.setIpAdress(clent.getClientIp());
//		logData.setUserId(appId);
//		logData.setExecute(execute);
//		logData.setLogData(request.getParameterMap());
//		LogDataService_.setLogData(logData);
//		LogDataService_.flush();
		return true;
	}
}
