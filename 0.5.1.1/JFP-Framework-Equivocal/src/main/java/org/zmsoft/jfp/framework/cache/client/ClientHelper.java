package org.zmsoft.jfp.framework.cache.client;

import org.zmsoft.jfp.framework.client.ClientBusinessSupport;

/**
 * 登录用户Session（LoginerBean）本地ThreadLocal
 * 
 * @author Spook
 * @version 4.2.1 2017/03/15
 * @since 4.2.1 2017/03/15
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
