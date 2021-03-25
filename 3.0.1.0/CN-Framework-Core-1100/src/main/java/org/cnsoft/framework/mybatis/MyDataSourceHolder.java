package org.cnsoft.framework.mybatis;

/**
 * SQL数据源管理器
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <MyDataSourceManager><MyFrameworkQueryPlugin><ISDataSourceName>
 */
public class MyDataSourceHolder {
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();

	public static void putDataSourceName(String name) {
		holder.set(name);
	}

	public static String getDataSourceName() {
		return holder.get();
	}

}
