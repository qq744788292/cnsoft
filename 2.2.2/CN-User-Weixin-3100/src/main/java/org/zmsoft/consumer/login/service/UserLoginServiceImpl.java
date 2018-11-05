package org.zmsoft.consumer.login.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.beans.LoginerBean;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ICacheConstants;
import org.zmsoft.framework.login.ISUserLogin;
import org.zmsoft.framework.security.MD5SecurityHelper;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.token.TokenBusinessSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.HttpRequestHelper;
import org.zmsoft.framework.weixin.bean.WxUserBean;

import com.alibaba.fastjson.JSONObject;

@Service("UserLoginServiceImpl")
public class UserLoginServiceImpl extends MyTokenCommonSupport implements ISUserLogin<UserBean>, ICacheConstants {

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
	
	public List<String> checkUser(JSONObject resultZT){
//		//判断本地用户是否存在
//		CustomUserDBO _CustomUserDBO_ = new CustomUserDBO();
//		_CustomUserDBO_.setEb2(resultZT.getString("unionId"));
//		List<CustomUserDBO> users = CustomUserDao_.doSelectData(_CustomUserDBO_);
//		if (users == null || users.size() == 0) {//本地不存在
//			users = new ArrayList<CustomUserDBO>();
//			// 保存用户信息
//			_CustomUserDBO_.setId(resultZT.getString("unionId"));
//			_CustomUserDBO_.setCustomPhoto(resultZT.getString("headImg"));
//			_CustomUserDBO_.setMobilePhone(resultZT.getString("mobile"));
//			_CustomUserDBO_.setUserSecondName(resultZT.getString("nickName"));
//			_CustomUserDBO_.setEb4(resultZT.getString("gender"));	//性别	
//			_CustomUserDBO_.setEb2(resultZT.getString("unionId"));  //中台唯一识别ID
//
//			CustomUserDao_.doInsert(_CustomUserDBO_, 1);
//			users.add(_CustomUserDBO_);
//		}
		return null;
	}

	public RESTResultBean<UserBean> autoLogin(HttpServletRequest request, List<String> users, LoginerBean loginer) throws Exception {
		result = new RESTResultBean<UserBean>();
		UserBean curUser = new UserBean();
		
		if (ONE.equals(curUser)) 
		{
			// 检查冻结
			result.setCode(1011);
			result.setMsg("该用户已被冻结，请联系管理员");
			return result;
		} 
		//----------------------------------------------------------------------------------------------------
		if (ONE.equals(loginer.getLoginType())) {// 0手机号1账号
			// 检查密码
			if (loginer.getPassWord().equals(MD5SecurityHelper.encrypt(loginer.getPassWord()))){
				result.setCode(1001);
				result.setMsg("密码错误");
				return result;
			}
		} else if (TWO.equals(loginer.getLoginType())) {
			// 检查验证码
			if(super.checkSecurityCode(request, loginer.getSecurityCode(), true)==false){
				result.setCode(1002);
				result.setMsg("验证码错误");
				return result;
			}
		}
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
