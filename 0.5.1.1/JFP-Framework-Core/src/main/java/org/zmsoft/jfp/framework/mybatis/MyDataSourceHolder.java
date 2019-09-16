package org.zmsoft.jfp.framework.mybatis;
/**
 * SQL数据源管理器
 * 
 * @author zmsoft
 * 
 * @since 0.1 2012-7-13
 * @version 0.1
 * @version 2.1.2.20150422
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
