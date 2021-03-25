package org.cnsoft.framework.cache.session;

import org.cnsoft.framework.beans.user.UserBean;
import org.cnsoft.framework.token.TokenServiceImpl;

/**
 * 登录用户Session（LoginerBean）本地ThreadLocal
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class SessionHelper {

	private static final ThreadLocal<TokenServiceImpl> holder = new ThreadLocal<TokenServiceImpl>();

	public static void setSessionAttribute(TokenServiceImpl value) {
		holder.set(value);
	}

	/**
	 * 获得当前缓存的信息
	 * 
	 * @see <MyDataBaseObjectSupport2>
	 * @return
	 */
	public static TokenServiceImpl getSessionAttribute() {
		return holder.get();
	}

	public static UserBean currentUser() {
		try {
			return getSessionAttribute().currentUser();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String currentToken() {
		try {
			return getSessionAttribute().currentUser().getToken();
		} catch (Exception e) {
			return null;
		}
	}

	public static String currentUserId() {
		try {
			return getSessionAttribute().currentUser().getUserId();
		} catch (Exception e) {
			return null;
		}
	}

	public static String currentCompanyId() {
		try {
			return getSessionAttribute().currentUser().getCompanyId();
		} catch (Exception e) {
			return null;
		}
	}

	public static String currentUserSid() {
		try {
			return getSessionAttribute().currentUser().getSid();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 用户退出
	 * 
	 * @param event
	 */
	public static void sessionDestroyed() {
		try {
			getSessionAttribute().removeToken();
		} catch (Exception e) {
		}
	}
}
