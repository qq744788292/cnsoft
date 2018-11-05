package org.zmsoft.framework.cache.session;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.token.TokenBusinessSupport;

/**
 * 登录用户Session（LoginerBean）本地ThreadLocal
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
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
