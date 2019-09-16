package org.jfpc.framework.support;

import org.jfpc.constants.ISFrameworkConstants;
import org.jfpc.constants.ISModelConstants;
import org.jfpc.framework.bean.LoginerBean;
import org.jfpc.framework.helper.TokenHelper;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 业务框架超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.1 2014/8/27 增加自定义提示信息页面{getMessageMAV}
 * @version 0.1.0 2014/2/8
 */
public class MyFrameworkSupport implements ISSessionSupport, ISFrameworkConstants, ISModelConstants {

	// //////////////////////////////////////////////////////////////
	public void setSessionAttribute(String key, Object value) {
		RequestContextHolder.getRequestAttributes().setAttribute(key, value, RequestAttributes.SCOPE_SESSION);
		// CatchUtils.setAttribute(key, value);
	}

	protected Object getSessionAttribute(String key) {
		return RequestContextHolder.getRequestAttributes().getAttribute(key, RequestAttributes.SCOPE_SESSION);
		// 基于缓存服务器
		// return CatchUtils.getAttribute(key);
	}

	protected void removeSessionAttribute(String key) {
		RequestContextHolder.getRequestAttributes().removeAttribute(key, RequestAttributes.SCOPE_SESSION);
		// CatchUtils.removeAttribute(key);
	}

	// //////////////////////////////////////////////////////////////

	/**
	 * 校验图片验证码
	 * 
	 * @param verCode
	 * @return
	 */
	public boolean checkRandomCode(String verCode) {
		return verCode.toUpperCase().equals((String) getSessionAttribute(RANDOM_CODE));
	}

	/**
	 * 校验短信验证码
	 * 
	 * @param phone
	 * @param bizId
	 * @param code
	 * @return 核对手机验证码0正确1失败2过期
	 */
	// TODO
	public int validateSMSCode(String phone, String bizId, String code) {
		if(getSessionAttribute(phone+bizId)==null){
			return 2;
		}
		if(!code.equals(getSessionAttribute(phone+bizId))){
			return 1;
		}
		return 0;
	}

	// ////////////////处理线程安全/////////////////////////
	/**
	 * Session ID获得
	 * 
	 * @return
	 */
	public String getSessionid() {
		return (String) getSessionAttribute(CONSTANT_SESSION_ID);
	}

	/**
	 * Session ID持久化
	 * 
	 * @param sessionid
	 */
	public void setSessionid(String sessionid) {
		setSessionAttribute(CONSTANT_SESSION_ID, sessionid);
	}

	/**
	 * 获得登录者信息
	 * 
	 * @return
	 */
	public LoginerBean getLoginerBean() {
		LoginerBean loginerBean = (LoginerBean) getSessionAttribute(CONSTANT_LOGINER);
		if (loginerBean == null)
			loginerBean = new LoginerBean();
		return loginerBean;
	}

	/**
	 * 缓存登录者信息
	 */
	public void setLoginerBean(LoginerBean loginer) {
		// Session保存
		setSessionAttribute(CONSTANT_LOGINER, loginer);
	}

	public String getLoginerId() {
		return TokenHelper.getUserID(getToken());
	}

	public String getCompanyId() {
		return TokenHelper.getCompanyId(getToken());
	}

	public String getToken() {
		return getLoginerBean().getToken();
	}

	public void setToken(String token) {
		// Session保存
		setSessionAttribute(CONSTANT_USER_TOKEN, token);
	}
}
