package org.cnsoft.framework.mybatis.plugin;

import java.sql.Connection;

import org.springframework.stereotype.Component;

@Component
public class MyConnectionHolder {
	
	private static final ThreadLocal<Connection> holder = new ThreadLocal<Connection>();

	public static void setConnection(Connection value) {
		holder.set(value);
	}

	/**
	 * 获得当前缓存的信息
	 * 
	 * @see <MyDataBaseObjectSupport2>
	 * @return
	 */
	public static Connection getConnection() {
		return holder.get();
	}

}
