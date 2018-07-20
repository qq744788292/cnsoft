package org.zmsoft.jfp.framework.cache.session;

import org.zmsoft.jfp.framework.beans.user.UserBean;
import org.zmsoft.jfp.framework.token.TokenBusinessSupport;

/**
 * 登录用户Session（LoginerBean）本地ThreadLocal
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class SessionHelper {
	private static final ThreadLocal<TokenBusinessSupport> holder = new ThreadLocal<TokenBusinessSupport>();

	public static void setSessionAttribute(TokenBusinessSupport value) {
		holder.set(value);
	}

	/**
	 * 获得当前缓存的信息
	 * 
	 * @see <MyDataBaseObjectSupport2>
	 * @return
	 */
	public static TokenBusinessSupport getSessionAttribute() {
		return holder.get();
	}

	public static String currentHdp() {
		try {
			return getSessionAttribute().currentUser().getHdp();
		} catch (Exception e) {
			return null;
		}
	}

	public static String currentToken() {
		try {
			return getSessionAttribute().getToken();
		} catch (Exception e) {
			return null;
		}
	}

	public static UserBean currentUser() {
		try {
			return getSessionAttribute().currentUser();
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
