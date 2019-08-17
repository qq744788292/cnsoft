package org.zmsoft.framework.cache.client;

import org.zmsoft.framework.client.ClientBusinessSupport;

/**
 * 登录用户Session（LoginerBean）本地ThreadLocal
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class ClientHelper {
	private static final ThreadLocal<ClientBusinessSupport> holder = new ThreadLocal<ClientBusinessSupport>();

	public static void setClientBusinessSupport(ClientBusinessSupport clent) {
		holder.set(clent);
	}

	/**
	 * 获得当前缓存的信息
	 * 
	 * @see <MyDataBaseObjectSupport2>
	 * @return
	 */
	public static ClientBusinessSupport getClientBusinessSupport() {
		return holder.get();
	}

}
