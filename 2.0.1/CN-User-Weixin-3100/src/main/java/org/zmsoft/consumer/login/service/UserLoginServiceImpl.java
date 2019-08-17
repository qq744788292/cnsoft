package org.zmsoft.consumer.login.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.beans.LoginerBean;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ICacheConstants;
import org.zmsoft.framework.login.ISUserLoginSupport;
import org.zmsoft.framework.security.MD5SecurityHelper;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.token.TokenBusinessSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.HttpRequestHelper;
import org.zmsoft.framework.weixin.bean.WxUserBean;

import com.alibaba.fastjson.JSONObject;

@Service("UserLoginServiceImpl")
public class UserLoginServiceImpl extends MyTokenCommonSupport implements ISUserLoginSupport<UserBean>, ICacheConstants {

	RESTResultBean<UserBean> result;
	/**
	 * 账号密码登录
	 */
	public RESTResultBean<UserBean> doUserBaseLogin(HttpServletRequest request, LoginerBean loginer) throws Exception {
		result = new RESTResultBean<UserBean>();

		loginer.setLoginSource(ELEVEN);
		loginer.setLoginType(ONE);
		loginer.setPv1(ZERO);

		return autoLogin(request, null, loginer);
	}

	/**
	 * 自动登录
	 */
	public RESTResultBean<UserBean> doUserAutoLogin(HttpServletRequest request, String token, String unionId) throws Exception {
		
		//开始自动登录
		LoginerBean loginer = new LoginerBean();
		loginer.setToken(token);
		loginer.setLoginSource("20");
		loginer.setLoginType("99");
		loginer.setPv1(ZERO);

		return autoLogin(request, null, loginer);
	}

	/**
	 * 微信登录
	 */
	public RESTResultBean<UserBean> doUserWXLogin(HttpServletRequest request, WxUserBean wxUserBean) throws Exception {
		// 根据OpenID查找用户
		if(EmptyHelper.isEmpty(wxUserBean.getOpenid())){
			result = new RESTResultBean<UserBean>();
			TokenBusinessSupport tokenBean = TokenBusinessSupport.defaultToken(request,myCacheService);
			// 处理TOKEN
			result.setToken(tokenBean.getToken());
			result.setData(tokenBean.currentUser());
			return result;
		}

		return autoLogin(request, null, null);
	}

	//////////////////////////////////////////////////////////////////////////////////////////
	public RESTResultBean<UserBean> autoLogin(HttpServletRequest request, List<String> users, LoginerBean loginer) throws Exception {
		result = new RESTResultBean<UserBean>();
		UserBean curUser = new UserBean();
		// 99 自动登录
		{
			// 制作Token
			TokenBusinessSupport tokenBean = new TokenBusinessSupport();
			tokenBean.setToken(loginer.getToken());
			tokenBean.setSessionId(request.getSession().getId());
			tokenBean.setIpAdress(HttpRequestHelper.getClientRemoteIPAddr(request, false));
			tokenBean.setClientTimestamp();

//			curUser.setId(_CustomUserDBO_.getId());
//			// 保存用户信息
//			curUser.setUserHeadUrl(_CustomUserDBO_.getCustomPhoto());
//			curUser.setUserPhone(_CustomUserDBO_.getMobilePhone());
//			curUser.setUserNameCN(_CustomUserDBO_.getUserSecondName());
//
//			curUser.setPv1(loginer.getPv1());// 是否新用户

			// 保存系统类别
			// curUser.setHdp(loginer.getHdp());
			// 缓存中心缓存用户对象
			tokenBean.setMyCacheService(myCacheService);
			tokenBean.setCurUser(curUser);
			tokenBean.setWaitTimeSecond(1800);
			tokenBean.saveToken();// 处理TOKEN

			result.setToken(tokenBean.getToken());
			result.setData(curUser);
		}

		return result;
	}
}
