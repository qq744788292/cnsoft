package org.zmsoft.framework.cache.request;

import org.zmsoft.framework.beans.common.HeaderBean;

/**
 * 当前请求头（LoginerBean）本地ThreadLocal
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class RequestHelper {

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	private static final ThreadLocal<HeaderBean> holder = new ThreadLocal<HeaderBean>();

	public static void setSessionAttribute(HeaderBean value) {
		holder.set(value);
	}

	/**
	 * 获得当前缓存的信息
	 * 
	 * @see <MyDataBaseObjectSupport2>
	 * @return
	 */
	public static HeaderBean getSessionAttribute() {
		return holder.get();
	}

	public static String currentToken() {
		try {
			return getSessionAttribute().getToken();
		} catch (Exception e) {
			return null;
		}
	}

	public static String currentJobId() {
		try {
			return getSessionAttribute().getJobId();
		} catch (Exception e) {
			return null;
		}
	}

	public static String currentVersion() {
		try {
			return getSessionAttribute().getVersion();
		} catch (Exception e) {
			return null;
		}
	}

	public static String currentUserHdp() {
		try {
			return getSessionAttribute().getHdp();
		} catch (Exception e) {
			return null;
		}
	}
}
