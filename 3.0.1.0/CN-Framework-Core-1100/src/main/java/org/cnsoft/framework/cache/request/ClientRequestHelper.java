package org.cnsoft.framework.cache.request;

import org.cnsoft.framework.cache.client.ClientBean;

/**
 * 当前请求头（LoginerBean）本地ThreadLocal
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class ClientRequestHelper {
	
	private static final ThreadLocal<ClientBean> holder = new ThreadLocal<ClientBean>();

	public static void setClientBusinessSupport(ClientBean clent) {
		holder.set(clent);
	}

	/**
	 * 获得当前缓存的信息
	 * 
	 * @see <MyDataBaseObjectSupport2>
	 * @return
	 */
	public static ClientBean getClientBusinessSupport() {
		return holder.get();
	}

}
