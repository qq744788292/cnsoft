package org.zmsoft.framework.cache.request;

import org.zmsoft.framework.beans.common.HeaderBean;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 当前请求头（LoginerBean）本地ThreadLocal
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class RequestHelper {

	private static final ThreadLocal<HeaderBean> holder = new ThreadLocal<HeaderBean>();

	public static void setAttributes(HeaderBean value) {
		holder.set(value);
	}

	public static HeaderBean getAttributes() {
		HeaderBean header = holder.get();
		if (EmptyHelper.isEmpty(header)) {
			header = new HeaderBean();
		}
		return header;
	}

	public static void addAttribute(String key, String value) {
		HeaderBean header = getAttributes();
		header.addAttribute(key, value);
		holder.set(header);
	}

	public static String getAttribute(String key) {
		HeaderBean header = getAttributes();
		return header.getAttribute(key);
	}

	public static String currentToken() {
		try {
			return getAttributes().getToken();
		} catch (Exception e) {
			return null;
		}
	}

	public static String currentJobId() {
		try {
			return getAttributes().getJobId();
		} catch (Exception e) {
			return null;
		}
	}

	public static String currentVersion() {
		try {
			return getAttributes().getVersion();
		} catch (Exception e) {
			return null;
		}
	}

	public static String currentUserHdp() {
		try {
			return getAttributes().getHdp();
		} catch (Exception e) {
			return null;
		}
	}
}
